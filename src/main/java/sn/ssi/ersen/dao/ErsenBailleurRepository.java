package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenBailleurEntity;

@Repository
public interface ErsenBailleurRepository extends JpaRepository<ErsenBailleurEntity,String> {
}
