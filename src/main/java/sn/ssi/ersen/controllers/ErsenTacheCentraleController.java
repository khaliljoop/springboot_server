package sn.ssi.ersen.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sn.ssi.ersen.dao.*;
import sn.ssi.ersen.entity.*;
import sn.ssi.ersen.entity.ListPlanification;
import sn.ssi.ersen.entity.entitieMobile.*;
import sn.ssi.ersen.entity.forWeb.SpontaneousTask;
import sn.ssi.ersen.entity.forWeb.SpontaneousTaskCentrale;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
public class ErsenTacheCentraleController {
    private static ErsenTacheCentraleRepository ersenTacheCentraleRepository;
    private static ErsenConfigCentraleTacheRepository ersenConfigCentraleTacheRepository;
    private static ErsenPreuveTacheRepository ersenPreuveTacheRepository;
    private static ErsenNotificationRepository ersenNotificationRepository;
    private static ErsenPerfCalculRepository ersenPerfCalculRepository;
    private static ErsenFichierRepository ersenFichierRepository;
    private static ErsenPerfHistoriqueRepository ersenPerfHistoriqueRepository;
    private static ErsenPerfController ersenPerfController;
    private static ErsenBackupPreuveAndNotifTacheRepository ersenBackupPreuveAndNotifTacheRepository;
    private static ErsenTraceTacheReplanifierRepository ersenTraceTacheReplanifierRepository;
    private final ErsenCentraleRepository ersenCentraleRepository;
    private final ErsenTraceTacheRepository ersenTraceTacheRepository;
    private final ErsenUtilisateurController ersenUtilisateurController;
    private final ErsenTacheRepository ersenTacheRepository;
    private static ErsenConductExecutionNbRepository executionNbRepository;
    private static ErsenResidusPerfRepository ersenResidusPerfRepository;

    public ErsenTacheCentraleController(ErsenTacheCentraleRepository ersenTacheCentraleRepository, ErsenConfigCentraleTacheRepository ersenConfigCentraleTacheRepository, ErsenCentraleRepository ersenCentraleRepository, ErsenPreuveTacheRepository ersenPreuveTacheRepository, ErsenTraceTacheRepository ersenTraceTacheRepository, ErsenNotificationRepository ersenNotificationRepository, ErsenUtilisateurController ersenUtilisateurController, ErsenTacheRepository ersenTacheRepository, ErsenPerfController ersenPerfController, ErsenPerfCalculRepository ersenPerfCalculRepository, ErsenFichierRepository ersenFichierRepository, ErsenPerfHistoriqueRepository ersenPerfHistoriqueRepository, ErsenBackupPreuveAndNotifTacheRepository ersenBackupPreuveAndNotifTacheRepository, ErsenTraceTacheReplanifierRepository ersenTraceTacheReplanifierRepository, ErsenConductExecutionNbRepository executionNbRepository, ErsenResidusPerfRepository ersenResidusPerfRepository) {
        this.ersenTacheCentraleRepository = ersenTacheCentraleRepository;
        this.ersenConfigCentraleTacheRepository = ersenConfigCentraleTacheRepository;
        this.ersenCentraleRepository = ersenCentraleRepository;
        this.ersenPreuveTacheRepository = ersenPreuveTacheRepository;
        this.ersenTraceTacheRepository = ersenTraceTacheRepository;
        this.ersenNotificationRepository = ersenNotificationRepository;
        this.ersenUtilisateurController = ersenUtilisateurController;
        this.ersenTacheRepository = ersenTacheRepository;
        this.ersenPerfController = ersenPerfController;
        this.ersenPerfCalculRepository = ersenPerfCalculRepository;
        this.ersenFichierRepository=ersenFichierRepository;
        this.ersenPerfHistoriqueRepository = ersenPerfHistoriqueRepository;
        this.ersenBackupPreuveAndNotifTacheRepository = ersenBackupPreuveAndNotifTacheRepository;
        this.ersenTraceTacheReplanifierRepository = ersenTraceTacheReplanifierRepository;

        this.executionNbRepository = executionNbRepository;
        this.ersenResidusPerfRepository = ersenResidusPerfRepository;
    }

    @GetMapping("/tachecentrales")
    public List<ErsenTacheCentraleEntity> getTacheCentrale(){
        return ersenTacheCentraleRepository.findAll();
    }

    @PostMapping("/tachecentrales/addnew")
    public  ErsenTacheCentraleEntity addTacheCentrale(@RequestBody ErsenTacheCentraleEntity ersenTacheCentraleEntity){
        return ersenTacheCentraleRepository.save(ersenTacheCentraleEntity);
    }

    public static void replanifyTacheCentrals(Date dateNextPlanificationAtMidnight) throws ParseException {
        List<ErsenTacheCentraleEntity> tacheCentralesToReplanify = ersenTacheCentraleRepository.getTacheCentralesToReplanify(dateNextPlanificationAtMidnight);
        Integer valPeriode;
        Date dateNextPlanifOrDateUltimeForBackUpTache;

        for (ErsenTacheCentraleEntity tc : tacheCentralesToReplanify){
            valPeriode=ersenConfigCentraleTacheRepository.getPeriodiciteValeur(ersenConfigCentraleTacheRepository.getPeriodiciteID(tc.getId()));
            dateNextPlanifOrDateUltimeForBackUpTache = calculDateNextPlanification(valPeriode, tc.getDateNextPlanif());
            tc.setDateNextPlanif(dateNextPlanifOrDateUltimeForBackUpTache);

            ersenBackupPreuveAndNotifTacheRepository.save(new ErsenBackupTacheEntity(tc.getId(), tc.getConfigcentraletache(), tc.getCommentaire(), tc.getValide(), tc.getDateeffectuee(), dateNextPlanificationAtMidnight, tc.getValide()==1?dateNextPlanifOrDateUltimeForBackUpTache:null,tc.getEtattache(), tc.getTutoriel(),tc.getType(), tc.getPlanM(),valPeriode==1||tc.getValide()==2));
            tc.setDateupdate(tc.getDateNextPlanif());
            tc.setDateeffectuee(null);
            tc.setDateupdate(null);
            tc.setValide(0);
            tc.setEtattache("de977c2878f70a660178f8888e350007");
            ersenTacheCentraleRepository.save(tc);
            ersenTraceTacheReplanifierRepository.save(new ErsenTraceTacheReplanifier(tc.getId(),tc.getDateNextPlanif()));
            ersenPreuveTacheRepository.updatePreuveByIdTacheC(tc.getId());
            ersenNotificationRepository.updateNotificationByIdTacheC(tc.getId());
        }

        if(isFirstDayOfTheMonth(dateNextPlanificationAtMidnight)){
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateNextPlanificationAtMidnight);
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);// month est dans [0,11].
            if(month==0) {
                year -= 1;
                month=12;
            }//sinon nous retourne l'id du mois precedent.

            List<ErsenPerfHistorique> allUserPerfForBackUp = ersenPerfCalculRepository.getAllUserPerfForBackUp();
            for(ErsenPerfHistorique ph:allUserPerfForBackUp)
                ph.setNbTasksTotal(ph.getNbTasksTotal()+ersenResidusPerfRepository.getResiduDenominateurByCentrale(ersenResidusPerfRepository.getCentraleIdFromErsenConductExecutionNb(ph.getIdUser())));

            ersenPerfHistoriqueRepository.saveAll(setPerfHistoricsMoisAndYear(allUserPerfForBackUp,month,year));
            ersenPerfCalculRepository.deleteThesePerfCalcul(ersenTacheCentraleRepository.getTacheCentralsIDS());
            executionNbRepository.resetNbExecForEach(allUserPerfForBackUp.stream().collect(Collectors.mapping(ErsenPerfHistorique::getIdUser,Collectors.toSet())));

            for (ErsenTacheCentraleEntity tc : tacheCentralesToReplanify) {
                ersenPerfController.setTacheCFrequenceAndNbJourRes(tc.getId(), ersenConfigCentraleTacheRepository.getPeriodiciteValue(ersenConfigCentraleTacheRepository.getPeriodiciteID(tc.getId())), ersenTacheCentraleRepository.getCentraleIDByCCT(tc.getConfigcentraletache()));
                tc.setDatePlanif(dateNextPlanificationAtMidnight);
                ersenTacheCentraleRepository.save(tc);
            }
        }

        ersenBackupPreuveAndNotifTacheRepository.getTacheDepToValidateToDay(dateNextPlanificationAtMidnight).forEach((ErsenBackupTacheEntity bt)->{
            bt.setValideBackup(2);
            bt.setEtattache("40288187786932bd01786935173c0000");
            bt.setDateeffectuee(dateNextPlanificationAtMidnight);
            bt.setEstArchiver(true);
            ersenBackupPreuveAndNotifTacheRepository.save(bt);
        });
    }

    static List<ErsenPerfHistorique> setPerfHistoricsMoisAndYear(List<ErsenPerfHistorique> phs, int month, int year){
        for (ErsenPerfHistorique ph:phs) {
            ph.setIdMois(month);
            ph.setIdAnnee(year);
        }
        return phs;
    }

    @GetMapping("/getDateNextPlanification/{valPeriod}/{dateSave}")
    public static Date calculDateNextPlanification(@PathVariable Integer valPeriod,@PathVariable Date dateSave) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(dateSave);
        c.add(Calendar.DATE, valPeriod);
        return geDateDayOrNextDayAtMidnight(c.getTime(),true);
    }

    /**Retourne la date d'aujourd'huit a 00h 00 ou celle du jour suivant a 00h 00*/
    public static Date geDateDayOrNextDayAtMidnight(Date date, boolean forToday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (!forToday)
            cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    @GetMapping("/todayOrNext/{bool}")
    public Date geTodayOrNextDayAtMidnightTest(@PathVariable boolean bool){
        return geDateDayOrNextDayAtMidnight(new Date(),bool);
    }


    public static boolean isFirstDayOfTheMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_MONTH) == 1)
            return true;
        return false;
    }

    @GetMapping("/tachecentrales/{id}")
    public ErsenTacheCentraleEntity getTacheCentrale(@PathVariable String id){
        return ersenTacheCentraleRepository.findById(id).orElse(null);
    }

    @GetMapping(value = {"planification/all", "planification/all/{utilisateur}"})
    public List<Object> getConfigCentraleTachePeriode(@PathVariable(required = false) String utilisateur){
        if (utilisateur==null)
            return ersenTacheCentraleRepository.getconfigCentraleTachePeriode();
        else
            return ersenTacheCentraleRepository.getconfigCentraleTachePeriodeByUtilisateur(utilisateur);
    }

    public void addPalnification(@RequestBody Planification planification) {
        ErsenConfigCentraleTacheEntity configCentraleTache=new ErsenConfigCentraleTacheEntity(null,0,planification.getIdcentrale(),planification.getIdperiodicite(), planification.getIdtache());
        ErsenConfigCentraleTacheEntity cct = ersenConfigCentraleTacheRepository.save(configCentraleTache);
        ersenTacheCentraleRepository.save(new ErsenTacheCentraleEntity(null,planification.getCommentaire(),0,null, new Timestamp(System.currentTimeMillis()),null,"de977c2878f70a660178f8888e350007",0,0,cct.getId(),null,planification.getPlanm(), null, null));
    }

    public void addRequetteSpntane(@RequestBody SpontaneousTask spontaneousTask){
        ErsenConfigCentraleTacheEntity configCentraleTacheEntity = new ErsenConfigCentraleTacheEntity(null, 1, spontaneousTask.getEntr_oper().get(0),null,null );
    }

    @PostMapping(value = "/planification/add")
    public  void addListPalnification(@RequestBody ListPlanification listPlanification) throws ParseException {
        for(int i=0;i<listPlanification.getIdtache().size();i++){
            addPalnification(
                new Planification(
                    listPlanification.getIdcentrale(),
                    listPlanification.getIdtache().get(i),
                    listPlanification.getIdperiodicite(),
                    listPlanification.getCommentaire(),
                    listPlanification.getPlanm()
                )
            );
        }
    }

    @PostMapping(value = "/spontaneous/add")
    public Boolean addSpontaneaousTask(@RequestBody SpontaneousTask spontaneousTask) throws ParseException {
        int delaiInDay = (int) (spontaneousTask.getDateLimite().getTime()-new Date().getTime())
                 / (1000 * 60 * 60 * 24) ;
        for(int i=0;i<spontaneousTask.getEntr_oper().size();i++){
            String idcct =  ersenConfigCentraleTacheRepository.save(new ErsenConfigCentraleTacheEntity(null, spontaneousTask.getLevel(), spontaneousTask.getEntr_oper().get(i),null,null )).getId();
            ersenTacheCentraleRepository.save(
                    new ErsenTacheCentraleEntity(null,"",0,null, new Timestamp(System.currentTimeMillis()),null,"de977c2878f70a660178f8888e350007",1,0,idcct,spontaneousTask.getTuto(),null, spontaneousTask.getNomTache(), spontaneousTask.getDateLimite())
            );

            if(ersenTacheCentraleRepository.getConducteurByCCT(idcct)!=null)
                ersenPerfController.setTacheCFrequenceAndNbJourRes(ersenTacheCentraleRepository.getTacheCentraleIdByConfigTacheC(idcct), delaiInDay,ersenTacheCentraleRepository.getCentraleIDByCCT(idcct));
        }
        return true;
    }

    @GetMapping(value = "/spontaneous/all/{for_oper}")
    public List<SpontaneousTaskCentrale> getAllSpontaneousTasks(@PathVariable Integer for_oper){
        return ersenTacheCentraleRepository.getAllSponteneousTasks(for_oper);
    }

    List<SpontaneousTaskCentrale> getSpontaneousByCentr(String centrale){
        return ersenTacheCentraleRepository.getAllSponteneousTasksByCentrale(0,centrale);
    }

    @GetMapping(value = "/spontaneous/all/{for_oper}/{entr}")
    public List<SpontaneousTaskCentrale> getAllSpontaneousTasksFilter(@PathVariable Integer for_oper, @PathVariable String entr){
        List<SpontaneousTaskCentrale> results = new ArrayList<>();
        List<Centrale> centraleEntityList = ersenCentraleRepository.getCentraleForOperateur(entr);
        for(Centrale centrale : centraleEntityList)
            results.addAll(getSpontaneousByCentr(centrale.getId()));
        return results;
    }

    @DeleteMapping(value = "/spontaneous/delete/{idtc}/{idcct}")
    public Boolean deleteSpontaneoustask(@PathVariable String idtc, @PathVariable String idcct){
        ersenTacheCentraleRepository.deleteById(idtc);
        ersenConfigCentraleTacheRepository.deleteById(idcct);
        return true;
    }

    @GetMapping(value = "/mobile/spontaneousTasks/{forOper}/{centralOrEntreprise}",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")  //forOper=0 if conducteur, 1 if operateur
    public TachesSpontAndPreuves getSpontaneousTasksForMibile(@PathVariable Integer forOper, @PathVariable String centralOrEntreprise){
        List<Tache> sponteneousTasksForMobile = ersenTacheCentraleRepository.getCentralOrEntrepriseSponteneousTasksForMobile(forOper, centralOrEntreprise);
        if(forOper==1)
            sponteneousTasksForMobile.addAll(ersenTacheCentraleRepository.getConductSponteneousTasksOperaHaveToValidateFromMobile(ersenCentraleRepository.getEntrepriseCentralIDS(centralOrEntreprise)));

        if(!sponteneousTasksForMobile.isEmpty()) {
            List<String> idTachesC = sponteneousTasksForMobile.stream().collect(Collectors.mapping(Tache::getTacheId, Collectors.toList()));
            return new TachesSpontAndPreuves(sponteneousTasksForMobile, ersenUtilisateurController.getTasksTutos(idTachesC), ersenUtilisateurController.getTasksPreuves(idTachesC));
        }
        return null;
    }

    @GetMapping(value = "/mobile/newSpontaneousTasks/{forOper}/{centralOrEntreprise}/{oldSpontaneousTaskIds}",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public TachesSpontAndPreuves getNewSpontaneousTasksForMobile(@PathVariable Integer forOper, @PathVariable String centralOrEntreprise,@PathVariable List<String> oldSpontaneousTaskIds){
        List<Tache> newSponteneousTasksForMobile = ersenTacheCentraleRepository.getNewCentralOrEntrepriseSponteneousTasksForMobile(forOper,centralOrEntreprise, oldSpontaneousTaskIds);
        if(forOper==1)
            newSponteneousTasksForMobile.addAll(ersenTacheCentraleRepository.getConductNewSponteneousTasksOperaHaveToValidateFromMobile(ersenCentraleRepository.getEntrepriseCentralIDS(centralOrEntreprise),oldSpontaneousTaskIds));

        //this line removes from the oldSpontaneousTaskIds all not deleted spontaneous taks id so at from the oldSpontaneousTaskIds will
        // only contain the list of deleted spontaneous task id that we have to replic to the mobile.
        List<Tutoriel> tasksTutos=new ArrayList<>();
        List<Preuve> tasksPreuves=new ArrayList<>();
        if(!newSponteneousTasksForMobile.isEmpty()) {
            List<String> idTachesC = newSponteneousTasksForMobile.stream().collect(Collectors.mapping(Tache::getTacheId, Collectors.toList()));
            tasksTutos = ersenUtilisateurController.getTasksTutos(idTachesC);
            tasksPreuves = ersenUtilisateurController.getTasksPreuves(idTachesC);
        }
        //This line retruns oldSpontaneousTaskIds like the list of undeleted sponteneous tasks
        oldSpontaneousTaskIds.removeAll(ersenTacheCentraleRepository.getNotDeletedSponteneousTasksForMobile(oldSpontaneousTaskIds));
        return new TachesSpontAndPreuves(newSponteneousTasksForMobile, tasksTutos, tasksPreuves, oldSpontaneousTaskIds);
    }

    @GetMapping(value = "/spontaneoustc")
    public List<ErsenTacheCentraleEntity> getAllSpontaneaousTC(){
        return ersenTacheCentraleRepository.getAllSpontaneousTC();
    }

    @GetMapping(value = "/spontaneouscct")
    public List<ErsenConfigCentraleTacheEntity> getAllSpontaneaousCCT(){
        return ersenConfigCentraleTacheRepository.getAllSpontaneousCCT();
    }

    @PutMapping(value = "/planification/edit/{id}")
    public  void editPalnification(@PathVariable String id,@RequestBody Planification planification){
        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ErsenTacheCentraleEntity tachecentrale = ersenTacheCentraleRepository.findTacheCentraleById(id);
        tachecentrale.setPlanM(planification.getPlanm());
        tachecentrale.setCommentaire(planification.getCommentaire());
        String cctId = tachecentrale.getConfigcentraletache();
        ersenTacheCentraleRepository.save(tachecentrale);

        ErsenConfigCentraleTacheEntity cct = ersenConfigCentraleTacheRepository.findById(cctId).get();
        cct.setCentrale_or_entreprise(planification.getIdcentrale());
        cct.setTache(planification.getIdtache());
        cct.setPeriodicite(planification.getIdperiodicite());
        ersenConfigCentraleTacheRepository.save(cct);
    }

    @GetMapping(value = "tachecentrales/centales/{id}")
    public ErsenTacheCentraleEntity getAllTacheCentraleByIdcentral(@PathVariable String id){
        return ersenTacheCentraleRepository.findTacheCentraleById(id);
    }

    @GetMapping(value = "/tachecentrales/realises")
    public List<ErsenTacheCentraleEntity> getTacheRealise(){
        return ersenTacheCentraleRepository.getTacheRealise();
    }

    /** Permet:
     * - de valider(valide=2) ou invalider(valide=3) une tache
     * - de clore une notification liée a une tache
     *   et trace l'action effectuée dans la table TraceTache avec type='t' pour tache tracée et type='n' pour notif tracée*/
    @GetMapping(value = {
            "/validerTache/{idTacheC}/{valide}/{tacheDep}",//valider, invalider tache (respectivement 2, 3) -- tacheDep (respectivement 0 if true, 1 if false)
            "/cloreTacheNotif/{idTacheC}/{objet}/{idNotif}"//clore une notification liee a une tache
    })
    public Boolean setTacheCentraleValider(@PathVariable String idTacheC,@PathVariable(required = false) String objet, @PathVariable(required = false) Integer valide,@PathVariable(required = false) String idNotif,@PathVariable(required = false) Integer tacheDep){
        ErsenTacheCentraleEntity tacheCentrale=ersenTacheCentraleRepository.findById(idTacheC).get();
        ErsenConfigCentraleTacheEntity cct = ersenConfigCentraleTacheRepository.findById(tacheCentrale.getConfigcentraletache()).get();
        if(idNotif==null)
        {
            if(tacheDep==0) //valider tache depassee
                ersenBackupPreuveAndNotifTacheRepository.validerDateBackup(idTacheC,valide);
            else //valider une tache
            {
                ersenTacheCentraleRepository.validerTacheCentrale(idTacheC, valide);

                if (valide == 2)
                   ersenPerfController.incrementNbExec(ersenPerfCalculRepository.getPerfCalculsUserId(idTacheC,ersenTacheCentraleRepository.findTacheCentraleById(idTacheC).getDateeffectuee()), idTacheC);
            }
            ersenTraceTacheRepository.save(new ErsenTraceTache(idTacheC, valide, 't',new Date()));
        }
        else
        {   //Clore une notif
            ersenTacheCentraleRepository.validerTacheCentrale(idTacheC, 2);
            ersenTraceTacheRepository.save(new ErsenTraceTache(idTacheC,2,'n',  new Date()));
            ersenNotificationRepository.updateNotification(idNotif, objet, new Date());
        }
        return true;

    }



    /**recueillir les ids des taches validées: utiliser par le mobile*/
    @PostMapping(value = "/validated_tasks")
    public List<TraceTache> getTraces(@RequestBody List<String> idTs){
        List<TraceTache> validatedTasks = ersenTraceTacheRepository.getValidatedTasksId(idTs);
        ersenTraceTacheRepository.deleteAllById(ersenTraceTacheRepository.getReplicTracesId(idTs));
        return validatedTasks;
    }

    /** create new traceTache*/
    @PostMapping(value = "/new_trace_task")
    public ErsenTraceTache setNewTraceTask(@RequestBody ErsenTraceTache traceTache){
        traceTache.setDateAction(new Date());
        return ersenTraceTacheRepository.save(traceTache);
    }

    /**
     * idCs_PlanMs_TasksID est la concatenation respective de variables suivantes: idC, planM,de 2 a idCPlanTaksID.length-1 : de nouvelles idTaches
     * Selon
     * idCPlanTaksID[0]=idC,
     * idCPlanTaksID[1]=planM,
     * de idCPlanTaksID=2 a idCPlanTaksID.length-1 : des idTaches
     */
    @GetMapping(value = "/centrales/newtache/{idCs_PlanMs_TasksID}",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public NewTasksAndPlans getCentralsNewTask(@PathVariable String idCs_PlanMs_TasksID) {
        String[] idCPlanMTaksID;
        List<String> idOldTasks;
        List<Tache> centralesNewTasks;
        List<CentralPlanMChange> centralPlanMChanges=new ArrayList<>();
        List<Tache> newTachesForMobile = new ArrayList<>();
        List<String> idNewTaches=new ArrayList<>();
        String currentCentralPlan;
        String[] idCsPlanMsTasksIDAsArray= idCs_PlanMs_TasksID.split(";");

        for(String idCandPlanMasString : idCsPlanMsTasksIDAsArray){
            idCPlanMTaksID= idCandPlanMasString.split(",");
            idOldTasks = new ArrayList<>(Arrays.asList(idCPlanMTaksID).subList(2, idCPlanMTaksID.length));
            currentCentralPlan=ersenCentraleRepository.getCentralsPM(idCPlanMTaksID[0]);

            if(!idCPlanMTaksID[1].equals(currentCentralPlan)){
                centralPlanMChanges.add(new CentralPlanMChange(idCPlanMTaksID[0],currentCentralPlan));
                centralesNewTasks = ersenTacheCentraleRepository.getTasksListByCentrale(idCPlanMTaksID[0],currentCentralPlan);
            }
            else if(!idOldTasks.isEmpty())
                centralesNewTasks = ersenTacheCentraleRepository.getCentralsNewTasks(idCPlanMTaksID[0], idCPlanMTaksID[1], idOldTasks);
            else
                centralesNewTasks = ersenTacheCentraleRepository.getTasksListByCentrale(idCPlanMTaksID[0], idCPlanMTaksID[1]);

            for (Tache tache : centralesNewTasks) {
                tache.setImageAsBase64(ersenUtilisateurController.convertFileToBase64(tache.getImageAsBase64()));
                idNewTaches.add(tache.getTacheId());
            }
            newTachesForMobile.addAll(centralesNewTasks);
        }

        return new NewTasksAndPlans(newTachesForMobile,new ArrayList<>(ersenUtilisateurController.getTasksTutos(idNewTaches)),centralPlanMChanges);
    }

    /**Retourne la liste des tache centrales supprimees au mobiles*/
    @GetMapping(value = "/centrale/detetedtaches/{idC_PlanM_TasksID}",produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    public List<String> getDeletedTaksIDs(@PathVariable String idC_PlanM_TasksID){
        /* The following field holds old tasks ids at the begining of the method
           and deleted task ids at the end*/
        List<String> deletedTaksID;
        List<String> notDeletedTaskIDS;
        String[] idCPlanMTaksID= idC_PlanM_TasksID.split(",");
        deletedTaksID = new ArrayList<>(Arrays.asList(idCPlanMTaksID).subList(2, idCPlanMTaksID.length));
        notDeletedTaskIDS = ersenTacheCentraleRepository.getNotDeletedTask(idCPlanMTaksID[0],idCPlanMTaksID[1],deletedTaksID);
        List<String> finalNotDeletedTaskIDS = notDeletedTaskIDS;
        deletedTaksID.removeIf(finalNotDeletedTaskIDS::contains);
        //vider les traces des taches centrales supprimees
        ersenTraceTacheRepository.deleteAllById(ersenTraceTacheRepository.getReplicTracesId(deletedTaksID));
        return deletedTaksID;
    }

    @GetMapping(value = "/replanify/taches/{idTachesC}",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    public List<ErsenTraceTacheReplanifier> getReplanifyIDs(@PathVariable List<String> idTachesC){
        ersenTraceTacheReplanifierRepository.deleteTacheCAfterReplanify(idTachesC);
        return ersenTraceTacheReplanifierRepository.getReplanifyTacheC(idTachesC);
    }

    @DeleteMapping("/tachecentrales/delete/{id}")
    public Boolean deleteTacheCentrale(@PathVariable("id") String id){
        String cct = ersenTacheCentraleRepository.getById(id).getConfigcentraletache();
        ersenTacheCentraleRepository.deleteById(id);
        ersenConfigCentraleTacheRepository.deleteById(cct);
        return true;
    }

    @PostMapping("/tachecentrales/deletemore")
    public Boolean deleteMore(@RequestBody List<String> tcsList){
        for(String tc : tcsList){
            deleteTacheCentrale(tc);
        }
        return true;
    }

    @GetMapping(value = "/tachenotcentrale/{centrale}/{plan_m}")
    public List<ErsenTacheEntity> getTacheListe(@PathVariable String centrale,@PathVariable String plan_m){
        return ersenTacheRepository.getTacheNotCentrale(centrale,plan_m);
    }

    //28/03/2022
    @PutMapping(value = "planifier/{configcentraletache}/{idPeriodicite}/{tutoriel}")
    public Boolean updateconfig(@PathVariable String configcentraletache, @PathVariable String idPeriodicite, @PathVariable String tutoriel) throws ParseException {
        Integer idConducteur =ersenTacheCentraleRepository.getConducteurByCCT(configcentraletache);
        ersenTacheCentraleRepository.updatecct(configcentraletache,tutoriel);
        ersenTacheCentraleRepository.updatetc(configcentraletache, idPeriodicite);
        ersenTacheCentraleRepository.setTacheCentralsDateNextPlanif(configcentraletache,calculDateNextPlanification(ersenConfigCentraleTacheRepository.getPeriodiciteValeur(idPeriodicite), new Date()));
        ersenPerfController.setTacheCFrequenceAndNbJourRes(ersenTacheCentraleRepository.getTacheCentraleIdByConfigTacheC(configcentraletache), ersenConfigCentraleTacheRepository.getPeriodiciteValue(idPeriodicite),ersenTacheCentraleRepository.getCentraleIDByCCT(configcentraletache));
        return true;
    }

    @PutMapping(value = "planifiermore/{idPeriodicite}/{tutoriel}")
    public Boolean updateconfigMore(@RequestBody List<String> ccts, @PathVariable String idPeriodicite, @PathVariable String tutoriel) throws ParseException {
        for(String cct : ccts) {
            updateconfig(cct,idPeriodicite,tutoriel);
        }
        return true;
    }

    boolean areAllTasksExecuted(String idC,String planM,List<String> idOldTasks){
        return ersenTacheCentraleRepository.countTacheRealisees(idC,planM)==idOldTasks.size();
    }

    @PostMapping(value = "/replicOperateurActionFromMobile")
    void replicOperateurActionFromMobile(@RequestBody List<ErsenTraceTache> traceTaches){
        for(ErsenTraceTache tt : traceTaches){
            if(tt.getIdTache() !=null){
                if(tt.getType()=='t') {
                    ersenTacheCentraleRepository.validerTacheCentrale(tt.getIdTache(), tt.getValide());
                    if(tt.getValide()==3) {
                        ersenPreuveTacheRepository.deletePreuveByIdTacheC(tt.getIdTache());
                        ersenFichierRepository.deleteEntitiesFiles(ersenPreuveTacheRepository.getPreuveTacheID(tt.getIdTache()));
                    }
                }
                else {
                    ersenTacheCentraleRepository.validerTacheCentrale(tt.getIdTache(), 2);
                    ersenNotificationRepository.updateNotification(tt.getIdNotif(), tt.getObjet(), new Date());
                }
            }
            else
                ersenNotificationRepository.updateNotification(tt.getIdNotif(),tt.getObjet(), new Date());
        }
        ersenTraceTacheRepository.saveAll(traceTaches);
    }

    @PostMapping(value = "/executed_tasks",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    List<TacheAndEtatForOprateurMobile> getTacheEffectueeForAperateurMobile(@RequestBody List<String> idTacheFromMobile){
        return ersenTacheCentraleRepository.getTacheEffectueeForAperateurMobil(idTacheFromMobile).stream().map(tAndE -> {
            if(tAndE.getEtat().equals("40288187786932bd01786935173c0000")) {
                tAndE.setEtat("Réalisée");
                ErsenPreuveTacheEntity preuveEntity = ersenPreuveTacheRepository.getErsenPreuveTacheEntityByTachecentre(tAndE.getIdTacheC());
                tAndE.setPreuvesOrNotifs(new Preuve(preuveEntity.getId(), preuveEntity.getTachecentre(), preuveEntity.getDatesave(), preuveEntity.getDateupdate(),preuveEntity.getIsSync(), ersenUtilisateurController.getEntitiesFilesInBase64Format(preuveEntity.getId())));
            }
            else
            {
                tAndE.setEtat("Panne constatée sur la section");
                ErsenNotificationEntity notifEntity = ersenNotificationRepository.getNotifByTacheC(tAndE.getIdTacheC());
                tAndE.setPreuvesOrNotifs(new Notification(notifEntity.getId(), notifEntity.getImpact(), notifEntity.getLibelle(), notifEntity.getObjet(), notifEntity.getIdTacheOrSectM(), notifEntity.getDateSave(), notifEntity.getIsRelative(),notifEntity.getIsSync(),notifEntity.getCentralid(),ersenUtilisateurController.getEntitiesFilesInBase64Format(notifEntity.getId())));
            }
            return tAndE;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/tachecentrale/delete/{id}")
    @Transactional
    public void deletePannaux(@PathVariable String id){
        ErsenTacheCentraleEntity tacheCentrale = ersenTacheCentraleRepository.getById(id);
        ersenTacheCentraleRepository.deleteById(id);
        ersenConfigCentraleTacheRepository.deleteById(tacheCentrale.getConfigcentraletache());
    }
}
