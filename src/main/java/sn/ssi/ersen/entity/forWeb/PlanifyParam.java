package sn.ssi.ersen.entity.forWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlanifyParam {
    private String cct;
    private String periodicite;
    private String tutoriel;
}
