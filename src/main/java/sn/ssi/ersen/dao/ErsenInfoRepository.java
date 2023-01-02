package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sn.ssi.ersen.entity.ErsenInfos;

import java.util.List;

@RepositoryRestResource
public interface ErsenInfoRepository extends JpaRepository <ErsenInfos, String>{

    @Query(value = "SELECT c.id FROM `ersen_centrale` c", nativeQuery = true)
    List<String> getNbCentrale();

    @Query(value = "SELECT c.id FROM `ersen_centrale` c WHERE typecentrale=\"MCH\"", nativeQuery = true)
    List<String> getnbmc();

    @Query(value = "SELECT c.id FROM `ersen_centrale` c WHERE typecentrale=\"SHS\"", nativeQuery = true)
    List<String> getnbshs();

    @Query(value = "SELECT c.id FROM `ersen_centrale` c WHERE typecentrale=\"de977c2879fb4f4f0179fbc6e77e0000\"", nativeQuery = true)
    List<String> getnbmt();

    @Query(value = "SELECT c.id FROM ersen_centrale c WHERE etat_centrale=:etat_centrale and typecentrale=:typecentrale",nativeQuery = true)
    List<String> getnb(String typecentrale,Integer etat_centrale);

    //##########################################################################################"

    @Query(value = "SELECT c.Id\n" +
            "FROM `ersen_centrale` c, `utilisateur` u\n" +
            "WHERE c.entreprise=u.entreprise AND u.usr_id=:utilisateur", nativeQuery = true)
    List<String> getNbCentraleByUser(Integer utilisateur);

    @Query(value = "SELECT c.id " +
            "FROM `ersen_centrale` c,`utilisateur` u " +
            "WHERE c.entreprise=u.entreprise AND u.usr_id=:utilisateur AND typecentrale=\"MCH\"", nativeQuery = true)
    List<String> getnbmcByUser(Integer utilisateur);

    @Query(value = "SELECT c.id " +
            "FROM `ersen_centrale` c,`utilisateur` u " +
            "WHERE c.entreprise=u.entreprise AND u.usr_id=:utilisateur AND typecentrale=\"SHS\"", nativeQuery = true)
    List<String> getnbshsByUser(Integer utilisateur);

    @Query(value = "SELECT c.id " +
            "FROM `ersen_centrale` c,`utilisateur` u " +
            "WHERE c.entreprise=u.entreprise AND u.usr_id=:utilisateur AND typecentrale=\"de977c2879fb4f4f0179fbc6e77e0000\"", nativeQuery = true)
    List<String> getnbmtByUser(Integer utilisateur);

    @Query(value = "SELECT c.id\n" +
            "FROM ersen_centrale c, `utilisateur` u\n" +
            "WHERE c.entreprise = u.entreprise and c.etat_centrale=:etat_centrale and c.typecentrale=:typecentrale and u.usr_id=:utilisateur",nativeQuery = true)
    List<String> getnbByUser(Integer utilisateur,String typecentrale,Integer etat_centrale);

}
