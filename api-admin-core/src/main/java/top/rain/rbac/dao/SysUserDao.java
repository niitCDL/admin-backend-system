package top.rain.rbac.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.rain.mybatis.dao.BaseDao;
import top.rain.rbac.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户 dao
 *
 * @author rain
 */
@Mapper
public interface SysUserDao extends BaseDao<SysUserEntity> {

    default SysUserEntity getByUsername(String username){
        return this.selectOne(new QueryWrapper<SysUserEntity>().eq("username", username));
    }

    /**
     * 根据条件查询用户数据
     * @param params 参数
     * @return List<SysUserEntity>
     */
    List<SysUserEntity> getList(Map<String,Object> params);

    SysUserEntity getById(@Param("id") Long id);
}