package com.xwl.otherserver.service;

import com.xwl.otherserver.dto.RoleMenuDto;
import com.xwl.otherserver.vo.Query;
import com.xwl.otherserver.domain.RoleInfo;
import com.xwl.otherserver.utils.MyPage;
import org.springframework.util.StringUtils;

import java.util.List;

public interface RoleService {
    MyPage<RoleInfo> findRoleList(Query query, String roleName);

    void saveRoleByParams(RoleInfo roleInfo);

    Boolean deleteRoleById(Long id);

    List<RoleInfo> findRoleLists();

    List<RoleMenuDto> findRoleMenuListById(Long id);

    Boolean saveRoleWithMenuByIds(String permissIds, Long roleId, String mydeleteds);
}
