package sn.ssi.ersen.entity.entitieMobile;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tutoriel {
    String id;
    Date datesave;
    String nom;
    List<Fichier> fichiers;
}
