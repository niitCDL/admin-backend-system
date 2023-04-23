package top.rain.rbac.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import top.rain.mybatis.dao.BaseDao;
import top.rain.rbac.entity.SysUserEntity;

/**
 * 系统用户管理 dao
 *
 * @author mqxu
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

}