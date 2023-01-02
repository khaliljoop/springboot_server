package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenDepartementRepository;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleDepartement;
import sn.ssi.ersen.entity.ErsenDepartementEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/departement")
public class ErsenDepartementController {
    private final ErsenDepartementRepository ersenDepartementRepository;
    private final RequeteMapper requeteMapper;

    public ErsenDepartementController(ErsenDepartementRepository ersenDepartementRepository, RequeteMapper requeteMapper) {
        this.ersenDepartementRepository = ersenDepartementRepository;
        this.requeteMapper = requeteMapper;
    }

    @GetMapping("/all")
    public List<ErsenDepartementEntity> getCommune(){
        return ersenDepartementRepository.findAll();
    }

    @GetMapping("/all/dto")
    public List<ProjectionCentraleDepartement> getDepartementDto(){
        return requeteMapper.departementEntityListToProjectionCentraleDepartementList(
                ersenDepartementRepository.getDepartementNomRegion()
        );
    }
   /* @GetMapping("/dto")
    public List<ProjectionCentraleDepartement> getDepartementDtoNomRegion(){
        return requeteMapper.departementEntityListToProjectionCentraleDepartementList(
                ersenDepartementRepository.getDepartementNomRegion()
        );
    }*/

    @PostMapping("/add")
    @Transactional
    public  ErsenDepartementEntity addCommune(@RequestBody ErsenDepartementEntity ersenDepartementEntity){
        return ersenDepartementRepository.save(ersenDepartementEntity);
    }

    @PutMapping("/edit")
    @Transactional
    public ErsenDepartementEntity updateCommune(@PathVariable String id, @RequestBody ErsenDepartementEntity ersenDepartementEntity){
        ersenDepartementEntity.setId(id);
        return ersenDepartementRepository.save(ersenDepartementEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteCommune(@PathVariable String id){
        ersenDepartementRepository.deleteById(id);
    }
}
