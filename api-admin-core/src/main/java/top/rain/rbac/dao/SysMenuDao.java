package top.rain.rbac.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.rain.mybatis.dao.BaseDao;
import top.rain.rbac.entity.SysMenuEntity;

import java.util.List;


/**
 * 菜单管理 dao
 *
 * @author mqxu
 */
@Mapper
public interface SysMenuDao extends BaseDao<SysMenuEntity> {

}