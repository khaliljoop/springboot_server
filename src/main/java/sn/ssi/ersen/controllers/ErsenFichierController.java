package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenFichierRepository;
import sn.ssi.ersen.dao.ErsenParametresGenerauxRepository;
import sn.ssi.ersen.entity.entitieMobile.Fichier;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@CrossOrigin
@RequestMapping(value = "fichier")
public class ErsenFichierController {

    private final ErsenFichierRepository ersenFichierRepository;
    private final ErsenParametresGenerauxRepository ersenParametresGenerauxRepository;
    private String DIRECTORY ;

    public ErsenFichierController(ErsenFichierRepository ersenFichierRepository, ErsenParametresGenerauxRepository ersenParametresGenerauxRepository) {
        this.ersenFichierRepository = ersenFichierRepository;
        this.ersenParametresGenerauxRepository = ersenParametresGenerauxRepository;
        getDir();
    }

    void getDir(){
        this.DIRECTORY=ersenParametresGenerauxRepository.getDirectory();
    }

    @PostMapping("/addEntity")
    public String addEntity(@RequestBody List<Fichier> fichiers){
        for (Fichier fichier : fichiers) {
            fichier.setContent(uploadBase64String(fichier));
            ersenFichierRepository.save(fichier);
        }
        return "yes";
    }

    @PostMapping("/delete")
    public Boolean deleteFiles(@RequestBody List<String> files){
        for (String id : files){
            ersenFichierRepository.deleteById(id);
        }
        return true;
    }


    String uploadBase64String(Fichier fichier){
        String fileName="";
        //On verifie si le type de contenu est different de text
        if (!fichier.getContent().equals("")) {
            if(fichier.getContenttype().equalsIgnoreCase("image")){
                try {
                    byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(fichier.getContent());
                    //On crée le nom de l'image
                    fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()) + "_" + "image_tuto.png";
                    //directory = chemin de l'image
                    String directory = this.DIRECTORY + fileName;
                    new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                    //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
                } catch (IOException e) {
                    e.printStackTrace();
                    return  "";
                }
            }
            else if(fichier.getContenttype().equalsIgnoreCase("audio")){
                try {
                    byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(fichier.getContent());
                    //On crée le nom de l'image
                    fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()) + "_" + "audio_tuto.mp3";
                    //directory = chemin de l'image
                    String directory = this.DIRECTORY + fileName;
                    new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                    //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
                } catch (IOException e) {
                    e.printStackTrace();
                    return  "";
                }
            }
            else if(fichier.getContenttype().equalsIgnoreCase("video")){
                try {
                    byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(fichier.getContent());
                    //On crée le nom de l'image
                    fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()) + "_" + "tuto_video.mp4";
                    //directory = chemin de l'image
                    String directory = this.DIRECTORY + fileName;
                    new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                    //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
                } catch (IOException e) {
                    e.printStackTrace();
                    return  "";
                }
            }
            else if(fichier.getContenttype().equalsIgnoreCase("pdf")){
                try {
                    byte[] fileFromBase64 = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(fichier.getContent());
                    //On crée le nom de l'image
                    fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault()).format(new Date()) + "_" + "tuto_pdf.pdf";
                    //directory = chemin de l'image
                    String directory = this.DIRECTORY + fileName;
                    new FileOutputStream(directory).write(fileFromBase64); //upload l'image
                    //preuveTacheEntity.setUrlfichier(imageName);  //Change la b64 en url
                } catch (IOException e) {
                    e.printStackTrace();
                    return  "";
                }
            }
        }
        return fileName;
    }
}

