package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenEntrepriseEntity;
import sn.ssi.ersen.entity.UtilisateurEntity;

import java.util.List;

@RepositoryRestResource
public interface EntrepriseRepository extends JpaRepository<ErsenEntrepriseEntity,String> {
    @RestResource
    @Query(value = "SELECT * FROM ersen_entreprise e", nativeQuery = true)
    List<ErsenEntrepriseEntity> getEntreprise();

    @Query(value = "SELECT e.* from ersen_entreprise e, ersen_utilisateur_centrale uc, utilisateur u" +
            " WHERE u.USR_ID=uc.utilisateur and u.entreprise=e.id and uc.centrale=:centrale limit 1", nativeQuery = true)
    ErsenEntrepriseEntity getEntrepriseByCentrale(String centrale);

    @Query(value = "SELECT u.* from ersen_utilisateur_centrale uc, utilisateur u" +
            " WHERE u.USR_ID=uc.utilisateur  and uc.centrale=:centrale and u.typeutilisateur=\"402881877869fac6017869fbd4a10000\" limit 1", nativeQuery = true)
    Object getConducteurByCentrale(String centrale);
}
