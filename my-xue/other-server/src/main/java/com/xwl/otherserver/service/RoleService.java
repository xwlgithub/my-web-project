package com.xwl.otherserver.service;

import com.xwl.otherserver.domain.Query;
import com.xwl.otherserver.domain.RoleInfo;
import com.xwl.otherserver.utils.MyPage;

import java.util.List;

public interface RoleService {
    MyPage<RoleInfo> findRoleList(Query query, String roleName);

    void saveRoleByParams(RoleInfo roleInfo);

    Boolean deleteRoleById(Long id);

    List<RoleInfo> findRoleLists();

}
