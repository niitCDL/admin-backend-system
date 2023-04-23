package top.rain.rbac.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.rain.common.cache.RedisCache;
import top.rain.common.cache.RedisKeys;
import top.rain.rbac.service.SysCaptchaService;
import top.rain.rbac.vo.SysCaptchaVO;

/**
 * 验证码接口实现
 *
 * @author rain
 */
@Service
@AllArgsConstructor
public class SysCaptchaServiceImpl implements SysCaptchaService {
    private final RedisCache redisCache;

    @Override
    public SysCaptchaVO generate() {
        // 生成验证码key
        String key = UUID.randomUUID().toString();
        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        String image = captcha.toBase64();

        // 保存到缓存
        String redisKey = RedisKeys.getCaptchaKey(key);
        redisCache.set(redisKey, captcha.text(), 300);

        // 封装返回数据
        SysCaptchaVO captchaVO = new SysCaptchaVO();
        captchaVO.setKey(key);
        captchaVO.setImage(image);

        return captchaVO;
    }

    /**
     *
     * @param key  key redis的后缀key
     * @param code 验证码
     * @return
     */
    @Override
    public boolean validate(String key, String code) {
        if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            return false;
        }

        // 获取redis中的验证码
        String captcha = getCache(key);

        // 效验成功
        return code.equalsIgnoreCase(captcha);
    }

    private String getCache(String key) {
        key = RedisKeys.getCaptchaKey(key);
        String captcha = (String) redisCache.get(key);
        // 删除验证码
        if (captcha != null) {
            redisCache.delete(key);
        }

        return captcha;
    }

}