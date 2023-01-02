package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.SysOptionEntity;

@Repository
public interface SysOptionRepository extends JpaRepository<SysOptionEntity,String> {
}
