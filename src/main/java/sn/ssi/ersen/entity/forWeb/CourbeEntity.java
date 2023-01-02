package sn.ssi.ersen.entity.forWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourbeEntity implements Serializable {//28/032022
    private Map<String,List<Integer>> shs;
    private Map<String,List<Integer>> mc;
    private Map<String,List<Integer>> bt;
}