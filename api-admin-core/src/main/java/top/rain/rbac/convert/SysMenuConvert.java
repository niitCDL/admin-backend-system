package top.rain.rbac.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.rain.rbac.entity.SysMenuEntity;
import top.rain.rbac.vo.SysMenuVO;

import java.util.List;

@Mapper
public interface SysMenuConvert {
    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    SysMenuVO convert(SysMenuEntity sysMenuEntity);

    SysMenuEntity convertDetail(SysMenuVO sysMenuVO);

    SysMenuEntity convert(SysMenuVO vo);

    List<SysMenuVO> convertList(List<SysMenuEntity> list);
}
