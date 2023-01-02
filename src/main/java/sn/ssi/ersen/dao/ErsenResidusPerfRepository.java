package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenResidusPerf;

import javax.transaction.Transactional;

@RepositoryRestResource
@Repository
public interface ErsenResidusPerfRepository extends JpaRepository<ErsenResidusPerf,String> {

    @Transactional
    @Modifying

    @Query(value = "update ErsenResidusPerf set denominateur=denominateur+:denominateur where centrale=:centrale")
    Integer updateErsenPerfCalcul(Integer denominateur, String centrale);

    @Query(value = "select rp.denominateur from ErsenResidusPerf rp where rp.centrale=:centrale")
    Integer getResiduDenominateurByCentrale(String centrale);

    @Query(value = "select ce.centrale from ErsenConductExecutionNb ce where ce.idUser=:userId")
    String getCentraleIdFromErsenConductExecutionNb(int userId);
}
