package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenPaiementRepository;
import sn.ssi.ersen.dto.projectionrecusdto.ProjectionRecusNumeroPaiement;
import sn.ssi.ersen.entity.ErsenPaiementEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/paiement")
@CrossOrigin("*")
public class ErsenPaiementController {
    private final ErsenPaiementRepository ersenPaiementRepository;
    private final RequeteMapper requeteMapper;

    public ErsenPaiementController(ErsenPaiementRepository ersenPaiementRepository, RequeteMapper requeteMapper) {
        this.ersenPaiementRepository = ersenPaiementRepository;
        this.requeteMapper = requeteMapper;
    }

    @GetMapping("/all")
    public List<ErsenPaiementEntity> getPaiement(){
        return ersenPaiementRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenPaiementEntity getPaiementById(@PathVariable String id){
        return ersenPaiementRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  List<ErsenPaiementEntity> addPaiement(@RequestBody ErsenPaiementEntity ersenPaiementEntity){
        ersenPaiementRepository.saveAndFlush(ersenPaiementEntity);
        return getPaiement();
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public List<ErsenPaiementEntity> updatePaiement(@PathVariable String id, @RequestBody ErsenPaiementEntity ersenPaiementEntity){
        ersenPaiementEntity.setId(id);
        ersenPaiementRepository.saveAndFlush(ersenPaiementEntity);
        return getPaiement();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public List<ErsenPaiementEntity> deletePaiement(@PathVariable String id){
        ersenPaiementRepository.deleteById(id);
        return getPaiement();
    }
}
