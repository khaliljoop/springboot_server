package sn.ssi.ersen.entity.databaseEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DimensionEntity implements Serializable {
    @Id
    private Integer id;
    private String nom;
    private Integer number;
}
