package top.rain.rbac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import top.rain.common.constant.Constant;
import top.rain.common.excel.ExcelFinishCallBack;
import top.rain.common.exception.ServerException;
import top.rain.common.utils.DateUtils;
import top.rain.common.utils.ExcelUtils;
import top.rain.common.utils.PageResult;
import top.rain.mybatis.service.impl.BaseServiceImpl;
import top.rain.rbac.convert.SysUserConvert;
import top.rain.rbac.dao.SysUserDao;
import top.rain.rbac.entity.SysUserEntity;
import top.rain.rbac.enums.SuperAdminEnum;
import top.rain.rbac.query.SysUserQuery;
import top.rain.rbac.service.SysUserService;
import top.rain.rbac.vo.SysUserExcelVO;
import top.rain.rbac.vo.SysUserVO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户服务实现类
 *
 * @author rain
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(Long id, String newPassword) {
        // 修改密码
        SysUserEntity user = getById(id);
        user.setPassword(newPassword);
        updateById(user);
    }

    @Override
    public PageResult<SysUserVO> page(SysUserQuery query) {
        Map<String, Object> params = getParams(query);
        IPage<SysUserEntity> page = getPage(query);
        params.put(Constant.PAGE, page);
        List<SysUserEntity> list = baseMapper.getList(params);
        return new PageResult<>(SysUserConvert.INSTANCE.convertList(list), page.getTotal());
    }

    private Map<String, Object> getParams(SysUserQuery query) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", query.getUsername());
        params.put("mobile", query.getMobile());
        params.put("gender", query.getGender());
        return params;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysUserVO vo) {
        SysUserEntity entity = SysUserConvert.INSTANCE.convert(vo);
        entity.setSuperAdmin(SuperAdminEnum.NO.getValue());
        isExistByUserName(entity.getUsername());
        baseMapper.insert(entity);
    }

    private void isExistByUserName(String username) {
        SysUserEntity user = baseMapper.getByUsername(username);
        if (user != null) {
            throw new ServerException("用户名已经存在");
        }
    }

    @Override
    public void update(SysUserVO vo) {
        SysUserEntity entity = SysUserConvert.INSTANCE.convert(vo);
        isExistByUserName(entity.getUsername());
        updateById(entity);
    }

    @Override
    public void delete(List<Long> ids) {
        removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importByExcel(MultipartFile file, String password) {
        ExcelUtils.readAnalysis(file, SysUserExcelVO.class, new ExcelFinishCallBack<>() {

            @Override
            public void doAfterAllAnalysed(List<SysUserExcelVO> result) {
                saveUser(result);
            }

            @Override
            public void doSaveBatch(List<SysUserExcelVO> result) {
                ExcelFinishCallBack.super.doSaveBatch(result);
            }

            private void saveUser(List<SysUserExcelVO> result) {
//                ExcelUtils.parseDict(result);
                List<SysUserEntity> sysUserEntities = SysUserConvert.INSTANCE.convertListEntity(result);
                sysUserEntities.forEach(user -> user.setPassword(password));
                saveBatch(sysUserEntities);
            }
        });
    }


    @Override
    public void export() {
        List<SysUserEntity> list = list(Wrappers.lambdaQuery(SysUserEntity.class)
                .eq(SysUserEntity::getSuperAdmin, SuperAdminEnum.NO.getValue()));
        List<SysUserExcelVO> userExcelVOList = SysUserConvert.INSTANCE.convert2List(list);
        ExcelUtils.excelExport(SysUserExcelVO.class,
                "system_user_excel" + DateUtils.format(new Date()),
                "sheet1", userExcelVOList);
    }

}
