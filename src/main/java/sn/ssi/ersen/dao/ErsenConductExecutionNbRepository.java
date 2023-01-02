package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sn.ssi.ersen.entity.ErsenConductExecutionNb;

import javax.transaction.Transactional;
import java.util.Set;

public interface ErsenConductExecutionNbRepository extends JpaRepository<ErsenConductExecutionNb,String> {
    @Transactional
    @Modifying
    @Query("update ErsenConductExecutionNb ce set ce.nbExec=0 where ce.idUser in :users")
    void resetNbExecForEach(Set<Integer> users);

    @Transactional
    @Modifying
    @Query("update ErsenConductExecutionNb ce set ce.centrale=:centrale WHERE ce.idUser=:user")
    void updateConductExecByUsr(String centrale, Integer user);
}
