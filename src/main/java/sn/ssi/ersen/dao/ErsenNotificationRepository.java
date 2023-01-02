package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenNotificationEntity;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface ErsenNotificationRepository extends JpaRepository<ErsenNotificationEntity, String> {
    @RestResource
    @Query(value = "select notif from ErsenNotificationEntity notif where notif.isSync=2 and notif.destinataire = :entreprise and notif.estHistoriser=false")
    List<ErsenNotificationEntity> getOperateurNotifs(String entreprise);

    @Query(value = "select notif from ErsenNotificationEntity notif where notif.isSync=2 and notif.destinataire = :entreprise and notif.estHistoriser=false and notif.id not in :oldNotifsIDs")
    List<ErsenNotificationEntity> getNewOperateurNotifs(String entreprise,List<String> oldNotifsIDs);

    @Query(value = "select notif from ErsenNotificationEntity notif where notif.idTacheOrSectM = :idTacheC and notif.estHistoriser=false")
    ErsenNotificationEntity getNotifByTacheC(String idTacheC);

    @Query(value = "select hasnotif from utilisateur where usr_id=:user", nativeQuery = true)
    Boolean getNew(Integer user);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "\n" +
            "UPDATE utilisateur u, (SELECT DISTINCT entreprise FROM utilisateur WHERE usr_id=:user) e\n" +
            " SET u.hasnotif=true \n" +
            "WHERE  (u.typeutilisateur=\"de977c2878f70a660178f87f84bd0000\" or u.typeutilisateur=\"de977c2878f70a660178f881d37a0001\")\n" +
            "AND u.entreprise =e.entreprise", nativeQuery = true)
    void updateUsersNotif(Integer user);

    @Query(value = "SELECT count(*) FROM `ersen_notification` WHERE is_sync=2", nativeQuery = true)
    Integer getNbNotifNonResolues();

    @Query(value = "SELECT count(*) FROM `ersen_notification` WHERE is_sync=2 and destinataire=:entreprise", nativeQuery = true)
    Integer getNbNotifNonResoluesByEntr(String entreprise);

    @Query(value = "select notif from ErsenNotificationEntity notif where notif.expediteur = :expediteur and notif.estHistoriser=false")
    List<ErsenNotificationEntity> getConductNotif(Integer expediteur);

    @Query(value = "select n.* from ersen_notification n, ersen_centrale c, utilisateur u " +
            "where n.centralid=c.id and u.entreprise=c.entreprise and u.usr_id=:operateur and n.est_historiser=false", nativeQuery = true)
    List<ErsenNotificationEntity> findNotifByOperateur(Integer operateur);

    @Query(value = "select n.* from ersen_notification n where n.est_historiser=false", nativeQuery = true)
    List<ErsenNotificationEntity> findAllNotif();


    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ErsenNotificationEntity n SET n.isSync=3,n.objet=:objet, n.dateResolue=:dateResolue where n.id=:id ")
    Integer updateNotification(String id, String objet, Date dateResolue);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ErsenNotificationEntity n SET n.objet=:objet where n.id=:id ")
    Integer updateCommentaire(String id, String objet);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "update UtilisateurEntity u set u.hasnotif=false where u.usrId=:user")
    Integer makeNewNotifFalse(Integer user);

    @Query(value = "select n.id from ErsenNotificationEntity n where n.idTacheOrSectM=:idTacheC ")
    String getNotifID(String idTacheC);

    @Transactional
    @Modifying
    @Query("DELETE FROM ErsenNotificationEntity n where n.idTacheOrSectM=:idTacheC")
    void deleteNotificationByIdTacheC(String idTacheC);

    @Transactional
    @Modifying
    @Query("update ErsenNotificationEntity n set n.estHistoriser=true where n.idTacheOrSectM=:idTacheC and n.estHistoriser=false")
    void updateNotificationByIdTacheC(String idTacheC);
}
