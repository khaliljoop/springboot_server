package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenVillageQuartierRepository;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleProjet;
import sn.ssi.ersen.dto.projectioncentraledto.ProjectionCentraleVillage;
import sn.ssi.ersen.entity.ErsenVillagequartierEntity;
import sn.ssi.ersen.mappers.RequeteMapper;

import java.util.List;


@RestController
@RequestMapping(value = "/villagequartier")
@CrossOrigin("*")
public class ErsenVillageQuartierController {
    private final ErsenVillageQuartierRepository ersenVillageQuartierRepository;
    private final RequeteMapper requeteMapper;

    public ErsenVillageQuartierController(ErsenVillageQuartierRepository ersenVillageQuartierRepository, RequeteMapper requeteMapper) {
        this.ersenVillageQuartierRepository = ersenVillageQuartierRepository;
        this.requeteMapper = requeteMapper;
    }

    @GetMapping("/all")
    public List<ErsenVillagequartierEntity> getVillagequartier(){
        return ersenVillageQuartierRepository.findAll();
    }

    @GetMapping("/all/dto")
        public List<ProjectionCentraleVillage> getVillagequartierDTO(){
        return requeteMapper.projetToProjectionCentraleVillageList(ersenVillageQuartierRepository.findAll());
    }
    @GetMapping("/{id}")
    public ErsenVillagequartierEntity getVillagequartierById(@PathVariable String id){
        return ersenVillageQuartierRepository.findById(id).get();
    }

    @PostMapping("/add")
    public  ErsenVillagequartierEntity addVillagequartier(@RequestBody ErsenVillagequartierEntity ersenVillagequartierEntity){
        return ersenVillageQuartierRepository.saveAndFlush(ersenVillagequartierEntity);
    }

    @PutMapping("/edit/{id}")
    public ErsenVillagequartierEntity updateAnnee( @PathVariable String id,@RequestBody ErsenVillagequartierEntity ersenVillagequartierEntity){
        ersenVillagequartierEntity.setId(id);
        return ersenVillageQuartierRepository.saveAndFlush(ersenVillagequartierEntity);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteAnnee(@PathVariable String id){
        ersenVillageQuartierRepository.deleteById(id);
    }

    @GetMapping(value = "/centrale/{libelle}")
    public List<ProjectionCentraleVillage> getVillageQuartierDeCentrale(@PathVariable(name = "libelle") String libelle){
        return requeteMapper.projetToProjectionCentraleVillageList(ersenVillageQuartierRepository.getAllVillageQuartier(libelle));
    }
}
