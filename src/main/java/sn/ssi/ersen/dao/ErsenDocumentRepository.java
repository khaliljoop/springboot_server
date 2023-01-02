package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenBatterieEntity;
import sn.ssi.ersen.entity.ErsenDocumentEntity;

@Repository
public interface ErsenDocumentRepository extends JpaRepository<ErsenDocumentEntity,String> {
}