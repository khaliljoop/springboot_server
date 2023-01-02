package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenPeriodiciteRepository;
import sn.ssi.ersen.entity.ErsenPeriodiciteEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/periodicite")
@CrossOrigin("*")
public class ErsenPeriodiciteController {
    private final ErsenPeriodiciteRepository ersenPeriodiciteRepository;

    public ErsenPeriodiciteController(ErsenPeriodiciteRepository ersenPeriodiciteRepository) {
        this.ersenPeriodiciteRepository = ersenPeriodiciteRepository;
    }



    @GetMapping("/all")
    public List<ErsenPeriodiciteEntity> getPeriodicite(){
        return ersenPeriodiciteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenPeriodiciteEntity getPeriodiciteById(@PathVariable String id){
        return ersenPeriodiciteRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenPeriodiciteEntity addPeriodicite(@RequestBody ErsenPeriodiciteEntity ersenPeriodiciteEntity){
        return ersenPeriodiciteRepository.saveAndFlush(ersenPeriodiciteEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenPeriodiciteEntity updatePeriodicite(@PathVariable String id,@RequestBody ErsenPeriodiciteEntity ersenPeriodiciteEntity){
        ersenPeriodiciteEntity.setId(id);
        return ersenPeriodiciteRepository.saveAndFlush(ersenPeriodiciteEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deletePeriodicite(@PathVariable String id){
        ersenPeriodiciteRepository.deleteById(id);
    }
}
