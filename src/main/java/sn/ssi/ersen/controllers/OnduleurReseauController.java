package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.OnduleurReseauRepository;
import sn.ssi.ersen.entity.equipement.OnduleurReseauEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/onduleurreseau")
@CrossOrigin("*")
public class OnduleurReseauController {
    private final OnduleurReseauRepository onduleurReseauRepository;

    public OnduleurReseauController(OnduleurReseauRepository onduleurReseauRepository) {
        this.onduleurReseauRepository = onduleurReseauRepository;
    }

    @GetMapping("/all")
    public List<OnduleurReseauEntity> getOnduleurChargeur(){
        return onduleurReseauRepository.findAll();
    }

    @GetMapping("/{id}")
    public OnduleurReseauEntity getOnduleurChargeurById(@PathVariable String id){
        return onduleurReseauRepository.findById(id).get();
    }

    @PostMapping(value = "/add")
    @Transactional
    public  OnduleurReseauEntity addOnduleurChargeur(@RequestBody OnduleurReseauEntity onduleurReseauEntity){
        return onduleurReseauRepository.saveAndFlush(onduleurReseauEntity);
    }

    @PutMapping(value = "/edit/{id}")
    @Transactional
    public OnduleurReseauEntity updateOnduleurChargeur(@PathVariable String id, @RequestBody OnduleurReseauEntity onduleurReseauEntity){
        onduleurReseauEntity.setId(id);
        return onduleurReseauRepository.saveAndFlush(onduleurReseauEntity);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Transactional
    public void deleteOnduleurChargeur(@PathVariable String id){
        onduleurReseauRepository.deleteById(id);
    }
}
