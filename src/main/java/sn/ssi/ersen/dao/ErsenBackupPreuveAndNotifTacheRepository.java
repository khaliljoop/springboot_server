package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenBackupTacheEntity;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface ErsenBackupPreuveAndNotifTacheRepository extends JpaRepository<ErsenBackupTacheEntity, String> {
    @Query("select bt from ErsenBackupTacheEntity bt where bt.dateUltime=:dateUltime")
    List<ErsenBackupTacheEntity> getTacheDepToValidateToDay(Date dateUltime);

    @RestResource
    @Transactional
    @Modifying
    @Query(value = "UPDATE ErsenBackupTacheEntity bt SET bt.valideBackup=:valide WHERE bt.idTacheDep=:idTacheDep")
    void validerDateBackup(String idTacheDep, int valide);
}
