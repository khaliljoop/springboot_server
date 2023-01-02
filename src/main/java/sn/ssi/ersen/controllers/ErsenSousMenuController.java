package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenSousMenuRepository;
import sn.ssi.ersen.entity.ErsenSousMenuEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/sousmenu")
@CrossOrigin("*")
public class ErsenSousMenuController {
   private final ErsenSousMenuRepository ersenSousMenuRepository;

    public ErsenSousMenuController(ErsenSousMenuRepository ersenSousMenuRepository) {
        this.ersenSousMenuRepository = ersenSousMenuRepository;
    }

    @GetMapping("/all")
    public List<ErsenSousMenuEntity> getSousMenu(){
        return ersenSousMenuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenSousMenuEntity getSousMenuById(@PathVariable String id){
        return ersenSousMenuRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenSousMenuEntity addSousMenu(@RequestBody ErsenSousMenuEntity ersenMenuEntity){
        return ersenSousMenuRepository.saveAndFlush(ersenMenuEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenSousMenuEntity updateSousMenu( @PathVariable String id,@RequestBody ErsenSousMenuEntity ersenMenuEntity){
        ersenMenuEntity.setId(id);
        return ersenSousMenuRepository.saveAndFlush(ersenMenuEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteSousMenu(@PathVariable String id){
        ersenSousMenuRepository.deleteById(id);
        return "ok";
    }
}
