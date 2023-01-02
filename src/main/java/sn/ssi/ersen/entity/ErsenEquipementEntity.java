package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersenequipement")
public class ErsenEquipementEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idtypeequiment")
    private ErsenTypeEquipementEntity ersenTypeEquipementEntity;
}
