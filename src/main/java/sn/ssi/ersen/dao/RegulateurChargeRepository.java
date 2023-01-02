package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.equipement.PannauxEntity;
import sn.ssi.ersen.entity.equipement.RegulateurChargeEntity;

@Repository
public interface RegulateurChargeRepository extends JpaRepository<RegulateurChargeEntity,String> {
}
