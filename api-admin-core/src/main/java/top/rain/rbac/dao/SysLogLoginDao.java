package top.rain.rbac.dao;

import org.apache.ibatis.annotations.Mapper;
import top.rain.mybatis.dao.BaseDao;
import top.rain.rbac.entity.SysLogLoginEntity;

/**
 * 登录日志 dao
 *
 * @author rain
 **/
@Mapper
public interface SysLogLoginDao extends BaseDao<SysLogLoginEntity> {

}