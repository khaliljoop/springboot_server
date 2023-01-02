package sn.ssi.ersen.entity.forWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sn.ssi.ersen.entity.entitieMobile.Fichier;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PreuveWeb implements Serializable {
    private String id;
    private Date datesave;
    private List<Fichier> fichiers;
}
