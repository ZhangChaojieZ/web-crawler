package com.zhang.webcrawleres.service;

import com.alibaba.fastjson.JSON;
import com.zhang.webcrawleres.dao.ArticleItemMapper;
import com.zhang.webcrawleres.pojo.ArticleItem;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 张超杰
 * @version 1.0
 * @date 2020/7/15 15:09
 */
@Service
public class EsService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    // 数据库操作
    @Autowired
    private ArticleItemMapper articleItemMapper;

    // 读取数据放入 es 索引中
    public Boolean addEs() throws IOException {
        // 已成功放入100个
        List<ArticleItem> listItems = articleItemMapper.queryLimit(0, 100);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        // for (int i = 0; i < 900; i++) {
            for (int j = 0; j < listItems.size(); j++) {
                bulkRequest.add(new IndexRequest("article_item").source(JSON.toJSONString(listItems.get(j)),
                        XContentType.JSON));
            }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
        // }
    }

    /**
     * 获取数据实现搜索功能，模糊查询，默认每页显示10个
     * @param keyword 搜索的关键词
     * @param pageNum 第几页
     * @return 返回查询到结果，list集合，用于前端数据
     */
    public List<Map<String,Object>> searchPage(String keyword,int pageNum) throws IOException {
        // 页码必须>=1
        if(pageNum <= 1) {
            pageNum = 1;
        }

        // 条件搜索，传入索引名称
        SearchRequest searchRequest = new SearchRequest("article_item");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 分页，小数据量使用from就行
        sourceBuilder.from((pageNum - 1) * 10);
        sourceBuilder.size(10);

        // 模糊查询
        WildcardQueryBuilder wqBuilderTitle = QueryBuilders.wildcardQuery("articleTitle", keyword);
        WildcardQueryBuilder wqBuilderDesc = QueryBuilders.wildcardQuery("description", keyword);
        
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(wqBuilderDesc).should(wqBuilderTitle);

        sourceBuilder.query(boolQueryBuilder).timeout(new TimeValue(60, TimeUnit.SECONDS));
        
        // 执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 解析结果
        ArrayList<Map<String, Object>> listResponses = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            listResponses.add(hit.getSourceAsMap());
        }
        return listResponses;
    }

}
