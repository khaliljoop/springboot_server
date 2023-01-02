package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.SysParametresgenerauxEntity;

@RepositoryRestResource
public interface ErsenParametresGenerauxRepository extends JpaRepository<SysParametresgenerauxEntity, String> {
    @RestResource
    @Query(value = "SELECT LIBELLE FROM `sys_parametresgeneraux` WHERE code='URL_IMAGES'",nativeQuery = true)
    String getDirectory();
}

