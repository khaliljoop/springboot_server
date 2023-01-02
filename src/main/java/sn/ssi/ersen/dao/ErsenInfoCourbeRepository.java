package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sn.ssi.ersen.entity.ErsenInfoCourbe;

@RepositoryRestResource
public interface ErsenInfoCourbeRepository extends JpaRepository<ErsenInfoCourbe, String> {
}
