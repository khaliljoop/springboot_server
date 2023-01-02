package sn.ssi.ersen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Planification implements Serializable {
    private String idcentrale;
    private String idtache;
    private String idperiodicite;
    private String commentaire;
    private String planm;
}
