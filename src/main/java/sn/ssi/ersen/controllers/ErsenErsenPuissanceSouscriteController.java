package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenPuissanceSouscriteRepository;
import sn.ssi.ersen.entity.ErsenPuissanceSouscriteEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/puissanceSouscrite")
@CrossOrigin("*")
public class ErsenErsenPuissanceSouscriteController {
    private final ErsenPuissanceSouscriteRepository ersenPuissanceSouscriteRepository;

    public ErsenErsenPuissanceSouscriteController(ErsenPuissanceSouscriteRepository ersenPuissanceSouscriteRepository) {
        this.ersenPuissanceSouscriteRepository = ersenPuissanceSouscriteRepository;
    }
    @GetMapping("/all")
    public List<ErsenPuissanceSouscriteEntity> getPuissanceSouscrite(){
        return ersenPuissanceSouscriteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenPuissanceSouscriteEntity getPuissanceSouscriteById(@PathVariable String id){
        return ersenPuissanceSouscriteRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenPuissanceSouscriteEntity addPuissanceSouscrite(@RequestBody ErsenPuissanceSouscriteEntity ersenPuissanceSouscriteEntity){
        return ersenPuissanceSouscriteRepository.saveAndFlush(ersenPuissanceSouscriteEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenPuissanceSouscriteEntity updatePuissanceSouscrite(@PathVariable String id,@RequestBody ErsenPuissanceSouscriteEntity ersenPuissanceSouscriteEntity){
        ersenPuissanceSouscriteEntity.setId(id);
        return ersenPuissanceSouscriteRepository.saveAndFlush(ersenPuissanceSouscriteEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deletePuissanceSouscrite(@PathVariable String id){
        ersenPuissanceSouscriteRepository.deleteById(id);
        return "ok";
    }
}
