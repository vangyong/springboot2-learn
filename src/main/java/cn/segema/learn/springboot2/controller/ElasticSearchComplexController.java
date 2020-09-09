package cn.segema.learn.springboot2.controller;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/elasticsearch/complex")
public class ElasticSearchComplexController {
    private static Logger logger = LoggerFactory.getLogger(ElasticSearchComplexController.class);

    public static String INDEX = "csoc-logs-*";

    public static String TYPE = "logs";

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable String id) {
        try {
            GetRequest request = new GetRequest("csoc-logs-2018.10.31", "logs", "I45VegWnRQimLLxVvBkSOw");
            GetResponse getResponse = this.restHighLevelClient.get(request, RequestOptions.DEFAULT);
            return new ResponseEntity(getResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity findList(@RequestBody Map map) {
        SearchRequest searchRequest = new SearchRequest().indices(INDEX).types(TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchPhrasePrefixQueryBuilder mppqb = QueryBuilders.matchPhrasePrefixQuery("make", map.get("name"));
        searchSourceBuilder.query(mppqb);
        try {
            SearchResponse searchResponse = this.restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            String result = searchResponse.toString();
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/logs/list")
    public ResponseEntity findLogsList(Map map) {
        
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.fetchSource(new String[]{"event_typeA","event_typeB","destination_endpoint_ip","dst_ep_asset_set","event_description"}, new String[]{});
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("event_name", "恶意扫描");
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("src_ep_asset", "223.167.152.70");
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("timestamp_occurredTime");
        rangeQueryBuilder.gte("2018-10-30T08:00:00Z");
        rangeQueryBuilder.lte("2020-09-01T20:00:00Z");
        
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(matchQueryBuilder);
        boolBuilder.must(termQueryBuilder);
        boolBuilder.must(rangeQueryBuilder);
        searchSourceBuilder.query(boolBuilder);
        SearchRequest searchRequest = new SearchRequest(INDEX);
        searchRequest.types(TYPE);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse response = restHighLevelClient.search(searchRequest);
            System.out.println(response);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(null, HttpStatus.OK);
    }

}
