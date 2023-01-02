package sn.ssi.ersen.entity.forWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpontaneousTask {
    private String nomTache;
    private Date dateLimite;
    private Integer level;
    private Integer type;
    private List<String> entr_oper;
    private String tuto;
}
