package sn.ssi.ersen.entity.entitieMobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConductPerf {
    int idConduct;
    String nomConduct;
    String prenomnomConduct;
    Map<String, Object> perfs;

    public ConductPerf(int idConduct,String nomConduct, String prenomnomConduct) {
        this.idConduct = idConduct;
        this.nomConduct = nomConduct;
        this.prenomnomConduct = prenomnomConduct;
    }
}
