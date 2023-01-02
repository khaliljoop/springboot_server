package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenPlanMaintenanceEntity;

import java.util.Date;
import java.util.List;


@Repository
public interface ErsenPlanMaintenanceRepository extends JpaRepository<ErsenPlanMaintenanceEntity,String> {
    @Query(value = "select libelle from ErsenPlanMaintenanceEntity where id=:planmID")
    String getPlanmName(String planmID);

    @Query(value = "select plan_maintenance from ersen_centrale where id=:centrale", nativeQuery = true)
    String getIdPlanByCentrale(String centrale);

    @Query(value = "select plan_last_change_date from ersen_centrale where id=:centrale", nativeQuery = true)
    Date getPlanLastChangeDateByCentrale(String centrale);

    @Query(value = "select * from ersen_plan_maintenance where id not in " +
            "(select plan from ersen_plan_centrale where centrale=:centrale)", nativeQuery = true)
    List<ErsenPlanMaintenanceEntity> getPlanNotInCentrale(String centrale);

    @Query(value = "select * from ersen_plan_maintenance where id in " +
            "(select plan from ersen_plan_centrale where centrale=:centrale)", nativeQuery = true)
    List<ErsenPlanMaintenanceEntity> getPlanByCentrale(String centrale);

    @Query(value = "select distinct * from ersen_plan_maintenance where id in " +
            "(select plan from ersen_plan_centrale where centrale in (" +
            "select id from ersen_centrale where entreprise=:entreprise))", nativeQuery = true)
    List<ErsenPlanMaintenanceEntity> getPlanByEntreprise(String entreprise);
}
