package sn.ssi.ersen.entity.entitieMobile;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Abonne {
    String id;
    String adresse;
    String cni;
    String nom;
    String prenom;
    String numeroabonnement;
    String telephone;
    String centrale;
    String puissancesouscrite;
    String sexe;
    String typecategorie;
    String date;
}