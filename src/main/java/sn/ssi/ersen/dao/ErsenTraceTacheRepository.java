package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenTraceTache;
import sn.ssi.ersen.entity.entitieMobile.TraceTache;

import java.util.List;

public interface ErsenTraceTacheRepository extends JpaRepository<ErsenTraceTache,String> {
    //Pour les tache
    @Query(value = "select new sn.ssi.ersen.entity.entitieMobile.TraceTache(tt.idTache,tt.valide,tt.type) from ErsenTraceTache tt WHERE tt.idTache in (:ids)")
    List<TraceTache> getValidatedTasksId(List<String> ids);

    @Query(value = "select tt.id from ErsenTraceTache tt WHERE tt.idTache in (:ids)")
    List<String> getReplicTracesId(List<String> ids);

    //Pour les notifications
    @Query(value = "select new sn.ssi.ersen.entity.entitieMobile.TraceTache(tt.idNotif,tt.objet) from ErsenTraceTache tt WHERE tt.idNotif in (:notifIds)")
    List<TraceTache> getNotTaskClosedNotifsIds(List<String> notifIds);

    @Query(value = "select tt.id from ErsenTraceTache tt WHERE tt.idNotif in (:ids)")
    List<String> getReplicTracesIdsByNotifIDS(List<String> ids);
}
