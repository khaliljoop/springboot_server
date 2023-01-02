package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.equipement.KiosquesEntity;
import sn.ssi.ersen.entity.equipement.McBoxEntity;

@Repository
public interface McBoxRepository extends JpaRepository<McBoxEntity,String> {
}
