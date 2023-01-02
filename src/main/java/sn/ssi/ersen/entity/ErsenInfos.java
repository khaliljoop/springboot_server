package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class ErsenInfos {
    @Id
    private Integer nbvillage;
    private Integer nbcentrale;
    private Integer nbmc;
    private Integer nbshs;
    private Integer nbmt;
    private Integer nbabonnes;
    @ElementCollection
    private List <String> noteservice;
    private String visionprojet;
    private String definitionprojet;
}
