package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ConcentrateurRepository;
import sn.ssi.ersen.entity.equipement.ConcentrateurEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/concentrateur")
@CrossOrigin("*")
public class ConcentrateurController {
    private final ConcentrateurRepository concentrateurRepository;

    public ConcentrateurController(ConcentrateurRepository concentrateurRepository) {
        this.concentrateurRepository = concentrateurRepository;
    }

    @GetMapping("/all")
    public List<ConcentrateurEntity> getConcentrateur(){
        return concentrateurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ConcentrateurEntity getConcentrateurById(@PathVariable String id){
        return concentrateurRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ConcentrateurEntity addConcentrateur(@RequestBody ConcentrateurEntity concentrateurEntity){
        return concentrateurRepository.saveAndFlush(concentrateurEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public List<ConcentrateurEntity> updateConcentrateur(@PathVariable String id, @RequestBody ConcentrateurEntity concentrateurEntity){
        concentrateurEntity.setId(id);
        concentrateurRepository.saveAndFlush(concentrateurEntity);
        return getConcentrateur();
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteConcentrateur(@PathVariable String id){
        concentrateurRepository.deleteById(id);
    }
}
