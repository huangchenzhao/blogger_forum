package com.ruixin.dao;

import com.ruixin.bean.Comment;
import com.ruixin.common.dao.CrudDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDao extends CrudDao<Comment> {

    List<Comment> getFindId(@Param("id") int id);

}