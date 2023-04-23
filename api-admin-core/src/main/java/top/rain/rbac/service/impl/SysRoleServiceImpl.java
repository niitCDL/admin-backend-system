package top.rain.rbac.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.dao.SysRoleDao;
import top.rain.rbac.entity.SysRoleEntity;
import top.rain.rbac.service.SysRoleService;

/**
 * 系统角色业务实现类
 *
 * @author mqxu
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends BaseServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

}