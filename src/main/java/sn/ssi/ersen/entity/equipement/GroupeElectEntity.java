package sn.ssi.ersen.entity.equipement;

import lombok.*;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.hibernate.annotations.GenericGenerator;
import sn.ssi.ersen.entity.ErsenProjetEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_groupeelect")
public class GroupeElectEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String libelle;
    private String fabricant;
    private float puissance;
    private float tension;
    private String phase;
    private float frequence;
}
