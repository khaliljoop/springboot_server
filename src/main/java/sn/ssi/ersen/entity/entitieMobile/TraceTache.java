package sn.ssi.ersen.entity.entitieMobile;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TraceTache {
    String idTache;
    /**valide=2 if validate, 3 if unvalidate*/
    Integer valide;
    /**null if, traced object is executed task, not null if its notif*/
    String idNotif;
    /**null if, traced object is executed task, not null if its notif*/
    String objet;
    /**=t pour tache,=n pour task notif, s= pour section de maintemance, =e pour notif ni liée lier a ni tache ni section de maintenance*/
    char type;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    Date dateAction;

    public TraceTache(String idTache, int valide, char type) {
        this.idTache = idTache;
        this.valide = valide;
        this.type = type;
    }

    /**use for tracing not task notif*/
    public TraceTache(String idNotif, String objet) {
        this.idNotif = idNotif;
        this.objet = objet;
    }
}
