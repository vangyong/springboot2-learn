package cn.segema.learn.springboot2.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/elasticsearch")
public class ElasticSearchController {
    
    public static String  INDEX  = "commodity";
    
    public static String  TYPE  = "transaction";

	@Autowired
	private RestHighLevelClient restHighLevelClient;

	@PostMapping
	public ResponseEntity add(@RequestBody Map map) {
		try {
			IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, UUID.randomUUID().toString()).source(map);
			IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
			return new ResponseEntity(indexResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity(map, HttpStatus.OK);
	}

	@PostMapping("/batch")
	public ResponseEntity addBatch() {
		BulkRequest bulkRequest = new BulkRequest();
		for (int i = 0; i < 10; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", i);
			map.put("name", "测试"+i);
			map.put("age", 22+i);
			IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, UUID.randomUUID().toString()).source(map);
			bulkRequest.add(indexRequest);
		}
		BulkResponse bulkResponse;
		try {
			bulkResponse = restHighLevelClient.bulk(bulkRequest,RequestOptions.DEFAULT);
			return new ResponseEntity(bulkResponse, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity(null, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable String id) {
		 DeleteRequest request = new DeleteRequest(INDEX, TYPE, id);
         DeleteResponse deleteResponse;
		try {
			deleteResponse = this.restHighLevelClient.delete(request, RequestOptions.DEFAULT);
			return new ResponseEntity(deleteResponse, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity("刪除成功", HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity update(@RequestBody Map map) {
		try {
			UpdateRequest request = new UpdateRequest(INDEX, TYPE, map.get("id").toString()).doc(map);
			UpdateResponse updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
			return new ResponseEntity(updateResponse, HttpStatus.OK);
		} catch (Exception e) {

		}
		return new ResponseEntity("success", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity findById(@PathVariable String id) {
		try {
			GetRequest request = new GetRequest(INDEX, TYPE, id);
			GetResponse getResponse = this.restHighLevelClient.get(request, RequestOptions.DEFAULT);
			return new ResponseEntity(getResponse, HttpStatus.OK);
		} catch (Exception e) {

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
	
}
