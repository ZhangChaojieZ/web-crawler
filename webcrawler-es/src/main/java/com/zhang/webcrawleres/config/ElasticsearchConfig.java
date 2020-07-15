package com.zhang.webcrawleres.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张超杰
 * @version 1.0
 * @date 2020/7/15 8:45
 * 两步骤
 * 1、找对象
 * 2、放入到spring中备用
 */

@Configuration
public class ElasticsearchConfig {
    // 配置Bean，默认名称为方法名，使用@Autowired注入时注意
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
        return client;
    }
}
