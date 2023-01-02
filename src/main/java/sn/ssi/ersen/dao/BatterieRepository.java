package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.equipement.BatFusEntity;
import sn.ssi.ersen.entity.equipement.BatterieEntity;

@Repository
public interface BatterieRepository extends JpaRepository<BatterieEntity,String> {
}
