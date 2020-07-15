package com.zhang.webcrawleres.pojo;

import org.springframework.stereotype.Component;

/**
 * @author 张超杰
 * @version 1.0
 * @date 2020/7/14 10:50
 */
@Component
public class ArticleItem {

    private long id;
    // 文章名称
    private String articleTitle;
    // 文章描述
    private String description;
    // 文章作者
    private String articleAuthor;
    // 文章爬取时间
    private String articleDate;
    // 文章标签
    private String articleLabel;
    // 文章点赞数,如果不是数字，则置0
    private String articleGood;
    // 文章详情页地址
    private String articleUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(String articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }

    public String getArticleLabel() {
        return articleLabel;
    }

    public void setArticleLabel(String articleLabel) {
        this.articleLabel = articleLabel;
    }

    public String getArticleGood() {
        return articleGood;
    }

    public void setArticleGood(String articleGood) {
        this.articleGood = articleGood;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    @Override
    public String toString() {
        return "ArticleItem{" +
                "id=" + id +
                ", articleTitle='" + articleTitle + '\'' +
                ", description='" + description + '\'' +
                ", articleAuthor='" + articleAuthor + '\'' +
                ", articleDate=" + articleDate +
                ", articleLabel='" + articleLabel + '\'' +
                ", articleGood=" + articleGood +
                ", articleUrl='" + articleUrl + '\'' +
                '}';
    }
}
