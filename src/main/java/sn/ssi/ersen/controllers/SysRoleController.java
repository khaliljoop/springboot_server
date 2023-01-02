package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.SysActionRepository;
import sn.ssi.ersen.dao.SysOptionRepository;
import sn.ssi.ersen.dao.SysRoleActionRepository;
import sn.ssi.ersen.dao.SysRoleRepository;
import sn.ssi.ersen.entity.SysActionEntity;
import sn.ssi.ersen.entity.SysOptionEntity;
import sn.ssi.ersen.entity.SysRoleActionEntity;
import sn.ssi.ersen.entity.SysRoleEntity;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping(value = "/role")
@CrossOrigin("*")
public class SysRoleController {
    private final SysRoleRepository sysRoleRepository;
    private final SysRoleActionRepository sysRoleActionRepository;
    private final SysActionRepository sysActionRepository;
    private final SysOptionRepository sysOptionRepository;
    public SysRoleController(SysRoleRepository sysRoleRepository, SysRoleActionRepository sysRoleActionRepository, SysActionRepository sysActionRepository, SysOptionRepository sysOptionRepository) {
        this.sysRoleRepository = sysRoleRepository;
        this.sysRoleActionRepository = sysRoleActionRepository;
        this.sysActionRepository = sysActionRepository;
        this.sysOptionRepository = sysOptionRepository;
    }

    @GetMapping("/all")
    public List<SysRoleEntity> getRole(){
        return sysRoleRepository.findAll();
    }

    @GetMapping("/{id}")
    public SysRoleEntity getRoleById(@PathVariable String id){
        return sysRoleRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  List<SysRoleEntity> addRole(@RequestBody SysRoleEntity sysRoleEntity){
        String idRole= sysRoleRepository.saveAndFlush(sysRoleEntity).getId();
        List<SysOptionEntity> options=getOptions();
        System.out.print("ROLE  ############################"+idRole);
        for (int i=0;i<options.size();i++){
            addRoleAction(new SysRoleActionEntity(null,false,false,false,false,options.get(i).getId(),idRole));
        }
        return getRole();
    }

    public List<SysOptionEntity> getOptions(){
        return sysOptionRepository.findAll();
    }

    public List<SysActionEntity> getAction(){
        return sysActionRepository.findAll();
    }

    public SysRoleActionEntity addRoleAction(SysRoleActionEntity sysRoleAction){
        return sysRoleActionRepository.save(sysRoleAction);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public List<SysRoleEntity> updateRole(@PathVariable String id, @RequestBody SysRoleEntity sysRoleEntity){
        sysRoleEntity.setId(id);
        sysRoleRepository.saveAndFlush(sysRoleEntity);
        return getRole();
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public List<SysRoleEntity> deleteRole(@PathVariable String id){
        sysRoleRepository.deleteById(id);
        return getRole();
    }
}
