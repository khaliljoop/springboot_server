package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.SysActionEntity;
import sn.ssi.ersen.entity.SysRoleActionEntity;

@Repository
public interface SysActionRepository extends JpaRepository<SysActionEntity,String> {
}
