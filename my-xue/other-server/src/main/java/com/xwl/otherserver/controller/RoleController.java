package com.xwl.otherserver.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xwl.comserver.domain.RoleInfo;
import com.xwl.comserver.exception.ApiException;
import com.xwl.comserver.exception.ExceptionEnum;
import com.xwl.comserver.utils.R;
import com.xwl.otherserver.dto.RoleMenuDto;
import com.xwl.otherserver.vo.Query;
import com.xwl.otherserver.service.RoleService;
import com.xwl.otherserver.utils.MyPage;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/6/3 16:36
 * @Description:
 */
@RestController
@RequestMapping("/roleList")
@AllArgsConstructor
@SuppressWarnings("ALL")
public class RoleController {
    private RoleService roleService;

    /**
     * 分页查询
     *
     * @param query
     * @return
     */
    @GetMapping("findRoleList")
    public R<MyPage<RoleInfo>> findRoleList(Query query, String roleName) {
        MyPage<RoleInfo> infoMyPage = roleService.findRoleList(query, roleName);
        return R.data(infoMyPage);
    }

    /**
     * 新增或修改
     *
     * @param userJson
     * @return
     */
    @PostMapping("saveRoleByParams")
    public R<Boolean> saveRoleByParams(@RequestBody String userJson) {
        Object roleObject = JSONObject.parseObject(userJson).get("roleJson");
        RoleInfo roleInfo = JSONObject.toJavaObject((JSON) roleObject, RoleInfo.class);
        if (StringUtils.isEmpty(roleInfo)) {
            return R.errors(ExceptionEnum.MUST_PARAM_IS_NOT_NULL);
        }
        try {
            roleService.saveRoleByParams(roleInfo);
        } catch (ApiException e) {
            return R.errors(e.getExceptionEnum());
        }
        return R.data(true);
    }

    /**
     * 删除用户-ID
     *
     * @param id
     * @return
     */
    @PostMapping("deleteRoleById/{id}")
    public R<Boolean> deleteRoleById(@PathVariable("id") Long id) {
        if (StringUtils.isEmpty(id)) {
            return R.errors(ExceptionEnum.MUST_PARAM_IS_NOT_NULL);
        }
        Boolean isSuccess = null;
        try {
            isSuccess = roleService.deleteRoleById(id);
        } catch (ApiException e) {
            return R.errors(e.getExceptionEnum());
        }
        return R.data(isSuccess);
    }

    /**
     * 角色下拉
     *
     * @return
     */
    @GetMapping("findRoleLists")
    public R<List<RoleInfo>> findRoleLists() {
        List<RoleInfo> roleInfoList = null;
        try {
            roleInfoList = roleService.findRoleLists();
        } catch (ApiException e) {
            return R.errors(ExceptionEnum.THROW_SERVER);
        }
        return R.data(roleInfoList);
    }

    /**
     * 根据角色id渲染权限树形菜单
     *
     * @param id
     * @return
     */
    @GetMapping("findRoleMenuListById/{id}")
    public R<List<RoleMenuDto>> findRoleMenuListById(@PathVariable("id") Long id) {
        List<RoleMenuDto> menuDtoList = null;
        try {
            menuDtoList = roleService.findRoleMenuListById(id);
        } catch (ApiException e) {
            return R.errors(ExceptionEnum.THROW_SERVER);
        }
        return R.data(menuDtoList);
    }

    /**
     * 角色分配权限
     *
     * @param permissIds
     * @return
     */
    @PostMapping("saveRoleWithMenuByIds")
    public R<Boolean> saveRoleWithMenuByIds(@RequestParam String permissIds,
                                            @RequestParam Long roleId,
                                            @RequestParam String mydeleteds) {
        if (StringUtils.isEmpty(permissIds)&&StringUtils.isEmpty(mydeleteds)) {
            return R.data(true);
        }
        Boolean isSucces = false;
        try {
            isSucces = roleService.saveRoleWithMenuByIds(permissIds, roleId,mydeleteds);
        } catch (ApiException e) {
            return R.errors(e.getExceptionEnum());
        }
        return R.data(isSucces);
    }
}
