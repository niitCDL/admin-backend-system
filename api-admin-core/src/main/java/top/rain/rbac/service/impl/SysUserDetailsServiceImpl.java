package top.rain.rbac.service.impl;


import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import top.rain.rbac.convert.SysUserConvert;
import top.rain.rbac.entity.SysUserEntity;
import top.rain.rbac.enums.UserStatusEnum;
import top.rain.rbac.service.SysMenuService;
import top.rain.rbac.service.SysUserDetailsService;
import top.rain.security.user.UserDetail;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户详细信息接口实现类
 *
 * @author rain
 */
@Service
@AllArgsConstructor
public class SysUserDetailsServiceImpl implements SysUserDetailsService {
    private final SysMenuService sysMenuService;

    @Override
    public UserDetails getUserDetails(SysUserEntity userEntity) {
        // 转换成UserDetail对象
        UserDetail userDetail = SysUserConvert.INSTANCE.convertDetail(userEntity);

        // 账号不可用
        if (userEntity.getStatus() == UserStatusEnum.DISABLE.getValue()) {
            userDetail.setEnabled(false);
        }

        // 用户权限列表
        Set<String> authoritySet = sysMenuService.getUserAuthority(userDetail);
        userDetail.setAuthoritySet(authoritySet);

        return userDetail;
    }

}
