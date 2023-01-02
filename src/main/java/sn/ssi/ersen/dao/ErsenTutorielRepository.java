package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenPreuveTacheEntity;
import sn.ssi.ersen.entity.ErsenTutorielEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ErsenTutorielRepository extends JpaRepository<ErsenTutorielEntity, String> {
    @RestResource
    @Query(value = "SELECT distinct t FROM ErsenTutorielEntity t,ErsenTacheCentraleEntity tc WHERE t.id=tc.tutoriel and tc.id in (:idTacheCentrale)")
    List<ErsenTutorielEntity> getTutoByTache(List<String> idTacheCentrale);

    @Query(value = "select * from ersen_preuve_tache where  tachecentre=:idtachecentre",nativeQuery = true)
    ErsenPreuveTacheEntity getErsenPreuveTacheEntityByTachecentre(String idtachecentre);

    @Transactional
    @Modifying
    @Query(value = "update ersen_tutoriel set nom=:nom where id=:id", nativeQuery = true)
    void updateLib(String id, String nom);
   /* @RestResource
    List<Fichier> getFichierByIdEntite(String idpreuve);*/
}
