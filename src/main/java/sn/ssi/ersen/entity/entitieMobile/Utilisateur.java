package sn.ssi.ersen.entity.entitieMobile;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    private Integer userId;
    private String tel;
    private String nom;
    private String prenom;
    private String password;
    private String userType;
    private String entreprise;
    private String photoProfil;
}
