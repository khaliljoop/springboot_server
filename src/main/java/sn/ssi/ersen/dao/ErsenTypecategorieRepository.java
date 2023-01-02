package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenAbonneEntity;
import sn.ssi.ersen.entity.ErsenTypecategorieEntity;

import java.util.List;

@Repository
public interface ErsenTypecategorieRepository extends JpaRepository<ErsenTypecategorieEntity,String> {

    @RestResource
     List<ErsenTypecategorieEntity> getErsenTypecategorieEntitiesByCategorie(String id);

    @Query(value = "SELECT typecategorie.id,typecategorie.libelle,typecategorie.montantredevance,typecategorie.puissance,categorie.libelle as categorie from ersen_typecategorie typecategorie,ersen_categorie categorie" +
            " where typecategorie.categorie=categorie.id ", nativeQuery = true)
    List<ErsenTypecategorieEntity> getTypeCategorieDTO();
}
