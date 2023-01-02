package sn.ssi.ersen.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "ersen_notification")
public class ErsenNotificationEntity{
    @Id
    private String id;

    @Basic
    @Column(name = "impact")
    private Integer impact;

    @Basic
    @Column(name = "libelle")
    private String libelle;

    @Basic
    @Column(name = "objet")
    private String objet;

    @Basic
    @Column(name = "destinataire")
    private String destinataire;

    @Basic
    @Column(name = "expediteur")
    private Integer expediteur;

    @Basic
    @Column(name = "id_tache_or_sectm")
    private String idTacheOrSectM;

    @Column(name = "datesave")
    private Date dateSave;

    @Basic
    @Column(name = "is_relative")
    private Integer isRelative;/*equals to 0 if sectionM notif, 1 if tache notif, sommething else if push notif*/

    @Basic
    @Column(name = "is_sync")
    private Integer isSync;

    @Basic
    @Column(name = "centralid")
    private String centralid;

    @Basic
    @Column(name = "est_historiser",columnDefinition = "boolean default false")
    private boolean estHistoriser;

    @Basic
    @Column(name = "date_resolue")
    private Date dateResolue;

    @Basic
    @Column(name = "by_operateur")
    private Integer byOperateur;


    public ErsenNotificationEntity(String id, Integer impact, String libelle, String objet, String destinataire, int expediteur, String idTacheOrSectM, Date dateSave, Integer isRelative, Integer isSync, String centralid, Integer byOperateur) {
        this.id = id;
        this.impact = impact;
        this.libelle = libelle;
        this.objet = objet;
        this.destinataire = destinataire;
        this.expediteur = expediteur;
        this.idTacheOrSectM = idTacheOrSectM;
        this.dateSave = dateSave;
        this.isRelative = isRelative;
        this.isSync=isSync;
        this.centralid=centralid;
        this.byOperateur = byOperateur;
    }
}
