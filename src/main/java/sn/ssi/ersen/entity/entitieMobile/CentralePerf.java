package sn.ssi.ersen.entity.entitieMobile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CentralePerf {
    String idC;
    String centralName;
   List<ConductPerf> conductPerfs;

    public CentralePerf(String idC, String centralName) {
        this.idC = idC;
        this.centralName = centralName;
        conductPerfs= new ArrayList<>();
    }
}
