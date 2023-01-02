package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenMenuRepository;
import sn.ssi.ersen.entity.ErsenMenuEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/menu")
@CrossOrigin("*")
public class ErsenMenuController {
    private final ErsenMenuRepository ersenMenuRepository;

    public ErsenMenuController(ErsenMenuRepository ersenMenuRepository) {
        this.ersenMenuRepository = ersenMenuRepository;
    }


    @GetMapping("/all")
    public List<ErsenMenuEntity> getMenu(){
        return ersenMenuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenMenuEntity getMenuById(@PathVariable String id){
        return ersenMenuRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenMenuEntity addMenu(@RequestBody ErsenMenuEntity ersenMenuEntity){
        return ersenMenuRepository.saveAndFlush(ersenMenuEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenMenuEntity updateMenu( @PathVariable String id,@RequestBody ErsenMenuEntity ersenMenuEntity){
        ersenMenuEntity.setId(id);
        return ersenMenuRepository.saveAndFlush(ersenMenuEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteMenu(@PathVariable String id){
        ersenMenuRepository.deleteById(id);
        return "ok";
    }
}
