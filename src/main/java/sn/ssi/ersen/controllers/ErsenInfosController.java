package sn.ssi.ersen.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import sn.ssi.ersen.dao.ErsenInfoRepository;
import sn.ssi.ersen.entity.ErsenCentraleEntity;
import sn.ssi.ersen.entity.ErsenInfos;
import sn.ssi.ersen.entity.forWeb.Dashbord;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ErsenInfosController {
    private final ErsenInfoRepository infoRepository;

    public ErsenInfosController(ErsenInfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @GetMapping(value = {"/dashinfos","/dashinfos/{utilisateur}"}, produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
    public Dashbord webgetInfo (@PathVariable(required = false) Integer utilisateur){
        if(utilisateur==null){
            List<String> nbcentrale = infoRepository.getNbCentrale();
            List<String> nbmc = infoRepository.getnbmc();
            List<String> nbshs = infoRepository.getnbshs();
            List<String> nbbt = infoRepository.getnbmt();
            List<String> nbmcfunc = infoRepository.getnb("MCH", 1);
            List<String> nbmcpanne = infoRepository.getnb("MCH", 2);
            List<String> nbmcnonfunc = infoRepository.getnb("MCH", 3);
            List<String> nbshsfunc = infoRepository.getnb("SHS", 1);
            List<String> nbshspanne = infoRepository.getnb("SHS", 2);
            List<String> nbshsnonfunc = infoRepository.getnb("SHS", 3);
            List<String> nbbtfunc = infoRepository.getnb("de977c2879fb4f4f0179fbc6e77e0000", 1);
            List<String> nbbtpanne = infoRepository.getnb("de977c2879fb4f4f0179fbc6e77e0000", 2);
            List<String> nbbtnonfunc = infoRepository.getnb("de977c2879fb4f4f0179fbc6e77e0000", 3);

            return new Dashbord(nbcentrale,
                    nbmc, nbshs, nbbt,
                    nbmcfunc, nbmcpanne, nbmcnonfunc,
                    nbshsfunc, nbshspanne, nbshsnonfunc,
                    nbbtfunc, nbbtpanne, nbbtnonfunc);
        }else{
            List<String> nbcentrale = infoRepository.getNbCentraleByUser(utilisateur);
            List<String> nbmc = infoRepository.getnbmcByUser(utilisateur);
            List<String> nbshs = infoRepository.getnbshsByUser(utilisateur);
            List<String> nbbt = infoRepository.getnbmtByUser(utilisateur);
            List<String> nbmcfunc = infoRepository.getnbByUser(utilisateur,"MCH", 1);
            List<String> nbmcpanne = infoRepository.getnbByUser(utilisateur,"MCH", 2);
            List<String> nbmcnonfunc = infoRepository.getnbByUser(utilisateur,"MCH", 3);
            List<String> nbshsfunc = infoRepository.getnbByUser(utilisateur,"SHS", 1);
            List<String> nbshspanne = infoRepository.getnbByUser(utilisateur,"SHS", 2);
            List<String> nbshsnonfunc = infoRepository.getnbByUser(utilisateur,"SHS", 3);
            List<String> nbbtfunc = infoRepository.getnbByUser(utilisateur,"de977c2879fb4f4f0179fbc6e77e0000", 1);
            List<String> nbbtpanne = infoRepository.getnbByUser(utilisateur,"de977c2879fb4f4f0179fbc6e77e0000", 2);
            List<String> nbbtnonfunc = infoRepository.getnbByUser(utilisateur,"de977c2879fb4f4f0179fbc6e77e0000", 3);

            return new Dashbord(nbcentrale,
                    nbmc, nbshs, nbbt,
                    nbmcfunc, nbmcpanne, nbmcnonfunc,
                    nbshsfunc, nbshspanne, nbshsnonfunc,
                    nbbtfunc, nbbtpanne, nbbtnonfunc);
        }

    }
}
