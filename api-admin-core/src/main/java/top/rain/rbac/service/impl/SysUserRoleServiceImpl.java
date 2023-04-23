package top.rain.rbac.service.impl;


import org.springframework.stereotype.Service;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.dao.SysUserRoleDao;
import top.rain.rbac.entity.SysUserRoleEntity;
import top.rain.rbac.service.SysUserRoleService;

/**
 * 用户角色关系业务实现类
 *
 * @author rain
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {
}