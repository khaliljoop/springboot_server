package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenTypeutilisateurEntity;

@Repository
public interface ErsenTypeUtilisateurRepository extends JpaRepository<ErsenTypeutilisateurEntity,String> {
}
