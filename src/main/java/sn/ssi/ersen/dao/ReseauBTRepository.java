package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.equipement.ReseauBTEntity;

@Repository
public interface ReseauBTRepository extends JpaRepository<ReseauBTEntity,String> {

}

