package sn.ssi.ersen.controllers;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.*;
import sn.ssi.ersen.entity.ErsenBackupTacheEntity;
import sn.ssi.ersen.entity.ErsenNotificationEntity;
import sn.ssi.ersen.entity.ErsenPreuveTacheEntity;
import sn.ssi.ersen.entity.ErsenTacheCentraleEntity;
import sn.ssi.ersen.entity.entitieMobile.Fichier;
import sn.ssi.ersen.entity.entitieMobile.Notification;
import sn.ssi.ersen.entity.entitieMobile.Preuve;
import sn.ssi.ersen.entity.entitieMobile.PreuvesAndNotifs;
import sn.ssi.ersen.entity.forWeb.PreuveWeb;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class ErsenPreuveTacheController {
    private final ErsenPreuveTacheRepository ersenPreuveTacheRepository;
    private String DIRECTORY;
    private final ErsenParametresGenerauxRepository ersenParametresGenerauxRepository;
    private final ErsenTacheCentraleRepository tacheCentraleRepository;
    private final ErsenPreuveTacheRepository preuveTacheRepository;
    private final ErsenNotificationRepository notificationRepository;
    private final ErsenFichierRepository ersenFichierRepository;
    private final ErsenCentraleRepository ersenCentraleRepository;
    private final ErsenBackupPreuveAndNotifTacheRepository backupTacheRepository;
    private final ErsenNotificationRepository ersenNotificationRepository;


    public ErsenPreuveTacheController(ErsenPreuveTacheRepository ersenPreuveTacheRepository, ErsenTacheCentraleRepository tacheCentraleRepository, ErsenPreuveTacheRepository preuveTacheRepository, ErsenNotificationRepository notificationRepository, ErsenFichierRepository ersenFichierRepository, ErsenParametresGenerauxRepository ersenParametresGenerauxRepository, ErsenParametresGenerauxRepository ersenParametresGenerauxRepository1, ErsenCentraleRepository ersenCentraleRepository, ErsenBackupPreuveAndNotifTacheRepository backupTacheRepository, ErsenNotificationRepository ersenNotificationRepository) {
        this.ersenPreuveTacheRepository = ersenPreuveTacheRepository;
        this.tacheCentraleRepository = tacheCentraleRepository;
        this.preuveTacheRepository = preuveTacheRepository;
        this.notificationRepository = notificationRepository;
        this.ersenFichierRepository = ersenFichierRepository;
        this.DIRECTORY= ersenParametresGenerauxRepository.getDirectory();
        this.ersenParametresGenerauxRepository = ersenParametresGenerauxRepository1;
        this.ersenCentraleRepository = ersenCentraleRepository;
        this.backupTacheRepository = backupTacheRepository;
        this.ersenNotificationRepository = ersenNotificationRepository;
        getDir();
    }

    void getDir(){
        this.DIRECTORY=ersenParametresGenerauxRepository.getDirectory();
    }

    @GetMapping("/preuvetaches")
    public List<ErsenPreuveTacheEntity> getPreuveTache(){
        return ersenPreuveTacheRepository.findAll();
    }

    @GetMapping("/preuvetaches/{id}")
    public ErsenPreuveTacheEntity getPreuveTache(@PathVariable String id){
        return ersenPreuveTacheRepository.findById(id).orElse(null);
    }

    /** mettre l'etat de la tache à 40288187786932bd01786935173c0000(Réalisée) ou
     * à 40288187786932bd0178693533920001(Panne constatée sur la section) et valide a 1
     * pour dire qu'elle n'a pas été validéé ou que la notification n'a pas été résolue*/
    public void setTacheCentraleEtatValideEtDateEffectuee(String idT,Date dateeffectuee,boolean isPreuve,int byOperateur){
        ErsenTacheCentraleEntity tacheC = tacheCentraleRepository.findTacheCentraleById(idT);
        if(tacheC !=null) {
            tacheC.setEtattache(isPreuve?"40288187786932bd01786935173c0000":"40288187786932bd0178693533920001");
            tacheC.setValide(byOperateur==0?1:2);
            tacheC.setDateeffectuee(dateeffectuee);
            tacheCentraleRepository.save(tacheC);
        }
    }

    /** mettre l'etat de la tache à 40288187786932bd01786935173c0000(Réalisée) ou
     * à 40288187786932bd0178693533920001(Panne constatée sur la section) et valide a 1
     * pour dire qu'elle n'a pas été validéé ou que la notification n'a pas été résolue*/
    public void setBackUpTacheEtatValideEtDateEffectuee(String idT,Date dateeffectuee,boolean isPreuve,int byOperateur){
        ErsenBackupTacheEntity tacheBackUp = tacheCentraleRepository.findBackUpTacheId(idT);
        if(tacheBackUp!=null) {
            tacheBackUp.setEtattache(isPreuve?"40288187786932bd01786935173c0000":"40288187786932bd0178693533920001");
            tacheBackUp.setDateeffectuee(dateeffectuee);
            tacheBackUp.setValideBackup(byOperateur==0?1:2);
            backupTacheRepository.save(tacheBackUp);
        }
    }

    @PostMapping("/preuve_notif/add")
    public void addPreuvesOrNotifsTache(@RequestBody PreuvesAndNotifs preuvesAndNotifs){
        if(!preuvesAndNotifs.getPreuves().isEmpty()){
            savePreuvesFromMobile(preuvesAndNotifs.getPreuves(),preuvesAndNotifs.getIdExpediteur(),preuvesAndNotifs.getByOperateur());
            for (Preuve preuve : preuvesAndNotifs.getPreuves())
                setTacheCentraleEtatValideEtDateEffectuee(preuve.getIdtache(), preuve.getDatesave(), true,preuvesAndNotifs.getByOperateur());
        }

        if(!preuvesAndNotifs.getNotifs().isEmpty()) {
            ersenNotificationRepository.updateUsersNotif(preuvesAndNotifs.getIdExpediteur());
            saveNotifsfFromMobile(preuvesAndNotifs.getNotifs(), preuvesAndNotifs.getIdExpediteur(), preuvesAndNotifs.getIdDestinataire(),preuvesAndNotifs.getByOperateur());
            for (Notification notif : preuvesAndNotifs.getNotifs()) {
                if(notif.getIdTacheOrSectM()!=null && notif.getIsRelative()==1)
                  setTacheCentraleEtatValideEtDateEffectuee(notif.getIdTacheOrSectM(),notif.getDateSave(),false,preuvesAndNotifs.getByOperateur());
            }
        }

        if(!preuvesAndNotifs.getPreuvesTacheDep().isEmpty()){
            savePreuvesFromMobile(preuvesAndNotifs.getPreuvesTacheDep(),preuvesAndNotifs.getIdExpediteur(),preuvesAndNotifs.getByOperateur());
            for (Preuve p : preuvesAndNotifs.getPreuvesTacheDep())
                setBackUpTacheEtatValideEtDateEffectuee(p.getIdtache(),p.getDatesave(),true,preuvesAndNotifs.getByOperateur());
        }

        if(!preuvesAndNotifs.getNotifsTacheDep().isEmpty()) {
            saveNotifsfFromMobile(preuvesAndNotifs.getNotifsTacheDep(), preuvesAndNotifs.getIdExpediteur(), preuvesAndNotifs.getIdDestinataire(),preuvesAndNotifs.getByOperateur());
            for (Notification n : preuvesAndNotifs.getNotifsTacheDep()) {
                if(n.getIdTacheOrSectM()!=null && n.getIsRelative()==1)
                    setBackUpTacheEtatValideEtDateEffectuee(n.getIdTacheOrSectM(),n.getDateSave(),false,preuvesAndNotifs.getByOperateur());
            }
        }
    }

    //Conversion d'un object de type Preuve en liste de ErsenPreuveEntity
    private void savePreuvesFromMobile(List<Preuve> preuves, int idExpediteur, int byOperateur/*0 if conducteur,1 if operateur*/) {
        List<ErsenPreuveTacheEntity> preuveTacheEntityList = new ArrayList<>();
        for (Preuve p : preuves){
            preuveTacheEntityList.add(new ErsenPreuveTacheEntity(p.getId(), p.getDatesave(),p.getIdtache(), 2,byOperateur, idExpediteur));
            for(Fichier fichier : p.getFichiers())
                fichier.setContent(findFileInBase64FormatAndPutItToDisc(fichier));
            ersenFichierRepository.saveAll(p.getFichiers());
        }
        preuveTacheRepository.saveAll(preuveTacheEntityList);
    }

    //Conversion d'un object de type Notification en liste de ErsenNotificationEntity
    private void saveNotifsfFromMobile(List<Notification> notifs, int idExpediteur, String idDestinataire, int byOperateur/*0 if conducteur,1 if operateur*/) {
        List<ErsenNotificationEntity> notifsEntityList = new ArrayList<>();
        for (Notification notif : notifs){
            notifsEntityList.add(new ErsenNotificationEntity(notif.getId(),notif.getImpact(),notif.getLibelle(),notif.getObjet(), idDestinataire, idExpediteur,notif.getIdTacheOrSectM(),notif.getDateSave(),notif.getIsRelative(),byOperateur==0?2:3,notif.getCentralid(),byOperateur));
            for(Fichier fichier : notif.getFichiers())
                fichier.setContent(findFileInBase64FormatAndPutItToDisc(fichier));
            ersenFichierRepository.saveAll(notif.getFichiers());
        }
        notificationRepository.saveAll(notifsEntityList);

        Map<String, Set<Integer>> centraleNotifs=notifsEntityList.stream().collect(Collectors.groupingBy(ErsenNotificationEntity::getCentralid,Collectors.mapping(ErsenNotificationEntity::getImpact, Collectors.toSet())));
        centraleNotifs.forEach((centralID, centralImpactsDistinc) -> {
            if(centralImpactsDistinc.contains(2))
                ersenCentraleRepository.updateEtatCentrale(centralID,3);
            else if(centralImpactsDistinc.contains(1))
                ersenCentraleRepository.updateEtatCentrale(centralID,2);
        });
    }

    /**Telechargement de la base64 dans le repertoire des fichier et retourne son nom.*/
    String findFileInBase64FormatAndPutItToDisc(Fichier fichier){
        String fileName;
        //On verifie si le type de contenu est different de text
        if (!fichier.getContenttype().equals("text")) {
            try {
                byte[] fileFromBase64 = Base64.decodeBase64(fichier.getContent());
                //On crée le nom de l'image
                fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()) + "_" + fichier.getContenttype();
                //directory = chemin de l'image
                String directory = this.DIRECTORY + fileName;
                new FileOutputStream(directory).write(fileFromBase64); //copier l'image dans le repertoire
                //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
            } catch (IOException e) {
                e.printStackTrace();
                return  "";
            }
        }
        else
            fileName=fichier.getContent();//donne le text contenu par ce fichier
        return fileName;
    }
    //Ceci est un commantaire

    @PutMapping("/preuvetaches/edit/{id}")
    @Transactional
    public ErsenPreuveTacheEntity updatePreuveTache(@PathVariable String id,@RequestBody ErsenPreuveTacheEntity ersenPreuveTacheEntity){
        ersenPreuveTacheEntity.setId(id);
        return ersenPreuveTacheRepository.saveAndFlush(ersenPreuveTacheEntity);
    }

    @DeleteMapping("/preuvetaches/delete/{id}")
    @Transactional
    public void deletePreuveTache(@PathVariable("id") String id){
        ersenPreuveTacheRepository.deleteById(id);
    }

    @GetMapping(value = "/preuveweb/{tachecentre}")
    public PreuveWeb getPreuveWeb(@PathVariable String tachecentre){
        ErsenPreuveTacheEntity ersenPreuveTacheEntitie= ersenPreuveTacheRepository.getErsenPreuveTacheEntityByTachecentre(tachecentre);
        List<Fichier> fichiers=ersenFichierRepository.getFilesOfAnEntity(ersenPreuveTacheEntitie.getId());
        for (Fichier fichier : fichiers)
            fichier.setContent(convertStringToBase64(fichier.getContent()));
        return  new PreuveWeb(ersenPreuveTacheEntitie.getId(), ersenPreuveTacheEntitie.getDatesave(), fichiers);
    }

    public String convertStringToBase64(String fileName) {
        byte[] fileContent;
        if (fileName!=null) {
            try {
                File file = new File(this.DIRECTORY + fileName);
                if (file.exists()) {
                    fileContent = FileUtils.readFileToByteArray(file);
                    return java.util.Base64.getEncoder().encodeToString(fileContent);
                } else
                    return "";
            } catch (IOException e) {
                return "Erreur de conversio";
            }
        }else{
            return "";
        }
    }

    //30/03/2022
    @GetMapping(value = "/preuve/{tachecentre}")
    public List<Fichier> getFichierBycentre(@PathVariable String tachecentre){
        /*for (Fichier fichier : fichiers)
            fichier.setContent(convertStringToBase64(fichier.getContent()));*/
        return ersenFichierRepository.getFichierByCentre(tachecentre);
    }


    @GetMapping(path = "/downloadfile/{fichier}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fichier) throws IOException {

        File file = new File(DIRECTORY+fichier);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fichier);
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }
}
