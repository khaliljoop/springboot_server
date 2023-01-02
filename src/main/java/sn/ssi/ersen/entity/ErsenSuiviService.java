package sn.ssi.ersen.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErsenSuiviService {
    @Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private Integer annee;
    private Integer mois;
    private Integer nbHeuresServJour;
    private Integer nbHeuresServNuit;
    private char source; //A ou M
    private Integer status;
    private String centraleID;
}
