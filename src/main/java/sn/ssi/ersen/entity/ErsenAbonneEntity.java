package sn.ssi.ersen.entity;

import lombok.*;
import sn.ssi.ersen.static_and_constants.StaticObjects;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "ersen_abonne")
public class ErsenAbonneEntity implements Serializable {
    @Id
    private String id;
    private String adresse;
    private String cni;
    private String nom;
    private String numeroabonnement;
    private String prenom;
    private String telephone;
    private String centrale;
    private String puissancesouscrite;
    private String sexe;
    private String typecategorie;
    private Date date;
    private Integer etat;
    private String mode_facturation;

    @PrePersist
    @PreUpdate
    void setIdIfMissing() {
        if (id == null) {
            id = StaticObjects.getCredential();
        }
    }
}
