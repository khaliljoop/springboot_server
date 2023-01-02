package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenTypeUtilisateurRepository;
import sn.ssi.ersen.entity.ErsenTypeutilisateurEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ErsenTypeUtilisateurController {
    private final ErsenTypeUtilisateurRepository ersenTypeUtilisateurRepository;

    public ErsenTypeUtilisateurController(ErsenTypeUtilisateurRepository ersenTypeUtilisateurRepository) {
        this.ersenTypeUtilisateurRepository = ersenTypeUtilisateurRepository;
    }
    @GetMapping("/typeutilisateur/all")
    public List<ErsenTypeutilisateurEntity> getTypeutilisateur(){
        return ersenTypeUtilisateurRepository.findAll();
    }

    @GetMapping("/typeutilisateur/{id}")
    public ErsenTypeutilisateurEntity getTypeutilisateurById(@PathVariable String id){
        return ersenTypeUtilisateurRepository.findById(id).get();
    }

    @PostMapping("/typeutilisateur/add")
    @Transactional
    public  List<ErsenTypeutilisateurEntity> addTypeutilisateur(@RequestBody ErsenTypeutilisateurEntity ersenTypeutilisateurEntity){
        ersenTypeUtilisateurRepository.saveAndFlush(ersenTypeutilisateurEntity);
        return  getTypeutilisateur();
    }

    @PutMapping("/typeutilisateur/edit/{id}")
    @Transactional
    public List<ErsenTypeutilisateurEntity> updateTypeutilisateur(@PathVariable String id,@RequestBody ErsenTypeutilisateurEntity ersenTypeutilisateurEntity){
        ersenTypeutilisateurEntity.setId(id);
        ersenTypeUtilisateurRepository.saveAndFlush(ersenTypeutilisateurEntity);
        return getTypeutilisateur();
    }

    @DeleteMapping("/typeutilisateur/delete/{id}")
    @Transactional
    public List<ErsenTypeutilisateurEntity> deleteTypeutilisateur(@PathVariable("id") String id){
        ersenTypeUtilisateurRepository.deleteById(id);
        return getTypeutilisateur();
    }
}

