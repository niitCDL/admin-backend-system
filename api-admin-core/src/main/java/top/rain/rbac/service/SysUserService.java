package top.rain.rbac.service;


import org.springframework.web.multipart.MultipartFile;
import top.rain.common.utils.PageResult;
import top.rain.mybatis.service.BaseService;
import top.rain.rbac.entity.SysUserEntity;
import top.rain.rbac.query.SysUserQuery;
import top.rain.rbac.vo.SysUserVO;

import java.util.List;

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

    PageResult<SysUserVO> page(SysUserQuery query);

    void save(SysUserVO vo);

    void update(SysUserVO vo);

    void delete(List<Long> ids);

    //导入
    void importByExcel(MultipartFile file,String password);

    //导出
    void export();

    //修改用户状态
    void updateStatus(Long id,Integer status);

}
