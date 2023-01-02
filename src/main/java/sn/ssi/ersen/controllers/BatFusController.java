package sn.ssi.ersen.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.BatFusRepository;
import sn.ssi.ersen.entity.equipement.BatFusEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/batfus")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BatFusController {
    private final BatFusRepository batFusRepository;

    @GetMapping(value = "/all")
    public List<BatFusEntity> getBatFus(){
        return batFusRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public BatFusEntity getBatFusById(@PathVariable String id){
        return batFusRepository.findById(id).get();
    }

    @PostMapping(value = "/add")
    public  BatFusEntity addBatFus(@RequestBody BatFusEntity batFusEntity){
        return batFusRepository.saveAndFlush(batFusEntity);
    }

    @PutMapping("/edit/{id}")
    public BatFusEntity updateBatFus( @PathVariable String id,@RequestBody BatFusEntity batFusEntity){
        batFusEntity.setId(id);
        return batFusRepository.saveAndFlush(batFusEntity);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteBatFus(@PathVariable String id){
        batFusRepository.deleteById(id);
    }
}
