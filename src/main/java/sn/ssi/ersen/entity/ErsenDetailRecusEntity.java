package sn.ssi.ersen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_detail_recus")
public class ErsenDetailRecusEntity {
    @Id
    private String id;
    private String idRecu;
    private float montant;
    private Integer mois;
}
