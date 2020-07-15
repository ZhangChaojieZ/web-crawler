package com.zhang.webcrawleres.service.impl;

import com.zhang.webcrawleres.dao.ArticleItemMapper;
import com.zhang.webcrawleres.pojo.ArticleItem;
import com.zhang.webcrawleres.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 张超杰
 * @version 1.0
 * @date 2020/7/14 22:37
 */
@Component
public class ArticleIServiceImp implements ArticleService {
    @Autowired
    ArticleItemMapper articleItemMapper;

    @Override
    public List<ArticleItem> queryAll() {
        return articleItemMapper.queryAll();
    }

    @Override
    public void addArticleItem(ArticleItem item) {
        articleItemMapper.addArticleItem(item);
    }
}
