package top.rain.rbac.service.impl;

import org.springframework.stereotype.Service;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.dao.SysRoleMenuDao;
import top.rain.rbac.entity.SysRoleMenuEntity;
import top.rain.rbac.service.SysRoleMenuService;


/**
 * 角色与菜单对应业务实现类
 *
 * @author mqxu
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl<SysRoleMenuDao, SysRoleMenuEntity> implements SysRoleMenuService {

}