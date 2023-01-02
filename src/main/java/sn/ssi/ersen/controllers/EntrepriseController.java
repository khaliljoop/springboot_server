package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.EntrepriseRepository;
import sn.ssi.ersen.entity.ErsenEntrepriseEntity;
import sn.ssi.ersen.entity.UtilisateurEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/entreprise")
@CrossOrigin("*")

public class EntrepriseController {
   private final EntrepriseRepository entrepriseRepository;

    public EntrepriseController(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @GetMapping(value = "/all")
    public List<ErsenEntrepriseEntity> getEntreprise(){
        return entrepriseRepository.getEntreprise();
    }

    @GetMapping(value = "/{id}")
    public ErsenEntrepriseEntity getEntrepriseById(@PathVariable String id){
        return entrepriseRepository.findById(id).get();
    }

    @GetMapping(value = "/operateur/{centrale}")
    public ErsenEntrepriseEntity getEntrepriseByCentrale(@PathVariable String centrale){
        return entrepriseRepository.getEntrepriseByCentrale(centrale);
    }

    @GetMapping(value = "/conducteur/{centrale}")
    public Object getConducteurByCentrale(@PathVariable String centrale){
        return entrepriseRepository.getConducteurByCentrale(centrale);
    }

    @PostMapping(value = "/add")
    @Transactional
    public  List<ErsenEntrepriseEntity> addEntreprise(@RequestBody ErsenEntrepriseEntity ersenEntrepriseEntity){
        entrepriseRepository.saveAndFlush(ersenEntrepriseEntity);
        return getEntreprise();
    }

    @PutMapping(value = "/edit/{id}")
    @Transactional
    public List<ErsenEntrepriseEntity> updateEntreprise(@PathVariable String id, @RequestBody ErsenEntrepriseEntity ersenEntrepriseEntity){
        ersenEntrepriseEntity.setId(id);
        entrepriseRepository.saveAndFlush(ersenEntrepriseEntity);
        return getEntreprise();
    }

    @DeleteMapping(value = "/delete/{id}")
    @Transactional
    public List<ErsenEntrepriseEntity> deleteEntreprise(@PathVariable String id){
        entrepriseRepository.deleteById(id);
        return getEntreprise();
    }
}
