package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.databaseEntities.AttribueEntity;

@Repository
public interface AttribueRepository extends JpaRepository<AttribueEntity,Long>{
}
