package _009_MybatisTest.mapper;

import _009_MybatisTest.model.TPayLog;

public interface TPayLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPayLog record);

    int insertSelective(TPayLog record);

    TPayLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPayLog record);

    int updateByPrimaryKey(TPayLog record);
}