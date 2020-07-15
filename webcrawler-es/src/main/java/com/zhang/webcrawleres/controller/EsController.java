package com.zhang.webcrawleres.controller;

import com.zhang.webcrawleres.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 张超杰
 * @version 1.0
 * @date 2020/7/15 15:43
 */
@RestController
public class EsController {
    @Autowired
    private EsService esService;

    // 模糊查询，传入关键词和页码，如果名称或者描述里面有，就被查到
    @GetMapping("/search/{keyword}/{pageNum}")
    public List<Map<String,Object>> search(@PathVariable("keyword") String keyword,
                                            @PathVariable("pageNum") int pageNum) throws IOException {
        return esService.searchPage(keyword,pageNum);
    }

}
