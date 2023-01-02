package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ErsenTraceTacheReplanifier {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String idTacheCentrale;
    private Date dateLine;

    public ErsenTraceTacheReplanifier(String idTacheCentrale,Date dateLine) {
        this.idTacheCentrale = idTacheCentrale;
        this.dateLine=dateLine;
    }
}
