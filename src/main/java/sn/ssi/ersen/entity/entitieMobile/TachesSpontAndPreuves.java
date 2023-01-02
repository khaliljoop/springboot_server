package sn.ssi.ersen.entity.entitieMobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TachesSpontAndPreuves {
    //The AllArgsConstructor is user after connection in order refresh and download
    // new spontaneous tasks or to replic those that has been deleted.
    List<Tache> tachesSpontannees;
    List<Tutoriel> tutoTachesSpon;
    List<Preuve> preuvesTachesSpon;
    List<String> deletedSponTasksIds;

    //User for downloading spontaneous tasks from server on user login through the mobile application.
    public TachesSpontAndPreuves(List<Tache> tachesSpontannees, List<Tutoriel> tutoTachesSpon, List<Preuve> preuvesTachesSpon) {
        this.tachesSpontannees = tachesSpontannees;
        this.tutoTachesSpon = tutoTachesSpon;
        this.preuvesTachesSpon = preuvesTachesSpon;
    }
}

