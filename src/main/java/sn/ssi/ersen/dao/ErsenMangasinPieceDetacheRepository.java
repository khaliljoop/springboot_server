package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenMangasinPieceDetacheEntity;
import sn.ssi.ersen.entity.ErsenUtilisateurOperateurEntity;

@Repository
public interface ErsenMangasinPieceDetacheRepository extends JpaRepository<ErsenMangasinPieceDetacheEntity,String> {
}
