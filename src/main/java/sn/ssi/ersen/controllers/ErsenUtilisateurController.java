package sn.ssi.ersen.controllers;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.*;
import sn.ssi.ersen.dto.utilisateurStat.UtilisateurDto;
import sn.ssi.ersen.entity.*;
import sn.ssi.ersen.entity.entitieMobile.*;
import sn.ssi.ersen.entity.entitieMobile.SectionMaintenance;
import sn.ssi.ersen.mappers.RequeteMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin("*")
public class ErsenUtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final ErsenNotificationRepository ersenNotificationRepository;
    private final ErsenCentraleRepository ersenCentraleRepository;
    private final ErsenTacheCentraleRepository ersenTacheCentraleRepository;
    private final ErsenParametresGenerauxRepository ersenParametresGenerauxRepository;
    private final ErsenTutorielRepository ersenTutorielRepository;
    private final ErsenPreuveTacheRepository preuveTacheRepository;
    private final ErsenFichierRepository ersenFichierRepository;
    private final ErsenAbonneRepository ersenAbonneRepository;
    private final ErsenPlanMaintenanceRepository ersenPlanMaintenanceRepository;
    private final ErsenProjetRepository ersenProjetRepository;
    private final ErsenConductExecutionNbRepository ersenConductExecutionNbRepository;
    private final ErsenSectM ersenSectM;
    private final RequeteMapper requeteMapper;
    public String DIRECTORY;

    public ErsenUtilisateurController(UtilisateurRepository utilisateurRepository, ErsenNotificationRepository ersenNotificationRepository, ErsenTacheCentraleRepository ersenTacheCentraleRepository, ErsenParametresGenerauxRepository ersenParametresGenerauxRepository, ErsenCentraleRepository ersenCentraleRepository, ErsenTutorielRepository ersenTutorielRepository, ErsenPreuveTacheRepository preuveTacheRepository, ErsenFichierRepository ersenFichierRepository, ErsenAbonneRepository ersenAbonneRepository, ErsenPlanMaintenanceRepository ersenPlanMaintenanceRepository, ErsenProjetRepository ersenProjetRepository, ErsenConductExecutionNbRepository ersenConductExecutionNbRepository, ErsenSectM ersenSectM, RequeteMapper requeteMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.ersenNotificationRepository = ersenNotificationRepository;
        this.ersenCentraleRepository = ersenCentraleRepository;
        this.ersenTacheCentraleRepository = ersenTacheCentraleRepository;
        this.ersenParametresGenerauxRepository = ersenParametresGenerauxRepository;
        this.ersenTutorielRepository = ersenTutorielRepository;
        this.preuveTacheRepository = preuveTacheRepository;
        this.ersenFichierRepository = ersenFichierRepository;
        this.ersenAbonneRepository = ersenAbonneRepository;
        this.ersenPlanMaintenanceRepository = ersenPlanMaintenanceRepository;
        this.ersenProjetRepository = ersenProjetRepository;
        this.ersenConductExecutionNbRepository = ersenConductExecutionNbRepository;
        this.ersenSectM = ersenSectM;
        this.requeteMapper = requeteMapper;
        getDir();
        System.out.println("######################"+DIRECTORY+"##################");
    }

    void getDir(){
        this.DIRECTORY=this.ersenParametresGenerauxRepository.getDirectory();
    }


    @GetMapping(value={"/utilisateurs","/usersbyentr/{entreprise}"})
    public List<UtilisateurEntity> getUtilisateur(@PathVariable(required = false) String entreprise){
        if(entreprise==null)
            return utilisateurRepository.findAll();
        else
            return utilisateurRepository.getUsersByEntreprise(entreprise);
    }

    @GetMapping(value = "/all")
    public List<UtilisateurDto> getAll(){
        return  requeteMapper.utilisateurEntityListToUtilisateurDTO(utilisateurRepository.findAll());
    }

    @GetMapping(value = "/utilisateurs/{id}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public UtilisateurEntity getUtilisateurById(@PathVariable Integer id){
        UtilisateurEntity user = utilisateurRepository.findById(id).get();
        user.setPp(convertFileToBase64(user.getPp()));
        return  user;
    }

    @PostMapping (value = "/fichier/add",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public Fichier saveFichier(@RequestBody Fichier fichier){
        return ersenFichierRepository.save(fichier);
    }

    @PostMapping (value = "/fichier/addlist",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public List<Fichier> saveFichiers(@RequestBody List<Fichier> fichiers){
        return ersenFichierRepository.saveAll(fichiers);
    }

    @GetMapping(value = "/fileweb/{idEntity}")
    List<Fichier> getFile(@PathVariable String idEntity) {
        return ersenFichierRepository.getFilesOfAnEntity(idEntity);
    }

    @GetMapping(value = "/file/{idEntity}")
    List<Fichier> getEntitiesFilesInBase64Format(@PathVariable String idEntity) {
        List<Fichier> fichiers = ersenFichierRepository.getFilesOfAnEntity(idEntity);
        if(!fichiers.isEmpty()){
            for(Fichier fichier: fichiers){
                if(!fichier.getContenttype().equals("text"))
                    fichier.setContent(convertFileToBase64(fichier.getContent()));
            }
        }
        return fichiers;
    }

    ////////////////////////get connected user's informations///////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping(value = "/user/connexion/{tel}/{mdp}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public Object getUser(@PathVariable String tel, @PathVariable String mdp) {
        try
        {
            UtilisateurEntity user = utilisateurRepository.getByTel(tel);//declanche l'exception:NoSuchElementException au cas ou il n'existe pas d'utilisateur avec ce numero
            //la personne n'exite pas dans la base
            if (user.getUsrPassword().equals(mdp))
                return new Utilisateur(user.getUsrId(),user.getTel(),user.getUsrNom(),user.getUsrPrenom(),user.getUsrPassword(),user.getTypeutilisateur(), user.getEntreprise(),convertFileToBase64(user.getPp()));
            else
                return 1;
        }
        catch (Exception e) {return 1;}
    }


    /**
     * le entrepriseOperateur doit etre null s'il s'agit d'une connexion avec le profil conducteur
     * mais ne doit pas etre null s'il s'agit d'une connexion operateur
    * */
    @GetMapping(value = {
            "/taches_and_centrals/conducteur/{idUser}",
            "/taches_and_centrals/operateur/{entreprise}"},produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public TasksAndCentrals getUsersTacheAndCentrale(@PathVariable(required = false) Integer idUser,@PathVariable(required = false)  String entreprise) {

        List<Centrale> listeCentraleForMobile = entreprise==null?
                ersenCentraleRepository.getCentraleForConducteur(idUser):
                ersenCentraleRepository.getCentraleForOperateur(entreprise);

        List<Tache> listeTaches = new ArrayList<>();
        List<Tache> listeTachesDep = new ArrayList<>();
        for (Centrale c : listeCentraleForMobile) {
            c.setNomEntreprise(ersenCentraleRepository.getEntrepriseNameByCentrale(c.getId()));
            c.setNbAbonnees(ersenAbonneRepository.getCountErsenAbonneEntitiesByCentrale(c.getId()));
            c.setUrlimage(convertFileToBase64(c.getUrlimage()));
            if(c.getIdProjet()!=null)
                c.setProjetName(ersenProjetRepository.getProjectName(c.getIdProjet()));

            if(c.getIdPlanM()!=null) {
                c.setNomPlanM(ersenPlanMaintenanceRepository.getPlanmName(c.getIdPlanM()));

                listeTaches = ersenTacheCentraleRepository.getTasksListByCentrale(c.getId(), c.getIdPlanM());
                for (Tache tache : listeTaches)
                    tache.setImageAsBase64(convertFileToBase64(tache.getImageAsBase64()));

                listeTachesDep = getTacheDepassees(c.getId(), c.getIdPlanM());
                for(Tache tacheDep : listeTachesDep)
                    tacheDep.setImageAsBase64(convertFileToBase64(tacheDep.getImageAsBase64()));
            }

        }
        return new TasksAndCentrals(listeCentraleForMobile, listeTaches, listeTachesDep);
    }

    @GetMapping(value = "/getCentrales/{idUser}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public List<Centrale> getCentrales(@PathVariable Integer idUser) {
        List<Centrale> listeCentraleForMobile = ersenCentraleRepository.getCentraleForConducteur(idUser);
        for(Centrale c : listeCentraleForMobile) {
            c.setNomEntreprise(ersenCentraleRepository.getEntrepriseNameByCentrale(c.getId()));
            c.setNbAbonnees(ersenAbonneRepository.getCountErsenAbonneEntitiesByCentrale(c.getId()));
            c.setUrlimage(convertFileToBase64(c.getUrlimage()));
        }
        return listeCentraleForMobile;
    }

    @GetMapping(value = "/getTacheDep/{centraleID}/{planM}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    List<Tache> getTacheDepassees(@PathVariable String centraleID,@PathVariable String planM) {
        return ersenTacheCentraleRepository.getBackUpTasksListByCentrale(centraleID,planM);
    }

    @GetMapping("/user/hasnotif/{user}")
    public  Boolean updatehasNotifFalse(@PathVariable Integer user){
        utilisateurRepository.updateHasNotifFalse(user);
        return true;
    }

    @GetMapping(value = {"/getTacheDepWeb","/getTacheDepWeb/{entr}"},produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    List<Tache> getTacheDepasseesWeb(@PathVariable (required = false) String entr) {
        if(entr==null)
            return ersenTacheCentraleRepository.getBackUpTasksListWeb();
        else
            return ersenTacheCentraleRepository.getBackUpTasksListWebByEntr(entr);
    }

    @GetMapping(value = "/preuvesof/{idTacheCentrales}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public List<Preuve> getTasksPreuves(@PathVariable List<String> idTacheCentrales) {
        //Liste des preuves tache
        List<Preuve> preuveListOfTasks = new ArrayList<>();
        List<ErsenPreuveTacheEntity> preuves_FromBackEnd = preuveTacheRepository.getPreuveByTacheCentre(idTacheCentrales);
        if (!preuves_FromBackEnd.isEmpty()) {
            for (ErsenPreuveTacheEntity preuveFromBackEnd : preuves_FromBackEnd)
                preuveListOfTasks.add(new Preuve(preuveFromBackEnd.getId(), preuveFromBackEnd.getTachecentre(), preuveFromBackEnd.getDatesave(), preuveFromBackEnd.getDateupdate(),preuveFromBackEnd.getIsSync(),getEntitiesFilesInBase64Format(preuveFromBackEnd.getId())));
        }
        return preuveListOfTasks;
    }

    @GetMapping(value = {
            "/notifsof/{entrepise}", // for getting all notifs sent to a sepecific entreprise
            "/notifsofConduct/{idExpediteur}" // conducteur notifs when reinstall
    },produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public List<Notification> getNotifs(@PathVariable(required = false) String entrepise, @PathVariable(required = false) Integer idExpediteur) {
        List<ErsenNotificationEntity> notifsFromBackEnd;
        if(entrepise != null)
            notifsFromBackEnd = ersenNotificationRepository.getOperateurNotifs(entrepise);
        else
            notifsFromBackEnd = ersenNotificationRepository.getConductNotif(idExpediteur);
        return getNotificationsInMobileFormat(notifsFromBackEnd);
    }

    @GetMapping(value = "/newNotifsForEntreprise/{entrepise}/{oldNotifsIDs}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public List<Notification> getNewEntrepriseNotifs(@PathVariable String entrepise, @PathVariable List<String> oldNotifsIDs) {
        return getNotificationsInMobileFormat(ersenNotificationRepository.getNewOperateurNotifs(entrepise,oldNotifsIDs));
    }

    private List<Notification> getNotificationsInMobileFormat(List<ErsenNotificationEntity> notifsFromBackEnd) {
        List<Notification> notifListOfTasks = new ArrayList<>();
        if(!notifsFromBackEnd.isEmpty()) {
            for (ErsenNotificationEntity notifFromBackEnd : notifsFromBackEnd)
                notifListOfTasks.add(new Notification(notifFromBackEnd.getId(), notifFromBackEnd.getImpact(), notifFromBackEnd.getLibelle(), notifFromBackEnd.getObjet(), notifFromBackEnd.getIdTacheOrSectM(), notifFromBackEnd.getDateSave(), notifFromBackEnd.getIsRelative(),notifFromBackEnd.getIsSync(),notifFromBackEnd.getCentralid(),getEntitiesFilesInBase64Format(notifFromBackEnd.getId())));
        }
        return notifListOfTasks;
    }

    @GetMapping(value = "/tutosof/{idTacheCentrales}",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<Tutoriel> getTasksTutos(@PathVariable List<String> idTacheCentrales) {
        List<Tutoriel> tutoListOfTasks = new ArrayList<>();
        List<ErsenTutorielEntity> tutosFromBackEnd = ersenTutorielRepository.getTutoByTache(idTacheCentrales);
        if (!tutosFromBackEnd.isEmpty()) {
            for (ErsenTutorielEntity tutoFromBackEnd : tutosFromBackEnd)
                tutoListOfTasks.add(new Tutoriel(tutoFromBackEnd.getId(), tutoFromBackEnd.getDatesave(), tutoFromBackEnd.getNom(), getEntitiesFilesInBase64Format(tutoFromBackEnd.getId())));
        }
        return tutoListOfTasks;
    }

    @GetMapping(value = "/get_centrales_section_ms/{idCentrals}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<SectionMaintenance> getConfigCentraleSectionMs(@PathVariable List<String> idCentrals){
        List<SectionMaintenance> sectionMs= ersenSectM.getSectMs(idCentrals);
        if(!sectionMs.isEmpty()) {
            for (SectionMaintenance sM : sectionMs)
                sM.setUrlimage(convertFileToBase64(sM.getUrlimage()));
        }
        return sectionMs;
    }

    @GetMapping(value = "/repliquerNewSection/{idCentrals}/{oldCentralsSectionsIDS}", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public NewSectAndDeletedOnes getNewCentralsSectionMs(@PathVariable List<String> idCentrals,@PathVariable List<String> oldCentralsSectionsIDS){
        List<SectionMaintenance> sectionNewSectionM=ersenSectM.getCentalNewSectM(idCentrals,oldCentralsSectionsIDS);
        if(!sectionNewSectionM.isEmpty()) {
            for (SectionMaintenance sM : sectionNewSectionM)
                sM.setUrlimage(convertFileToBase64(sM.getUrlimage()));
        }
        oldCentralsSectionsIDS.removeAll(ersenSectM.getNotDeletedSectionM(idCentrals, oldCentralsSectionsIDS));
        return new NewSectAndDeletedOnes(sectionNewSectionM, oldCentralsSectionsIDS);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String convertFileToBase64(String fileName) {
        if (fileName!=null || !fileName.isEmpty()) {
           try {
                File file = new File(this.DIRECTORY + fileName);
                if (file.exists())
                    return Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file));
                else
                    return "";
              } catch (IOException e) {
                return "";
              }
        }
        else
            return "";
    }

    @PostMapping("/utilisateur/add")
    public  Integer addUtilisateurOperateur(@RequestBody UtilisateurEntity utilisateurEntity){
        if(utilisateurRepository.getNbUserByEmail(utilisateurEntity.getUsrId(),utilisateurEntity.getUsrMail())>0){
            return 0;
        }
        else if(utilisateurRepository.getNbUserByTel(utilisateurEntity.getUsrId(), utilisateurEntity.getTel())>0){
            return -1;
        }
        UtilisateurEntity user = utilisateurRepository.save(utilisateurEntity);
        if(user.getTypeutilisateur().equals("402881877869fac6017869fbd4a10000"))
            ersenConductExecutionNbRepository.save(new ErsenConductExecutionNb(user.getUsrId()));
         return 1;
    }

    @PutMapping("/utilisateur/edit/{id}")
    public Integer updateUtilisateurOperateur(@PathVariable Integer id, @RequestBody UtilisateurEntity utilisateurEntity){
        if(utilisateurRepository.getNbUserByEmail(id,utilisateurEntity.getUsrMail())>0){
            return 0;
        }else if(utilisateurRepository.getNbUserByTel(id, utilisateurEntity.getTel())>0){
            return -1;
        }
       utilisateurEntity.setUsrId(id);
       utilisateurEntity.setPp(upload(utilisateurEntity.getPp(), utilisateurEntity.getUsrId().toString(), utilisateurEntity.getUsrPrenom()+"_"+utilisateurEntity.getUsrNom()));
       utilisateurRepository.save(utilisateurEntity);
       return 1;
    }

    String upload(String urlimage,String id, String name){
        String filename="";
        //On verifie si le type de contenu est different de text
        if (!urlimage.equals("")) {
            try {
                byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(urlimage);
                //On crée le nom de l'image
                filename = name + "_" +id+".png";
                //directory = chemin de l'image
                String directory = this.DIRECTORY + filename;
                new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
            } catch (IOException e) {
                e.printStackTrace();
                return  "";
            }
        }
        return filename;
    }

    @DeleteMapping("/utilisateur/delete/{id}")
    public List<UtilisateurEntity> deleteUtilisateurOperateur(@PathVariable("id") Integer id){
        utilisateurRepository.deleteById(id);
        return getUtilisateur("");
    }

    @GetMapping(value="/operateur")
    public List<UtilisateurEntity> getUtilisateurOperateur(){
        return utilisateurRepository.getALLUtilisateuroperateur();
    }

    @GetMapping(value="/conducteur/")
    public List<UtilisateurEntity> getUtilisateurConducteur(){
        return utilisateurRepository.getUtilisateurConducteur();
    }

    @GetMapping(value="/conducteur/all/{entreprise}")
    public List<UtilisateurEntity> getAllUtilisateurConducteur(@PathVariable String entreprise){
        return utilisateurRepository.getALLUtilisateurConducteur(entreprise);
    }
}
