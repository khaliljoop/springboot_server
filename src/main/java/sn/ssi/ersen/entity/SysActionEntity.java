package sn.ssi.ersen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "sys_action")
public class SysActionEntity implements Serializable {
    @Id
    private String code;
    private String description;
    private String libelle_fr;
    private String libelle_en;
    private String libelle_es;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "_option")
    private SysOptionEntity sysOptionEntity;
}