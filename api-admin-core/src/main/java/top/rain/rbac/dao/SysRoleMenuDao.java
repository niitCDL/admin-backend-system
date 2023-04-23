package top.rain.rbac.dao;

import org.apache.ibatis.annotations.Mapper;
import top.rain.mybatis.dao.BaseDao;
import top.rain.rbac.entity.SysRoleMenuEntity;


/**
 * 角色与菜单对应关系 dao
 *
 * @author rain
 */
@Mapper
public interface SysRoleMenuDao extends BaseDao<SysRoleMenuEntity> {
}
