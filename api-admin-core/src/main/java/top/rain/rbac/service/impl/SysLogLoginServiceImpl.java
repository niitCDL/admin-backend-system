package top.rain.rbac.service.impl;

import org.springframework.stereotype.Service;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.dao.SysLogLoginDao;
import top.rain.rbac.entity.SysLogLoginEntity;
import top.rain.rbac.service.SysLogLoginService;

/**
 * 登录日志业务实现类
 *
 * @author mqxu
 **/
@Service
public class SysLogLoginServiceImpl extends BaseServiceImpl<SysLogLoginDao, SysLogLoginEntity> implements SysLogLoginService {

}
