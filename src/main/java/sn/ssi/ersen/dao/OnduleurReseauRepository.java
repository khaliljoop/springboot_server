package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.equipement.OnduleurChargeurEntity;
import sn.ssi.ersen.entity.equipement.OnduleurReseauEntity;

@Repository
public interface OnduleurReseauRepository extends JpaRepository<OnduleurReseauEntity,String> {
}
