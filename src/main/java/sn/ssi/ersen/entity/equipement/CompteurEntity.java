package sn.ssi.ersen.entity.equipement;

import lombok.*;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import sn.ssi.ersen.entity.ErsenProjetEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_compteur")
public class CompteurEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String libelle;
    private String modele;
    private String fabricant;
    private String type;
    private String certificat;
    private String phase;
    private String imax;
}
