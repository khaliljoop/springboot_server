package sn.ssi.ersen.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenFichierRepository;
import sn.ssi.ersen.dao.ErsenParametresGenerauxRepository;
import sn.ssi.ersen.dao.ErsenTutorielRepository;
import sn.ssi.ersen.entity.ErsenTutorielEntity;
import sn.ssi.ersen.entity.entitieMobile.Fichier;
import sn.ssi.ersen.entity.forWeb.TutorielWeb;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/tutoriel")
public class ErsenTutorielController {
    private final ErsenTutorielRepository ersenTutorielRepository;
    private final ErsenParametresGenerauxRepository ersenParametresGenerauxRepository;
    private final ErsenFichierController ersenFichierController;
    private final ErsenFichierRepository ersenFichierRepository;
    private String DIRECTORY ;

    public ErsenTutorielController(ErsenTutorielRepository ersenTutorielRepository, ErsenParametresGenerauxRepository ersenParametresGenerauxRepository, ErsenFichierController ersenFichierController, ErsenFichierRepository ersenFichierRepository) {
        this.ersenTutorielRepository = ersenTutorielRepository;
        this.ersenParametresGenerauxRepository = ersenParametresGenerauxRepository;
        this.ersenFichierController = ersenFichierController;
        this.ersenFichierRepository = ersenFichierRepository;
        getDir();
    }

    @GetMapping("/libedit/{id}/{nom}")
    public Boolean updateLibTuto(@PathVariable String id, @PathVariable String nom){
        ersenTutorielRepository.updateLib(id, nom);
        return true;
    }

    @GetMapping("/all")
    public List<TutorielWeb> getTutorielweb(){
        List<TutorielWeb> tutoWebs=new ArrayList<>();
        List<ErsenTutorielEntity> tutoriels=ersenTutorielRepository.findAll();
        //tutorielWeb.getDatesave()=ersenTutorielEntities.get(i).getDatesave();
        for (ErsenTutorielEntity tutoriel : tutoriels) {
            List<Fichier> fichiers = ersenFichierRepository.getFilesOfAnEntity(tutoriel.getId());
            tutoWebs.add(new TutorielWeb(tutoriel.getId(), tutoriel.getNom(), tutoriel.getDatesave(), tutoriel.getDateupdate(), fichiers));
        }
        return tutoWebs;
    }

    @GetMapping("/{id}")
    public ErsenTutorielEntity getTutoById(@PathVariable String id){
        return ersenTutorielRepository.findById(id).orElse(null);
    }

    //28/032022
    @PostMapping("/add")
    @Transactional
    public  List<TutorielWeb> addTuto(@RequestBody TutorielWeb tutorielWeb){
        Date date=new Date();
        ErsenTutorielEntity ersenTutorielEntity = new ErsenTutorielEntity(null,date,date,tutorielWeb.getNom());
        ErsenTutorielEntity tuto = ersenTutorielRepository.saveAndFlush(ersenTutorielEntity);
        String idtuto= tuto.getId();
        for (int i=0;i<tutorielWeb.getFichiers().size();i++){
            tutorielWeb.getFichiers().get(i).setIdEntite(idtuto);
            tutorielWeb.getFichiers().get(i).setContent(ersenFichierController.uploadBase64String(tutorielWeb.getFichiers().get(i)));
            ersenFichierRepository.save(tutorielWeb.getFichiers().get(i));
        }
        return getTutorielweb();
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenTutorielEntity updateCategorie( @PathVariable String id,@RequestBody ErsenTutorielEntity ersenTutorielEntity){
        ersenTutorielEntity.setId(id);
        return ersenTutorielRepository.saveAndFlush(ersenTutorielEntity);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCategorie(@PathVariable String id){
        ersenTutorielRepository.deleteById(id);
        return "ok";
    }

    @GetMapping(value = "")
    public List<ErsenTutorielEntity> getTutoriel(){
        return ersenTutorielRepository.findAll();
    }
    /* @GetMapping(value = "/preuveweb/{tachecentre}")
     public PreuveWeb getPreuveWeb(@PathVariable String tachecentre){
         ErsenPreuveTacheEntity ersenPreuveTacheEntitie= ersenTutorielRepository.getErsenPreuveTacheEntityByTachecentre(tachecentre);
         //String idpreuve = ersenPreuveTacheEntitie.getId();
         List<Fichier> fichiers=ersenTutorielRepository.getFichierByIdEntite(tachecentre);
         for (int i=0;i<fichiers.size();i++)
         {
             fichiers.get(i).setContent(
                     convertStringToBase64(fichiers.get(i).getContent())
             );
         }
         PreuveWeb preuveWebs= new PreuveWeb(ersenPreuveTacheEntitie.getId(), ersenPreuveTacheEntitie.getDatesave(), fichiers);
         return  preuveWebs;
     }
 */
    void getDir(){
        this.DIRECTORY=ersenParametresGenerauxRepository.getDirectory();
    }

}
