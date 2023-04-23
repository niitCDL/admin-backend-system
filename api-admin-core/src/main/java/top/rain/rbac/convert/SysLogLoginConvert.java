package top.rain.rbac.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import top.rain.rbac.entity.SysLogLoginEntity;
import top.rain.rbac.vo.SysLogLoginVO;

import java.util.List;

@Mapper
public interface SysLogLoginConvert {
    SysLogLoginConvert INSTANCE = Mappers.getMapper(SysLogLoginConvert.class);

    SysLogLoginVO convert(SysLogLoginEntity sysMenuEntity);

    SysLogLoginEntity convert(SysLogLoginVO vo);

    List<SysLogLoginVO> convertList(List<SysLogLoginEntity> list);
}
