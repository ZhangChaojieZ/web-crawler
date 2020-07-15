package com.zhang.webcrawleres;

import com.zhang.webcrawleres.dao.ArticleItemMapper;
import com.zhang.webcrawleres.pojo.ArticleItem;
import com.zhang.webcrawleres.service.EsService;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.index.engine.Engine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class WebcrawlerEsApplicationTests {

    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient client;
    // 数据库
    @Autowired
    private ArticleItemMapper articleItemMapper;
    // 索引
    @Autowired
    private EsService esService;

    // 数据查询测试
    @Test
    void queryLimitTest() {
        List<ArticleItem> articleItems = articleItemMapper.queryLimit(0, 10);
        for (ArticleItem articleItem : articleItems) {
            System.out.println(articleItem);
        }
    }

    // 测试创建索引，对应命令 PUT code_index
    @Test
    void createIndexTest() throws IOException {
        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("code_index");
        // 客户端执行请求 IndicesClient
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse);
    }

    // 测试获取索引，判断其是否存在，相当于GET命令
    @Test
    void exitIndexTest() throws IOException {
        GetIndexRequest request = new GetIndexRequest("code_index");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    // 测试删除索引
    @Test
    void deleteIndexTest() throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest("code_index");
        // 删除
        AcknowledgedResponse delete = client.indices().delete(request, RequestOptions.DEFAULT);
        // true表示删除成功
        System.out.println(delete.isAcknowledged());
    }

    // 测试添加到索引是否成功
    @Test
    void addEsTest() {
        try {
            Boolean aBoolean = esService.addEs();
            System.out.println(aBoolean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 测试模糊查询结果
    @Test
    void searchTest() {
        try {
            List<Map<String, Object>> javalist = esService.searchPage("java", 1);
            System.out.println(javalist.size());
            for (Map<String, Object> stringObjectMap : javalist) {
                System.out.println(stringObjectMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
