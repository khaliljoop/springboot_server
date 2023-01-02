package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenCommuneRepository;
import sn.ssi.ersen.dao.ErsenDepartementRepository;
import sn.ssi.ersen.dao.ErsenRegionRepository;
import sn.ssi.ersen.dao.ErsenVillageQuartierRepository;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleRegion;
import sn.ssi.ersen.entity.ErsenCommuneEntity;
import sn.ssi.ersen.entity.ErsenDepartementEntity;
import sn.ssi.ersen.entity.ErsenRegionEntity;
import sn.ssi.ersen.entity.ErsenVillagequartierEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@CrossOrigin("*")
public class ErsenRegionController {
    private final ErsenRegionRepository ersenRegionRepository;
    private final ErsenDepartementRepository ersenDepartementRepository;
    private final ErsenCommuneRepository ersenCommuneRepository;
    private final RequeteMapper requeteMapper;

    private final ErsenVillageQuartierRepository ersenVillageQuartierRepository;

    public ErsenRegionController(ErsenRegionRepository ersenRegionRepository, ErsenDepartementRepository ersenDepartementRepository, ErsenCommuneRepository ersenCommuneRepository, RequeteMapper requeteMapper, ErsenVillageQuartierRepository ersenVillageQuartierRepository) {
        this.ersenRegionRepository = ersenRegionRepository;
        this.ersenDepartementRepository = ersenDepartementRepository;
        this.ersenCommuneRepository = ersenCommuneRepository;
        this.requeteMapper = requeteMapper;
        this.ersenVillageQuartierRepository = ersenVillageQuartierRepository;
    }


    @GetMapping("/region/all")
    public List<ErsenRegionEntity> getRegion(){
        return ersenRegionRepository.findAll();
    }

    @GetMapping(value = "/departements/{id}")
    public List<ErsenDepartementEntity> getDepartementByRegion(@PathVariable String id){
        return  ersenDepartementRepository.getErsenDepartementEntitiesByRegion(id);
    }

    @GetMapping(value = "/departements/all")
    public List<ErsenDepartementEntity> getDepartementByRegion(){
        return  ersenDepartementRepository.findAll();
    }

    @GetMapping(value = "/communes/{id}")
    public List<ErsenCommuneEntity> getCommuneByDepartement(@PathVariable String id){
        return  ersenCommuneRepository.getErsenCommuneEntitiesByDepartement(id);
    }

    @GetMapping(value = "/villages/dto")
    public List<ErsenVillagequartierEntity> getVillageQuartierByCommune(@PathVariable String id){
        return  ersenVillageQuartierRepository.getErsenVillagequartierEntitiesByCommune(id);
    }

    @GetMapping(value = "/villages/all")
    public List<ErsenVillagequartierEntity> getVillagesAll(){
        return  ersenVillageQuartierRepository.findAll();
    }

    @GetMapping(value = "/communes/all")
    public List<ErsenCommuneEntity> getCommuneAll() {
        return  ersenCommuneRepository.findAll();
    }

    @GetMapping("/region/{id}")
    public ErsenRegionEntity getRegionById(@PathVariable String id){
        return ersenRegionRepository.findById(id).get();
    }

    @PostMapping("/region/add")
    @Transactional
    public  ErsenRegionEntity addRegion(@RequestBody ErsenRegionEntity ersenRegionEntity){
        return ersenRegionRepository.saveAndFlush(ersenRegionEntity);
    }

    @PutMapping("/region/edit/{id}")
    @Transactional
    public ErsenRegionEntity updateRegion(@PathVariable String id,@RequestBody ErsenRegionEntity ersenRegionEntity){
        ersenRegionEntity.setId(id);
        return ersenRegionRepository.saveAndFlush(ersenRegionEntity);
    }


    @DeleteMapping("/region/delete/{id}")
    @Transactional
    public String deleteAnnee(@PathVariable String id){
        ersenRegionRepository.deleteById(id);
        return "ok";
    }

    @GetMapping(value = "/region/centrale")
    public List<ProjectionCentraleRegion> getRegionDto(){
        return requeteMapper.projetToProjectionCentraleRegionList(ersenRegionRepository.findAll());
    }

}
