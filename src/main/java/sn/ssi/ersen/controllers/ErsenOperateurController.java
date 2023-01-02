package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenOperateurRepository;
import sn.ssi.ersen.entity.ErsenOperateurEntity;
import sn.ssi.ersen.entity.equipement.McBoxEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/operateur")
public class ErsenOperateurController {
    private final ErsenOperateurRepository ersenOperateurRepository;

    public ErsenOperateurController(ErsenOperateurRepository ersenOperateurRepository) {
        this.ersenOperateurRepository = ersenOperateurRepository;
    }

    @GetMapping("/all")
    public List<ErsenOperateurEntity> getOperateur(){
        return ersenOperateurRepository.findAll();
    }

   /* @GetMapping("/{id}")
    public ErsenOperateurEntity getMcBoxById(@PathVariable String id){
        return ersenOperateurRepository.findById(id).get();
    }*/

    @PostMapping("/add")
    @Transactional
    public  ErsenOperateurEntity addOperateur(@RequestBody ErsenOperateurEntity ersenOperateurEntity){
        return ersenOperateurRepository.saveAndFlush(ersenOperateurEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenOperateurEntity updateOperateur(@PathVariable String id, @RequestBody ErsenOperateurEntity ersenOperateurEntity){
        ersenOperateurEntity.setId(id);
        return ersenOperateurRepository.saveAndFlush(ersenOperateurEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteOperateur(@PathVariable String id){
        ersenOperateurRepository.deleteById(id);
    }
}
