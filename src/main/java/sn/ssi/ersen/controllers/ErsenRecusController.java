package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenDetailRecusRepository;
import sn.ssi.ersen.dao.ErsenRecusRepository;
import sn.ssi.ersen.entity.ErsenRecusEntity;
import sn.ssi.ersen.entity.entitieMobile.RecuFromMobile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/recus")
@CrossOrigin("*")
public class ErsenRecusController {
//779098733
    private final ErsenRecusRepository ersenRecusRepository;
    private final ErsenDetailRecusRepository ersenDetailRecusRepository;

    public ErsenRecusController(ErsenRecusRepository ersenRecusRepository, ErsenDetailRecusRepository ersenDetailRecusRepository) {
        this.ersenRecusRepository = ersenRecusRepository;
        this.ersenDetailRecusRepository = ersenDetailRecusRepository;
    }

    @GetMapping(value = {   "/liste/{centrales}"})
    public List<RecuFromMobile> getRecusByAbonne(@PathVariable List<String> centrales){
        List<RecuFromMobile> recuFromMobiles=new ArrayList<>();
        List<ErsenRecusEntity> recus = ersenRecusRepository.getErsenRecusEntitiesByAbonne(centrales);

            for (ErsenRecusEntity r : recus)
                recuFromMobiles.add(new RecuFromMobile(r,ersenRecusRepository.getErsenDetailRecusByRecu(r.getId())));
        System.out.println("liste system plus "+recuFromMobiles);
        return recuFromMobiles;
    }

    @GetMapping(value = {"/list/{idRecus}"})
    public List<ErsenRecusEntity> getRecusByIds(@PathVariable List<String> idRecus){
        return ersenRecusRepository.findAllById(idRecus);
    }
    @GetMapping(value = {   "/list/new/{ids}"})
    public List<RecuFromMobile> getNewRecus(@PathVariable List<String> ids){
        List<RecuFromMobile>recuFromMobiles= new ArrayList<>();
        List<ErsenRecusEntity> recusEntityList= ersenRecusRepository.getErsenNewRecus(ids);
        if(!recusEntityList.isEmpty()){
            for(ErsenRecusEntity r : recusEntityList){
                recuFromMobiles.add(new RecuFromMobile(r,ersenRecusRepository.getErsenDetailRecusByRecu(r.getId())));
            }
        }
        System.out.println("system plus "+recuFromMobiles);
        return recuFromMobiles;
    }
    @GetMapping(value = {   "/list/notValidated/{centrales}"})
    public List<RecuFromMobile> getRecusByIsSync(@PathVariable List<String> centrales){
        List<RecuFromMobile>recuFromMobiles= new ArrayList<>();
        List<ErsenRecusEntity> recusEntityList= ersenRecusRepository.getErsenRecusEntitiesByIsSync(centrales);
        if(!recusEntityList.isEmpty()){
            for(ErsenRecusEntity r : recusEntityList){
                recuFromMobiles.add(new RecuFromMobile(r,ersenRecusRepository.getErsenDetailRecusByRecu(r.getId())));
            }
        }
        System.out.println("system plus "+recuFromMobiles);
        return recuFromMobiles;
    }

    @PostMapping(value = {"/all",})
    public List<RecuFromMobile> getRecus(@RequestBody List<String> centrales){
        List<RecuFromMobile> recuFromMobiles=new ArrayList<>();
        List<ErsenRecusEntity> lesrecus = new ArrayList<>();
        for(String idcentrale : centrales){
            lesrecus.addAll(ersenRecusRepository.getErsenRecusEntitiesByCentrale(idcentrale)) ;
        }
        for (ErsenRecusEntity r : lesrecus)
            recuFromMobiles.add(new RecuFromMobile(r,ersenRecusRepository.getErsenDetailRecusByRecu(r.getId())));
        return recuFromMobiles;
    }
    @GetMapping("/{id}")
    public Optional<ErsenRecusEntity> getRecusById(@PathVariable String id){
        return ersenRecusRepository.findById(id);
    }

    @PostMapping("/add")
    public  void addRecus(@RequestBody ErsenRecusEntity ersenRecusEntity){
        ersenRecusRepository.save(ersenRecusEntity);
    }

    @PostMapping("/addAll")
    public  void addAllRecus(@RequestBody List<ErsenRecusEntity> recus){
        ersenRecusRepository.saveAll(recus);
    }

    @PutMapping("/edit/{id}")
    public void updateRecus(@PathVariable String id, @RequestBody ErsenRecusEntity ersenRecusEntity){
        ersenRecusEntity.setId(id);
        ersenRecusRepository.save(ersenRecusEntity);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteRecus(@PathVariable String id){
        ersenRecusRepository.deleteById(id);
    }

    @PostMapping("/sync")
    public  void recuSync(@RequestBody List<RecuFromMobile> recuFromMobiles){
        ErsenRecusEntity recu;
        for( RecuFromMobile r : recuFromMobiles) {
            recu = r.getRecu();
            if(recu.getIsSync()==0)
                recu.setIsSync(1);
            if(recu.getIsSync()==2)
                recu.setIsSync(3);
            System.out.println("##################################################");
            System.out.println("recuu "+recu.getId()+" vs rid "+r.getRecu().getId());
            ersenRecusRepository.save(recu);
            ersenDetailRecusRepository.saveAll(r.getDetailrecus());
        }
    }
}
