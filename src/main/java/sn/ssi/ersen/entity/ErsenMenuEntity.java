package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_menu")
public class ErsenMenuEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String libelle;
    private String description;

    @OneToMany(mappedBy = "ersenMenuEntity",fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    List<ErsenSousMenuEntity> sousmenu;
}
