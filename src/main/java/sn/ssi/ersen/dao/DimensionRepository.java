package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.databaseEntities.DimensionEntity;

@Repository
public interface DimensionRepository extends JpaRepository<DimensionEntity,Integer> {

}
