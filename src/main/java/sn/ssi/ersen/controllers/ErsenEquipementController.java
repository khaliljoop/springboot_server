package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenEquipementRepository;
import sn.ssi.ersen.entity.ErsenEquipementEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/equipement")
@CrossOrigin("*")
public class ErsenEquipementController {
    private final ErsenEquipementRepository ersenEquipementRepository;

    public ErsenEquipementController(ErsenEquipementRepository ersenEquipementRepository) {
        this.ersenEquipementRepository = ersenEquipementRepository;
    }

    @GetMapping("/all")
    public List<ErsenEquipementEntity> getEquipement(){
        return ersenEquipementRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenEquipementEntity getEquipementById(@PathVariable String id){
        return ersenEquipementRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  List<ErsenEquipementEntity> addEquipement(@RequestBody ErsenEquipementEntity ersenEquipementEntity){
        ersenEquipementRepository.saveAndFlush(ersenEquipementEntity);
        return getEquipement();
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public List<ErsenEquipementEntity> updateEquipement(@PathVariable String id, @RequestBody ErsenEquipementEntity ersenEquipementEntity){
        ersenEquipementEntity.setId(id);
       ersenEquipementRepository.saveAndFlush(ersenEquipementEntity);
       return getEquipement();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public List<ErsenEquipementEntity> deleteEquipement(@PathVariable String id){
        ersenEquipementRepository.deleteById(id);
        return getEquipement();
    }
}
