package sn.ssi.ersen.controllers;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.SysProfileRepository;
import sn.ssi.ersen.entity.SysProfilEntity;

import java.util.List;


@RestController
@RequestMapping(value = "/profile")
@CrossOrigin("*")
public class SysProfileController {
  private final SysProfileRepository profileRepository;

    public SysProfileController(SysProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/all")
    public List<SysProfilEntity> getProfile(){
        return profileRepository.findAll();
    }

    @GetMapping("/{id}")
    public SysProfilEntity getProfileById(@PathVariable String id){
        return profileRepository.findById(id).orElse(null);
    }

    @PostMapping("/add")
    @Transactional
    public  List<SysProfilEntity> addProfile(@RequestBody SysProfilEntity sysProfilEntity){
        profileRepository.saveAndFlush(sysProfilEntity);
        return getProfile();
    }

    @PutMapping("/edit/{id}")
    @Transactional
    public List<SysProfilEntity> updateProfile(@PathVariable String id, @RequestBody SysProfilEntity sysProfilEntity){
        sysProfilEntity.setId(id);
        profileRepository.saveAndFlush(sysProfilEntity);
        return getProfile();
    }


    @DeleteMapping("/delete/{id}")
    @Transactional
    public List<SysProfilEntity> deleteProfile(@PathVariable String id){
        profileRepository.deleteById(id);
        return getProfile();
    }
}
