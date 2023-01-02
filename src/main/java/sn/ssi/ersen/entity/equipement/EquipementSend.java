package sn.ssi.ersen.entity.equipement;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import sn.ssi.ersen.entity.ErsenProjetEntity;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EquipementSend implements Serializable {
    private String idEquipement;
    private String libelleEquipement;
    private String fabricantEquipement;
    private String centrale;
    private Integer nbre;
    private String idEquipementCentrale;
    private String tableName;
}

