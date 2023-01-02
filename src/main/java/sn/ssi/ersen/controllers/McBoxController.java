package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.McBoxRepository;
import sn.ssi.ersen.entity.equipement.McBoxEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/mcbox")
@CrossOrigin("*")
public class McBoxController {
    private final McBoxRepository mcBoxRepository;

    public McBoxController(McBoxRepository mcBoxRepository) {
        this.mcBoxRepository = mcBoxRepository;
    }

    @GetMapping("/all")
    public List<McBoxEntity> getMcBox(){
        return mcBoxRepository.findAll();
    }

    @GetMapping("/{id}")
    public McBoxEntity getMcBoxById(@PathVariable String id){
        return mcBoxRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  McBoxEntity addMcBox(@RequestBody McBoxEntity mcBoxEntity){
        return mcBoxRepository.saveAndFlush(mcBoxEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public McBoxEntity updateMcBox(@PathVariable String id, @RequestBody McBoxEntity mcBoxEntity){
        mcBoxEntity.setId(id);
        return mcBoxRepository.saveAndFlush(mcBoxEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteMcBox(@PathVariable String id){
        mcBoxRepository.deleteById(id);
    }
}
