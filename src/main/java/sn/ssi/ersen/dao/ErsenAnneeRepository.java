package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenAnneeEntity;

@Repository
public interface ErsenAnneeRepository extends JpaRepository<ErsenAnneeEntity,String> {
}
