package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenTacheEntity;

import java.util.List;

@Repository
public interface ErsenTacheRepository extends JpaRepository<ErsenTacheEntity,String> {

    @RestResource
    @Query(value = "SELECT t.*,s.libelle,s.urlimage FROM ersen_tache t,ersen_sectionmaintenance s\n" +
            "WHERE t.sectionmaintenance=s.id",nativeQuery = true)
    List<Object> getTacheSectionMaintenance();

    @RestResource
    @Query(value = "select * from ersen_tache t where t.Id not in (\n" +
            "\t\t\tselect cct.tache from ersen_tache_centrale tc,ersen_config_centrale_tache cct where tc.configcentraletache=cct.Id\n" +
            "            and cct.centrale_or_entreprise=:centrale and  tc.plan_m=:plan_m\n" +
            " )",nativeQuery = true)
    List<ErsenTacheEntity> getTacheNotCentrale(String centrale,String plan_m);
}
