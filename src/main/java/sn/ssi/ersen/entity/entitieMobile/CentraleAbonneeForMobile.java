package sn.ssi.ersen.entity.entitieMobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CentraleAbonneeForMobile {
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
}
