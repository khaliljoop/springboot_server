package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_parametresgeneraux")
public class SysParametresgenerauxEntity implements Serializable {
    @Id
    @Column(name = "CODE")
    private String code;
    @Basic
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "LIBELLE")
    private String libelle;
    @Basic
    @Column(name = "VALEUR")
    private Long valeur;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysParametresgenerauxEntity that = (SysParametresgenerauxEntity) o;
        return valeur.equals(that.valeur) && Objects.equals(code, that.code) && Objects.equals(libelle, that.libelle);
    }
    @Override
    public int hashCode() {
        return Objects.hash(code, libelle, valeur);
    }
}