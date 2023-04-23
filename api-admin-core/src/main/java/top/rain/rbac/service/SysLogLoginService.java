package top.rain.rbac.service;

import top.rain.common.utils.PageResult;
import top.rain.mybatis.service.BaseService;
import top.rain.rbac.entity.SysLogLoginEntity;
import top.rain.rbac.query.SysLogLoginQuery;
import top.rain.rbac.vo.SysLogLoginVO;

/**
 * 登录日志业务接口
 *
 * @author mqxu
 **/
public interface SysLogLoginService extends BaseService<SysLogLoginEntity> {
    /**
     * 按条件分页查询
     *
     * @param query 查询参数
     * @return 分页结果
     */
    PageResult<SysLogLoginVO> page(SysLogLoginQuery query);

    /**
     * 保存登录日志
     *
     * @param username  用户名
     * @param status    登录状态
     * @param operation 操作信息
     */
    void save(String username, Integer status, Integer operation);
}
