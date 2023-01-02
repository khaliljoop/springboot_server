package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenOperateurEntity;

@Repository
public interface ErsenOperateurRepository extends JpaRepository<ErsenOperateurEntity,String> {
}
