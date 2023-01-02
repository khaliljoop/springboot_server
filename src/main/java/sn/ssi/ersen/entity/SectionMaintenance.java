package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SectionMaintenance implements Serializable {
    Map<String, CentraleTache> red;
    Map<String, CentraleTache> green;
    Map<String, CentraleTache> orange;
    Map<String, CentraleTache> grey;
}
