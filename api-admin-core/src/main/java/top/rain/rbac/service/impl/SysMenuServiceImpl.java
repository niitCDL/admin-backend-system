package top.rain.rbac.service.impl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.dao.SysMenuDao;
import top.rain.rbac.entity.SysMenuEntity;
import top.rain.rbac.service.SysMenuService;


/**
 * 系统菜单业务实现类
 *
 * @author mqxu
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

}