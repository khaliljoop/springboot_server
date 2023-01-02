package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenAbonneEntity;
import sn.ssi.ersen.entity.entitieMobile.CentraleAbonneeForMobile;

import java.util.List;

@RepositoryRestResource
public interface ErsenAbonneRepository extends JpaRepository<ErsenAbonneEntity,String> {
    @RestResource
    @Query(value = "select * from ersen_abonne where centrale=:centrale", nativeQuery = true)
    List<ErsenAbonneEntity> getAbonnesByCentrale(String centrale);

    @RestResource
    @Query(value = "select * from ersen_abonne  where centrale in :centrales AND id not in :idAbonnes", nativeQuery = true)
    List<ErsenAbonneEntity> getNewAbonnes(List<String> idAbonnes,List<String> centrales);

    @Query(value = "select count(*) from ersen_abonne where centrale=:centrale ",nativeQuery = true)
    Integer getCountErsenAbonneEntitiesByCentrale(String centrale);
    //28/03/22
    @Query(value = "SELECT COUNT(*) FROM ersen_centrale c,ersen_typecentrale tc,ersen_abonne a,ersen_region r\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id AND c.region=r.id\n" +
            "AND tc.libelle=:tc\n"+
            "AND c.region=:region\n" +
            "AND c.entreprise=:operateur\n"+
            "AND substring(a.date,1,4)=:annee\n" +
            "AND substring(a.date,6,2)=:mois ",nativeQuery = true)
    Integer getNombreAbonneByParams(String annee,String region,String operateur,String mois,String tc);

    @Query(value = "SELECT COUNT(*) FROM ersen_centrale c,ersen_typecentrale tc,ersen_abonne a\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id " +
            "AND tc.libelle=:tc\n"+
            "AND substring(a.date,1,4)=:annee\n" +
            "AND substring(a.date,6,2)=:mois ",nativeQuery = true)
    Integer getNombreAbonneByAnnee(String annee,String mois,String tc);

    @Query(value = "SELECT COUNT(*) FROM ersen_centrale c,ersen_typecentrale tc,ersen_abonne a,ersen_region r\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id AND c.region=r.id " +
            "AND tc.libelle=:tc\n"+
            "AND c.region=:region\n" +
            "AND substring(a.date,1,4)=:annee\n" +
            "AND substring(a.date,6,2)=:mois ",nativeQuery = true)
    Integer getNombreAbonneByAnneeAndRegion(String annee,String region,String mois,String tc);

    //#######################################################
    @Query(value = "SELECT COUNT(DISTINCT(ic.id)) FROM `ersen_info_courbe` ic, ersen_abonne a, ersen_centrale c, ersen_typecentrale tc\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND c.Id = ic.centrale\n" +
            "AND tc.libelle=:tc\n" +
            "AND c.entreprise=:operateur\n" +
            "AND SUBSTRING(ic.datesave,1,4)=:annee\n" +
            "AND SUBSTRING(ic.datesave,6,2)=:mois", nativeQuery = true)
    Integer getNbTraceByAnneeAndOperateur(String annee,String operateur,String mois,String tc);

    @Query(value = "SELECT COUNT(DISTINCT(ic.id)) FROM `ersen_info_courbe` ic, ersen_abonne a, ersen_centrale c, ersen_typecentrale tc\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND c.Id = ic.centrale\n" +
            "AND tc.libelle=:tc\n" +
            "AND c.region=:region\n" +
            "AND SUBSTRING(ic.datesave,1,4)=:annee\n" +
            "AND SUBSTRING(ic.datesave,6,2)=:mois", nativeQuery = true)
    Integer getNbTraceByAnneeAndRegion(String annee,String region,String mois,String tc);

    @Query(value = "SELECT COUNT(DISTINCT(ic.id)) FROM `ersen_info_courbe` ic, ersen_abonne a, ersen_centrale c, ersen_typecentrale tc\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND c.Id = ic.centrale\n" +
            "AND tc.libelle=:tc\n" +
            "AND c.region=:region\n" +
            "AND c.entreprise=:operateur\n" +
            "AND SUBSTRING(ic.datesave,1,4)=:annee\n" +
            "AND SUBSTRING(ic.datesave,6,2)=:mois", nativeQuery = true)
    Integer getNbTraceByParams(String annee,String region,String operateur,String mois,String tc);


    @Query(value = "SELECT COUNT(DISTINCT(ic.id)) FROM `ersen_info_courbe` ic, ersen_abonne a, ersen_centrale c, ersen_typecentrale tc\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND c.Id = ic.centrale\n" +
            "AND tc.libelle=:tc\n" +
            "AND SUBSTRING(ic.datesave,1,4)=:annee\n" +
            "AND SUBSTRING(ic.datesave,6,2)=:mois", nativeQuery = true)
    Integer getNbTraceByAnnee(String annee,String mois,String tc);


    //***************************************************

    @Query(value = "SELECT COUNT(DISTINCT(ic.id)) FROM `ersen_info_courbe` ic, ersen_abonne a, ersen_centrale c, ersen_typecentrale tc\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND c.Id = ic.centrale\n" +
            "AND tc.libelle=:tc\n" +
            "AND c.entreprise=:operateur\n" +
            "AND SUBSTRING(ic.datedeleted,1,4)=:annee\n" +
            "AND SUBSTRING(ic.datedeleted,6,2)=:mois", nativeQuery = true)
    Integer getNbDelByAnneeAndOperateur(String annee,String operateur,String mois,String tc);

    @Query(value = "SELECT COUNT(DISTINCT(ic.id)) FROM `ersen_info_courbe` ic, ersen_abonne a, ersen_centrale c, ersen_typecentrale tc\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND c.Id = ic.centrale\n" +
            "AND tc.libelle=:tc\n" +
            "AND c.region=:region\n" +
            "AND SUBSTRING(ic.datedeleted,1,4)=:annee\n" +
            "AND SUBSTRING(ic.datedeleted,6,2)=:mois", nativeQuery = true)
    Integer getNbDelByAnneeAndRegion(String annee,String region,String mois,String tc);

    @Query(value = "SELECT COUNT(DISTINCT(ic.id)) FROM `ersen_info_courbe` ic, ersen_abonne a, ersen_centrale c, ersen_typecentrale tc\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND c.Id = ic.centrale\n" +
            "AND tc.libelle=:tc\n" +
            "AND c.region=:region\n" +
            "AND c.entreprise=:operateur\n" +
            "AND SUBSTRING(ic.datedeleted,1,4)=:annee\n" +
            "AND SUBSTRING(ic.datedeleted,6,2)=:mois", nativeQuery = true)
    Integer getNbDelByParams(String annee,String region,String operateur,String mois,String tc);


    @Query(value = "SELECT COUNT(DISTINCT(ic.id)) FROM `ersen_info_courbe` ic, ersen_abonne a, ersen_centrale c, ersen_typecentrale tc\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND c.Id = ic.centrale\n" +
            "AND tc.libelle=:tc\n" +
            "AND SUBSTRING(ic.datedeleted,1,4)=:annee\n" +
            "AND SUBSTRING(ic.datedeleted,6,2)=:mois", nativeQuery = true)
    Integer getNbDelByAnnee(String annee,String mois,String tc);

    //#######################################################

    @Query(value = "select new sn.ssi.ersen.entity.entitieMobile.CentraleAbonneeForMobile(ea.id, ea.adresse, ea.cni, ea.nom, ea.numeroabonnement, ea.prenom, ea.telephone, ea.centrale, ep.libelle, ea.sexe, et.libelle, ea.date) from ErsenAbonneEntity ea, ErsenPuissanceSouscriteEntity ep, ErsenTypecategorieEntity et where ea.puissancesouscrite=ep.id and ea.typecategorie=et.id")
    List<CentraleAbonneeForMobile> getAllAbonneesForMobile();

    @Query(value = "select count(ea.id) from ersen_abonne ea where ea.centrale=:idCentrale", nativeQuery = true)
    Integer getNombreAbonneeByCentrale(String idCentrale);


    @Query(value = "SELECT COUNT(*) FROM ersen_centrale c,ersen_typecentrale tc,ersen_abonne a\n" +
            "WHERE c.typecentrale=tc.Id AND a.centrale=c.id\n" +
            "AND tc.libelle=:tc\n"+
            "AND c.entreprise=:operateur\n"+
            "AND substring(a.date,1,4)=:annee\n" +
            "AND substring(a.date,6,2)=:mois ",nativeQuery = true)
    Integer getNombreAbonneByAnneeAndOperateur(String annee,String operateur,String mois,String tc);

    // Creation des requetes virtuel pour les reques
    @Query(value = "SELECT abonne.id,abonne.adresse,abonne.cni,abonne.nom,abonne.numeroabonnement,abonne.prenom,abonne.telephone,centrale.nom as centrale,puissancesouscrite.libelle as puissancesouscrite,abonne.sexe,typecategorie.libelle as typecategorie,abonne.date,abonne.etat, abonne.mode_facturation " +
            "from ersen_abonne abonne,ersen_centrale centrale,ersen_puissance_souscrite puissancesouscrite,ersen_typecategorie typecategorie" +
            " where abonne.centrale = centrale.id and abonne.puissancesouscrite = puissancesouscrite.id and abonne.typecategorie = typecategorie.id", nativeQuery = true)
    List<ErsenAbonneEntity> getDataAbonne();

    @Query(value = "SELECT abonne.id,abonne.adresse,abonne.cni,abonne.nom,abonne.numeroabonnement,abonne.prenom,abonne.telephone,centrale.nom as centrale,puissancesouscrite.libelle as puissancesouscrite,abonne.sexe,typecategorie.libelle as typecategorie,abonne.date " +
            "from ersen_abonne abonne,ersen_centrale centrale,ersen_puissance_souscrite puissancesouscrite,ersen_typecategorie typecategorie" +
            " where abonne.centrale = centrale.id and abonne.puissancesouscrite = puissancesouscrite.id and abonne.typecategorie = typecategorie.id and centrale=:centrale", nativeQuery = true)
    List<ErsenAbonneEntity> getErsenAbonneEntitiesByCentrale(String centrale);

    //une restruction selon adresse
    @Query(value = "SELECT abonne.id,abonne.adresse,abonne.cni,abonne.nom,abonne.numeroabonnement,abonne.prenom,abonne.telephone,centrale.nom as centrale,puissancesouscrite.libelle as puissancesouscrite,abonne.sexe,typecategorie.libelle as typecategorie,abonne.date " +
            "from ersen_abonne abonne,ersen_centrale centrale,ersen_puissance_souscrite puissancesouscrite,ersen_typecategorie typecategorie" +
            " where abonne.centrale = centrale.id and abonne.puissancesouscrite = puissancesouscrite.id and abonne.typecategorie = typecategorie.id and abonne.adresse=:adresse", nativeQuery = true)
    List<ErsenAbonneEntity> getErsenAbonneByAdresse(String adresse);

    @Query(value = "SELECT a.* FROM ersen_abonne a, utilisateur u, ersen_centrale c\n" +
            "    WHERE a.centrale=c.id AND u.entreprise=c.entreprise AND u.usr_id=:idUser",nativeQuery = true)
    List<ErsenAbonneEntity> getErsenAbonneEntitiesByUser(String idUser);

    @Query(value = "select count(*) from `ersen_abonne` where id!=:id and telephone=:telephone", nativeQuery = true)
    Integer getNbAbonneByTel(String id ,String telephone);

    @Query(value = "select count(*) from `ersen_abonne` where id!=:id and numeroabonnement=:numeroabonnement", nativeQuery = true)
    Integer getNbAbonneByNumAbonnement(String id,String numeroabonnement);

    @Query(value = "select count(*) from `ersen_abonne` where id!=:id and cni=:cni", nativeQuery = true)
    Integer getNbAbonneByCNI(String id,String cni);
}
