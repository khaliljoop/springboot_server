package sn.ssi.ersen.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class CentraleDto implements Serializable {
/*    private String id;*/
    private String adresse;
    private Double coutinstallation;
    private String nom;
    private String operateur;
    private String typecentrale;
    private String village;
    private String commune;
    private String departement;
    private String region;
    private String projet;
    private Integer etat_centrale;
}
