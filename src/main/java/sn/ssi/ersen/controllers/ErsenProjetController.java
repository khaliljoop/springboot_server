package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenProjetRepository;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleProjet;
import sn.ssi.ersen.entity.ErsenProjetEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/projet")
@CrossOrigin("*")
public class ErsenProjetController {
    private final ErsenProjetRepository ersenProjetRepository;
    private final RequeteMapper requeteMapper;

    public ErsenProjetController(ErsenProjetRepository ersenProjetRepository, RequeteMapper requeteMapper) {
        this.ersenProjetRepository = ersenProjetRepository;
        this.requeteMapper = requeteMapper;
    }

    @GetMapping("/all")
    public List<ErsenProjetEntity> getProjet(){
        return ersenProjetRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenProjetEntity getProjetById(@PathVariable String id){
        return ersenProjetRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public List<ErsenProjetEntity> addProjet(@RequestBody ErsenProjetEntity ersenProjetEntity){
        ersenProjetRepository.saveAndFlush(ersenProjetEntity);
        return getProjet();
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public List<ErsenProjetEntity> updateProjet(@PathVariable String id, @RequestBody ErsenProjetEntity ersenProjetEntity){
        ersenProjetEntity.setId(id);
        ersenProjetRepository.saveAndFlush(ersenProjetEntity);
        return getProjet();
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public List<ErsenProjetEntity> deleteAnnee(@PathVariable String id){
        ersenProjetRepository.deleteById(id);
        return getProjet();
    }

    @GetMapping(value = "/centrale/dto")
    List<ProjectionCentraleProjet> getProjetCentraleDto(){
        return requeteMapper.projetProjectionProjetListe(ersenProjetRepository.findAll());
    }
}
