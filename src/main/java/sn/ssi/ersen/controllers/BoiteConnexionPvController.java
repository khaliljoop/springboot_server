package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.BoiteConnexionPvRepository;
import sn.ssi.ersen.entity.equipement.BoiteConnexionPvEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/boiteconnexionpv")
public class BoiteConnexionPvController {
    private final BoiteConnexionPvRepository boiteConnexionPvRepository;

    public BoiteConnexionPvController(BoiteConnexionPvRepository boiteConnexionPvRepository) {
        this.boiteConnexionPvRepository = boiteConnexionPvRepository;
    }

    @GetMapping("/all")
    public List<BoiteConnexionPvEntity> getBoiteConnexionPv(){
        return boiteConnexionPvRepository.findAll();
    }

    @GetMapping("/{id}")
    public BoiteConnexionPvEntity getBoiteConnexionPvById(@PathVariable String id){
        return boiteConnexionPvRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    @Transactional
    public  BoiteConnexionPvEntity addBoiteConnexionPv(@RequestBody BoiteConnexionPvEntity boiteConnexionPvEntity){
        return boiteConnexionPvRepository.saveAndFlush(boiteConnexionPvEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public BoiteConnexionPvEntity updateBoiteConnexionPv( @PathVariable String id,@RequestBody BoiteConnexionPvEntity boiteConnexionPvEntity){
        boiteConnexionPvEntity.setId(id);
        return boiteConnexionPvRepository.saveAndFlush(boiteConnexionPvEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteBoiteConnexionPv(@PathVariable String id){
        boiteConnexionPvRepository.deleteById(id);
    }
}
