package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.equipement.OnduleurReseauEntity;
import sn.ssi.ersen.entity.equipement.PannauxEntity;

@Repository
public interface PannauxRepository extends JpaRepository<PannauxEntity,String> {
}
