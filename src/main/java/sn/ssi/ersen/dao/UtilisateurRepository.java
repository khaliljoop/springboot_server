package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.UtilisateurEntity;
import sn.ssi.ersen.entity.entitieMobile.Utilisateur;

import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@RestResource
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity,Integer> {

    @Query(value = "select count(*) from utilisateur where usr_mail=:email", nativeQuery = true)
    Integer getNbByEmail(String email);

    @Query(value = "select usr_password from utilisateur where usr_mail=:email", nativeQuery = true)
    String getPasswordByEmail(String email);

    @Query("select u from  UtilisateurEntity u where u.tel=:tel")
    UtilisateurEntity getByTel(String tel);

    @Modifying
    @Transactional
    @Query(value = "update utilisateur set hasnotif=fasle where usr_id=:user", nativeQuery = true)
    void updateHasNotifFalse(Integer user);

    @RestResource
    @Query(value = "SELECT u.* FROM `utilisateur` u,`ersen_typeutilisateur` tu\n" +
            "WHERE u.typeutilisateur=tu.id\n" +
            "AND tu.id=\"de977c2878f70a660178f87f84bd0000\"",nativeQuery = true)
    List<UtilisateurEntity> getALLUtilisateuroperateur();


    @RestResource
    @Query(value = "SELECT u.* FROM `utilisateur` u,`ersen_typeutilisateur` tu\n" +
            "            WHERE u.typeutilisateur=tu.id\n" +
            "            AND tu.id=\"402881877869fac6017869fbd4a10000\" and u.usr_id not in\n" +
            "            (select utilisateur from ersen_utilisateur_centrale)",nativeQuery = true)
    List<UtilisateurEntity> getUtilisateurConducteur();

    @RestResource
    @Query(value = "SELECT u.* FROM `utilisateur` u,`ersen_typeutilisateur` tu\n" +
            "  WHERE u.typeutilisateur=tu.id\n" +
            "  AND tu.id=\"402881877869fac6017869fbd4a10000\" AND u.entreprise=:entreprise\n", nativeQuery = true)
    List<UtilisateurEntity> getALLUtilisateurConducteur(String entreprise);

    @RestResource
    @Query(value = "select * from utilisateur where entreprise=:entreprise", nativeQuery = true)
    List<UtilisateurEntity> getUsersByEntreprise(String entreprise);

    @RestResource
    @Query(value = "SELECT c.nom, u.usr_prenom,u.usr_nom FROM `utilisateur` u, `ersen_utilisateur_centrale` uc, `ersen_centrale` c\n" +
            "WHERE u.usr_id=uc.utilisateur AND uc.centrale=c.id AND u.usr_id=:usr_id", nativeQuery = true)
    Object getUserInfos(Integer usr_id);

    @Query(value = "SELECT COUNT(*) FROM utilisateur WHERE usr_id!=:usr_id AND usr_mail=:usr_mail", nativeQuery = true)
    Integer getNbUserByEmail(Integer usr_id,String usr_mail);

    @Query(value = "SELECT COUNT(*) FROM utilisateur WHERE usr_id!=:usr_id AND tel=:tel", nativeQuery = true)
    Integer getNbUserByTel(Integer usr_id,String tel);

}
