package top.rain.rbac.service;


import top.rain.rbac.vo.SysAccountLoginVO;
import top.rain.rbac.vo.SysTokenVO;

/**
 * 权限认证服务
 *
 * @author rain
 */
public interface SysAuthService {
    /**
     * 账号密码登录
     *
     * @param login 登录信息
     */
    SysTokenVO loginByAccount(SysAccountLoginVO login);

    /**
     * 退出登录
     *
     * @param accessToken accessToken
     */
    void logout(String accessToken);
}
