package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.BatterieRepository;
import sn.ssi.ersen.entity.equipement.BatterieEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/batteries")
@CrossOrigin("*")
public class BatterieController {
    private final BatterieRepository batterieRepository;

    public BatterieController(BatterieRepository batterieRepository) {
        this.batterieRepository = batterieRepository;
    }

    @GetMapping(value = "/all")
    public List<BatterieEntity> getBatterie(){
        return batterieRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public BatterieEntity getBatterieById(@PathVariable String id){
        return batterieRepository.findById(id).get();
    }

    @PostMapping(value = "/add")
    @Transactional
    public  BatterieEntity addBatterie(@RequestBody BatterieEntity batterieEntity){
        return batterieRepository.saveAndFlush(batterieEntity);
    }

    @PutMapping(value = "/edit/{id}")
    @Transactional
    public BatterieEntity updateBatterie( @PathVariable String id,@RequestBody BatterieEntity batterieEntity){
        batterieEntity.setId(id);
        return batterieRepository.saveAndFlush(batterieEntity);
    }


    @DeleteMapping(value = "/delete/{id}")
    @Transactional
    public void deleteBatterie(@PathVariable String id){
        batterieRepository.deleteById(id);
    }
}
