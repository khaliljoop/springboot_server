package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenAnneeEntity;
import sn.ssi.ersen.entity.ErsenSectionmaintenanceEntity;

import java.util.List;

@RepositoryRestResource
public interface ErsenSectionMaintenanceRepository extends JpaRepository<ErsenSectionmaintenanceEntity,String> {
    @Query(value = "SELECT * FROM `ersen_sectionmaintenance` WHERE id NOT IN\n" +
            " (SELECT sectionmid FROM `ersen_config_centrale_sectionm` WHERE centrale_id=:centrale_id)", nativeQuery = true)
    List<ErsenSectionmaintenanceEntity> getSectionNotInCentrale(String centrale_id);
}
