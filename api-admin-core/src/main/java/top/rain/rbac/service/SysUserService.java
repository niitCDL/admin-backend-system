package top.rain.rbac.service;


import top.rain.mybatis.service.BaseService;
import top.rain.rbac.entity.SysUserEntity;

/**
 * 系统用户服务接口
 *
 * @author rain
 */
public interface SysUserService extends BaseService<SysUserEntity> {

    /**
     * 修改密码
     *
     * @param id          用户ID
     * @param newPassword 新密码
     */
    void updatePassword(Long id, String newPassword);

}
