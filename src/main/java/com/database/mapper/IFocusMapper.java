package com.database.mapper;

import com.database.data.DisplayFocus;
import com.database.data.Focus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Chenzhao Huang
 * @CreateTime 2021-07-27 20:13
 * @Version 1.0.0
 */
public interface IFocusMapper {
    @Insert("insert into h_focus (user_name, article_id)"+
            " values(#{userName},#{articleId})")
    void add(Focus focus) throws Exception;

    @Select("select * from h_focus where article_id=#{articleId} and user_name=#{userName}")
    Focus query(Focus focus) throws Exception;

    @Select("select h_news.title,h_type.name,h_user.username,h_news.read from h_focus,h_news,h_type,h_user where h_user.id=h_news.create_by and h_focus.article_id=h_news.id and h_news.type_id=h_type.id and user_name=#{user_focus}")
    List<DisplayFocus> queryAll(String user_focus);
}
