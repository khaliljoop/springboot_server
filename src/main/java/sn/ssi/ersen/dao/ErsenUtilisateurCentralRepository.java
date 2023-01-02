package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sn.ssi.ersen.entity.ErsenUtilisateurCentraleEntity;
import sn.ssi.ersen.entity.UtilisateurEntity;

import java.util.List;

@RepositoryRestResource
public interface ErsenUtilisateurCentralRepository extends JpaRepository<ErsenUtilisateurCentraleEntity,String> {
    @RestResource
    List<ErsenUtilisateurCentraleEntity> getErsenUtilisateurCentraleEntitiesByCentrale(String idUtilisateurCentrale);


    @Query(value = "select * from ersen_utilisateur_centrale where centrale=:centrale", nativeQuery = true)
    ErsenUtilisateurCentraleEntity getErsenUtilisateurCentraleByCentrale(String centrale);

    @Query(value = "SELECT count(*) from ersen_utilisateur_centrale where utilisateur=:utilisateur", nativeQuery = true)
    Integer getNombreCond(Integer utilisateur);

    @Query(value = "SELECT count(*) from ersen_utilisateur_centrale where utilisateur=:utilisateur and centrale=:centrale", nativeQuery = true)
    Integer getNombreOperByCentrale(Integer utilisateur,String centrale);

    //Renvoie 1 si la centrale dispose au moins un opérateur et 0 sinon
    @Query(value = "SELECT count(*) from ersen_utilisateur_centrale uc, utilisateur u " +
            "where u.usr_id=uc.utilisateur and u.typeutilisateur=\"de977c2878f70a660178f87f84bd0000\" and centrale=:centrale", nativeQuery = true)
    Integer getNombreOperbyCentrale(String centrale);

    //Verifier si la centrale dispose un conducteur
    //SI oui on modifie sa centrale
    @Query(value = "SELECT id from ersen_utilisateur_centrale where centrale=:centrale and utilisateur in " +
            "(select usr_id from utilisateur where typeutilisateur=\"402881877869fac6017869fbd4a10000\")", nativeQuery = true)
    String getNombreCondByCentrale(String centrale);

    @Modifying
    @Transactional
    @Query(value = "update ErsenUtilisateurCentraleEntity uc set uc.utilisateur=:utilisateur where uc.centrale=:centrale")
    Integer updateUtilisateurByCentrale(Integer utilisateur, String centrale);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ersen_utilisateur_centrale set utilisateur=:utilisateur where centrale=:centrale" +
            " and utilisateur in (select usr_id from utilisateur where typeutilisateur=\"de977c2878f70a660178f87f84bd0000\")", nativeQuery = true)
    Integer updateOperByCentrale(Integer utilisateur, String centrale);
}
