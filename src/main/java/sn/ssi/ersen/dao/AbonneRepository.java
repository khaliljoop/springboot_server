package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenAbonneEntity;
import sn.ssi.ersen.entity.ErsenPuissanceSouscriteEntity;
import sn.ssi.ersen.entity.entitieMobile.Abonne;
import sn.ssi.ersen.entity.entitieMobile.Centrale;

import javax.transaction.Transactional;
import java.util.List;

@RestResource
public interface AbonneRepository  extends JpaRepository<ErsenAbonneEntity,String> {
    @Query(value = "SELECT new sn.ssi.ersen.entity.ErsenAbonneEntity(a.id,a.adresse,a.cni,a.nom, a.prenom, a.numeroabonnement,a.telephone, c.nom, p.libelle,a.sexe,a.typecategorie,a.date) FROM `ersen_abonne` a, `ersen_puissance_souscrite` p, `ersen_centrale` c WHERE a.centrale=c.Id AND a.puissancesouscrite=p.Id", nativeQuery = true)
    List<ErsenAbonneEntity> getAbonneByCentrale();


    /*@RestResource
    @Query(value = "SELECT new sn.ssi.ersen.entity.entitieMobile.Abonne(c.id, c.adresse,c.coutinstallation, c.datedemarrage, c.datesave, c.dateupdate, c.latitude, c.longitude, c.nom, c.puissance,c.typecentrale,c.village,c.urlimage, c.description, c.projet,\n" +
            " co.libelle, dep.libelle, r.libelle,c.plan_maintenance) FROM ErsenUtilisateurCentraleEntity uc, ErsenCentraleEntity c, ErsenVillagequartierEntity v, ErsenTypecentraleEntity t,\n" +
            "ErsenCommuneEntity co, ErsenDepartementEntity dep, ErsenRegionEntity r , UtilisateurEntity u \n" +
            "WHERE  uc.centrale=c.id AND c.village=v.id  AND c.typecentrale=t.id AND r.id=dep.region AND dep.id=co.departement AND co.id=v.commune\n" +
            "AND uc.utilisateur=u.usrId AND uc.utilisateur=:id AND c.village= v.id AND v.commune = co.id AND co.departement = dep.id AND dep.region = r.id")
    List<Abonne> getCentraleByUtilisateur(Integer id);*/


  /*  @Transactional
   @Modifying
    @Query(value = "UPDATE ersen_abonne set prenom =:#{#a.prenom},nom=:#{#a.nom},adresse=:#{#a.adresse},cni=:#{#a.cni},telephone=:#{#a.telephone},puissancesouscrite=:#{#p.Id},sexe=:#{#a.sexe},typecategorie=:#{#a.typecategorie} where id = :#{#a.id}",
            nativeQuery = true)
    /*void updateAbonne(String prenom, String nom, String adresse, String cni, String tel, String p, String sexe, String type, String id);
    void updateAbonne(@Param("a") ErsenAbonneEntity a, @Param("p")ErsenPuissanceSouscriteEntity p);*/
}


