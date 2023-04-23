package top.rain.security.user;

import org.springframework.security.core.context.SecurityContextHolder;
import top.rain.security.user.UserDetail;

/**
 * Security 用户
 *
 * @author rain
 */
public class SecurityUser {

    /**
     * 获取用户信息
     */
    public static UserDetail getUser() {
        UserDetail user;
        try {
            user = (UserDetail)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return new UserDetail();
        }

        return user;
    }

    /**
     * 获取用户ID
     */
    public static Long getUserId() {
        return getUser().getId();
    }

}