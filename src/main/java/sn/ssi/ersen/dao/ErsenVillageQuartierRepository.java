package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenVillagequartierEntity;

import java.util.List;

@Repository
public interface ErsenVillageQuartierRepository extends JpaRepository<ErsenVillagequartierEntity,String> {
    @RestResource
    List<ErsenVillagequartierEntity> getErsenVillagequartierEntitiesByCommune(String id);


    @Query(value = "SELECT village.id,village.code,village.libelle,commune.libelle as commune FROM ersen_villagequartier village,ersen_commune commune,ersen_departement departement,ersen_region region \n" +
            "where village.commune=commune.id and commune.departement=departement.id and departement.region=region.id and region.libelle=:libelle", nativeQuery = true)
    List<ErsenVillagequartierEntity> getAllVillageQuartier(String libelle);
}
