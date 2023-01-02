package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.dto.CentraleDto;
import sn.ssi.ersen.entity.ErsenCentraleEntity;
import sn.ssi.ersen.entity.ErsenPerfCalcul;
import sn.ssi.ersen.entity.entitieMobile.Centrale;

import javax.transaction.Transactional;
import java.util.List;
import sn.ssi.ersen.entity.entitieMobile.Centrale;

@RepositoryRestResource
public interface ErsenCentraleRepository extends JpaRepository<ErsenCentraleEntity,String>{

    @Query("select e.id from ErsenEntrepriseEntity e, ErsenCentraleEntity c WHERE e.id=c.entreprise AND c.id=:centrale")
    String getEntrepriseIdByCentrale(String centrale);

    @Query("select e.nom from ErsenEntrepriseEntity e, ErsenCentraleEntity c WHERE e.id=c.entreprise AND c.id=:centrale")
    String getEntrepriseNameByCentrale(String centrale);

    @Query("select c.etat_centrale from  ErsenCentraleEntity c WHERE c.id=:centrale")
    int getEtatCentrale(String centrale);

    @Query(value = "SELECT c.Id,c.latitude,c.longitude, c.nom, c.puissance, pm.libelle, c.entreprise,c.typecentrale,c.urlimage,c.etat_centrale,c.projet,1,2,c.date_service,pm.id\n" +
            "FROM ersen_centrale c LEFT JOIN ersen_plan_maintenance pm on pm.id=c.plan_maintenance LEFT JOIN ersen_operateur o \n" +
            "ON o.Id=c.operateur", nativeQuery = true)
    List<Object> getCentraleForMap();

    @RestResource
    @Query(value = "SELECT c.Id,c.latitude,c.longitude, c.nom, c.puissance, c.plan_maintenance, c.entreprise,c.typecentrale,c.urlimage,c.etat_centrale,c.projet,1,2,c.date_service\n" +
            "            FROM ersen_centrale c JOIN `utilisateur` u  \n" +
            "            ON c.entreprise=u.entreprise  AND u.usr_id=:utilisateur", nativeQuery = true)
    List<Object> getCentraleForMapByOperateur(Integer utilisateur);

    /////////////////
    @Query(value = "SELECT distinct new sn.ssi.ersen.entity.entitieMobile.Centrale(c.id,c.nom,c.urlimage,c.puissance,c.typecentrale,c.entreprise,c.projet,c.dateService,c.etat_centrale,c.plan_maintenance) " +
            "FROM ErsenCentraleEntity c,ErsenUtilisateurCentraleEntity uc " +
            "where uc.centrale=c.id AND uc.utilisateur=:utilisateur")
    List<Centrale> getCentraleForConducteur(Integer utilisateur);

    @Query(value = "SELECT new sn.ssi.ersen.entity.entitieMobile.Centrale(c.id,c.nom,c.urlimage,c.puissance,c.typecentrale,c.entreprise,c.projet,c.dateService,c.etat_centrale,c.plan_maintenance) " +
            "FROM ErsenCentraleEntity c where c.entreprise=:entreprise")
    List<Centrale> getCentraleForOperateur(String entreprise);

    @Query(value = "SELECT c.id FROM ErsenCentraleEntity c where c.entreprise=:entreprise")
    List<String> getEntrepriseCentralIDS(String entreprise);
    /////////////////

    @RestResource
    @Query(value = "SELECT DISTINCT t.Id tacheid,tc.Id tcid, c.Id centraleid,cct.Id cctid,t.nom nomtache,c.nom nomcentrale,tc.datesave,p.valeur,tc.tutoriel ti,tc.commentaire, sm.libelle sm,c.plan_maintenance, tc.dateeffectuee, tc.date_replanification\n"+
            "  FROM ersen_tache t, ersen_config_centrale_tache cct, ersen_tache_centrale tc, ersen_centrale c, ersen_periodicite p,ersen_sectionmaintenance sm \n" +
            "  WHERE t.Id = cct.tache and c.Id=cct.centrale_or_entreprise and cct.Id=tc.configcentraletache  and cct.periodicite=p.Id and t.sectionmaintenance=sm.id and cct.centrale_or_entreprise=:centrale AND tc.valide=:valide and tc.etattache!=\"40288187786932bd0178693533920001\"", nativeQuery = true)
    List<Object> getTaskByCentraleValide(String centrale,int valide);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "update ersen_centrale set urlimage=:urlimage where id=:id", nativeQuery = true)
    Integer updateurlimage(String urlimage, String id);

    @RestResource
    @Query(value = "SELECT DISTINCT c.* \n" +
            "from ersen_centrale c, ersen_config_centrale_tache cct, ersen_" +
            "tache_centrale tc\n" +
            "WHERE cct.centrale_or_entreprise=c.Id and tc.configcentraletache=cct.Id and tc.valide=:valide and tc.etattache!=\"40288187786932bd0178693533920001\"", nativeQuery = true)
    List<ErsenCentraleEntity> getCentraleHavingTaskByValide(int valide);

    @RestResource
    @Query(value = "SELECT DISTINCT c.* \n" +
            "from ersen_centrale c, ersen_config_centrale_tache cct, ersen_tache_centrale tc\n" +
            "WHERE cct.centrale_or_entreprise=c.Id and tc.configcentraletache=cct.Id and tc.valide=:valide and c.id=:idcentrale and tc.etattache!=\"40288187786932bd0178693533920001\"", nativeQuery = true)
    List<ErsenCentraleEntity> getCentraleHavingTaskByValideAndCentrale(int valide, String idcentrale);

    @RestResource
    @Query(value = "SELECT DISTINCT c.* \n" +
            "from ersen_centrale c, ersen_config_centrale_tache cct, ersen_tache_centrale tc, utilisateur u\n" +
            "WHERE cct.centrale_or_entreprise=c.Id and tc.configcentraletache=cct.Id and c.entreprise=u.entreprise and tc.valide=:valide and u.usr_id=:operateur and tc.etattache!=\"40288187786932bd0178693533920001\"", nativeQuery = true)
    List<ErsenCentraleEntity> getCentraleHavingTaskByValideAndOperateur(int valide, int operateur);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE `ersen_centrale` e SET e.plan_maintenance= :plan_maintenance, plan_last_change_date=now() WHERE e.Id=:Id", nativeQuery = true)
    Integer updatePlan(String plan_maintenance, String Id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ErsenCentraleEntity c SET c.etat_centrale= :etat_centrale WHERE c.id=:centraleID")
    Integer updateEtatCentrale(String centraleID,int etat_centrale);

    @Transactional
    @Modifying
    @Query(value = "update ersen_centrale set entreprise=:entreprise where id=:centrale", nativeQuery = true)
    Integer updateEntreprise(String centrale ,String entreprise);

    @Query(value = "select p.valeur from ersen_tache_centrale tc,ersen_config_centrale_tache cct, ersen_periodicite p where cct.periodicite=p.id and tc.configcentraletache=cct.id and tc.id=:idtc", nativeQuery = true)
    Integer getValPeriodiciteByTC(String idtc);


    @Query(value = "SELECT c.* from ersen_centrale c, utilisateur u " +
            "WHERE c.entreprise=u.entreprise and u.usr_id=:utilisateur",nativeQuery = true)
    List<ErsenCentraleEntity> getErsenCentraleEntitiesByUtilisateur(String utilisateur);

    @Query(value = "SELECT uc.centrale from ErsenUtilisateurCentraleEntity uc WHERE uc.utilisateur=:utilisateur")
    String getConductsCentralID(Integer utilisateur);

    @Query(value = "SELECT c.*  from ersen_centrale c, utilisateur u where c.entreprise=u.entreprise and u.usr_id=:utilisateur",nativeQuery = true)
    List<ErsenCentraleEntity> getErsenCentraleAllEntitiesByUtilisateur(String utilisateur);

    @Query(value = "select c.plan_maintenance from ErsenCentraleEntity c where c.id=:centralID")
    String getCentralsPM(String centralID);

    @Query(value = "SELECT centrale.id,centrale.adresse,centrale.coutinstallation,centrale.datedemarrage,centrale.datesave,centrale.dateupdate,centrale.latitude,centrale.longitude,centrale.nom,operateur.nom as operateur,centrale.puissance," +
            "typecentrale.libelle as typecentrale,village.libelle as village,region.libelle as region,centrale.urlimage,centrale.description," +
            "projet.nom as projet,centrale.idgerant,centrale.etat_centrale,centrale.plan_maintenance,centrale.date_service,centrale.plan_last_change_date  from ersen_centrale centrale,ersen_projet projet,ersen_region region,ersen_villagequartier village,ersen_typecentrale typecentrale,ersen_operateur operateur " +
            "where centrale.projet=projet.id and centrale.village=village.id and centrale.region=region.id and centrale.typecentrale=typecentrale.id and centrale.operateur=operateur.id",nativeQuery = true)
    List<ErsenCentraleEntity> getAllCentraleDTO();

 @Query(value = "SELECT centrale.id,centrale.adresse,centrale.coutinstallation,centrale.datedemarrage,centrale.datesave,centrale.dateupdate,centrale.latitude,centrale.longitude,centrale.nom,centrale.puissance,centrale.operateur," +
         "centrale.typecentrale,centrale.village,commune.libelle as commune,departement.libelle as departement,region.libelle as region,centrale.urlimage,centrale.description," +
         "projet.nom projet,centrale.etat_centrale,centrale.plan_maintenance,centrale.date_service,centrale.entreprise,centrale.plan_last_change_date from ersen_centrale centrale,ersen_commune commune,ersen_departement departement,ersen_region region,ersen_projet projet where centrale.commune=commune.id and centrale.departement=departement.id and centrale.region=region.id and centrale.projet=projet.id" ,nativeQuery = true)
 List<ErsenCentraleEntity> getAllCentraleDTOAdresse();

    @Query(value = "DELETE FROM equipenent_centrale WHERE centrale NOT IN (SELECT id FROM ersen_centrale)", nativeQuery = true)
    Void deleteCentraleLiaison();

    @Query(value = "SELECT DISTINCT pc FROM ErsenPerfCalcul pc, ErsenTacheCentraleEntity tc, ErsenConfigCentraleTacheEntity cct WHERE pc.idTacheC=tc.id and tc.planM=:plan and cct.centrale_or_entreprise=:centrale")
    List<ErsenPerfCalcul> getPerfCalculByPlanAndCentrale(String plan, String centrale);
}
