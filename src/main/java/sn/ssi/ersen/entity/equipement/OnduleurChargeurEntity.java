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
@Table(name = "ersen_onduleurchargeur")
public class OnduleurChargeurEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String libelle;
    private String fabricant;
    private float sortiepuissance;
    private float tensionSortie;
    private float frequencenom;
    private float lacnom;
    private float tension;
    private float puissanceMax;
    private  float batTension;
    private float batCapacite;
}
