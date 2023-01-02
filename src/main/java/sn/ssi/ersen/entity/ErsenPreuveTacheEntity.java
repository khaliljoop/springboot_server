package sn.ssi.ersen.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ersen_preuve_tache")
public class ErsenPreuveTacheEntity {
    @Id
    private String id;

    @Basic
    @Column(name = "datesave")
    private Date datesave;

    @Basic
    @Column(name = "tachecentre")
    private String tachecentre;

    @Basic
    @Column(name = "dateupdate")
    private Date dateupdate;

    @Basic
    @Column(name = "is_sync")
    private Integer isSync;

    @Basic
    @Column(name = "esthistoriser",columnDefinition = "boolean default false")
    //Nous pouvions egalement implementer la meme logique en utilisant : private boolean estHistoriser=false
    //mais cette approche ne definie pas la valeur par defaut dans le schema ce qui fait qu'il est
    //deconseiller s'il a des possibilites que la base de donnees soit exporter
    private boolean estHistoriser;

    @Basic
    @Column(name = "by_operateur")
    private Integer byOperateur;

    @Basic
    @Column(name = "expediteur")
    private Integer expediteur;

    public ErsenPreuveTacheEntity(String id, Date datesave,String tachecentre, int isSync,Integer byOperateur,Integer expediteur) {
        this.id = id;
        this.datesave = datesave;
        this.tachecentre = tachecentre;
        this.byOperateur=byOperateur;
        this.expediteur = expediteur;
        this.isSync=isSync;
    }
}
