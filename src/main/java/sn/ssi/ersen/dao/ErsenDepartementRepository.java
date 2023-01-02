package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenBatterieEntity;
import sn.ssi.ersen.entity.ErsenDepartementEntity;

import java.util.List;

@Repository
public interface ErsenDepartementRepository extends JpaRepository<ErsenDepartementEntity,String> {
    @RestResource
    List<ErsenDepartementEntity> getErsenDepartementEntitiesByRegion(String id);

    @Query(value = "select departement.id,departement.code,departement.libelle,region.libelle as region from ersen_departement departement,ersen_region region where departement.region=region.id",nativeQuery = true)
    List<ErsenDepartementEntity> getDepartementNomRegion();
}
