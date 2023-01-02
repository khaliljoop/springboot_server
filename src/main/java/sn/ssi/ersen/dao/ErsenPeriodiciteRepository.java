package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.ErsenPeriodiciteEntity;

@Repository
public interface ErsenPeriodiciteRepository extends JpaRepository<ErsenPeriodiciteEntity, String> {
}
