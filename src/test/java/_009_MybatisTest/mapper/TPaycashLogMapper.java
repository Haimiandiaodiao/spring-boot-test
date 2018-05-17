package _009_MybatisTest.mapper;

import _009_MybatisTest.model.TPaycashLog;

public interface TPaycashLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPaycashLog record);

    int insertSelective(TPaycashLog record);

    TPaycashLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPaycashLog record);

    int updateByPrimaryKey(TPaycashLog record);
}