package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenPuissanceSouscriteRepository;
import sn.ssi.ersen.entity.ErsenPuissanceSouscriteEntity;

import java.util.List;

@RestController
@RequestMapping(value = "/puissanceSous")
@CrossOrigin("*")
public class ErsenPuissanceSouscriteController {
    private final ErsenPuissanceSouscriteRepository puissanceSouscriteRepository;

    public ErsenPuissanceSouscriteController(ErsenPuissanceSouscriteRepository puissanceSouscriteRepository){
        this.puissanceSouscriteRepository = puissanceSouscriteRepository;
    }

    @GetMapping(value = "/all")
    public List<ErsenPuissanceSouscriteEntity> getAllPS (){
        return puissanceSouscriteRepository.findAll();
    }

    @PostMapping(value = "/add")
    public List<ErsenPuissanceSouscriteEntity> addPS(@RequestBody ErsenPuissanceSouscriteEntity puissanceSouscriteEntity){
        puissanceSouscriteRepository.save(puissanceSouscriteEntity);
        return getAllPS();
    }

    @PutMapping(value = "/edit/{id}")
    public List<ErsenPuissanceSouscriteEntity> editPS(@PathVariable String id, @RequestBody ErsenPuissanceSouscriteEntity puissanceSouscriteEntity){
        puissanceSouscriteEntity.setId(id);
        puissanceSouscriteRepository.saveAndFlush(puissanceSouscriteEntity);
        return getAllPS();
    }

    @DeleteMapping(value = "/delete/{id}")
    public List<ErsenPuissanceSouscriteEntity> deletePS(@PathVariable String id){
        puissanceSouscriteRepository.deleteById(id);
        return getAllPS();
    }
}
