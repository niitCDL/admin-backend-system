package top.rain.rbac.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import top.rain.common.exception.ServerException;
import top.rain.rbac.service.SysAuthService;
import top.rain.rbac.service.SysCaptchaService;
import top.rain.rbac.vo.SysAccountLoginVO;
import top.rain.rbac.vo.SysTokenVO;
import top.rain.security.cache.TokenStoreCache;
import top.rain.security.user.UserDetail;
import top.rain.security.utils.TokenUtils;


/**
 * 权限认证服务实现类
 *
 * @author rain
 */
@Service
@AllArgsConstructor
public class SysAuthServiceImpl implements SysAuthService {
    private final TokenStoreCache tokenStoreCache;
    private final AuthenticationManager authenticationManager;
    private final SysCaptchaService sysCaptchaService;

    @Override
    public SysTokenVO loginByAccount(SysAccountLoginVO login) {
        //验证码校验
        boolean flag = sysCaptchaService.validate(login.getKey(),login.getCaptcha());

        if (!flag){
            throw new ServerException("验证码错误");
        }
        Authentication authentication;
        try {
            // 用户认证
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ServerException("用户名或密码错误");
        }

        // 用户信息
        UserDetail user = (UserDetail) authentication.getPrincipal();

        // 生成 accessToken
        String accessToken = TokenUtils.generator();

        // 保存用户信息到缓存，accessToken默认过期时间为24小时
        tokenStoreCache.saveUser(accessToken, user);

        return new SysTokenVO(accessToken);
    }

    @Override
    public void logout(String accessToken) {
        // 获取用户信息
        UserDetail user = tokenStoreCache.getUser(accessToken);
        // 从缓存中删除用户
        tokenStoreCache.deleteUser(accessToken);
    }
}
