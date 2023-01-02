package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sn.ssi.ersen.entity.ErsenPerfHistorique;
import java.util.List;

public interface ErsenPerfHistoriqueRepository  extends JpaRepository<ErsenPerfHistorique,String> {
    @Query(value = "SELECT * FROM ersen_perf_historique ph where ph.id_user=:idUser order by ph.id_annee,ph.id_mois desc LIMIT :nbMoisOrdreDesc", nativeQuery = true)
    List<ErsenPerfHistorique> getPerfHistorique(int idUser, int nbMoisOrdreDesc);
}
