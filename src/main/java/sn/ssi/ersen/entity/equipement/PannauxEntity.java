package sn.ssi.ersen.entity.equipement;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import sn.ssi.ersen.entity.ErsenProjetEntity;
import sn.ssi.ersen.entity.ErsenPuissanceSouscriteEntity;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_pannaux")
public class PannauxEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String libelle;
    private String fabricant;
    private float puissancenominale;
    private float voc;
    private float vmpp;
    private float maxTention;
    private float isc;
    private float impp;
}
