package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenBatterieRepository;
import sn.ssi.ersen.entity.ErsenBatterieEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/batterie")
@CrossOrigin("*")
public class ErsenBatterieController {
    private final ErsenBatterieRepository ersenBatterieRepository;

    public ErsenBatterieController(ErsenBatterieRepository ersenBatterieRepository) {
        this.ersenBatterieRepository = ersenBatterieRepository;
    }

    @GetMapping("/all")
    public List<ErsenBatterieEntity> getBatterie(){
        return ersenBatterieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenBatterieEntity getBatterieById(@PathVariable String id){
        return ersenBatterieRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenBatterieEntity addBatterie(@RequestBody ErsenBatterieEntity ersenBatterieEntity){
        return ersenBatterieRepository.saveAndFlush(ersenBatterieEntity);
    }

    @PutMapping("/edit")
    @Transactional
    public ErsenBatterieEntity updateBatterie(@PathVariable String id,@RequestBody ErsenBatterieEntity ersenBatterieEntity){
        ersenBatterieEntity.setId(id);
        return ersenBatterieRepository.saveAndFlush(ersenBatterieEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteBatterie(@PathVariable String id){
        ersenBatterieRepository.deleteById(id);
        return "ok";
    }
}
