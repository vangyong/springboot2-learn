package cn.segema.learn.springboot2.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import cn.segema.learn.springboot2.domain.User;

/**
 * @description https请求测试
 * @author wangyong
 * @createDate 2020/09/08
 * @link https://www.cnblogs.com/softidea/p/10663849.html
 */
@RestController
@RequestMapping(value = "/https")
public class HttpsController {

    private static Logger logger = LoggerFactory.getLogger(HttpsController.class);

    @Resource
    private HttpComponentsClientHttpRequestFactory httpsFactory;

    @GetMapping("/get")
    public ResponseEntity getDemo(User user) {
//        String url = "https://www.baidu.com";
//        String url ="https://182.150.116.8:8443/v4/comprehensive/level";
        String url ="https://10.10.19.175/v1/public/paramlist";
        
        HttpEntity<MultiValueMap<String, String>> request = buildRequest();
        
        RestTemplate restTemplateHttps = new RestTemplate(httpsFactory);
        ResponseEntity<String> response = restTemplateHttps.exchange(url,HttpMethod.GET, request, String.class);
        JSONArray jsonObject = (JSONArray)JSON.parse(response.getBody());

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity postDemo(User user) {
        return new ResponseEntity(null, HttpStatus.OK);
    }

    
    /**
     * @description 构建封装的请求头
     * @return HttpEntity
     */
    private HttpEntity<MultiValueMap<String, String>> buildRequest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        //String sessionId = UserContext.getUserSessionId();
        //headers.add("Authrization", sessionId);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, headers);
        return request;
    }
}
