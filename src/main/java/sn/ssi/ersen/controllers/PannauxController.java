package sn.ssi.ersen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.PannauxRepository;
import sn.ssi.ersen.entity.equipement.PannauxEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/panneaux")
@CrossOrigin("*")
@RequiredArgsConstructor
public class PannauxController {
    private final PannauxRepository pannauxRepository;

    @GetMapping("/all")
    public List<PannauxEntity> getPannaux(){
        return pannauxRepository.findAll();
    }

    @GetMapping("/{id}")
    public PannauxEntity getPannauxById(@PathVariable String id){
        return pannauxRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  PannauxEntity addPannaux(@RequestBody PannauxEntity pannauxEntity){
        return pannauxRepository.saveAndFlush(pannauxEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public PannauxEntity updatePannaux(@PathVariable String id, @RequestBody PannauxEntity pannauxEntity){
        pannauxEntity.setId(id);
        return pannauxRepository.saveAndFlush(pannauxEntity);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deletePannaux(@PathVariable String id){
        pannauxRepository.deleteById(id);
    }
}
