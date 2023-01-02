package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenAnneeEntity;
import sn.ssi.ersen.entity.ErsenPaiementEntity;

@Repository
public interface ErsenPaiementRepository extends JpaRepository<ErsenPaiementEntity,String> {

}
