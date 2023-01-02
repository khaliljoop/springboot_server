package sn.ssi.ersen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import sn.ssi.ersen.entity.SysRoleActionEntity;
import java.util.List;

@Repository
public interface SysRoleActionRepository extends JpaRepository<SysRoleActionEntity,Integer> {
    @RestResource
    @Query(value = "SELECT ra.id idroleaction, ra.role, o.libelle opt FROM `sys_roleaction` ra, `sys_option` o WHERE ra._option=o.id",nativeQuery = true)
    List<Object> getRolesActions();

    @RestResource
    @Query(value = "SELECT ra.* from ersen_typeutilisateur tu, sys_role r, sys_roleaction ra \n" +
            "WHERE tu.role=r.id AND r.id=ra.role AND tu.Id=:userType", nativeQuery = true)
    List<SysRoleActionEntity> getRolesActionsByUserType(String userType);
}