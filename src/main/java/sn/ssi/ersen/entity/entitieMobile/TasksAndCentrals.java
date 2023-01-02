package sn.ssi.ersen.entity.entitieMobile;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TasksAndCentrals {
    List<Centrale> centrales;
    List<Tache> taches;
    List<Tache> tachesDep;
}
