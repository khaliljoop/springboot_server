package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_onduleurreseau")
public class ErsenOnduleurreseauEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private Double edcpuissancemax;
    private Double edctensionmax;
    private Double edctensionmin;
    private String libelle;
    private Double saccourant;
    private String sacfrequence;
    private Double sacpuissance;
    private String sactension;
}
