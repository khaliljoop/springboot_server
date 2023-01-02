package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.AllEquipementRepository;
import sn.ssi.ersen.dao.EquipementCentraleRepository;
import sn.ssi.ersen.entity.AllEquipementEntity;
import sn.ssi.ersen.entity.EquipementCentrale;
import sn.ssi.ersen.entity.equipement.EquipementSend;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/allequipement")
@CrossOrigin("*")
public class AllEquipementController {
    private final AllEquipementRepository allEquipementRepository;
    private final EquipementCentraleRepository equipementCentraleRepository;

    public AllEquipementController(AllEquipementRepository allEquipementRepository, EquipementCentraleRepository equipementCentraleRepository){
        this.equipementCentraleRepository=equipementCentraleRepository;
        this.allEquipementRepository = allEquipementRepository;
    }

    @GetMapping(value = "/all")
    public List<AllEquipementEntity> getAllEquipement(){
        return allEquipementRepository.findAll();
    }

    @GetMapping(value = "/{tablename}/{centrale}")
    public List<EquipementSend> getEquipementByTableName(@PathVariable String tablename, @PathVariable String centrale){
       List<Object> objects = getEquipementByTablenameAndCentrale(tablename, centrale);
       List<EquipementSend> equipementSendList = new ArrayList<>();
       for(Object o: objects)
           equipementSendList.add(
                   new EquipementSend(((Object[])o)[0].toString(),((Object[]) o)[1].toString(),((Object[]) o)[2].toString(),((Object[]) o)[3].toString(),Integer.parseInt(((Object[]) o)[4].toString()),((Object[]) o)[5].toString(),((Object[]) o)[6].toString())
           );
       return equipementSendList;
    }

    @GetMapping(value = "/{centrale}")
    public List<EquipementSend> getEquipementNbreByTableName(@PathVariable String centrale){
        List<AllEquipementEntity> equipementList = getAllEquipement();
        List<EquipementSend> equipementSendList = new ArrayList<>();
        for(AllEquipementEntity equipement: equipementList){
            List<Object> objects = getEquipementNbreByTablenameAndCentrale(equipement.getTablename(), centrale);
            for(Object o: objects)
                equipementSendList.add(
                        new EquipementSend(((Object[])o)[0].toString(),((Object[]) o)[1].toString(),((Object[]) o)[2].toString(),((Object[]) o)[3].toString(),Integer.parseInt(((Object[]) o)[4].toString()),((Object[]) o)[5].toString(),((Object[]) o)[6].toString())
                );
        }
        return equipementSendList;
    }

    @PersistenceContext
    private EntityManager entityManager;
    public List<Object> getEquipementByTablenameAndCentrale(String tablename, String centrale){
        String sql = "select t.id, t.libelle, t.fabricant,ec.centrale,ec.nbre,ec.id, ec.tablename" +
                " from "+tablename+" t, EquipementCentrale ec" +
                " where t.id=ec.equipement and ec.centrale='"+centrale+"'";
        return entityManager.createQuery(sql).getResultList();
    }



    public List<Object> getEquipementNbreByTablenameAndCentrale(String tablename, String centrale){
        String sql = "select t.id, t.libelle, t.fabricant,ec.centrale,ec.nbre,ec.id, ec.tablename" +
                " from "+tablename+" t, EquipementCentrale ec" +
                " where  ec.equipement=t.id AND  ec.centrale='"+centrale+"' and ec.nbre!=0 and ec.tablename='"+tablename+"' ";
        return entityManager.createQuery(sql).getResultList();
    }

    @PutMapping(value = "/editequipementcentrale")
    public Boolean updateEquipementsCentrales(@RequestBody List<EquipementSend> equipementSendList){
        for(EquipementSend o: equipementSendList){
            equipementCentraleRepository.saveAndFlush(
                    new EquipementCentrale(
                            o.getIdEquipementCentrale(),
                            o.getCentrale(),
                            o.getIdEquipement(),
                            o.getTableName(),
                            "",
                            o.getNbre()
                    )
            );
        }
        return true;
    }
}
