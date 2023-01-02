package sn.ssi.ersen.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ersen_perf_calcul")
public class ErsenPerfCalcul {
    //Cette entité nous permet de sauvegarder pour chaque tache planifié le nombre de fois qu'elle
    //devrait etre executer et le nombre de fois qu'elle à puis etre executer dans le mois
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String idTacheC;
    private boolean first;
    //doit prend la valeur 0 au moment de la programmation de la tache
    private int nbExec;
    private int frequenceMensuelleMin;
    private Integer nbJourRes;//Le nombre de jours restants du mois sela la frequence d'execution de la tache
    private String centrale;

    //A utiliser si une tache est programmee (une toute nouvelle ou ayant deja ete programmee)
    public ErsenPerfCalcul(String idTacheC, String centrale) {
        this.idTacheC = idTacheC;
        this.centrale = centrale;
        this.nbExec = 0;
        this.first = true;
    }

    public ErsenPerfCalcul(String idTacheC, boolean first, Integer nbExec, Integer frequenceMensuelleMin, Integer nbJourRes, String centrale) {
        this.idTacheC = idTacheC;
        this.first = first;
        this.nbExec = nbExec;
        this.frequenceMensuelleMin = frequenceMensuelleMin;
        this.nbJourRes = nbJourRes;
        this.centrale = centrale;
    }
}
