package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenTypecentraleEntity;

import java.util.List;

@Repository
public interface ErsenTypeCentraleRepository extends JpaRepository<ErsenTypecentraleEntity, String> {

  /*  @Query(value = "",nativeQuery = true)
    Object getAbonneByCentrale(String annee,String region);*/

    @Query(value = "SELECT typecentrale.id,typecentrale.libelle,typecentrale.description FROM ersen_typecentrale typecentrale",nativeQuery = true)
    List<ErsenTypecentraleEntity> getAllTypeCentraleListe();
}
