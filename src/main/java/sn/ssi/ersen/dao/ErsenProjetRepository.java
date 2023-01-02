package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenAnneeEntity;
import sn.ssi.ersen.entity.ErsenProjetEntity;

@Repository
public interface ErsenProjetRepository extends JpaRepository<ErsenProjetEntity,String> {
    @Query(value = "select p.nom from ErsenProjetEntity p where p.id=:projetID")
    String getProjectName(String projetID);
}
