package sn.ssi.ersen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ErsenTraceTache {
        @Id
        @GeneratedValue(generator="system-uuid")
        @GenericGenerator(name="system-uuid", strategy = "uuid")
        String id;
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
        /**identifiant de la section de maintenace*/
        String idSM;
        /**état d'une section de manitenance*/
        String etat;

        /**use for tracing validate tasks or task notif or SM*/
        public ErsenTraceTache(String idTache, Integer valide, char type, Date dateAction) {
                this.idTache = idTache;
                this.valide = valide;
                this.type = type;
                this.dateAction = dateAction;
        }

        public ErsenTraceTache(String idNotif, String objet, Date dateAction) {
                this.idNotif = idNotif;
                this.objet = objet;
                this.dateAction = dateAction;
        }
}
