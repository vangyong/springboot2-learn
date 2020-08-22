package cn.segema.learn.springboot2.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.vo.UserVO;

@RestController
@RequestMapping(value = "/restTemplate")
public class RestTemplateController {

	private static Logger logger = LoggerFactory.getLogger(RestTemplateController.class);


	@Resource
	private RestTemplate restTemplate;

	@GetMapping("/get")
	public ResponseEntity getDemo(User user) {
		String url = "http://localhost:8080/user/all";
	    ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + "?name={1}", String.class,"username");
	    List result = restTemplate.getForObject(url, List.class);
//      JSONObject jsonObject = JSONObject.fromObject(body);
//      JSONArray jArray = JSONArray.fromObject(body);
	    
	    
//	    HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headers.add("Authrization", "1.0");
//        
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("email", "844072586@qq.com");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//        ResponseEntity<String> response = restTemplate.postForEntity("", request, String.class);
	    
	    
		return new ResponseEntity(null, HttpStatus.OK);
	}

	@GetMapping("/post")
	public ResponseEntity postDemo(User user) {
		return new ResponseEntity(null, HttpStatus.OK);
	}

	

}
