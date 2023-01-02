package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenDetailRecusEntity;
import sn.ssi.ersen.entity.ErsenRecusEntity;

import java.util.List;

@Repository
public interface ErsenDetailRecusRepository extends JpaRepository<ErsenDetailRecusEntity,String> {

}
