package sn.ssi.ersen.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_config_centrale_tache")
public class ErsenConfigCentraleTacheEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Basic
    @Column(name = "Id")
    private String id;
    @Basic
    @Column(name = "for_oper")
    private int for_oper;
    @Basic
    @Column(name = "centrale_or_entreprise")
    private String centrale_or_entreprise;
    @Basic
    @Column(name = "periodicite")
    private String periodicite;
    @Basic
    @Column(name = "tache")
    private String tache;
}
