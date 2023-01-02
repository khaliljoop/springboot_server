package sn.ssi.ersen.entity.entitieMobile;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewTasksAndPlans {
    List<Tache> newTaches;
    List<Tutoriel> newTachesTutoriels;
    List<CentralPlanMChange> planMChanges;
}
