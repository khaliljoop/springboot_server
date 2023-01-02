package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenEquipementEntity;

@RestResource
public interface ErsenEquipementRepository extends JpaRepository<ErsenEquipementEntity,String> {

}
