package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ersen_conduct_nbexec")
public class ErsenConductExecutionNb {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private int nbExec;
    private Integer idUser;
    private String centrale;

    public ErsenConductExecutionNb(Integer idUser) {
        this.nbExec = 0;
        this.idUser = idUser;
    }
}
