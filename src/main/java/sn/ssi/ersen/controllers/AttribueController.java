package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.ssi.ersen.dao.AttribueRepository;
import sn.ssi.ersen.entity.databaseEntities.AttribueEntity;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/attribue")
public class AttribueController {
    private final AttribueRepository attribueRepository;

    public AttribueController(AttribueRepository attribueRepository) {
        this.attribueRepository = attribueRepository;
    }

    @GetMapping(value = "/all")
    public List<AttribueEntity> getAttribue() {
        return attribueRepository.findAll();
    }
}
