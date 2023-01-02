package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.entitieMobile.Fichier;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ErsenFichierRepository extends JpaRepository<Fichier,String> {
    @Query("SELECT f FROM Fichier f WHERE f.idEntite=:idEntite")
    List<Fichier> getFilesOfAnEntity(String idEntite);

    //30/03/2022 de la repository FichierRepository
    @Query(value = "SELECT f.* FROM ersen_preuve_tache pt,ersen_fichier f WHERE pt.id=f.id_entite AND pt.tachecentre=:tachecentre",nativeQuery = true)
    List<Fichier> getFichierByCentre(String tachecentre);

    @Transactional
    @Modifying
    @Query("DELETE from Fichier f where f.idEntite=:idEntity")
    void deleteEntitiesFiles(String idEntity);
}
