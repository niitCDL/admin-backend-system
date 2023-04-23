package top.rain.rbac.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.dao.SysUserDao;
import top.rain.rbac.entity.SysUserEntity;
import top.rain.rbac.service.SysUserService;

/**
 * 系统用户业务实现类
 *
 * @author mqxu
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

}
