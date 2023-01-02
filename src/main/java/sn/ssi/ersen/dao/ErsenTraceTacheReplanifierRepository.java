package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import sn.ssi.ersen.entity.ErsenTraceTacheReplanifier;
import javax.transaction.Transactional;
import java.util.List;

public interface ErsenTraceTacheReplanifierRepository extends JpaRepository<ErsenTraceTacheReplanifier,String> {
    @Query("select new sn.ssi.ersen.entity.ErsenTraceTacheReplanifier(tp.idTacheCentrale,tp.dateLine) from ErsenTraceTacheReplanifier tp where tp.idTacheCentrale in :idTacheFromMobile")
    List<ErsenTraceTacheReplanifier> getReplanifyTacheC(List<String> idTacheFromMobile);

    @Transactional
    @Modifying
    @Query("DELETE FROM ErsenTraceTacheReplanifier tp where tp.idTacheCentrale in :idTacheFromMobile")
    void deleteTacheCAfterReplanify(List<String> idTacheFromMobile);
}
