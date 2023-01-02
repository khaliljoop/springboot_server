package sn.ssi.ersen.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.ErsenConfigCentraleSectionMRepository;
import sn.ssi.ersen.entity.ErsenConfigCentraleSectionM;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ErsenConfigCentraleSectionMController {
    private final ErsenConfigCentraleSectionMRepository ersenConfigCentSectMRepository;

    public ErsenConfigCentraleSectionMController(ErsenConfigCentraleSectionMRepository ersenConfigCentSectMRepository) {
        this.ersenConfigCentSectMRepository = ersenConfigCentSectMRepository;
    }

    @PostMapping("/add_config_centrale_section_m")
    public List<ErsenConfigCentraleSectionM> addConfigCentraleSectionM(@RequestBody ErsenConfigCentraleSectionM ersenConfigCentraleSectionM){
        ersenConfigCentSectMRepository.saveAndFlush(ersenConfigCentraleSectionM);
        return getAllCentraleSectM();
    }

    @DeleteMapping("/centralesectionm/{id}")
    public void deleteCentraleSect(@PathVariable String id){
        ersenConfigCentSectMRepository.deleteById(id);
    }

    @GetMapping(value = "/centralesectionm/all",produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    List<ErsenConfigCentraleSectionM> getAllCentraleSectM(){
        return ersenConfigCentSectMRepository.findAll();
    }

    @PostMapping("/addcentralesection/{centrale}")
    public boolean addCentraleSect(@PathVariable String centrale, @RequestBody List<String> sects){
        for (String sect : sects) {
            ersenConfigCentSectMRepository.save(new ErsenConfigCentraleSectionM(null, centrale, sect, true, 0));
            //traceNewSectionMRepository.save(new TraceNewSectionM(null, sect, centrale));
        }

        return true;
    }
}
