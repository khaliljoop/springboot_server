package sn.ssi.ersen.controllers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenCategorieRepository;
import sn.ssi.ersen.dto.projectionabonnedto.ProjectionCategorieNom;
import sn.ssi.ersen.entity.ErsenCategorieEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/categorie",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
@CrossOrigin("*")
public class ErsenCategorieController {
    private final ErsenCategorieRepository ersenCategorieRepository;
    private final RequeteMapper requeteMapper;

    public ErsenCategorieController(ErsenCategorieRepository ersenCategorieRepository, RequeteMapper requeteMapper) {
        this.ersenCategorieRepository = ersenCategorieRepository;
        this.requeteMapper = requeteMapper;
    }

    @GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public List<ErsenCategorieEntity> getCategorie(){
        return ersenCategorieRepository.findAll();
    }

    @GetMapping("/all/dto")
    public List<ProjectionCategorieNom> getNomCategorie(){
        return requeteMapper.categorieEntityToProjectioCategorieDtoNomList(ersenCategorieRepository.findAll());
    }

    @GetMapping("/{id}")
    public ErsenCategorieEntity getCategorieById(@PathVariable String id){
        return ersenCategorieRepository.findById(id).get();
    }

    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    @Transactional
    public List<ErsenCategorieEntity> addCategorie(@RequestBody ErsenCategorieEntity ersenCategorieEntity){
        ersenCategorieRepository.saveAndFlush(ersenCategorieEntity);
        return getCategorie();
    }

    @PutMapping(value = "/edit/{id}",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    @Transactional
    public List<ErsenCategorieEntity> updateCategorie( @PathVariable String id,@RequestBody ErsenCategorieEntity ersenCategorieEntity){
        ersenCategorieEntity.setId(id);
        ersenCategorieRepository.saveAndFlush(ersenCategorieEntity);
        return  getCategorie();
    }


    @DeleteMapping("/delete/{id}")
    public List<ErsenCategorieEntity> deleteCategorie(@PathVariable String id){
        ersenCategorieRepository.deleteById(id);
        return getCategorie();
    }
}
