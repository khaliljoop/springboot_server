package sn.ssi.ersen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ersen_tache_centrale")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErsenTacheCentraleEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "Id")
    private String id;

    @Basic
    @Column(name = "commentaire")
    private String commentaire;

    @Basic
    @Column(name = "consulte")
    private Integer consulte;

    @Basic
    @Column(name = "dateeffectuee")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateeffectuee;

    @Basic
    @Column(name = "datesave")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date datesave;

    @Basic
    @Column(name = "dateupdate")
    private Date dateupdate;

    @Basic
    @Column(name = "date_planification")
    private Date datePlanif;

    @Basic
    @Column(name = "date_first_planif")
    private Date dateFirstPlanif;

    @Basic
    @Column(name = "date_replanification")
    private Date dateNextPlanif;

    @Basic
    @Column(name = "etattache")
    private String etattache;

    @Basic
    @Column(name = "type",columnDefinition = "integer default 0")
    private Integer type;

    @Basic
    @Column(name = "valide")
    private Integer valide;

    @Basic
    @Column(name = "configcentraletache")
    private String configcentraletache;

    @Column(name = "tutoriel")
    private String tutoriel;

    @Basic
    @Column(name = "plan_m")
    private String planM;

    @Basic
    @Column(name = "request_name")
    private String requestName;

    public ErsenTacheCentraleEntity(String id, String commentaire, Integer consulte, Date dateeffectuee, Date datesave, Date dateupdate, String etattache, Integer type, Integer valide, String configcentraletache, String tutoriel, String planM, String requestName, Date dateNextPlanif) {
        this.id = id;
        this.commentaire = commentaire;
        this.consulte = consulte;
        this.dateeffectuee = dateeffectuee;
        this.datesave = datesave;
        this.dateupdate = dateupdate;
        this.etattache = etattache;
        this.type = type;
        this.valide = valide;
        this.configcentraletache = configcentraletache;
        this.tutoriel = tutoriel;
        this.planM = planM;
        this.requestName = requestName;
        this.dateNextPlanif=dateNextPlanif;
        this.datePlanif=new Date();
    }
}
