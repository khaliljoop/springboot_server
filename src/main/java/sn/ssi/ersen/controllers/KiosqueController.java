package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.KiosqueRepository;
import sn.ssi.ersen.entity.equipement.KiosquesEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/kiosque")
@CrossOrigin("*")
public class KiosqueController {
    private final KiosqueRepository kiosqueRepository;

    public KiosqueController(KiosqueRepository kiosqueRepository) {
        this.kiosqueRepository = kiosqueRepository;
    }

    @GetMapping("/all")
    public List<KiosquesEntity> getKiosques(){
        return kiosqueRepository.findAll();
    }

    @GetMapping("/{id}")
    public KiosquesEntity getKiosquesById(@PathVariable String id){
        return kiosqueRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  KiosquesEntity addKiosques(@RequestBody KiosquesEntity kiosquesEntity){
        return kiosqueRepository.saveAndFlush(kiosquesEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public KiosquesEntity updateKiosques( @PathVariable String id,@RequestBody KiosquesEntity kiosquesEntity){
        kiosquesEntity.setId(id);
        return kiosqueRepository.saveAndFlush(kiosquesEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteKiosques(@PathVariable String id){
        kiosqueRepository.deleteById(id);
    }
}
