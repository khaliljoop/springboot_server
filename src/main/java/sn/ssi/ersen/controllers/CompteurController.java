package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.CompteurRepository;
import sn.ssi.ersen.entity.equipement.CompteurEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/compteur")
@CrossOrigin("*")
public class CompteurController {
    private final CompteurRepository compteurRepository;

    public CompteurController(CompteurRepository compteurRepository) {
        this.compteurRepository = compteurRepository;
    }

    @GetMapping("/all")
    public List<CompteurEntity> getCompteur(){
        return compteurRepository.findAll();
    }

    @GetMapping("/{id}")
    public CompteurEntity getCompteurById(@PathVariable String id){
        return compteurRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  CompteurEntity addCompteur(@RequestBody CompteurEntity compteurEntity){
        return compteurRepository.saveAndFlush(compteurEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public CompteurEntity updateCompteur( @PathVariable String id,@RequestBody CompteurEntity compteurEntity){
        compteurEntity.setId(id);
        return compteurRepository.saveAndFlush(compteurEntity);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteCompteur(@PathVariable String id){
        compteurRepository.deleteById(id);
    }
}
