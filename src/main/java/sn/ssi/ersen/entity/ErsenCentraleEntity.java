package sn.ssi.ersen.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "ersen_centrale")
public class ErsenCentraleEntity {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String adresse;
    private double coutinstallation;
    private Timestamp datedemarrage;
    private Timestamp datesave;
    private Timestamp dateupdate;
    private double latitude;
    private double longitude;
    private String nom;
    private double puissance;
    private String operateur;
    private String typecentrale;
    private String village;
    private String commune;
    private String departement;
    private String region;
    private String urlimage;
    private String description;
    private String projet;
    private Integer etat_centrale;
    private String plan_maintenance;
    private Timestamp dateService;
    private String entreprise;
    private Date planLastChangeDate;
}
