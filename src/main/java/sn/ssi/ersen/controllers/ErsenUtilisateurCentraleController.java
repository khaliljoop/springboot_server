package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenConductExecutionNbRepository;
import sn.ssi.ersen.dao.ErsenUtilisateurCentralRepository;
import sn.ssi.ersen.entity.ErsenUtilisateurCentraleEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "utilisateurcentrale")
public class ErsenUtilisateurCentraleController {
    private ErsenUtilisateurCentralRepository ersenUtilisateurCentralRepository;
    private final ErsenConductExecutionNbRepository ersenConductExecutionNbRepository;
    public ErsenUtilisateurCentraleController(ErsenUtilisateurCentralRepository ersenUtilisateurCentralRepository, ErsenConductExecutionNbRepository ersenConductExecutionNbRepository) {
        this.ersenUtilisateurCentralRepository = ersenUtilisateurCentralRepository;
        this.ersenConductExecutionNbRepository = ersenConductExecutionNbRepository;
    }

    @GetMapping("/all")
    public List<ErsenUtilisateurCentraleEntity> getCentrale(){
        return ersenUtilisateurCentralRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenUtilisateurCentraleEntity getCentrale(@PathVariable String id){
        return ersenUtilisateurCentralRepository.findById(id).get();
    }

    @PostMapping("/add/")
    @Transactional
    public  Boolean addUserCentrale(@RequestBody ErsenUtilisateurCentraleEntity utilisateurCentrale){
        ersenUtilisateurCentralRepository.save(
                utilisateurCentrale
        );
        return true;
    }


    @PutMapping("/edit/")
    @Transactional
    public ErsenUtilisateurCentraleEntity updateCentrale(@RequestBody ErsenUtilisateurCentraleEntity uc){
        ersenUtilisateurCentralRepository.updateUtilisateurByCentrale(uc.getUtilisateur(), uc.getCentrale());
        if(uc.getCentrale()!=null)
            ersenConductExecutionNbRepository.updateConductExecByUsr(uc.getCentrale(),uc.getUtilisateur());
        return ersenUtilisateurCentralRepository.getErsenUtilisateurCentraleByCentrale(uc.getCentrale());
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteCentrale(@PathVariable("id") String id){
        ersenUtilisateurCentralRepository.deleteById(id);
    }

}
