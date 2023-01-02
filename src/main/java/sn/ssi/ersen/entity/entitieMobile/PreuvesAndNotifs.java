package sn.ssi.ersen.entity.entitieMobile;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreuvesAndNotifs {
    int idExpediteur;
    String idDestinataire;
    Integer byOperateur;
    List<Notification> notifs;
    List<Preuve> preuves;
    List<Notification> notifsTacheDep;
    List<Preuve> preuvesTacheDep;
}
