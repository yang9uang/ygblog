package yg.blog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import yg.blog.pojo.BlogUser;
import yg.blog.pojo.BlogUserExample;

public interface BlogUserDao {
    long countByExample(BlogUserExample example);

    int deleteByExample(BlogUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(BlogUser record);

    int insertSelective(BlogUser record);

    List<BlogUser> selectByExample(BlogUserExample example);

    BlogUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") BlogUser record, @Param("example") BlogUserExample example);

    int updateByExample(@Param("record") BlogUser record, @Param("example") BlogUserExample example);

    int updateByPrimaryKeySelective(BlogUser record);

    int updateByPrimaryKey(BlogUser record);

    List<BlogUser> selectall();   //test

    List<BlogUser> login(Map<String, String> map);
}