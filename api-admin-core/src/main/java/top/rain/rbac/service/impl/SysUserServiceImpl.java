package top.rain.rbac.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.dao.SysUserDao;
import top.rain.rbac.entity.SysUserEntity;
import top.rain.rbac.service.SysUserService;

/**
 * 系统用户服务实现类
 *
 * @author rain
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) {
        // 修改密码
        SysUserEntity user = getById(id);
        user.setPassword(newPassword);
        updateById(user);
    }

}
