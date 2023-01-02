package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenBailleurRepository;
import sn.ssi.ersen.entity.ErsenAnneeEntity;
import sn.ssi.ersen.entity.ErsenBailleurEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/bailleurs")
@CrossOrigin("*")
public class ErsenBailleurController {
    private final ErsenBailleurRepository ersenBailleurRepository;

    public ErsenBailleurController(ErsenBailleurRepository ersenBailleurRepository) {
        this.ersenBailleurRepository = ersenBailleurRepository;
    }


    @GetMapping("/all")
    public List<ErsenBailleurEntity> getBailleur(){
        return ersenBailleurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenBailleurEntity getBailleurById(@PathVariable String id){
        return ersenBailleurRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenBailleurEntity addBailleur(@RequestBody ErsenBailleurEntity ersenBailleurEntity){
        return ersenBailleurRepository.saveAndFlush(ersenBailleurEntity);
    }

    @PutMapping("/edit")
    @Transactional
    public ErsenBailleurEntity updateBailleur(@PathVariable String id,@RequestBody ErsenBailleurEntity ersenBailleurEntity){
        ersenBailleurEntity.setId(id);
        return ersenBailleurRepository.saveAndFlush(ersenBailleurEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteBailleur(@PathVariable String id){
        ersenBailleurRepository.deleteById(id);
        return "ok";
    }
}
