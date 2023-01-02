package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.OnduleurChargeurRepository;
import sn.ssi.ersen.entity.equipement.OnduleurChargeurEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/onduleurchargeur")
@CrossOrigin("*")
public class OnduleurChargeurController {
    private final OnduleurChargeurRepository onduleurChargeurRepository;

    public OnduleurChargeurController(OnduleurChargeurRepository onduleurChargeurRepository) {
        this.onduleurChargeurRepository = onduleurChargeurRepository;
    }

    @GetMapping("/all")
    public List<OnduleurChargeurEntity> getOnduleurChargeur(){
        return onduleurChargeurRepository.findAll();
    }

    @GetMapping("/{id}")
    public OnduleurChargeurEntity getOnduleurChargeurById(@PathVariable String id){
        return onduleurChargeurRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  OnduleurChargeurEntity addOnduleurChargeur(@RequestBody OnduleurChargeurEntity onduleurChargeurEntity){
        return onduleurChargeurRepository.saveAndFlush(onduleurChargeurEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public OnduleurChargeurEntity updateOnduleurChargeur(@PathVariable String id, @RequestBody OnduleurChargeurEntity onduleurChargeurEntity){
        onduleurChargeurEntity.setId(id);
        return onduleurChargeurRepository.saveAndFlush(onduleurChargeurEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteOnduleurChargeur(@PathVariable String id){
        onduleurChargeurRepository.deleteById(id);
    }
}
