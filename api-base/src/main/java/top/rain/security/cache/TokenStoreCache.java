package top.rain.security.cache;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.rain.common.cache.RedisCache;
import top.rain.common.cache.RedisKeys;
import top.rain.security.user.UserDetail;

/**
 * 登录用户的缓存操作
 *
 * @author rain
 */
@Component
@AllArgsConstructor
public class TokenStoreCache {

    private final RedisCache redisCache;

    public void saveUser(String accessToken, UserDetail user) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.set(key, user);
    }

    public UserDetail getUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        return (UserDetail) redisCache.get(key);
    }

    public void deleteUser(String accessToken) {
        String key = RedisKeys.getAccessTokenKey(accessToken);
        redisCache.delete(key);
    }
}
