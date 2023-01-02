package sn.ssi.ersen.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import sn.ssi.ersen.entity.ErsenCentraleEntity;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Accessors(chain = true)
public class AbonneeDto {
    private String adresse;
    private String cni;
    private String nom;
    private String numeroabonnement;
    private String prenom;
    private String telephone;
    private String centrale;
    private String puissancesouscrite;
    private String sexe;
    private String typecategorie;
    private Date date;
    private String id;
}


