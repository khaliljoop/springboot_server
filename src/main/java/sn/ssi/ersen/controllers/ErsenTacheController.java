package sn.ssi.ersen.controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenParametresGenerauxRepository;
import sn.ssi.ersen.dao.ErsenTacheRepository;
import sn.ssi.ersen.entity.ErsenTacheEntity;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin("*")
public class ErsenTacheController {
    public String DIRECTORY;
    private final ErsenTacheRepository ersenTacheRepository;
    private final ErsenParametresGenerauxRepository ersenParametresGenerauxRepository;
    public ErsenTacheController(ErsenTacheRepository ersenTacheRepository, ErsenParametresGenerauxRepository ersenParametresGenerauxRepository) {
        this.ersenTacheRepository = ersenTacheRepository;
        this.ersenParametresGenerauxRepository = ersenParametresGenerauxRepository;
        getDir();
    }
    @GetMapping("/tache/all")
    public List<ErsenTacheEntity> getTache(){
        return ersenTacheRepository.findAll();
    }

    void getDir(){
        this.DIRECTORY=ersenParametresGenerauxRepository.getDirectory();
    }

    @GetMapping("/tache/{id}")
    public ErsenTacheEntity getTacheById(@PathVariable String id){
        return ersenTacheRepository.findById(id).get();
    }

    @GetMapping(value = "tachesection/all")
    public List<Object> getTacheSection(){
        return ersenTacheRepository.getTacheSectionMaintenance();
    }

    @PostMapping("/tache/add")
    public  List<Object> addTache(@RequestBody ErsenTacheEntity tache){
        ersenTacheRepository.save(tache);
        return getTacheSection();
    }

    @PutMapping("/tache/edit/{id}")
    public List<Object> updateTache(@PathVariable String id,@RequestBody ErsenTacheEntity tache){
        tache.setId(id);
        ersenTacheRepository.save(tache);
         return getTacheSection();
    }

    @DeleteMapping("/tache/delete/{id}")
    public List<Object> deleteTache(@PathVariable("id") String id){
        ersenTacheRepository.deleteById(id);
        return getTacheSection();
    }
    public String convertStringToBase64(String fileName) {
        return getString(fileName, this.DIRECTORY);
    }

    static String getString(String fileName, String directory) {
        byte[] fileContent;
        if (fileName!=null) {
            try {
                File file = new File(directory + fileName);
                if (file.exists()) {
                    fileContent = FileUtils.readFileToByteArray(file);
                    return Base64.getEncoder().encodeToString(fileContent);
                } else
                    return "";
            } catch (IOException e) {
                return "Erreur de conversio";
            }
        }else{
            return "";
        }
    }
}
