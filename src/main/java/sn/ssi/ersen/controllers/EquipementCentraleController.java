package sn.ssi.ersen.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.AllEquipementRepository;
import sn.ssi.ersen.dao.EquipementCentraleRepository;
import sn.ssi.ersen.dao.ErsenCentraleRepository;
import sn.ssi.ersen.entity.AllEquipementEntity;
import sn.ssi.ersen.entity.EquipementCentrale;
import sn.ssi.ersen.entity.ErsenCentraleEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/equipementcentrale")
public class EquipementCentraleController {
    private final EquipementCentraleRepository equipementCentraleRepository;


    public EquipementCentraleController(EquipementCentraleRepository equipementCentraleRepository, ErsenCentraleRepository centraleRepository, AllEquipementRepository allEquipementRepository) {
        this.equipementCentraleRepository = equipementCentraleRepository;
    }

    @GetMapping(value = "/all")
    public List<EquipementCentrale> getAllEquipementCentrale(){
        return equipementCentraleRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public EquipementCentrale getByIdEquipementCentrale(@PathVariable String id){
        return equipementCentraleRepository.findById(id).orElse(null);
    }

    @PostMapping(value = "/add")
    @Transactional
    public EquipementCentrale saveEquipementCentrale(@RequestBody EquipementCentrale equipementCentrale){
        return equipementCentraleRepository.saveAndFlush(equipementCentrale);
    }

    @PutMapping(value = "/edit/{id}")
    public EquipementCentrale updateEquipementCentrale(@PathVariable String id, @RequestBody EquipementCentrale equipementCentrale){
        equipementCentrale.setId(id);
        return equipementCentraleRepository.saveAndFlush(equipementCentrale);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteEquipementCentrale(@PathVariable String id){
        equipementCentraleRepository.deleteById(id);
    }

}
