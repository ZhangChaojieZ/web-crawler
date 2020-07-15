package com.zhang.webcrawleres.dao;

import com.zhang.webcrawleres.pojo.ArticleItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 张超杰
 * @version 1.0
 * @date 2020/7/14 16:57
 */
//@Mapper : 表示本类是一个 MyBatis 的 Mapper
@Mapper
@Repository
public interface ArticleItemMapper {
    List<ArticleItem> queryAll();
    void addArticleItem(ArticleItem articleItem);
    List<ArticleItem> queryLimit(@Param("start") int start, @Param("num") int num);
}
