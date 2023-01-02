package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenConfigCentraleTacheEntity;

import java.util.List;

@Repository
public interface ErsenConfigCentraleTacheRepository extends JpaRepository<ErsenConfigCentraleTacheEntity,String> {
    @Query(value = "select period.valeur from ErsenPeriodiciteEntity period where period.id=:idPeriodicite")
    Integer getPeriodiciteValeur(String idPeriodicite);

    @Query(value = "select period.valeur from ErsenPeriodiciteEntity period where period.id=:idPeriodicite")
    int getPeriodiciteValue(String idPeriodicite);

    @Query(value = "select cct.periodicite from ErsenConfigCentraleTacheEntity cct,ErsenTacheCentraleEntity tc where tc.configcentraletache=cct.id and tc.id=:idTacheC")
    String getPeriodiciteID(String idTacheC);
    @Query(value = "select * from ersen_config_centrale_tache cct where id in (" +
            "select configcentraletache from ersen_tache_centrale tc where tc.type=1" +
            ")", nativeQuery = true)
    List<ErsenConfigCentraleTacheEntity> getAllSpontaneousCCT();



}
