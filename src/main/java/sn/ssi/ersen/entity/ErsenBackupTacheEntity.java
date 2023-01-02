package sn.ssi.ersen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ersen_backup_p_n_t")
public class ErsenBackupTacheEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String idTacheDep;
    private String tacheId;
    private String configcentraletache;
    private String commentaire;
    /*private String idPreuve;
    private String idNotif;
    private String idTache;*/
    private Integer valideBackup;

    @Basic
    @Column(name = "dateeffectuee")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateeffectuee;

    @Basic
    @Column(name = "date_backup")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateBackup;

    @Basic
    @Column(name = "date_ultime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateUltime;

    private String etattache;
    private String tutoriel;
    private Integer type;
    private String planM;
    private boolean estArchiver;

    public ErsenBackupTacheEntity(String tacheId, String configcentraletache, String commentaire, Integer valideBackup, Date dateeffectuee, Date dateBackup, Date dateUltime, String etattache, String tutoriel, Integer type, String planM, boolean estArchiver) {
        this.tacheId = tacheId;
        this.configcentraletache = configcentraletache;
        this.commentaire = commentaire;
        this.valideBackup = valideBackup;
        this.dateeffectuee = dateeffectuee;
        this.dateBackup = dateBackup;
        this.dateUltime = dateUltime;
        this.etattache = etattache;
        this.tutoriel = tutoriel;
        this.type = type;
        this.planM = planM;
        this.estArchiver=estArchiver;
    }
}
