package com.zhang.webcrawleres.service;

import com.zhang.webcrawleres.pojo.ArticleItem;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 张超杰
 * @version 1.0
 * @date 2020/7/14 22:35
 */
@Component
public interface ArticleService {
    List<ArticleItem> queryAll();
    void addArticleItem(ArticleItem item);
}
