package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sys_stateperm")
public class SysStatepermEntity implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private long spmId;
    private short staCanadd;
    private Short staCandelete;
    private short staCanedit;
    private short staCanprint;
    private short staCanview;
    private String pfCode;
    private String rolCode;
    private String staCode;
}
