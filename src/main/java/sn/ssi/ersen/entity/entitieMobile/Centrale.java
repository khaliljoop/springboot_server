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
public class Centrale {
    private String id;
    private String nom;
    private String urlimage;
    private double puissance;
    private String typecentrale;
    private String idProjet;
    private String projetName;
    private Date datedemarrage;
    private Integer etat;
    private Integer nbAbonnees;
    private String idPlanM;
    private String nomPlanM;
    private String idEntreprise;
    private String nomEntreprise;

    public Centrale(String id, String nom, String urlimage, double puissance, String typecentrale, String idEntreprise, String idProjet, Date datedemarrage, Integer etat, String idPlanM) {
        this.id = id;
        this.nom = nom;
        this.urlimage = urlimage;
        this.puissance = puissance;
        this.typecentrale = typecentrale;
        this.idEntreprise=idEntreprise;
        this.idProjet = idProjet;
        this.datedemarrage = datedemarrage;
        this.etat = etat;
        this.idPlanM = idPlanM;
    }
}