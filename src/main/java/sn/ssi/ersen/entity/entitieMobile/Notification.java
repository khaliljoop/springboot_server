package sn.ssi.ersen.entity.entitieMobile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {
    private String id;
    private Integer impact;//0 vert,1 jaune, 2 rouge
    private String libelle;
    private String objet;
    private String idTacheOrSectM;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date dateSave;
    private Integer isRelative;//equals to 0 if sectionM notif, 1 if tache notif
    private Integer isSync;
    private String centralid;
    private List<Fichier> fichiers;
}
