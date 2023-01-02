package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_periodicite")
public class ErsenPeriodiciteEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "Id")
    private String id;

    @Basic
    @Column(name = "code")
    private String code;
    @Basic
    @Column(name = "libelle")
    private String libelle;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "valeur")
    private Integer valeur;
}
