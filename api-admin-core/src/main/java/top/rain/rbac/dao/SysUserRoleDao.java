package top.rain.rbac.dao;

import org.apache.ibatis.annotations.Mapper;
import top.rain.mybatis.dao.BaseDao;
import top.rain.rbac.entity.SysUserRoleEntity;


/**
 * 用户角色关系 dao
 *
 * @author mqxu
 */
@Mapper
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {

}