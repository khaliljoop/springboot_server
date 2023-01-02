package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenRegularteurChargeRepository;
import sn.ssi.ersen.entity.equipement.RegulateurChargeEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/regulateurcharge")
@CrossOrigin("*")
public class RegulateurChargeController {
   private final ErsenRegularteurChargeRepository ersenRegularteurChargeRepository;

    public RegulateurChargeController(ErsenRegularteurChargeRepository ersenRegularteurChargeRepository) {
        this.ersenRegularteurChargeRepository = ersenRegularteurChargeRepository;
    }

    @GetMapping("/all")
    public List<RegulateurChargeEntity> getRegulateurCharge(){
        return ersenRegularteurChargeRepository.findAll();
    }

    @GetMapping("/{id}")
    public RegulateurChargeEntity getRegulateurChargeById(@PathVariable String id){
        return ersenRegularteurChargeRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  RegulateurChargeEntity addRegulateurCharge(@RequestBody RegulateurChargeEntity regulateurChargeEntity){
        return ersenRegularteurChargeRepository.saveAndFlush(regulateurChargeEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public RegulateurChargeEntity updateRegulateurCharge( @PathVariable String id,@RequestBody RegulateurChargeEntity regulateurChargeEntity){
        regulateurChargeEntity.setId(id);
        return ersenRegularteurChargeRepository.saveAndFlush(regulateurChargeEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteRegulateurCharge(@PathVariable String id){
        ersenRegularteurChargeRepository.deleteById(id);
    }
}
