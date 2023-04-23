package top.rain.dao;

import org.apache.ibatis.annotations.Mapper;
import top.rain.entity.NoticeEntity;
import top.rain.mybatis.dao.BaseDao;

/**
 * NoticeDao
 *
 * @author mqxu
 **/
@Mapper
public interface NoticeDao extends BaseDao<NoticeEntity> {
}