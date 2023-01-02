package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.ErsenUtilisateurOperateurEntity;
import sn.ssi.ersen.entity.entitieMobile.Utilisateur;

import java.util.List;

@RepositoryRestResource
public interface ErsenUtilisateurOperateurRepository extends JpaRepository<ErsenUtilisateurOperateurEntity,String> {

}
