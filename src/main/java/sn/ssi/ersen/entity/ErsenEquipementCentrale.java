package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import sn.ssi.ersen.entity.equipement.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "ersenequipementcentrale")
public class ErsenEquipementCentrale implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @ManyToOne
    @JoinColumn(name = "centrale")
    private ErsenCentraleEntity centrale;

    @ManyToOne
    @JoinColumn(name = "batfus")
    private BatFusEntity batFus;

    @ManyToOne
    @JoinColumn(name = "batterie")
    private BatterieEntity batterie;

    @ManyToOne
    @JoinColumn(name = "boitierconnexionpv")
    private BoiteConnexionPvEntity boitierConnexionPv;

    @ManyToOne
    @JoinColumn(name = "compteur")
    private CompteurEntity compteur;

    @ManyToOne
    @JoinColumn(name = "concentrateur")
    private ConcentrateurEntity concentrateur;

    @ManyToOne
    @JoinColumn(name = "ep")
    private EPEntity ep;

    @ManyToOne
    @JoinColumn(name = "groupeelectrogene")
    private GroupeElectEntity groupeElectrogene;

    @ManyToOne
    @JoinColumn(name = "kiosques")
    private KiosquesEntity kiosques;

    @ManyToOne
    @JoinColumn(name = "mcbox")
    private McBoxEntity mcBox;

    @ManyToOne
    @JoinColumn(name = "onduleurchargeur")
    private OnduleurChargeurEntity onduleurChargeur;

    @ManyToOne
    @JoinColumn(name = "onduleurreseau")
    private OnduleurReseauEntity onduleurReseau;

    @ManyToOne
    @JoinColumn(name = "pannaux")
    private PannauxEntity pannaux;

    @ManyToOne
    @JoinColumn(name = "regulateurcharge")
    private RegulateurChargeEntity regulateurCharge;

    @ManyToOne
    @JoinColumn(name = "reseaubt")
    private ReseauBTEntity reseauBT;
}
