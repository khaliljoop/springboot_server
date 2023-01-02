package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenBatterieEntity;
import sn.ssi.ersen.entity.ErsenCommuneEntity;

import java.util.List;

@Repository
public interface ErsenCommuneRepository extends JpaRepository <ErsenCommuneEntity,String>{
    @RestResource
    List<ErsenCommuneEntity> getErsenCommuneEntitiesByDepartement(String id);

    @Query(value = "select commune.id,commune.code,commune.libelle,departement.libelle as departement from ersen_commune commune, ersen_departement departement where commune.departement=departement.id",nativeQuery = true)
    List<ErsenCommuneEntity> getErsenCommuneEntityNomDepartement();
}
