package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenCategorieEntity;

@Repository
public interface ErsenCategorieRepository extends JpaRepository<ErsenCategorieEntity,String> {
}
