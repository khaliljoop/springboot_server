package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sn.ssi.ersen.entity.AllEquipementEntity;

import java.util.List;

@RepositoryRestResource
public interface AllEquipementRepository  extends JpaRepository<AllEquipementEntity,Integer> {
    @RestResource
    @Query(value = "select * from :tablename ", nativeQuery = true)
    List<Object> getEquipementByTableName(String tablename);


}
