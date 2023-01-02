package sn.ssi.ersen.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_recus")
public class ErsenRecusEntity{
    @Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private float montantTotal;
    private String abonne;
    private String centrale;
    private Integer annee;
    private Integer idReceveur;
    private Integer isSync;
    @Basic
    @Column(name = "_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date _date;
}
