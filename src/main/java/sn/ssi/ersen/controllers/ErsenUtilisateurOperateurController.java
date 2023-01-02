package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenUtilisateurOperateurRepository;
import sn.ssi.ersen.entity.ErsenUtilisateurOperateurEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ErsenUtilisateurOperateurController {

    private ErsenUtilisateurOperateurRepository ersenUtilisateurOperateurRepository;

    public ErsenUtilisateurOperateurController(ErsenUtilisateurOperateurRepository ersenUtilisateurOperateurRepository) {
        this.ersenUtilisateurOperateurRepository = ersenUtilisateurOperateurRepository;
    }

    @GetMapping("/utilisateuroperateurs")
    public List<ErsenUtilisateurOperateurEntity> getUtilisateurOperateur(){
        return ersenUtilisateurOperateurRepository.findAll();
    }

    @GetMapping("/utilisateuroperateurs/{id}")
    public ErsenUtilisateurOperateurEntity getUtilisateurOperateur(@PathVariable String id){
        return ersenUtilisateurOperateurRepository.findById(id).get();
    }

    @PostMapping("/utilisateuroperateurs/addnewutilisateuroperateur")
    @Transactional
    public  ErsenUtilisateurOperateurEntity addUtilisateurOperateur(@RequestBody ErsenUtilisateurOperateurEntity ersenUtilisateurOperateurEntity){
        return ersenUtilisateurOperateurRepository.saveAndFlush(ersenUtilisateurOperateurEntity);
    }

    @PutMapping("/utilisateuroperateurs/edit/{id}")
    @Transactional
    public ErsenUtilisateurOperateurEntity updateUtilisateurOperateur(@PathVariable String id,@RequestBody ErsenUtilisateurOperateurEntity ersenUtilisateurOperateurEntity){
        ersenUtilisateurOperateurEntity.setId(id);
        return ersenUtilisateurOperateurRepository.saveAndFlush(ersenUtilisateurOperateurEntity);
    }

    @DeleteMapping("/utilisateuroperateurs/delete/{id}")
    @Transactional
    public void deleteUtilisateurOperateur(@PathVariable("id") String id){
        ersenUtilisateurOperateurRepository.deleteById(id);
    }
}
