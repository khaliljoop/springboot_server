package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.AbonneRepository;
import sn.ssi.ersen.entity.ErsenAbonneEntity;
import sn.ssi.ersen.entity.ErsenRecusEntity;
import sn.ssi.ersen.entity.entitieMobile.Abonne;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AbonneController {
    private final AbonneRepository abonneRepository;


    public AbonneController(AbonneRepository abonneRepository) {
        this.abonneRepository = abonneRepository;
    }

    @GetMapping("/abonne")
    public List<ErsenAbonneEntity> getAbonneByCentrale(){
        return  abonneRepository.getAbonneByCentrale();
    }

   /* @PutMapping("/edit")
    public void updateabonne(@RequestBody List<ErsenAbonneEntity> list){
        for (ErsenAbonneEntity a: list) {
            abonneRepository.updateAbonne(a);
        }
    }*/
}
