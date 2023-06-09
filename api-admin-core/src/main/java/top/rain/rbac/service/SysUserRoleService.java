package top.rain.rbac.service;


import top.rain.mybatis.service.BaseService;
import top.rain.rbac.entity.SysUserRoleEntity;

import java.util.List;

/**
 * 用户角色关系业务接口
 *
 * @author rain
 */
public interface SysUserRoleService extends BaseService<SysUserRoleEntity> {
    List<Long> getRoleIdList(Long id);
}