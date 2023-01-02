package sn.ssi.ersen.controllers;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenPlanCentraleRepository;
import sn.ssi.ersen.dao.ErsenPlanMaintenanceRepository;
import sn.ssi.ersen.dao.ErsenResidusPerfRepository;
import sn.ssi.ersen.entity.ErsenPlanCentrale;
import sn.ssi.ersen.entity.ErsenPlanMaintenanceEntity;
import sn.ssi.ersen.entity.ErsenResidusPerf;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/planmaintenance")
@CrossOrigin("*")
public class ErsenPlanMaintenanceController {

    private final ErsenPlanMaintenanceRepository ersenPlanMaintenanceRepository;
    private final ErsenPlanCentraleRepository planCentraleRepository;
    private final ErsenResidusPerfRepository ersenResidusPerfRepository;

    public ErsenPlanMaintenanceController(ErsenPlanMaintenanceRepository ersenPlanMaintenanceRepository, ErsenPlanCentraleRepository planCentraleRepository, ErsenResidusPerfRepository ersenResidusPerfRepository) {
        this.ersenPlanMaintenanceRepository = ersenPlanMaintenanceRepository;
        this.planCentraleRepository = planCentraleRepository;
        this.ersenResidusPerfRepository = ersenResidusPerfRepository;
    }

    @GetMapping("/all")
    public List<ErsenPlanMaintenanceEntity> getPlanMaintenance(){
        return ersenPlanMaintenanceRepository.findAll();
    }

    @GetMapping("/all/not/{centrale}")
    public List<ErsenPlanMaintenanceEntity> getPlanMaintenanceNotInCentrale(@PathVariable  String centrale){
        return ersenPlanMaintenanceRepository.getPlanNotInCentrale(centrale);
    }

    @GetMapping("/all/in/{centrale}")
    public List<ErsenPlanMaintenanceEntity> getPlanInCentrale(@PathVariable String centrale){
        return ersenPlanMaintenanceRepository.getPlanByCentrale(centrale);
    }

    @GetMapping("/all/entr/{entreprise}")
    public List<ErsenPlanMaintenanceEntity> getPlansByEntreprise(@PathVariable String entreprise){
        return  ersenPlanMaintenanceRepository.getPlanByEntreprise(entreprise);
    }

    @GetMapping("/plancentrale/all")
    public List<ErsenPlanCentrale> getAllPlanCentrale(){
        return planCentraleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenPlanMaintenanceEntity getPlanMaintenanceById(@PathVariable String id){
        return ersenPlanMaintenanceRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  List<ErsenPlanMaintenanceEntity> addPlanMaintenance(@RequestBody ErsenPlanMaintenanceEntity ersenPlanMaintenanceEntity){
        ersenPlanMaintenanceRepository.save(ersenPlanMaintenanceEntity);
         return getPlanMaintenance();
    }

    @PostMapping("/addplancentrale/{centrale}")
    public  List<ErsenPlanCentrale> addPlanCentrale(@PathVariable String centrale, @RequestBody List<String> plans){
        for(String plan : plans){
            planCentraleRepository.save(new ErsenPlanCentrale(null,plan, centrale));
        }

        return getAllPlanCentrale();
    }


    @DeleteMapping("/plancentrale/delete/{id}")
    public List<ErsenPlanCentrale> deletePlanCentrale(@PathVariable String id){
        planCentraleRepository.deleteById(id);
        return getAllPlanCentrale();
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public List<ErsenPlanMaintenanceEntity> updatePlanMaintenance(@RequestBody ErsenPlanMaintenanceEntity ersenPlanMaintenanceEntity){
        ersenPlanMaintenanceRepository.save(ersenPlanMaintenanceEntity);
        return getPlanMaintenance();
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public List<ErsenPlanMaintenanceEntity> deletePlanMaintenance(@PathVariable String id){
        ersenPlanMaintenanceRepository.deleteById(id);
        return getPlanMaintenance();
    }


}
