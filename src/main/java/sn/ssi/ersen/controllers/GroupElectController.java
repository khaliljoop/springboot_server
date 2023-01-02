package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.GroupeElectRepository;
import sn.ssi.ersen.entity.equipement.GroupeElectEntity;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "/groupeelect")
@CrossOrigin("*")
public class GroupElectController {
    private final GroupeElectRepository groupeElectRepository;

    public GroupElectController(GroupeElectRepository groupeElectRepository) {
        this.groupeElectRepository = groupeElectRepository;
    }

    @GetMapping("/all")
    public List<GroupeElectEntity> getGroupeElect(){
        return groupeElectRepository.findAll();
    }

    @GetMapping("/{id}")
    public GroupeElectEntity getGroupeElectById(@PathVariable String id){
        return groupeElectRepository.findById(id).get();
    }

    @PostMapping("/add")
    @Transactional
    public  GroupeElectEntity addGroupeElect(@RequestBody GroupeElectEntity groupeElectEntity){
        return groupeElectRepository.saveAndFlush(groupeElectEntity);
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public GroupeElectEntity updateGroupeElect( @PathVariable String id,@RequestBody GroupeElectEntity groupeElectEntity){
        groupeElectEntity.setId(id);
        return groupeElectRepository.saveAndFlush(groupeElectEntity);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public void deleteGroupeElect(@PathVariable String id){
        groupeElectRepository.deleteById(id);
    }
}
