package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ReseauBTRepository;
import sn.ssi.ersen.entity.equipement.ReseauBTEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/reseaubt")
@CrossOrigin("*")
public class ReseauBTController {
    private final ReseauBTRepository reseauBTRepository;

    public ReseauBTController(ReseauBTRepository reseauBTRepository) {
        this.reseauBTRepository = reseauBTRepository;
    }

    @GetMapping("/all")
    public List<ReseauBTEntity> getReseauBT(){
        return reseauBTRepository.findAll();
    }

    @GetMapping("/{id}")
    public ReseauBTEntity getReseauBTById(@PathVariable String id){
        return reseauBTRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ReseauBTEntity addReseauBT(@RequestBody ReseauBTEntity reseauBTEntity){
        return reseauBTRepository.saveAndFlush(reseauBTEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ReseauBTEntity updateReseauBT(@PathVariable String id, @RequestBody ReseauBTEntity reseauBTEntity){
        reseauBTEntity.setId(id);
        return reseauBTRepository.saveAndFlush(reseauBTEntity);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteReseauBT(@PathVariable String id){
        reseauBTRepository.deleteById(id);
    }
}
