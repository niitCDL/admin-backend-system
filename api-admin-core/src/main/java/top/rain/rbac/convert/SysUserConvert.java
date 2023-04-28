package top.rain.rbac.convert;

import org.apache.catalina.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.rain.rbac.entity.SysUserEntity;
import top.rain.rbac.vo.SysUserExcelVO;
import top.rain.rbac.vo.SysUserVO;
import top.rain.security.user.UserDetail;

import java.util.List;


/**
 * 系统用户实体转换
 *
 * @author moqi
 */
@Mapper
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);


//    SysUserVO convert(SysUserEntity entity);
//    SysUserVO convert(UserDetail vo);
//    UserDetail convert(SysUserEntity vo);
    SysUserVO convert(SysUserEntity entity);
    SysUserEntity convert(SysUserVO vo);
    SysUserVO convert(UserDetail userDetail);

    UserDetail convertDetail(SysUserEntity entity);
    List<SysUserVO> convertList(List<SysUserEntity> list);
    List<SysUserExcelVO> convert2List(List<SysUserEntity> list);
    List<SysUserEntity> convertListEntity(List<SysUserExcelVO> list);







}
