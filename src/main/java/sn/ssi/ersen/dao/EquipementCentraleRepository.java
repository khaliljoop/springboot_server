package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.EquipementCentrale;

import java.util.List;

@Repository
@RepositoryRestResource
public interface EquipementCentraleRepository extends JpaRepository<EquipementCentrale,String> {
}
