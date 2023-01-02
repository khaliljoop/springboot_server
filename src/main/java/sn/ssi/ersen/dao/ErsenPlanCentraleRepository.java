package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErsenPlanCentraleRepository extends JpaRepository<sn.ssi.ersen.entity.ErsenPlanCentrale,String> {
}
