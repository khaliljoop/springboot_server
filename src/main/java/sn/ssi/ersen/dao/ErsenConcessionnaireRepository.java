package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenConcessionnaireEntity;

@Repository
public interface ErsenConcessionnaireRepository extends JpaRepository<ErsenConcessionnaireEntity,String> {
}