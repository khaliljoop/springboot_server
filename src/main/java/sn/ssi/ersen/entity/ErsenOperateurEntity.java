package sn.ssi.ersen.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersen_operateur")
public class ErsenOperateurEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String adresse;
    private String nom;
    private String prenom;
    private String responsable;
    private String telephone;
    private String typeoperateur;
}
