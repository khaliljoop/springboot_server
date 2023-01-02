package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenConductExecutionNb;
import sn.ssi.ersen.entity.ErsenPerfCalcul;
import sn.ssi.ersen.entity.ErsenPerfHistorique;
import sn.ssi.ersen.entity.UtilisateurEntity;
import sn.ssi.ersen.entity.entitieMobile.CentralePerf;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
@RestResource
public interface ErsenPerfCalculRepository extends JpaRepository<ErsenPerfCalcul,String> {

    //retourne le nombre de taches que l'utilisateur a pu executer dans le mois
    @Query("select ce.nbExec from  ErsenConductExecutionNb ce where ce.idUser=:idUser")
    int getNbTachesExecByUserThisMonth(int idUser);

    //retourne le nombre de taches que l'utilisateur devrait executer dans le mois
    @Query("select sum(pc.frequenceMensuelleMin) from  ErsenPerfCalcul pc where pc.centrale=:centrale")
    int getNbTachesTotalUserHaveToExecThisMonth(String centrale);

    //retourne le nombre de taches executer par l'utilisateur en un mois d'une annee donnée.
    @Query("select h.nbTasksExec from  ErsenPerfHistorique h where h.idUser=:idUser and h.idMois=:idMonth and h.idAnnee=:idAnnee")
    Integer getNbTachesUserExecutedInApecificMonth(int idUser,int idMonth,int idAnnee);

    //retourne le nombre de taches que l'utilisateur devrait executer dans le mois
    @Query("select h.nbTasksTotal from  ErsenPerfHistorique h where h.idUser=:idUser and h.idMois=:idMonth and h.idAnnee=:idAnnee")
    Integer getNbTachesTotalUserHaveExecInASpecificMonth(int idUser,int idMonth,int idAnnee);

    @Query("select pc from ErsenPerfCalcul pc where pc.idTacheC = :idTacheC")
    ErsenPerfCalcul getUserPerfAboutAspecificTask(String idTacheC);

    //retourne le nombre de jours restant pour une tache de l'utilisateur.
    @Query("select pc.nbJourRes from  ErsenPerfCalcul pc where pc.idTacheC=:idTacheC")
    int getNbJourResOfAUserTask(String idTacheC);

    //retourne le nombre de jours restant pour une tache de l'utilisateur
    @Query("select pc.first from  ErsenPerfCalcul pc where pc.idTacheC=:idTacheC")
    boolean checkIfFirst(String idTacheC);

    @Query("select new sn.ssi.ersen.entity.ErsenPerfHistorique(ce.idUser,ce.nbExec,sum(pc.frequenceMensuelleMin)) from ErsenPerfCalcul pc,ErsenConductExecutionNb ce where pc.centrale=ce.centrale group by ce.idUser")
    List<ErsenPerfHistorique> getAllUserPerfForBackUp();

    @Query("select ce from ErsenConductExecutionNb ce where ce.idUser=:userId")
    ErsenConductExecutionNb getUserErsenConductExecutionNb(Integer userId);

    @Transactional
    @Modifying
    @Query("update ErsenPerfCalcul pc set pc.nbJourRes=:nbJourRes,pc.frequenceMensuelleMin=:frequenceMensuelleMin,pc.first=false where pc.idTacheC=:idTacheC")
    void updateFrequenceNbExecTceMoisAndNbJourRest(int nbJourRes, int frequenceMensuelleMin, String idTacheC);

    //return l'id de l'utilisateur
    @Query("select p.expediteur from ErsenPreuveTacheEntity p where p.tachecentre=:idTacheC and p.estHistoriser=false and p.datesave=:datesave")
    Integer getPerfCalculsUserId(String idTacheC, Date datesave);

    @Query("select p from ErsenPerfCalcul p where p.centrale=:idCentrale")
    List<ErsenPerfCalcul> getPerfCalculsByCentral(String idCentrale);

    //vider les indicateurs de performance de l'utilisateur pour le mois
    @Modifying
    @Query("delete from ErsenPerfCalcul pc where pc.centrale=:centrale")
    void deleteUserPerfIndicator(String centrale);

    @Transactional
    @Modifying
    @Query(value = "delete from ErsenPerfCalcul pc where pc.idTacheC not in :idTacheCs")
    void deleteThesePerfCalcul(List<String> idTacheCs);

    //@Query("select new sn.ssi.ersen.entity.entitieMobile.CentralePerf(c.id,c.nom,u.usrId,u.usrNom,u.usrPrenom) from UtilisateurEntity u, ErsenUtilisateurCentraleEntity uc, ErsenCentraleEntity c where uc.utilisateur=u.usrId and u.typeutilisateur='402881877869fac6017869fbd4a10000' and u.entreprise=:entreprise and c.id=uc.centrale and uc.centrale in (SELECT DISTINCT centrale from ErsenUtilisateurCentraleEntity where utilisateur=u.usrId)")
    @Query("select new sn.ssi.ersen.entity.entitieMobile.CentralePerf(c.id,c.nom) from ErsenCentraleEntity c where c.entreprise=:entreprise")
    List<CentralePerf> getEntrepriseCentralsIDandName(String entreprise);

    @Query("select u from UtilisateurEntity u, ErsenUtilisateurCentraleEntity uc where uc.utilisateur=u.usrId and u.typeutilisateur='402881877869fac6017869fbd4a10000' and uc.centrale=:centraleID")
    List<UtilisateurEntity> getConducteursOfACentral(String centraleID);



    @Query("select new sn.ssi.ersen.entity.entitieMobile.CentralePerf(c.id,c.nom) from UtilisateurEntity u, ErsenUtilisateurCentraleEntity uc, ErsenCentraleEntity c where uc.utilisateur=u.usrId and u.typeutilisateur='402881877869fac6017869fbd4a10000' and c.id=uc.centrale and uc.centrale in (SELECT DISTINCT centrale from ErsenUtilisateurCentraleEntity where utilisateur=u.usrId)")
    List<CentralePerf> getConducteurPerfsForAdmin();
}
