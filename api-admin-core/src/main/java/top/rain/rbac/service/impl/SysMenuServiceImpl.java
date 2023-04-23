package top.rain.rbac.service.impl;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.rain.common.constant.Constant;
import top.rain.common.utils.TreeUtils;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.convert.SysMenuConvert;
import top.rain.rbac.dao.SysMenuDao;
import top.rain.rbac.entity.SysMenuEntity;
import top.rain.rbac.enums.SuperAdminEnum;
import top.rain.rbac.service.SysMenuService;
import top.rain.rbac.vo.SysMenuVO;
import top.rain.security.user.UserDetail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 系统菜单业务实现类
 *
 * @author rain
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Override
    public List<SysMenuVO> getMenuList(Integer type) {
        List<SysMenuEntity> menuList = baseMapper.getMenuList(type);
        return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(menuList), Constant.ROOT);
    }

    @Override
    public List<SysMenuVO> getUserMenuList(UserDetail user, Integer type) {
        List<SysMenuEntity> menuList;
        // 系统管理员，拥有最高权限
        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            menuList = baseMapper.getMenuList(type);
        } else {
            menuList = baseMapper.getUserMenuList(user.getId(), type);
        }
        return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(menuList));
    }

    @Override
    public Set<String> getUserAuthority(UserDetail user) {
        List<String> authorityList;
        // 系统管理员
        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            // 赋予所有权限
            authorityList = baseMapper.getAuthorityList();
        } else {
            // 查询该用户的所有权限
            authorityList = baseMapper.getUserAuthorityList(user.getId());
        }

        // 根据，分割字符串，得到的list，去重去空，加入set
        Set<String> permsSet = new HashSet<>();
        for (String authority : authorityList) {
            if (StrUtil.isBlank(authority)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(authority.trim().split(",")));
        }
        return permsSet;
    }
}