package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;
import sn.ssi.ersen.entity.ErsenAbonneEntity;
import sn.ssi.ersen.entity.ErsenSuiviService;

import java.util.List;

@RepositoryRestResource
public interface ErsenSuiviServiceRepository extends JpaRepository<ErsenSuiviService,String> {
    @Query(value = "SELECT * FROM ersen_suivi_service  WHERE centraleid IN :centrales",nativeQuery = true)
    List<ErsenSuiviService> getErsenSuiviServiceEntitiesByCentrale(List<String> centrales);
    @Query(value = "SELECT * FROM ersen_suivi_service  WHERE centraleid IN :centrales AND id NOT IN :ids",nativeQuery = true)
    List<ErsenSuiviService> getNewSuiviService(List<String> centrales,List<String> ids);

}
