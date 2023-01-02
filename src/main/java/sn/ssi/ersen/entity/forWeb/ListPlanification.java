package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListPlanification implements Serializable {
    private String idcentrale;
    private List<String> idtache;
    private String idperiodicite;
    private String commentaire;
    private String planm;
}
