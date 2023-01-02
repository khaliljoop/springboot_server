package sn.ssi.ersen.dto.utilisateurStat;

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
public class UtilisateurDto implements Serializable {
    private String pp;
    private String usrPrenom;
    private String usrNom;
    private String tel;
    private String usrMail;
    private String typeutilisateur;
    private String entreprise;
}
