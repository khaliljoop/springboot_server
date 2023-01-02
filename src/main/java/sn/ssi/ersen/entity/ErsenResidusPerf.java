package sn.ssi.ersen.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_residus_perf")
public class ErsenResidusPerf {
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Id
    private String id;
    private String centrale;
    private Integer denominateur;

    public ErsenResidusPerf(String centrale) {
        this.centrale = centrale;
        this.denominateur = 0;
    }
}
