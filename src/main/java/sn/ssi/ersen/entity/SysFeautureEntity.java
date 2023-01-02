package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "sys_feauture")
public class SysFeautureEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String feaCode;
    private String feaApplicationstate;
    private String feaDesc;
    private int feaDisplayable;
    private String feaLibelle;
    private String feaLibelleen;
    private String feaLibellees;
    private int feaSequence;
    private String modCode;
    private Long systeme;
    private String feaImage;
}
