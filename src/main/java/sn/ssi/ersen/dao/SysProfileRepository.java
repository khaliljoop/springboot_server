package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.SysProfilEntity;

@Repository
public interface SysProfileRepository extends JpaRepository<SysProfilEntity,String> {
}