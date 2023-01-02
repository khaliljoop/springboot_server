package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ersen_batterie")
public class ErsenBatterieEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private Double capacitestockage;
    private String libelle;
    private Double valeurah;
    private Double valeurv;
    private String fabricant;
    private String modelbatterie;
    private String typebatterie;
}
