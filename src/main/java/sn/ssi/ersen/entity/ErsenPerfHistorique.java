package sn.ssi.ersen.entity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ersen_perf_historique")

public class ErsenPerfHistorique {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private Integer idUser;
    private Integer idMois;
    private Integer nbTasksExec;
    private Integer nbTasksTotal;
    private Integer idAnnee;

    public ErsenPerfHistorique(Integer idUser, Integer idMois, Integer nbTasksExec, Integer nbTasksTotal, Integer idAnnee) {
        this.idUser = idUser;
        this.idMois = idMois;
        this.nbTasksExec = nbTasksExec;
        this.nbTasksTotal = nbTasksTotal;
        this.idAnnee = idAnnee;
    }

    public ErsenPerfHistorique(Integer idUser,Integer nbTasksExec,long nbTasksTotal) {
        this.idUser = idUser;
        this.nbTasksExec = nbTasksExec;
        this.nbTasksTotal = Math.toIntExact(nbTasksTotal);
    }
}
