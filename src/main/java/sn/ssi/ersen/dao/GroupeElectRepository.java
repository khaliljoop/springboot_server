package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.equipement.ConcentrateurEntity;
import sn.ssi.ersen.entity.equipement.GroupeElectEntity;

@Repository
public interface GroupeElectRepository extends JpaRepository<GroupeElectEntity,String> {
}
