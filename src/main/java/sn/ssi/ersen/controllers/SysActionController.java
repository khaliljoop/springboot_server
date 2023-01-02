package sn.ssi.ersen.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.SysActionRepository;
import sn.ssi.ersen.entity.SysActionEntity;

import java.util.List;

@RestController
@RequestMapping(value = "/action")
@CrossOrigin("*")
public class SysActionController {
   private final SysActionRepository sysActionRepository;

    public SysActionController(SysActionRepository sysActionRepository) {
        this.sysActionRepository = sysActionRepository;
    }

    @GetMapping("/all")
    public List<SysActionEntity> getAction(){
        return sysActionRepository.findAll();
    }
    @PostMapping("/add")
    @Transactional
    public SysActionEntity save(@RequestBody SysActionEntity action){
        return sysActionRepository.saveAndFlush(action);
    }
}
