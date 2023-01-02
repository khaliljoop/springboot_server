package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "allequipement")
public class AllEquipementEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    private Integer id;
    private String libelle;
    private String tablename;
}
