package com.xwl.otherserver.service.impl;

import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.otherserver.vo.Query;
import com.xwl.otherserver.domain.RoleInfo;
import com.xwl.otherserver.mapper.RoleInfoMapper;
import com.xwl.otherserver.service.RoleService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/3 16:45
 * @Description:
 */
@Service
@AllArgsConstructor
@SuppressWarnings("ALL")
public class RoleServiceImpl implements RoleService {
    private RoleInfoMapper roleInfoMapper;

    public MyPage<RoleInfo> findRoleList(Query query, String roleName) {
        Integer current = (query.getCurrent() - 1) * query.getSize();
        List<RoleInfo> userInfoList = roleInfoMapper.findLimit(current, query.getSize(), roleName);
        Integer totals = roleInfoMapper.findCounts(roleName);
        return new MyPage<RoleInfo>(query.getCurrent(), query.getSize(), userInfoList, totals);
    }
    @Transactional
    public void saveRoleByParams(RoleInfo roleInfo) {
        try {
            if (StringUtils.isEmpty(roleInfo.getId())) {
                roleInfoMapper.insert(roleInfo);
            } else {
                roleInfoMapper.updateById(roleInfo);
            }
        } catch (Exception e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
    }

    @Transactional
    public Boolean deleteRoleById(Long id) {
        try {
            Integer integer = roleInfoMapper.deleteById(id);
        } catch (Exception e) {
            throw new ApiException(ExceptionEnum.THROW_SERVER);
        }
        return true;
    }

    public List<RoleInfo> findRoleLists() {
        List<RoleInfo> roleInfos = roleInfoMapper.selectList(null);
        return roleInfos;
    }
}
