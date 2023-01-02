package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenAnneeEntity;
import sn.ssi.ersen.entity.ErsenRegionEntity;

@Repository
public interface ErsenRegionRepository extends JpaRepository<ErsenRegionEntity,String> {

}
