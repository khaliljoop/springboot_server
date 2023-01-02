package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.SysRoleActionRepository;
import sn.ssi.ersen.entity.SysRoleActionEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/roleaction")
@CrossOrigin("*")
public class SysRoleActionController {
    private final SysRoleActionRepository sysRoleActionRepository;

    public SysRoleActionController(SysRoleActionRepository sysRoleActionRepository) {
        this.sysRoleActionRepository = sysRoleActionRepository;
    }


    @GetMapping("/all")
    public List<SysRoleActionEntity> getRoleAction(){
        return sysRoleActionRepository.findAll();
    }

    @GetMapping("/allroleaction")
    public  List<Object> getAllRolesActions(){
        return sysRoleActionRepository.getRolesActions();
    }

    @GetMapping("/{id}")
    public SysRoleActionEntity getRoleActionById(@PathVariable Integer id){
        return sysRoleActionRepository.findById(id).get();
    }

    @GetMapping("/userrole/{userType}")
    public List<SysRoleActionEntity> getUserRole(@PathVariable String userType){
        return sysRoleActionRepository.getRolesActionsByUserType(userType);
    }

    @PostMapping("/add")
    @Transactional
    public SysRoleActionEntity addRoleAction(@RequestBody SysRoleActionEntity sysRoleAction){
        return sysRoleActionRepository.save(sysRoleAction);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public SysRoleActionEntity updateRoleAction(@PathVariable Integer id, @RequestBody SysRoleActionEntity sysRoleAction){
        sysRoleAction.setId(id);
        return sysRoleActionRepository.saveAndFlush(sysRoleAction);
    }

    @PutMapping("/editprivilege")
    @Transactional
    public List<SysRoleActionEntity> updateRoleActionList(@RequestBody List<SysRoleActionEntity> sysRoleActionList){
        for(SysRoleActionEntity sysRoleAction : sysRoleActionList){
            sysRoleActionRepository.saveAndFlush(sysRoleAction);
        }
        return getRoleAction();
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public String deleteRoleAction(@PathVariable Integer id){
        sysRoleActionRepository.deleteById(id);
        return "ok";
    }
}
