package sn.ssi.ersen.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.*;
import sn.ssi.ersen.dto.CentraleDto;
import sn.ssi.ersen.dto.ProjectionCentraleAbonne;
import sn.ssi.ersen.entity.*;
import sn.ssi.ersen.entity.entitieMobile.Centrale;
import sn.ssi.ersen.entity.forWeb.CentraleAbonne;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static sn.ssi.ersen.controllers.ErsenTacheController.getString;

@RestController
@CrossOrigin("*")
public class ErsenCentraleController {
    private final ErsenCentraleRepository ersenCentraleRepository;
    private final ErsenAbonneRepository ersenAbonneRepository;
    private final ErsenPerfCalculRepository ersenPerfCalculRepository;
    public String DIRECTORY;
    private final ErsenParametresGenerauxRepository ersenParametresGenerauxRepository;
    private final ErsenConfigCentraleTacheRepository ersenConfigCentraleTacheRepository;
    private final ErsenTacheCentraleRepository ersenTacheCentraleRepository;
    private final ErsenTacheCentraleController ersenTacheCentraleController;
    private final ErsenEtatCentraleRepository ersenEtatCentraleRepository;
    private final ErsenUtilisateurCentralRepository utilisateurCentralRepository;
    private final AllEquipementRepository allEquipementRepository;
    private final EquipementCentraleRepository equipementCentraleRepository;
    private final ErsenPlanMaintenanceRepository planMaintenanceRepository;
    private final ErsenResidusPerfRepository residusPerfRepository;

    private final RequeteMapper requeteMapper;

    public ErsenCentraleController(ErsenCentraleRepository ersenCentraleRepository, ErsenAbonneRepository ersenAbonneRepository, ErsenPerfCalculRepository ersenPerfCalculRepository, ErsenParametresGenerauxRepository ersenParametresGenerauxRepository, ErsenConfigCentraleTacheRepository ersenConfigCentraleTacheRepository, ErsenTacheCentraleRepository ersenTacheCentraleRepository, ErsenTacheCentraleController ersenTacheCentraleController, ErsenEtatCentraleRepository ersenEtatCentraleRepository, ErsenUtilisateurCentralRepository utilisateurCentralRepository, AllEquipementRepository allEquipementRepository, EquipementCentraleRepository equipementCentraleRepository, ErsenPlanMaintenanceRepository planMaintenanceRepository, ErsenResidusPerfRepository residusPerfRepository, RequeteMapper requeteMapper) {
        this.ersenCentraleRepository = ersenCentraleRepository;
        this.ersenAbonneRepository = ersenAbonneRepository;
        this.ersenPerfCalculRepository = ersenPerfCalculRepository;
        this.ersenParametresGenerauxRepository = ersenParametresGenerauxRepository;
        this.ersenConfigCentraleTacheRepository = ersenConfigCentraleTacheRepository;
        this.ersenTacheCentraleRepository = ersenTacheCentraleRepository;
        this.ersenTacheCentraleController = ersenTacheCentraleController;
        this.ersenEtatCentraleRepository = ersenEtatCentraleRepository;
        this.utilisateurCentralRepository = utilisateurCentralRepository;
        this.allEquipementRepository = allEquipementRepository;
        this.equipementCentraleRepository = equipementCentraleRepository;
        this.planMaintenanceRepository = planMaintenanceRepository;
        this.residusPerfRepository = residusPerfRepository;
        this.requeteMapper = requeteMapper;
        getDir();
    }
    void getDir(){
        this.DIRECTORY=ersenParametresGenerauxRepository.getDirectory();
    }

   @GetMapping(value = {
            "/centrale/all",
            "/centrale/all/{utilisateur}"
    })
    public List<ErsenCentraleEntity> getCentrale(@PathVariable(required = false) String utilisateur){
        if (utilisateur==null) {
            return ersenCentraleRepository.findAll();
        }
        else
            return ersenCentraleRepository.getErsenCentraleAllEntitiesByUtilisateur(utilisateur);

    }
    ///getEtatCentrales/

    @GetMapping(value = {"/getEtatCentrales/{idCentrales}" })
    public List<Integer> getEtatCentrale(@PathVariable List<String> idCentrales){
        List<Integer> etats= new ArrayList<>();
        for (String id:idCentrales ) {
            etats.add(ersenCentraleRepository.getEtatCentrale(id));
        }
        return etats;

    }
    @GetMapping(value ={
            "/centraleurl/all",
            "/centraleurl/all/{utilisateur}"
    })
    public List<ErsenCentraleEntity> getcentraleurl(@PathVariable(required = false) String utilisateur){
        List<ErsenCentraleEntity> ersenCentraleEntities;
        if(utilisateur==null)
            ersenCentraleEntities=ersenCentraleRepository.findAll();
        else
            ersenCentraleEntities=ersenCentraleRepository.getErsenCentraleAllEntitiesByUtilisateur(utilisateur);

        for (ErsenCentraleEntity ersenCentraleEntity : ersenCentraleEntities)
            ersenCentraleEntity.setUrlimage(convertStringToBase64(ersenCentraleEntity.getUrlimage()));

        return ersenCentraleEntities;
    }

    @GetMapping(value = "/centrale/dto")
    public List<CentraleDto> getCentralesDTO(){
        return requeteMapper.centraleEntityToCentraleDtoList(ersenCentraleRepository.getAllCentraleDTOAdresse());
    }

    @GetMapping(value = "/centrale/projet/dto")
    public List<CentraleDto> getCentraleProjet(){
        return requeteMapper.centraleEntityToCentraleDtoList(ersenCentraleRepository.findAll());
    }

    @GetMapping(value = "/centrale/{id}")
    public ErsenCentraleEntity getCentraleById(@PathVariable String id){
        return ersenCentraleRepository.findById(id).get();
    }

    //update MD
    @GetMapping(value = "/updateplan/{newPlanMaintenance}/{idCentral}")
    public void updateplan(@PathVariable String newPlanMaintenance, @PathVariable String idCentral) throws ParseException {
        List<ErsenPerfCalcul> perfCalculsByCentral = ersenPerfCalculRepository.getPerfCalculsByCentral(idCentral);
        residusPerfRepository.updateErsenPerfCalcul(calculSumNbPerfDenominateurFromList(perfCalculsByCentral,planMaintenanceRepository.getPlanLastChangeDateByCentrale(idCentral)),idCentral);
        ersenPerfCalculRepository.deleteAll(perfCalculsByCentral);
        List<ErsenTacheCentraleEntity> tachesCentrales = ersenTacheCentraleRepository.getTacheCentralePlanifieesByPlanAndCentrale(newPlanMaintenance, idCentral);
        Date datePlanif = new Date();
        for(ErsenTacheCentraleEntity tc:tachesCentrales){
            ersenTacheCentraleController.updateconfig(tc.getConfigcentraletache(),ersenConfigCentraleTacheRepository.findById(tc.getConfigcentraletache()).get().getPeriodicite(), tc.getTutoriel());
            tc.setDatePlanif(datePlanif);
            ersenTacheCentraleRepository.save(tc);
        }

        if(newPlanMaintenance.equalsIgnoreCase("neant"))
             ersenCentraleRepository.updatePlan(null, idCentral);
        else
            ersenCentraleRepository.updatePlan(newPlanMaintenance, idCentral);
    }

    /*Renvoi le nombre de fois que la tâche doit être exécuter
    Durant une intervalle de temps*/
    private int getCurrentPerfDenominateur(String idtc, Date planLastChangeDate){
        Integer periodicite = ersenCentraleRepository.getValPeriodiciteByTC(idtc);
        Long res = (((new Date().getTime() - planLastChangeDate.getTime()) / (1000 * 60 * 60 * 24)) / periodicite);
        return res.intValue();
    }
    /*Somme des dénominaeur des tâches tâches*/
    private Integer calculSumNbPerfDenominateurFromList(List<ErsenPerfCalcul> perfCalculs, Date planLastChangeDate){
        int sum=0,denom;
        if(planLastChangeDate!=null) {
            for (int i=0;i<perfCalculs.size();i++){
                denom = getCurrentPerfDenominateur(perfCalculs.get(i).getIdTacheC(), planLastChangeDate);
                if (denom<perfCalculs.get(i).getNbExec())
                    denom = perfCalculs.get(i).getNbExec();
                sum+= denom;
            }
        }
        return  sum;
    }

    private List<ErsenPerfCalcul> getPerfCalculByPlanAndCentrale(String plan, String centrale){
        List<ErsenPerfCalcul> perfsCalcul = ersenCentraleRepository.getPerfCalculByPlanAndCentrale(plan, centrale);
        return perfsCalcul;
    }

    private Integer getSumNbExecOfPerfsCalcul(List<ErsenPerfCalcul> perfCalculs){
        Integer somme=0;
        for (ErsenPerfCalcul pc : perfCalculs){
            somme=somme+pc.getNbExec();
        }
        return somme;
    }

    @PersistenceContext
    private EntityManager entityManager;
    @PostMapping(value = "/centrale/add")
    public  List<ErsenCentraleEntity> addCentrale(@RequestBody ErsenCentraleEntity ersenCentraleEntity){
        ersenCentraleEntity.setUrlimage(upload(ersenCentraleEntity.getUrlimage()));
        String centraleID = ersenCentraleRepository.save(ersenCentraleEntity).getId();
        utilisateurCentralRepository.save(new ErsenUtilisateurCentraleEntity(null, centraleID, null));
        residusPerfRepository.save(new ErsenResidusPerf(centraleID));
        //On initialise les equipements de cette centrale
        List<AllEquipementEntity> typeEquipements = allEquipementRepository.findAll();
        for (AllEquipementEntity typeEquipement : typeEquipements) {
            String sql = "select id, libelle, fabricant from " + typeEquipement.getTablename();
            List equipements = entityManager.createQuery(sql).getResultList();
            for (Object o : equipements)
                equipementCentraleRepository.save(new EquipementCentrale(null, centraleID, ((Object[]) o)[0].toString(), typeEquipement.getTablename(), "", 0));
        }
        return getCentrale("null");
    }

    @PutMapping("/centrale/edit/{id}")
    public List<ErsenCentraleEntity> updateCentrale(@PathVariable String id,@RequestBody ErsenCentraleEntity ersenCentraleEntity){
        ersenCentraleEntity.setId(id);
        if(ersenCentraleEntity.getUrlimage().length()>400)
            ersenCentraleEntity.setUrlimage(upload(ersenCentraleEntity.getUrlimage()));
        ersenCentraleRepository.save(ersenCentraleEntity);
        return getCentrale(null);
    }



    @GetMapping(value = "/cetralemobile/{id}")
    public List<Centrale> jjj(@PathVariable Integer id ){
        return ersenCentraleRepository.getCentraleForConducteur(id);
    }

    //################################# FOR WEB  ## #############################################"

    @GetMapping(value = {
            "/centraleabonne/all",
            "/centraleabonne/all/{utilisateur}"
    })
    public List<CentraleAbonne> getCentraleAbonne(@PathVariable(required = false) String utilisateur){
            return getCentraleAbonnes(ersenCentraleRepository, ersenAbonneRepository,utilisateur);
    }

    static List<CentraleAbonne> getCentraleAbonnes(ErsenCentraleRepository ersenCentraleRepository, ErsenAbonneRepository ersenAbonneRepository, String utilisateur) {
        List<CentraleAbonne> centraleAbonneList = new ArrayList<>();
        List<ErsenCentraleEntity> centrales;
        if(utilisateur!=null)
            centrales =  ersenCentraleRepository.getErsenCentraleEntitiesByUtilisateur(utilisateur);
        else
            centrales= ersenCentraleRepository.findAll();

        for(ErsenCentraleEntity centrale : centrales){
            centraleAbonneList.add(
                    new CentraleAbonne(
                    centrale.getId(), centrale.getAdresse(), centrale.getCoutinstallation(),
                    centrale.getDatedemarrage(), centrale.getDatesave(), centrale.getDateupdate(),
                    centrale.getLatitude(), centrale.getLongitude(), centrale.getNom(), centrale.getPuissance(),
                    centrale.getOperateur(), centrale.getTypecentrale(), centrale.getVillage(), centrale.getUrlimage(),
                    centrale.getDescription(), centrale.getProjet(),
                    ersenAbonneRepository.getAbonnesByCentrale(centrale.getId())
            ));
        }
        return centraleAbonneList;
    }

    @GetMapping(value =
            "/centraletache/all/nonexecute/{valide}/{operateur}/{idcentrale}"
            ,produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    List<CentraleTache> getTacheNonExecuteees(@PathVariable int valide, @PathVariable(required = false) int operateur, @PathVariable String idcentrale) throws ParseException {
        List<CentraleTache> centraleTaches = new ArrayList<>();
        List<ErsenCentraleEntity> centrales;
        if(idcentrale.equals("neant")){
            if (operateur==0)
                centrales = ersenCentraleRepository.getCentraleHavingTaskByValide(valide);
            else
                centrales = ersenCentraleRepository.getCentraleHavingTaskByValideAndOperateur(valide,operateur);
        }else
            centrales=ersenCentraleRepository.getCentraleHavingTaskByValideAndCentrale(valide,idcentrale);

        if (valide==0){//SI tache non réalisé
            for (ErsenCentraleEntity _centrale : centrales) {
                //On recupére la liste des tache de la centrale i
                List<Object> listTache = new ArrayList<>();
                List<Object> objectList = ersenCentraleRepository.getTaskByCentraleValide(_centrale.getId(), valide);
                for (Object o : objectList) {
                    String periode = calculePeriode(((Object[]) o)[6].toString(), Integer.parseInt(((Object[]) o)[7].toString()));
                    Date periodDate = new SimpleDateFormat("yyyy-MM-dd").parse(periode);
                    Date dateObj = new Date();
                    if (periodDate.before(dateObj)) {
                        // Si la date est dépassé
                        listTache.add(o);
                    }
                }
                centraleTaches.add(new CentraleTache(_centrale.getId(), _centrale.getNom(), listTache));
            }
        }
        return  centraleTaches;
    }

    @GetMapping(value = "/centraletache/all/arealise/{valide}/{operateur}/{idcentrale}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    List<CentraleTache> getTacheARealiser(@PathVariable int valide,@PathVariable int operateur,@PathVariable String idcentrale) throws ParseException {
        List<CentraleTache> centraleTaches = new ArrayList<>();
        List<ErsenCentraleEntity> centrales;
        if(idcentrale.equals("neant")){
            if (operateur==0)
                centrales = ersenCentraleRepository.getCentraleHavingTaskByValide(valide);
            else
                centrales = ersenCentraleRepository.getCentraleHavingTaskByValideAndOperateur(valide,operateur);
        }
        else
            centrales=ersenCentraleRepository.getCentraleHavingTaskByValideAndCentrale(valide,idcentrale);

        if (valide==0){//SI tache à réaliser
            for (ErsenCentraleEntity centrale : centrales) {
                //On recupére la liste des tache de la centrale i
                List<Object> listTache = new ArrayList<>();
                List<Object> objectList = ersenCentraleRepository.getTaskByCentraleValide(centrale.getId(), valide);
                for (Object o : objectList) {
                    String periode = calculePeriode(((Object[]) o)[6].toString(), Integer.parseInt(((Object[]) o)[7].toString()));
                    Date periodDate = new SimpleDateFormat("yyyy-MM-dd").parse(periode);
                    Date dateObj = new Date();
                    if (periodDate.after(dateObj)) {
                        listTache.add(o);
                    }
                }
                centraleTaches.add(new CentraleTache(centrale.getId(), centrale.getNom(), listTache));
            }
        }
        return  centraleTaches;
    }

    @GetMapping(value ={"/centraleformap/all", "/centraleformap/all/{utilisateur}"})
    List<Object> getcentraleformap(@PathVariable(required = false) Integer utilisateur){
        List<Object> resultats = new ArrayList<>();
        List<Object> centrales;
        if(utilisateur==null)
            centrales = ersenCentraleRepository.getCentraleForMap();
        else
            centrales=ersenCentraleRepository.getCentraleForMapByOperateur(utilisateur);
        for (Object o : centrales) {
            ((Object[]) o)[11] = ersenCentraleRepository.getEntrepriseIdByCentrale(((Object[]) o)[0].toString());
            ((Object[]) o)[12] = ersenAbonneRepository.getCountErsenAbonneEntitiesByCentrale(((Object[]) o)[0].toString());
            resultats.add(o);
        }
        return resultats;
    }


    @GetMapping(value = "/centraletache/all/realisenonvalide/{valide}/{operateur}/{idcentrale}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    List<CentraleTache> getTacheRealiseesnonvalide(@PathVariable int valide,@PathVariable int operateur,@PathVariable String idcentrale){
        List<CentraleTache> centraleTaches = new ArrayList<>();
        List<ErsenCentraleEntity> centrales;
        if(idcentrale.equals("neant")){
            if (operateur==0)
                centrales = ersenCentraleRepository.getCentraleHavingTaskByValide(valide);
            else
                centrales = ersenCentraleRepository.getCentraleHavingTaskByValideAndOperateur(valide,operateur);
        }
        else
            centrales=ersenCentraleRepository.getCentraleHavingTaskByValideAndCentrale(valide,idcentrale);

        if (valide==1){//SI tache à réaliser
            for (ErsenCentraleEntity centrale : centrales) {

                centraleTaches.add(new CentraleTache(centrale.getId(), centrale.getNom(), ersenCentraleRepository.getTaskByCentraleValide(centrale.getId(), valide)));
            }
        }
        return  centraleTaches;
    }

    @GetMapping(value = "/centraletache/all/realiseetvalide/{valide}/{operateur}/{idcentrale}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    List<CentraleTache> getTacheRealiseesEtValider(@PathVariable int valide, @PathVariable(required = false) int operateur, @PathVariable String idcentrale){
        List<CentraleTache> centraleTaches = new ArrayList<>();
        List<ErsenCentraleEntity> centrales;
        if(idcentrale.equals("neant")){
            if (operateur==0)
                centrales = ersenCentraleRepository.getCentraleHavingTaskByValide(valide);
            else
                centrales = ersenCentraleRepository.getCentraleHavingTaskByValideAndOperateur(valide,operateur);
        }else {
            centrales=ersenCentraleRepository.getCentraleHavingTaskByValideAndCentrale(valide,idcentrale);
        }

        if (valide==2 || valide==3){//Si tache à réaliser
            for (ErsenCentraleEntity centrale : centrales)
                centraleTaches.add(new CentraleTache(centrale.getId(), centrale.getNom(), ersenCentraleRepository.getTaskByCentraleValide(centrale.getId(), valide)));
        }
        return  centraleTaches;
    }

    @PostMapping("/centrale/addetat")
    public ErsenEtatCentrale getCentraleAbonne(@RequestBody ErsenEtatCentrale ersenEtatCentrale){
        return ersenEtatCentraleRepository.save(ersenEtatCentrale);
    }

    @GetMapping("/updateentreprise/{centrale}/{entreprise}")
    public boolean updateEntrepriseCentrale(@PathVariable String centrale ,@PathVariable String entreprise){
        ersenCentraleRepository.updateEntreprise(centrale,entreprise);
        return true;
    }

    public static String calculePeriode(String dateSave, Integer valPeriod) throws ParseException {
        return getNextPlanification(dateSave, valPeriod);
    }

    static String getNextPlanification(String datesave, Integer varPeriod) throws ParseException {
        Date date =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datesave);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, varPeriod);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
    }

    String upload(String urlimage){
        String filename="";
        //On verifie si le type de contenu est different de text
        if(urlimage!=null)
        if (!urlimage.equals("")) {
            try {
                byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(urlimage);
                //On crée le nom de l'image
                filename = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()) + "_" + "centrale.png";
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

    public String convertStringToBase64(String fileName) {
        return getString(fileName, this.DIRECTORY);
    }

    @GetMapping(value = "/centrale/tables/{id}")
    List<CentraleDto> getAttribueCentraleTables(@PathVariable Integer id){
        List<CentraleDto> fields= new ArrayList<>();
        fields= getCentralesDTO();
        return fields;
    }

    @GetMapping(value = "/projection/centraleabonne/centrale")
    List<ProjectionCentraleAbonne> getCentraleAb(){
        return requeteMapper.abonneEntityToProjectioCentraleAbonne(ersenCentraleRepository.getAllCentraleDTOAdresse());
    }

}
