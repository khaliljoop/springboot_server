package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenCommuneRepository;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleCommune;
import sn.ssi.ersen.entity.ErsenCommuneEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/commune")
@CrossOrigin("*")
public class ErsenCommuneController {
    private final ErsenCommuneRepository ersenCommuneRepository;
    private final RequeteMapper requeteMapper;

    public ErsenCommuneController(ErsenCommuneRepository ersenCommuneRepository, RequeteMapper requeteMapper) {
        this.ersenCommuneRepository = ersenCommuneRepository;
        this.requeteMapper = requeteMapper;
    }

    @GetMapping("/all")
    public List<ErsenCommuneEntity> getCommune(){
        return ersenCommuneRepository.findAll();
    }

    @GetMapping("/all/dto")
    public List<ProjectionCentraleCommune> getCommuneDto(){
        return requeteMapper.communeEntityListToProjectionCentraleCommunetList(ersenCommuneRepository.getErsenCommuneEntityNomDepartement());
    }

    @GetMapping("/{id}")
    public ErsenCommuneEntity getCommuneById(@PathVariable String id){
        return ersenCommuneRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenCommuneEntity addCommune(@RequestBody ErsenCommuneEntity ersenCommuneEntity){
        return ersenCommuneRepository.save(ersenCommuneEntity);
    }

    @PutMapping("/edit")
    @Transactional
    public ErsenCommuneEntity updateCommune(@PathVariable String id, @RequestBody ErsenCommuneEntity ersenCommuneEntity){
        ersenCommuneEntity.setId(id);
        return ersenCommuneRepository.save(ersenCommuneEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteCommune(@PathVariable String id){
        ersenCommuneRepository.deleteById(id);
    }
}
