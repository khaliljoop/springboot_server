package sn.ssi.ersen.entity.forWeb;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpontaneousTaskCentrale {
    private String idtc;
    private String idcct;
    private Date datesave;
    private Date dateeffectuee;
    private Integer valide;
    private String tutoriel;
    private Date datelimite;
    private String requestname;
    private String centrale_or_entreprise;
    private Integer for_oper;
}
