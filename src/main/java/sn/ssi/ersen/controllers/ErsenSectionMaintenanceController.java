package sn.ssi.ersen.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenParametresGenerauxRepository;
import sn.ssi.ersen.dao.ErsenSectionMaintenanceRepository;
import sn.ssi.ersen.entity.ErsenSectionmaintenanceEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping(value = "/sectionmaintenance")
@CrossOrigin("*")
public class ErsenSectionMaintenanceController {
    private final ErsenSectionMaintenanceRepository ersenSectionMaintenanceRepository;
    private final ErsenParametresGenerauxRepository ersenParametresGenerauxRepository;
    public String DIRECTORY;

    public ErsenSectionMaintenanceController(ErsenSectionMaintenanceRepository ersenSectionMaintenanceRepository, ErsenParametresGenerauxRepository ersenParametresGenerauxRepository) {
        this.ersenSectionMaintenanceRepository = ersenSectionMaintenanceRepository;
        this.ersenParametresGenerauxRepository = ersenParametresGenerauxRepository;
        getDir();
    }

    void getDir(){
        this.DIRECTORY=ersenParametresGenerauxRepository.getDirectory();
    }

    @GetMapping("/sectnotincentrale/{centrale_id}")
    public List<ErsenSectionmaintenanceEntity> getSectNotInCentrale(@PathVariable String centrale_id){
        return ersenSectionMaintenanceRepository.getSectionNotInCentrale(centrale_id);
    }

    @GetMapping("/all")
    public List<ErsenSectionmaintenanceEntity> getSectionMaintenance(){
        return ersenSectionMaintenanceRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenSectionmaintenanceEntity getSectionMaintenanceById(@PathVariable String id){
        return ersenSectionMaintenanceRepository.findById(id).get();
    }

    @PostMapping("/add")
    public  List<ErsenSectionmaintenanceEntity> addSectionMaintenance(@RequestBody ErsenSectionmaintenanceEntity ersenSectionmaintenanceEntity){
        ErsenSectionmaintenanceEntity ersenSection=new ErsenSectionmaintenanceEntity(
                ersenSectionmaintenanceEntity.getId(),
                ersenSectionmaintenanceEntity.getLibelle(),
                uploadBase64String(ersenSectionmaintenanceEntity.getUrlimage())
        );
        ersenSectionMaintenanceRepository.save(ersenSection);
         return getSectionMaintenance();
    }

    @PutMapping("/edit/{id}")
    public List<ErsenSectionmaintenanceEntity> updateSectionMaintenance(@RequestBody ErsenSectionmaintenanceEntity ersenSectionmaintenanceEntity){
        ErsenSectionmaintenanceEntity ersenSection=new ErsenSectionmaintenanceEntity(
                ersenSectionmaintenanceEntity.getId(),
                ersenSectionmaintenanceEntity.getLibelle(),
                uploadBase64String(ersenSectionmaintenanceEntity.getUrlimage())
        );
        ersenSectionMaintenanceRepository.save(ersenSection);
        return getSectionMaintenance();
    }

    @DeleteMapping("/delete/{id}")
    public List<ErsenSectionmaintenanceEntity> deleteSectionMaintenance(@PathVariable String id){
        ersenSectionMaintenanceRepository.deleteById(id);
        return getSectionMaintenance();
    }

    public String convertStringToBase64(String fileName) {
        byte[] fileContent;
        if (fileName!=null) {
            try {
                File file = new File(this.DIRECTORY + fileName);
                if (file.exists()) {
                    fileContent = FileUtils.readFileToByteArray(file);
                    return Base64.getEncoder().encodeToString(fileContent);
                } else
                    return "";
            } catch (IOException e) {
                return "Erreur de conversion";
            }
        }
        else
            return "";
    }

    //Tekechargement de la b64 dans le repertoire des fichier
    String uploadBase64String(String urlimage){
        String fileName="";
        //On verifie si le type de contenu est different de text
        if (!urlimage.equals("")) {
            try {
                byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(urlimage);
                //On crée le nom de l'image
                fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()) + "_" + "sect_maintenanceImage.png";
                //directory = chemin de l'image
                String directory = this.DIRECTORY + fileName;
                new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
            } catch (IOException e) {
                e.printStackTrace();
                return  "";
            }
        }
        return fileName;
    }
}
