package sn.ssi.ersen.controllers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenTypecategorieRepository;
import sn.ssi.ersen.dto.projectionabonnedto.ProjectionTypeCategorieNom;
import sn.ssi.ersen.entity.ErsenTypecategorieEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import java.util.List;


@RestController
@RequestMapping(value = "/typecategorie",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@CrossOrigin("*")
public class ErsenTypecategorieController {

    private final ErsenTypecategorieRepository ersenTypecategorieRepository;
    private final RequeteMapper requeteMapper;

    public ErsenTypecategorieController(ErsenTypecategorieRepository ersenTypecategorieRepository, RequeteMapper requeteMapper) {
        this.ersenTypecategorieRepository = ersenTypecategorieRepository;
        this.requeteMapper = requeteMapper;
    }

    @GetMapping("/all")
    public List<ErsenTypecategorieEntity> getTypeCategorie(){
        return ersenTypecategorieRepository.findAll();
    }
  /*  @GetMapping("/all/dto")
    public List<ProjectionTypeCategorieNom> getNomTypecategorie(){
        return requeteMapper.typeCategorieEntityToProjectioCategorieDtoNomList(ersenTypecategorieRepository.findAll());
    }*/
   @GetMapping("/all/dto")
    public List<ProjectionTypeCategorieNom> getNomTypecategorie(){
        return requeteMapper.typeCategorieEntityToProjectioCategorieDtoNomList(ersenTypecategorieRepository.getTypeCategorieDTO());
    }
    @GetMapping("/{id}")
    public ErsenTypecategorieEntity getTypeCategorieById(@PathVariable String id){
        return ersenTypecategorieRepository.findById(id).get();
    }

    @GetMapping(value = "/all/{id}")
    public List<ErsenTypecategorieEntity> getTypeCategorieByCategorie(@PathVariable String id){
        return ersenTypecategorieRepository.getErsenTypecategorieEntitiesByCategorie(id);
    }

    @PostMapping("/add")
    public  List<ErsenTypecategorieEntity> addTypeCategorie(@RequestBody ErsenTypecategorieEntity ersenTypecategorieEntity){
        ersenTypecategorieRepository.save(ersenTypecategorieEntity);
        return getTypeCategorie();
    }

    @PutMapping("/edit/{id}")
    public List<ErsenTypecategorieEntity> updateTypeCategorie(@PathVariable String id,@RequestBody ErsenTypecategorieEntity ersenTypecategorieEntity){
        ersenTypecategorieEntity.setId(id);
        ersenTypecategorieRepository.saveAndFlush(ersenTypecategorieEntity);
        return getTypeCategorie();
    }

    @DeleteMapping("/delete/{id}")
    public List<ErsenTypecategorieEntity> deleteTypeCategorie(@PathVariable String id){
        ersenTypecategorieRepository.deleteById(id);
        return getTypeCategorie();
    }

}
