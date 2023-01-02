package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenTypeCentraleRepository;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleTypeCentrale;
import sn.ssi.ersen.entity.ErsenTypecentraleEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.transaction.Transactional;
import java.util.List;
@RestController
@RequestMapping(value = "/typecentrale")
@CrossOrigin("*")
public class ErsenTypeCentraleController {
    private final ErsenTypeCentraleRepository ersenTypeCentraleRepository;
    private final RequeteMapper requeteMapper;

    public ErsenTypeCentraleController(ErsenTypeCentraleRepository ersenTypeCentraleRepository, RequeteMapper requeteMapper) {
        this.ersenTypeCentraleRepository = ersenTypeCentraleRepository;
        this.requeteMapper = requeteMapper;
    }

    @GetMapping("/all")
    public List<ErsenTypecentraleEntity> getTypeCentrale(){
        return ersenTypeCentraleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenTypecentraleEntity getTypeCentraleById(@PathVariable String id){
        return ersenTypeCentraleRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenTypecentraleEntity addTypeCentrale(@RequestBody ErsenTypecentraleEntity ersenTypecentraleEntity){
        return ersenTypeCentraleRepository.saveAndFlush(ersenTypecentraleEntity);
    }
    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenTypecentraleEntity updateTypeCentrale(@PathVariable String id,@RequestBody ErsenTypecentraleEntity ersenTypecentraleEntity){
        ersenTypecentraleEntity.setId(id);
        return ersenTypeCentraleRepository.saveAndFlush(ersenTypecentraleEntity);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteTypeCentrale(@PathVariable String id){
        ersenTypeCentraleRepository.deleteById(id);
        return "ok";
    }

    @GetMapping(value = "/projectionnom/dto")
    public List<ProjectionCentraleTypeCentrale> getProjectionCentraleTypeCentrale(){
        return requeteMapper.projectionToErsenTypeCentraleList(ersenTypeCentraleRepository.getAllTypeCentraleListe());
    }
}
