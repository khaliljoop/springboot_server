package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenCentraleRepository;
import sn.ssi.ersen.dao.ErsenSuiviServiceRepository;
import sn.ssi.ersen.entity.ErsenSuiviService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/suivi")
@CrossOrigin("*")
public class ErsenSuiviServiceController {

    private final ErsenSuiviServiceRepository ersenSuiviServiceRepository;
    private final ErsenCentraleRepository ersenCentraleRepository;

    public ErsenSuiviServiceController(ErsenSuiviServiceRepository ersenSuiviServiceRepository, ErsenCentraleRepository ersenCentraleRepository) {
        this.ersenSuiviServiceRepository = ersenSuiviServiceRepository;
        this.ersenCentraleRepository = ersenCentraleRepository;
    }

    @PostMapping("/add")
    public ErsenSuiviService addSuiviService(@RequestBody ErsenSuiviService suiviService){
        return ersenSuiviServiceRepository.save(suiviService);
    }


    @GetMapping(value = "/listSuivi/{idCentrales}")
    public List<ErsenSuiviService> getSuiviByCentrale(@PathVariable List<String> idCentrales){
        return ersenSuiviServiceRepository.getErsenSuiviServiceEntitiesByCentrale(idCentrales);
    }
    @GetMapping(value = "/list/{centrales}/{ids}")
    public List<ErsenSuiviService> getSuiviByCentrale(@PathVariable List<String> centrales,@PathVariable List<String> ids){

        List<ErsenSuiviService> serviceNewList=ersenSuiviServiceRepository.getNewSuiviService(centrales,ids);
        System.out.println("services : "+serviceNewList);

        return serviceNewList;
    }
    @PutMapping("/edit/{id}")
    public boolean editSuiviService (@RequestBody ErsenSuiviService suiviService, @PathVariable String id){
        suiviService.setId(id);
        ersenSuiviServiceRepository.saveAndFlush(suiviService);
        return true;
    }

    @GetMapping("/all")
    public List<ErsenSuiviService> getAllSuivi(){
        return ersenSuiviServiceRepository.findAll();
    }

    @PostMapping("/sync")
    public  void suiviSync(@RequestBody List<ErsenSuiviService> suiviServices){
        ersenSuiviServiceRepository.saveAll(suiviServices);
       /** List<ErsenSuiviService> list= ersenSuiviServiceRepository.saveAll(suiviServices);
        if(!list.isEmpty())
        {
            for( ErsenSuiviService s : list) {
                System.out.println("##################################################");
                ersenCentraleRepository.updateEtatCentrale(s.getCentraleID(),s.getStatus());
            }
        }
        else {
            System.out.println("liste mise a jour centrale vide");
        }*/
    }
    @DeleteMapping("/del/sync")
    public  void suiviDelSync(@RequestBody List<String> suiviServices){
        ersenSuiviServiceRepository.deleteAllById(suiviServices);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteSuivi(@PathVariable String id){
        ersenSuiviServiceRepository.deleteById(id);
        return  true;
    }
}