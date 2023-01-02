package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenPreuveTacheEntity;
import javax.transaction.Transactional;
import java.util.List;

@RepositoryRestResource
@RestResource
public interface ErsenPreuveTacheRepository extends JpaRepository<ErsenPreuveTacheEntity,String> {

    @Query(value = "SELECT p FROM ErsenPreuveTacheEntity p WHERE  p.estHistoriser=false and p.isSync=2 and p.tachecentre in :tachecentres")
    List<ErsenPreuveTacheEntity> getPreuveByTacheCentre(List<String> tachecentres);

    @Query(value = "SELECT DISTINCT tachecentre FROM `ersen_preuve_tache`", nativeQuery = true)
    List<String> getAllTachecentre();

    @Query(value = "select * from ersen_preuve_tache where  tachecentre=:idtachecentre",nativeQuery = true)
    ErsenPreuveTacheEntity getErsenPreuveTacheEntityByTachecentre(String idtachecentre);

    @Query(value = "select p.id from ErsenPreuveTacheEntity p where  p.tachecentre=:idtachecentre")
    String getPreuveTacheID(String idtachecentre);

    @Modifying
    @Transactional
    @Query(value = "DELETE from ErsenPreuveTacheEntity p where  p.tachecentre=:idtachecentre")
    void deletePreuveByIdTacheC(String idtachecentre);

    @Modifying
    @Transactional
    @Query(value = "update ErsenPreuveTacheEntity p set p.estHistoriser=true where p.tachecentre=:idtachecentre and p.estHistoriser=false")
    void updatePreuveByIdTacheC(String idtachecentre);
}
