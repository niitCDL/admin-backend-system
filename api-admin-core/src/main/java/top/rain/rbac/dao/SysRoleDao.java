package top.rain.rbac.dao;

import org.apache.ibatis.annotations.Mapper;
import top.rain.mybatis.dao.BaseDao;
import top.rain.rbac.entity.SysRoleEntity;

/**
 * 角色管理 dao
 *
 * @author mqxu
 */
@Mapper
public interface SysRoleDao extends BaseDao<SysRoleEntity> {

}
