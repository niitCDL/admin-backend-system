package top.rain.rbac.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.stereotype.Service;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.dao.SysUserRoleDao;
import top.rain.rbac.entity.SysUserRoleEntity;
import top.rain.rbac.service.SysUserRoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户角色关系业务实现类
 *
 * @author rain
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {
    @Override
    public List<Long> getRoleIdList(Long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",id);
        List<SysUserRoleEntity> list = baseMapper.selectList(queryWrapper);
        List<Long> roleIdList = new ArrayList<>();
        for (SysUserRoleEntity sysUserRoleEntity : list) {
            roleIdList.add(sysUserRoleEntity.getRoleId());
        }
        return roleIdList;
    }

}