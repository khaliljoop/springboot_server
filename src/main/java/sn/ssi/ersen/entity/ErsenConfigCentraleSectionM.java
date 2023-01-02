package sn.ssi.ersen.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class ErsenConfigCentraleSectionM {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;
    private String centraleId;
    private String sectionMId;
    @Column(name = "is_used",columnDefinition = "boolean default true")
    private boolean isUsed;
    private int status;//les valeurs de etat -1 si c'est non fonctionnel 0 est en panne 1
}
