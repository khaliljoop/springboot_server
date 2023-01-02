package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.EPRepository;
import sn.ssi.ersen.entity.equipement.EPEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/ep")
@CrossOrigin("*")
public class EPController {
    private final EPRepository epRepository;

    public EPController(EPRepository epRepository) {
        this.epRepository = epRepository;
    }

    @GetMapping("/all")
    public List<EPEntity> getEP(){
        return epRepository.findAll();
    }

    @GetMapping("/{id}")
    public EPEntity getEPById(@PathVariable String id){
        return epRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  EPEntity addEP(@RequestBody EPEntity epEntity){
        return epRepository.saveAndFlush(epEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public EPEntity updateEP( @PathVariable String id,@RequestBody EPEntity epEntity){
        epEntity.setId(id);
        return epRepository.saveAndFlush(epEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteEP(@PathVariable String id){
        epRepository.deleteById(id);
    }
}
