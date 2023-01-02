package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenBatterieEntity;

@Repository
public interface ErsenBatterieRepository extends JpaRepository<ErsenBatterieEntity,String> {
}
