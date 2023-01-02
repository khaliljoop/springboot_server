package sn.ssi.ersen.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ersen_tutoriel")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class ErsenTutorielEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private Date datesave;
    private Date dateupdate;
    private String nom;
}
