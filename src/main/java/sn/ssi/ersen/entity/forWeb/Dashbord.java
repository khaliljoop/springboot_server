package sn.ssi.ersen.entity.forWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.ssi.ersen.entity.ErsenCentraleEntity;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dashbord {
    private List<String> centrale;
    private List<String> mc;
    private List<String> shs;
    private List<String> bt;
    private List<String> mcfunc;
    private List<String> mcpanne;
    private List<String> mcnonfunc;
    private List<String> shsfunc;
    private List<String> shspanne;
    private List<String> shsnonfunc;
    private List<String> btfunc;
    private List<String> btpanne;
    private List<String> btnonfunc;
}
