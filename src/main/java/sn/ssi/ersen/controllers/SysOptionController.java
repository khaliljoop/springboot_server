package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.SysOptionRepository;
import sn.ssi.ersen.entity.SysOptionEntity;

import java.util.List;

@RestController
@RequestMapping(value = "/option")
@CrossOrigin("*")
public class SysOptionController {
    private SysOptionRepository sysOptionRepository;
    public SysOptionController(SysOptionRepository sysOptionRepository){
        this.sysOptionRepository=sysOptionRepository;
    }
    @GetMapping(value = "/all")
    public List<SysOptionEntity> getOptions(){
        return sysOptionRepository.findAll();
    }

    @PostMapping(value = "/add")
    public SysOptionEntity save(@RequestBody SysOptionEntity sysOptionEntity){
        return sysOptionRepository.save(sysOptionEntity);
    }
}
