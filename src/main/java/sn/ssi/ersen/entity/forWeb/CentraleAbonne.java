package sn.ssi.ersen.entity.forWeb;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import sn.ssi.ersen.entity.ErsenAbonneEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CentraleAbonne {
    private String id;
    private String adresse;
    private Double coutinstallation;
    private Timestamp datedemarrage;
    private Timestamp datesave;
    private Timestamp dateupdate;
    private Double latitude;
    private Double longitude;
    private String nom;
    private Double puissance;
    private String operateur;
    private String typecentrale;
    private String village;
    private String urlimage;
    private String description;
    private String projet;
    private List<ErsenAbonneEntity> abonnes;
}
