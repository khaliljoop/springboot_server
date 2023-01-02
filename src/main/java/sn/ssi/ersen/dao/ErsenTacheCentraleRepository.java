package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenBackupTacheEntity;
import sn.ssi.ersen.entity.ErsenTacheCentraleEntity;
import sn.ssi.ersen.entity.entitieMobile.Tache;
import sn.ssi.ersen.entity.entitieMobile.TacheAndEtatForOprateurMobile;
import sn.ssi.ersen.entity.forWeb.SpontaneousTaskCentrale;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface ErsenTacheCentraleRepository extends JpaRepository<ErsenTacheCentraleEntity,String> {

    @Query(value = "select tc.id from ErsenTacheCentraleEntity tc where tc.configcentraletache=:configTacheC")
    String getTacheCentraleIdByConfigTacheC(String configTacheC);

    @Query(value = "select tc.dateNextPlanif from ErsenTacheCentraleEntity tc where tc.id=:idTacheC")
    Date getTacheCDateFin(String idTacheC);

    @RestResource
    @Query(value = "SELECT new sn.ssi.ersen.entity.entitieMobile.Tache(tc.id, t.nom, sm.id,et.libelle, et.id,p.libelle, sm.urlimage, tc.dateeffectuee, tc.datesave, tc.valide, p.valeur, c.id,tc.dateNextPlanif,tc.tutoriel,tc.type,tc.planM) " +
            "FROM  ErsenTacheEntity t, ErsenConfigCentraleTacheEntity cct, ErsenCentraleEntity c, ErsenTacheCentraleEntity tc,\n" +
            "ErsenSectionmaintenanceEntity sm, ErsenEtattacheEntity et,ErsenPeriodiciteEntity p " +
            "WHERE cct.centrale_or_entreprise=:idC AND tc.planM=:planM AND t.sectionmaintenance=sm.id AND cct.centrale_or_entreprise=c.id AND cct.id=tc.configcentraletache AND t.id=cct.tache AND tc.configcentraletache=cct.id AND" +
            " t.sectionmaintenance=sm.id AND tc.etattache=et.id AND cct.periodicite=p.id")
    List<Tache> getTasksListByCentrale(String idC,String planM);

    @RestResource
    @Query(value = "SELECT new sn.ssi.ersen.entity.entitieMobile.Tache(bt.idTacheDep,bt.tacheId, t.nom, sm.id,et.libelle, et.id,p.libelle, sm.urlimage, bt.dateeffectuee, bt.dateBackup, bt.valideBackup, p.valeur, c.id,bt.dateUltime,bt.tutoriel,bt.type,bt.planM) FROM  ErsenTacheEntity t, ErsenConfigCentraleTacheEntity cct, ErsenCentraleEntity c, ErsenBackupTacheEntity bt,\n" +
            "ErsenSectionmaintenanceEntity sm, ErsenEtattacheEntity et,ErsenPeriodiciteEntity p WHERE cct.centrale_or_entreprise=:idC AND bt.planM=:planM AND t.sectionmaintenance=sm.id AND cct.centrale_or_entreprise=c.id AND cct.id=bt.configcentraletache AND t.id=cct.tache AND bt.configcentraletache=cct.id AND" +
            " t.sectionmaintenance=sm.id AND bt.etattache=et.id AND cct.periodicite=p.id and bt.estArchiver=false")
    List<Tache> getBackUpTasksListByCentrale(String idC,String planM);

    @RestResource
    @Query(value = "SELECT new sn.ssi.ersen.entity.entitieMobile.Tache(bt.idTacheDep,bt.tacheId, t.nom, sm.libelle,et.libelle, et.id,p.libelle, sm.urlimage, bt.dateeffectuee, bt.dateBackup, bt.valideBackup, p.valeur, c.id,bt.dateUltime,bt.tutoriel,bt.type,bt.planM) FROM  ErsenTacheEntity t, ErsenConfigCentraleTacheEntity cct, ErsenCentraleEntity c, ErsenBackupTacheEntity bt,\n" +
            "ErsenSectionmaintenanceEntity sm, ErsenEtattacheEntity et,ErsenPeriodiciteEntity p WHERE t.sectionmaintenance=sm.id AND cct.centrale_or_entreprise=c.id AND cct.id=bt.configcentraletache AND t.id=cct.tache AND bt.configcentraletache=cct.id AND" +
            " t.sectionmaintenance=sm.id AND bt.etattache=et.id AND cct.periodicite=p.id and bt.estArchiver=false")
    List<Tache> getBackUpTasksListWeb();

    @RestResource
    @Query(value = "SELECT new sn.ssi.ersen.entity.entitieMobile.Tache(bt.idTacheDep,bt.tacheId, t.nom, sm.libelle,et.libelle, et.id,p.libelle, sm.urlimage, bt.dateeffectuee, bt.dateBackup, bt.valideBackup, p.valeur, c.id,bt.dateUltime,bt.tutoriel,bt.type,bt.planM) FROM  " +
            "ErsenTacheEntity t, ErsenConfigCentraleTacheEntity cct, ErsenCentraleEntity c, ErsenBackupTacheEntity bt, " +
            "ErsenSectionmaintenanceEntity sm, ErsenEtattacheEntity et,ErsenPeriodiciteEntity p, UtilisateurEntity u " +
            "WHERE u.usrId=:user AND u.entreprise=c.entreprise AND t.sectionmaintenance=sm.id AND cct.centrale_or_entreprise=c.id AND cct.id=bt.configcentraletache AND t.id=cct.tache AND bt.configcentraletache=cct.id AND" +
            " t.sectionmaintenance=sm.id AND bt.etattache=et.id AND cct.periodicite=p.id and bt.estArchiver=false")
    List<Tache> getBackUpTasksListByCentraleWebByUser(String user);

    @RestResource
    @Query(value = "SELECT new sn.ssi.ersen.entity.entitieMobile.Tache(bt.idTacheDep,bt.tacheId, t.nom, sm.libelle,et.libelle, et.id,p.libelle, sm.urlimage, bt.dateeffectuee, bt.dateBackup, bt.valideBackup, p.valeur, c.id,bt.dateUltime,bt.tutoriel,bt.type,bt.planM) FROM  " +
            "ErsenTacheEntity t, ErsenConfigCentraleTacheEntity cct, ErsenCentraleEntity c, ErsenBackupTacheEntity bt, " +
            "ErsenSectionmaintenanceEntity sm, ErsenEtattacheEntity et,ErsenPeriodiciteEntity p, UtilisateurEntity u " +
            "WHERE c.entreprise=:entr and u.entreprise=c.entreprise AND t.sectionmaintenance=sm.id AND cct.centrale_or_entreprise=c.id AND cct.id=bt.configcentraletache AND t.id=cct.tache AND bt.configcentraletache=cct.id AND" +
            " t.sectionmaintenance=sm.id AND bt.etattache=et.id AND cct.periodicite=p.id and bt.estArchiver=false")
    List<Tache> getBackUpTasksListWebByEntr(String entr);

    @RestResource
    @Query(value = "SELECT distinct new sn.ssi.ersen.entity.entitieMobile.Tache(tc.id, t.nom, sm.id,et.libelle, et.id,p.libelle, sm.urlimage, tc.dateeffectuee, tc.datesave, tc.valide, p.valeur, c.id,tc.dateNextPlanif,tc.tutoriel,tc.type,tc.planM) FROM  ErsenTacheEntity t, ErsenConfigCentraleTacheEntity cct, ErsenCentraleEntity c, ErsenTacheCentraleEntity tc,\n" +
            "ErsenSectionmaintenanceEntity sm, ErsenEtattacheEntity et,ErsenPeriodiciteEntity p WHERE cct.centrale_or_entreprise=:idC AND tc.planM=:planM AND t.sectionmaintenance=sm.id AND cct.centrale_or_entreprise=c.id AND cct.id=tc.configcentraletache AND t.id=cct.tache AND tc.configcentraletache=cct.id AND" +
            " t.sectionmaintenance=sm.id AND tc.etattache=et.id AND cct.periodicite=p.id and tc.id not in (:tasksID)")
    List<Tache> getCentralsNewTasks(String idC,String planM,List<String> tasksID);

    @Query(value = "SELECT count(tc) from ErsenTacheCentraleEntity tc,ErsenConfigCentraleTacheEntity cct where tc.configcentraletache=cct.id " +
            "and cct.centrale_or_entreprise=:idC and tc.planM=:planM and tc.etattache <> 'de977c2878f70a660178f8888e350007'")
    Integer countTacheRealisees(String idC,String planM);

    @RestResource
    @Query(value = "SELECT tc.id FROM  ErsenTacheEntity t, ErsenConfigCentraleTacheEntity cct, ErsenCentraleEntity c, ErsenTacheCentraleEntity tc,\n" +
            "ErsenSectionmaintenanceEntity sm, ErsenEtattacheEntity et,ErsenPeriodiciteEntity p WHERE cct.centrale_or_entreprise=:idC AND t.sectionmaintenance=sm.id AND cct.centrale_or_entreprise=c.id AND cct.id=tc.configcentraletache AND t.id=cct.tache AND tc.configcentraletache=cct.id and tc.planM=:planM AND" +
            " t.sectionmaintenance=sm.id AND tc.etattache=et.id AND cct.periodicite=p.id and tc.id in (:oldTasksID)")
    List<String> getNotDeletedTask(String idC,String planM,List<String> oldTasksID);

    @Query(value = "SELECT tc FROM ErsenTacheCentraleEntity tc WHERE tc.id=:id")
    ErsenTacheCentraleEntity findTacheCentraleById(String id);

    @Query(value = "SELECT bt FROM ErsenBackupTacheEntity bt WHERE bt.idTacheDep=:idTacheDep")
    ErsenBackupTacheEntity findBackUpTacheId(String idTacheDep);

    @RestResource
    @Query(value = "SELECT * FROM ersen_tache_centrale\n" +
            "WHERE etattache LIKE '40288187786932bd01786935173c0000'\n" +
            "AND valide =1\n",nativeQuery = true)
    List<ErsenTacheCentraleEntity> getTacheRealise();

    @Query("SELECT tc FROM ErsenTacheCentraleEntity tc, ErsenConfigCentraleTacheEntity  cct " +
            "WHERE tc.configcentraletache=cct.id AND tc.planM=:planM AND cct.centrale_or_entreprise=:centrale AND cct.periodicite IS NOT NULL AND tutoriel IS NOT NULL")
    List<ErsenTacheCentraleEntity> getTacheCentraleByPlanAndCentrale(String planM, String centrale);

    @Query(value = "SELECT tc FROM ErsenTacheCentraleEntity tc WHERE  tc.type=0 and tc.dateNextPlanif=:dateNexPlanification")
    List<ErsenTacheCentraleEntity> getTacheCentralesToReplanify(Date dateNexPlanification);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ErsenTacheCentraleEntity tc SET tc.valide=:valide,tc.dateupdate=now() WHERE tc.id=:id")
    void validerTacheCentrale(String id,int valide);

    @Query(value = "select new sn.ssi.ersen.entity.entitieMobile.TacheAndEtatForOprateurMobile(tc.id,tc.etattache,tc.dateeffectuee) from ErsenTacheCentraleEntity tc WHERE tc.id in :idTacheFromMobile and tc.valide=1")
    List<TacheAndEtatForOprateurMobile> getTacheEffectueeForAperateurMobil(List<String> idTacheFromMobile);

    @RestResource
    @Query(value = "SELECT tc.id idtacheC,tc.commentaire,tc.datesave,tc.configcentraletache,cct.centrale_or_entreprise,cct.tache,cct.periodicite,c.nom nomcentrale,t.nom nomtache ,tc.plan_m plan_maintenance,tc.configcentraletache gg,c.idgerant\n" +
            "FROM `ersen_config_centrale_tache` cct,`ersen_tache_centrale` tc,`ersen_centrale` c,`ersen_tache` t\n" +
            "WHERE tc.configcentraletache=cct.id\n" +
            "AND cct.tache=t.id\n" +
            "AND cct.centrale_or_entreprise=c.id\n" +
            "order by tc.datesave DESC",nativeQuery = true)
    List<Object> getconfigCentraleTachePeriode();

    @Query(value = "SELECT tc.id idtacheC,tc.commentaire,tc.datesave,tc.configcentraletache,cct.centrale_or_entreprise,cct.tache,cct.periodicite,c.nom nomcentrale,t.nom nomtache ,tc.plan_m plan_maintenance \n" +
            "FROM `ersen_config_centrale_tache` cct,`ersen_tache_centrale` tc,utilisateur u, `ersen_centrale` c,`ersen_tache` t\n" +
            "WHERE tc.configcentraletache=cct.id\n" +
            "AND cct.tache=t.id\n" +
            "AND cct.centrale_or_entreprise=c.id\n" +
            "AND u.entreprise=c.entreprise\n" +
            "AND u.usr_id=:id"+
            " order by tc.datesave DESC",nativeQuery = true)
    List<Object> getconfigCentraleTachePeriodeByUtilisateur(String id);

    @Query(value = "select * from ersen_tache_centrale tc where tc.type=1 ", nativeQuery = true)
    List<ErsenTacheCentraleEntity> getAllSpontaneousTC();

    @Query(value = "select new  sn.ssi.ersen.entity.forWeb.SpontaneousTaskCentrale(tc.id, cct.id, tc.datesave, tc.dateeffectuee, tc.valide, tc.tutoriel, tc.dateNextPlanif, tc.requestName, cct.centrale_or_entreprise, cct.for_oper) from ErsenTacheCentraleEntity tc , ErsenConfigCentraleTacheEntity cct where tc.configcentraletache=cct.id and tc.type=1 and cct.for_oper=:for_oper")
    List<SpontaneousTaskCentrale> getAllSponteneousTasks(Integer for_oper);

    @Query(value = "select new  sn.ssi.ersen.entity.forWeb.SpontaneousTaskCentrale(tc.id, cct.id, tc.datesave, tc.dateeffectuee, tc.valide, tc.tutoriel, tc.dateNextPlanif, tc.requestName, cct.centrale_or_entreprise, cct.for_oper) from ErsenTacheCentraleEntity tc , ErsenConfigCentraleTacheEntity cct where tc.configcentraletache=cct.id and tc.type=1 and cct.for_oper=:for_oper and cct.centrale_or_entreprise=:centrale")
    List<SpontaneousTaskCentrale> getAllSponteneousTasksByCentrale(Integer for_oper, String centrale);

    @Query(value = "select new sn.ssi.ersen.entity.entitieMobile.Tache(tc.id, tc.requestName,et.libelle, tc.etattache, tc.dateeffectuee, tc.datesave, tc.valide, cct.centrale_or_entreprise,tc.dateNextPlanif,tc.tutoriel,tc.type) from ErsenTacheCentraleEntity tc , ErsenEtattacheEntity et,ErsenConfigCentraleTacheEntity cct where tc.configcentraletache=cct.id and et.id=tc.etattache and tc.type=1 and cct.for_oper=:for_oper and cct.centrale_or_entreprise=:idCentralOrEntreprise")
    List<Tache> getCentralOrEntrepriseSponteneousTasksForMobile(Integer for_oper, String idCentralOrEntreprise);

    @Query(value = "select new sn.ssi.ersen.entity.entitieMobile.Tache(tc.id, tc.requestName,et.libelle, tc.etattache, tc.dateeffectuee, tc.datesave, tc.valide, cct.centrale_or_entreprise,tc.dateNextPlanif,tc.tutoriel,tc.type) from ErsenTacheCentraleEntity tc , ErsenEtattacheEntity et,ErsenConfigCentraleTacheEntity cct where tc.configcentraletache=cct.id and et.id=tc.etattache and tc.type=1 and cct.for_oper=0 and tc.valide=1 and cct.centrale_or_entreprise in :idCentrals")
    List<Tache> getConductSponteneousTasksOperaHaveToValidateFromMobile(List<String> idCentrals);

    @Query(value = "select new sn.ssi.ersen.entity.entitieMobile.Tache(tc.id, tc.requestName,et.libelle, tc.etattache, tc.dateeffectuee, tc.datesave, tc.valide, cct.centrale_or_entreprise,tc.dateNextPlanif,tc.tutoriel,tc.type) from ErsenTacheCentraleEntity tc , ErsenEtattacheEntity et,ErsenConfigCentraleTacheEntity cct where tc.configcentraletache=cct.id and et.id=tc.etattache and tc.type=1 and tc.valide=1 and cct.for_oper=0 and cct.centrale_or_entreprise in :idCentrals and tc.id not in :oldSpontaneousTaskIds")
    List<Tache> getConductNewSponteneousTasksOperaHaveToValidateFromMobile(List<String> idCentrals,List<String> oldSpontaneousTaskIds);

    @Query(value = "select new sn.ssi.ersen.entity.entitieMobile.Tache(tc.id, tc.requestName,et.libelle, tc.etattache, tc.dateeffectuee, tc.datesave, tc.valide, cct.centrale_or_entreprise,tc.dateNextPlanif,tc.tutoriel,tc.type) from ErsenTacheCentraleEntity tc , ErsenEtattacheEntity et,ErsenConfigCentraleTacheEntity cct where tc.configcentraletache=cct.id and et.id=tc.etattache and tc.type=1 and cct.for_oper=:for_oper and cct.centrale_or_entreprise=:idCentralOrEntreprise and tc.id not in :oldSpontaneousTaskIds")
    List<Tache> getNewCentralOrEntrepriseSponteneousTasksForMobile(Integer for_oper, String idCentralOrEntreprise,List<String> oldSpontaneousTaskIds);

    @Query(value = "select tc.id from ErsenTacheCentraleEntity tc where tc.id in :oldSpontaneousTaskIds")
    List<String> getNotDeletedSponteneousTasksForMobile(List<String> oldSpontaneousTaskIds);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ersen_tache_centrale SET tutoriel=:tutoriel, dateupdate=now() where" +
            " configcentraletache=:configcentraletache",nativeQuery = true)
    void updatecct(String configcentraletache,String tutoriel);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ErsenTacheCentraleEntity tc SET tc.dateNextPlanif=:dateNextPlanif where tc.configcentraletache=:configcentraletache")
    void setTacheCentralsDateNextPlanif(String configcentraletache, Date dateNextPlanif);



    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ersen_config_centrale_tache set periodicite=:periodicite " +
            "  where id=:configcentraletache",nativeQuery = true)
    void updatetc(String configcentraletache,String periodicite);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ersen_config_centrale_tache set periodicite=:periodicite " +
            "  where id=:configcentraletache",nativeQuery = true)
    void deletetc(String configcentraletache,String periodicite);

    @Query("select id from ErsenTacheCentraleEntity")
    List<String> getTacheCentralsIDS();

    @RestResource
    @Query(value = "SELECT u.usr_id FROM ersen_config_centrale_tache cct, ersen_utilisateur_centrale uc, ersen_typeutilisateur tu, utilisateur u WHERE cct.centrale_or_entreprise=uc.centrale and u.USR_ID=uc.utilisateur and u.typeutilisateur=tu.Id and tu.Id='402881877869fac6017869fbd4a10000'  and cct.id=:cct limit 1", nativeQuery = true)
    Integer getConducteurByCCT(String cct);

    @Query(value = "SELECT cct.centrale_or_entreprise FROM ErsenConfigCentraleTacheEntity cct WHERE cct.id=:cct")
    String getCentraleIDByCCT(String cct);

    @Query("SELECT tc FROM ErsenTacheCentraleEntity tc, ErsenConfigCentraleTacheEntity  cct " +
            "WHERE tc.configcentraletache=cct.id AND tc.planM=:planM AND cct.centrale_or_entreprise=:centrale AND cct.periodicite IS NOT NULL AND tutoriel IS NOT NULL")
    List<ErsenTacheCentraleEntity> getTacheCentralePlanifieesByPlanAndCentrale(String planM, String centrale);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ErsenTacheCentraleEntity tc set tc.dateFirstPlanif=now() where tc.id=:idTacheC")
    void upDateDateFirstPlanif(String idTacheC);
}
