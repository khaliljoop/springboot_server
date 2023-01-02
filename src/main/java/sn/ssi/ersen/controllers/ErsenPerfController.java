package sn.ssi.ersen.controllers;

import org.springframework.web.bind.annotation.*;

import sn.ssi.ersen.dao.*;
import sn.ssi.ersen.entity.ErsenConductExecutionNb;
import sn.ssi.ersen.entity.ErsenPerfCalcul;
import sn.ssi.ersen.entity.UtilisateurEntity;
import sn.ssi.ersen.entity.entitieMobile.CentralePerf;
import sn.ssi.ersen.entity.entitieMobile.ConductPerf;

import java.time.LocalDate;
import java.util.*;

@RestController
@CrossOrigin("*")
public class ErsenPerfController {
    private final ErsenPerfCalculRepository ersenPerfCalculRepository;
    private final ErsenPerfHistoriqueRepository ersenPerfHistoriqueRepository;
    private final ErsenResidusPerfRepository ersenResidusPerfRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ErsenCentraleRepository ersenCentraleRepository;
    private final ErsenTacheCentraleRepository ersenTacheCentraleRepository;
    private final ErsenConductExecutionNbRepository ersenConductExecutionNbRepository;

    public ErsenPerfController(ErsenPerfCalculRepository ersenPerfCalculRepository, ErsenPerfHistoriqueRepository ersenPerfHistoriqueRepository, UtilisateurRepository utilisateurRepository, ErsenCentraleRepository ersenCentraleRepository, ErsenConductExecutionNbRepository ersenConductExecutionNbRepository, ErsenResidusPerfRepository ersenResidusPerfRepository, ErsenTacheCentraleRepository ersenTacheCentraleRepository) {
        this.ersenPerfCalculRepository = ersenPerfCalculRepository;
        this.ersenPerfHistoriqueRepository = ersenPerfHistoriqueRepository;
        this.ersenResidusPerfRepository = ersenResidusPerfRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.ersenCentraleRepository = ersenCentraleRepository;
        this.ersenConductExecutionNbRepository = ersenConductExecutionNbRepository;
        this.ersenTacheCentraleRepository = ersenTacheCentraleRepository;
    }

    @GetMapping("/perf/{idUser}")
    public int[] getUserCurrentPerf(@PathVariable int idUser){

        try
        {
            int nbTachesExecByUserThisMonth = ersenPerfCalculRepository.getNbTachesExecByUserThisMonth(idUser);
            String conductsCentralID = ersenCentraleRepository.getConductsCentralID(idUser);

            int residu = ersenResidusPerfRepository.getResiduDenominateurByCentrale(conductsCentralID);
            int nbTachesTotalUserHaveToExecThisMonth = ersenPerfCalculRepository.getNbTachesTotalUserHaveToExecThisMonth(conductsCentralID)+residu;
            return new int[]{nbTachesExecByUserThisMonth, nbTachesTotalUserHaveToExecThisMonth};
        }
        catch(Exception e){
            return new int[]{0,0};
        }
    }

    @GetMapping("/perf/user/historique/{idUser}/{annee}/{mois}")
    public int[] getUserPerfHistorique(@PathVariable int idUser,@PathVariable int annee,@PathVariable int mois){
        Integer nbTacheExec = ersenPerfCalculRepository.getNbTachesUserExecutedInApecificMonth(idUser, mois, annee);
        Integer nbTacheTotal = ersenPerfCalculRepository.getNbTachesTotalUserHaveExecInASpecificMonth(idUser, mois, annee);
        return new int[]{nbTacheExec!=null?nbTacheExec:0, nbTacheTotal!=null?nbTacheTotal:0};
    }

    @GetMapping("/delete/user/perf/{centrale}")
    public void deleteUserPerf(@PathVariable String centrale){
        ersenPerfCalculRepository.deleteUserPerfIndicator(centrale);
    }

    @GetMapping("/perf/{idUser}/{nbMoisOrdreDesc}")
    public Map<String, Object> getUserPerf(@PathVariable int idUser, @PathVariable int nbMoisOrdreDesc){
        Map<String, Object> historicAndActuelPerfStatus = new HashMap<>();
        historicAndActuelPerfStatus.put("currentPerf",getUserCurrentPerf(idUser));
        historicAndActuelPerfStatus.put("history",ersenPerfHistoriqueRepository.getPerfHistorique(idUser,nbMoisOrdreDesc));
        return historicAndActuelPerfStatus;
    }

    @GetMapping("/perfweb/{entr}/{nbMoisOrdreDesc}")
    public List<Map<String, Object>> getUsersPerf(@PathVariable String entr, @PathVariable int nbMoisOrdreDesc){
        List<Map<String,Object>> results = new ArrayList<>();
        List<UtilisateurEntity> utilisateurs = utilisateurRepository.getALLUtilisateurConducteur(entr);
        for (UtilisateurEntity utilisateur : utilisateurs)
            results.add(getUserPerf(utilisateur.getUsrId(), nbMoisOrdreDesc));
        return results;
    }

    //use by operation
    @GetMapping(value = "/perf/operateur/{entreprise}/{nbLigne}",produces = "application/json;charset=utf-8")
    public List<CentralePerf> getPerfsOfConducteus(@PathVariable String entreprise, @PathVariable Integer nbLigne){
        return getCentralePerfs(nbLigne, ersenPerfCalculRepository.getEntrepriseCentralsIDandName(entreprise));
    }

    @GetMapping(value = "/perf/admin/{nbLigne}",produces = "application/json;charset=utf-8")
    public List<CentralePerf> getPerfsOfConducteusAll(@PathVariable Integer nbLigne){
        return getCentralePerfs(nbLigne, ersenPerfCalculRepository.getConducteurPerfsForAdmin());
    }

    private List<CentralePerf> getCentralePerfs(@PathVariable Integer nbLigne, List<CentralePerf> centralePerfs) {
        if(!centralePerfs.isEmpty()) {
            for(CentralePerf cp : centralePerfs)
                ersenPerfCalculRepository.getConducteursOfACentral(cp.getIdC()).forEach(conducteur-> cp.getConductPerfs().add(new ConductPerf(conducteur.getUsrId(), conducteur.getUsrNom(), conducteur.getUsrPrenom(), getUserPerf(conducteur.getUsrId(), nbLigne))));
        }
        return centralePerfs;
    }


    @PostMapping("/perf/increment/{iduser}/{idTacheC}")
    public void incrementNbExec(@PathVariable int iduser, @PathVariable String idTacheC){
        ErsenConductExecutionNb conductNbExec=ersenPerfCalculRepository.getUserErsenConductExecutionNb(iduser);
        conductNbExec.setNbExec(conductNbExec.getNbExec()+1);
        ersenConductExecutionNbRepository.save(conductNbExec);

        ErsenPerfCalcul perfCalcul=ersenPerfCalculRepository.getUserPerfAboutAspecificTask(idTacheC);
        perfCalcul.setNbExec(perfCalcul.getNbExec()+1);
        if(perfCalcul.getFrequenceMensuelleMin() < perfCalcul.getNbExec())
            perfCalcul.setFrequenceMensuelleMin(perfCalcul.getNbExec());
        ersenPerfCalculRepository.save(perfCalcul);
    }

    @PostMapping("/perf/addcalculperf/{idTacheC}/{centrale}")
    public void addCalculPerf(@PathVariable String idTacheC,@PathVariable String centrale){
        ersenPerfCalculRepository.save(new ErsenPerfCalcul(idTacheC,centrale));
    }

    /**Cette methode devra etre appeler a chaque fois qu'une tache est programmer ou reprogrammer et son resutat permettra
     de renseigner ou mettre a jour une ligne de la table ersen_perf_calcul (first, frequence,nb_execution,nb_jour_res ) plus exactement les colonnes
     frequence et nb_jour_res*/
    @GetMapping("/perf/{periodiciteValeur}/{idTacheC}/{centrale}")
    public void setTacheCFrequenceAndNbJourRes(@PathVariable String idTacheC, @PathVariable int periodiciteValeur,@PathVariable String centrale){
        int nbJourRest,nbJourTotal,nbJourDuMois=LocalDate.now().lengthOfMonth(),dateDeProgrammation=LocalDate.now().getDayOfMonth();
        try
        {
            ersenPerfCalculRepository.checkIfFirst(idTacheC);
            nbJourRest=ersenPerfCalculRepository.getNbJourResOfAUserTask(idTacheC);
            if(nbJourRest != 0)
                nbJourTotal = nbJourDuMois + nbJourRest;
            else
                nbJourTotal = nbJourDuMois - dateDeProgrammation;
            ersenPerfCalculRepository.updateFrequenceNbExecTceMoisAndNbJourRest(nbJourTotal % periodiciteValeur, nbJourTotal/periodiciteValeur,idTacheC);
        }
        catch (Exception e){
            ersenTacheCentraleRepository.upDateDateFirstPlanif(idTacheC);
            ersenPerfCalculRepository.save(new ErsenPerfCalcul(idTacheC,true,0,(nbJourDuMois-dateDeProgrammation)/periodiciteValeur,(nbJourDuMois-dateDeProgrammation) % periodiciteValeur,centrale));
        }
    }
}
