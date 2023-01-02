package sn.ssi.ersen.entity.equipement;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import sn.ssi.ersen.entity.ErsenProjetEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_boiteconnexion")
public class BoiteConnexionPvEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String libelle;
    private String fabricant;
    private float tensionEntree;
    private float courantEntree;
}
