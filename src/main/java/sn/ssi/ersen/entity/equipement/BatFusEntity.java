package sn.ssi.ersen.entity.equipement;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import sn.ssi.ersen.entity.ErsenProjetEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_batfus")
public class BatFusEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String libelle;
    private String fabricant;
    private float nombredefusible;
    private String tensionnominale;
    private String couranttypefusible;
}
