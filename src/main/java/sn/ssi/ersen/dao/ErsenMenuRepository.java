package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenMenuEntity;

@Repository
public interface ErsenMenuRepository extends JpaRepository<ErsenMenuEntity,String> {
}
