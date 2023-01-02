package sn.ssi.ersen.controllers;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenAnneeRepository;
import sn.ssi.ersen.entity.ErsenAbonneEntity;
import sn.ssi.ersen.entity.ErsenAnneeEntity;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping(value = "/annee")
@CrossOrigin("*")
public class ErsenAnneeController {
    private final ErsenAnneeRepository ersenAnneeRepository;

    public ErsenAnneeController(ErsenAnneeRepository ersenAnneeRepository) {
        this.ersenAnneeRepository = ersenAnneeRepository;
    }

    @GetMapping("/all")
    public List<ErsenAnneeEntity> getAnnee(){
        return ersenAnneeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ErsenAnneeEntity getAnneeById(@PathVariable String id){
        return ersenAnneeRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  ErsenAnneeEntity addAnnee(@RequestBody ErsenAnneeEntity ersenAnneeEntity){
        return ersenAnneeRepository.saveAndFlush(ersenAnneeEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public ErsenAnneeEntity updateAnnee( @PathVariable String id,@RequestBody ErsenAnneeEntity ersenAnneeEntity){
        ersenAnneeEntity.setId(id);
        return ersenAnneeRepository.saveAndFlush(ersenAnneeEntity);
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteAnnee(@PathVariable String id){
        ersenAnneeRepository.deleteById(id);
        return "ok";
    }

    @Scheduled(cron = "0 0 0 1 1 *")
    public void doStuffOnFirstOfJanuary() {
        ersenAnneeRepository.save(new ErsenAnneeEntity(null, Calendar.getInstance().get(Calendar.YEAR)));
    }
}
