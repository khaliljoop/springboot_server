package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenConfigCentraleTacheRepository;
import sn.ssi.ersen.entity.ErsenConfigCentraleTacheEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ErsenConfigCentraleTacheController {

    private final ErsenConfigCentraleTacheRepository ersenConfigCentraleTacheRepository;

    public ErsenConfigCentraleTacheController(ErsenConfigCentraleTacheRepository ersenConfigCentraleTacheRepository) {
        this.ersenConfigCentraleTacheRepository = ersenConfigCentraleTacheRepository;
    }

    @GetMapping("/configcentraletaches")
    public List<ErsenConfigCentraleTacheEntity> getConfigCentraleTache(){
        return ersenConfigCentraleTacheRepository.findAll();
    }

    @GetMapping("/configcentraleTaches/{id}")
    public ErsenConfigCentraleTacheEntity getConfigCentraleTacheById(@PathVariable String id){
        return ersenConfigCentraleTacheRepository.findById(id).get();
    }

    @PostMapping("/configcentraleTaches/addnewconfigcentraleTaches")
    @Transactional
    public  ErsenConfigCentraleTacheEntity addConfigCentraleTache(@RequestBody ErsenConfigCentraleTacheEntity ersenConfigCentraleTacheEntity){
        return ersenConfigCentraleTacheRepository.saveAndFlush(ersenConfigCentraleTacheEntity);
    }

    @PutMapping("/configcentraleTaches/edit/{id}")
    @Transactional
    public ErsenConfigCentraleTacheEntity updateConfigCentraleTache(@PathVariable String id,@RequestBody ErsenConfigCentraleTacheEntity ersenConfigCentraleTacheEntity){
        ersenConfigCentraleTacheEntity.setId(id);
        return ersenConfigCentraleTacheRepository.saveAndFlush(ersenConfigCentraleTacheEntity);
    }

    @DeleteMapping("/configcentraleTaches/delete/{id}")
    @Transactional
    public void deleteConfigCentraleTache(@PathVariable("id") String id){
        ersenConfigCentraleTacheRepository.deleteById(id);
    }
}
