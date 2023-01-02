package sn.ssi.ersen.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "utilisateur")
public class UtilisateurEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private Integer usrId;
    @Basic
    @Column(name = "USR_LOGIN")
    private String usrLogin;
    @Basic
    @Column(name = "USR_MAIL")
    private String usrMail;
    @Basic
    @Column(name = "USR_NOM")                           
    private String usrNom;
    @Basic
    @Column(name = "USR_PASSWORD")
    private String usrPassword;
    @Basic
    @Column(name = "USR_PRENOM")
    private String usrPrenom;
    @Basic
    @Column(name = "tel")
    private String tel;
    @Basic
    @Column(name = "PF_CODE")
    private String pfCode;
    @Basic
    @Column(name = "typeutilisateur")
    private String typeutilisateur;
    @Basic
    @Column(name = "pp")
    private String pp;
    @Basic
    @Column(name = "entreprise")
    private String entreprise;
    @Basic
    @Column(name = "hasnotif")
    private Boolean hasnotif;
}
