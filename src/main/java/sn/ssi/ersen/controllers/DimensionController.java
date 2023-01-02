package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.ssi.ersen.dao.DimensionRepository;
import sn.ssi.ersen.entity.databaseEntities.DimensionEntity;

import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/dimension")
public class DimensionController {

    private final DimensionRepository dimensionRepository;

    public DimensionController(DimensionRepository dimensionRepository) {
        this.dimensionRepository = dimensionRepository;
    }

    @GetMapping(value = "/all")
    public List<DimensionEntity> getDimensions(){
        return dimensionRepository.findAll();

    }


}
