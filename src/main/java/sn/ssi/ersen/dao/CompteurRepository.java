package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.equipement.CompteurEntity;

@Repository
public interface CompteurRepository extends JpaRepository<CompteurEntity,String> {
}
