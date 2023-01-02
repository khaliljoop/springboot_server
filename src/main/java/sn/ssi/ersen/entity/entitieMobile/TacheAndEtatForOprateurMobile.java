package sn.ssi.ersen.entity.entitieMobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class TacheAndEtatForOprateurMobile {
    private String idTacheC;
    private String etat;
    private Date effectuerLe;
    private Object preuvesOrNotifs;


    public TacheAndEtatForOprateurMobile(String idTacheC, String etat,Date effectuerLe) {
        this.idTacheC = idTacheC;
        this.etat = etat;
        this.effectuerLe = effectuerLe;
    }
}
