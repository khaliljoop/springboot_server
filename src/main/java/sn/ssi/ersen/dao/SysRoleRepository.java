package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.SysRoleEntity;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRoleEntity,String> {
}
