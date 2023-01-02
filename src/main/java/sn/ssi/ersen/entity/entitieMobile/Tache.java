package sn.ssi.ersen.entity.entitieMobile;

import lombok.*;
import java.util.Date;

@Setter
@Getter
 @AllArgsConstructor
@ToString
public class Tache  {
    private String idTacheDep;
    private String tacheId;
    private String tacheNom;
    private String sectionMaintenance;
    private String etatLibelle;
    private String etatId;
    private String libPeriodicite;
    private String imageAsBase64;
    private Date effectuerLe;
    private Date dateSave;
    private Integer valide;
    private Integer valPeriod;
    private String centralID;
    private Date delai;
    private String tutoriel;
    private Integer type;
    private String planM;

    public Tache(String tacheId, String tacheNom, String sectionMaintenance, String etatLibelle, String etatId, String libPeriodicite, String imageAsBase64, Date effectuerLe, Date dateSave, Integer valide, Integer valPeriod, String centralID, Date delai, String tutoriel, Integer type, String planM) {
        this.tacheId = tacheId;
        this.tacheNom = tacheNom;
        this.sectionMaintenance = sectionMaintenance;
        this.etatLibelle = etatLibelle;
        this.etatId = etatId;
        this.libPeriodicite = libPeriodicite;
        this.imageAsBase64 = imageAsBase64;
        this.effectuerLe = effectuerLe;
        this.dateSave = dateSave;
        this.valide = valide;
        this.valPeriod = valPeriod;
        this.centralID = centralID;
        this.delai = delai;
        this.tutoriel = tutoriel;
        this.type = type;
        this.planM = planM;
    }

    public Tache(String tacheId, String tacheNom, String etatLibelle, String etatId, Date effectuerLe, Date dateSave, Integer valide, String centralID, Date delai, String tutoriel, Integer type) {
        this.tacheId = tacheId;
        this.tacheNom = tacheNom;
        this.etatLibelle = etatLibelle;
        this.etatId = etatId;
        this.effectuerLe = effectuerLe;
        this.dateSave = dateSave;
        this.valide = valide;
        this.centralID = centralID;
        this.delai = delai;
        this.tutoriel = tutoriel;
        this.type = type;
    }
}
