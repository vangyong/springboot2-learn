package cn.segema.learn.springboot2.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import com.xkcoding.http.HttpUtil;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.factory.SslVerificationFactory;

@RestController
@RequestMapping(value = "/restTemplate/ssl")
public class RestTemplateSslController {

    private static Logger logger = LoggerFactory.getLogger(RestTemplateSslController.class);

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/get")
    public ResponseEntity getDemo(User user) {

        String url = "https://182.150.116.8:8443/v4/comprehensive/level";

        String aString = HttpUtil.get(url);

        String message = null;
        RestTemplate restTemplateHttps = new RestTemplate(new SslVerificationFactory());

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        StringHttpMessageConverter stringHttpMessageConverter =
            new StringHttpMessageConverter(Charset.forName("UTF-8"));
        messageConverters.add(stringHttpMessageConverter);
        restTemplateHttps.setMessageConverters(messageConverters);

        System.setProperty("https.protocols", "SSLv3");

        ResponseEntity<String> responseEntity = restTemplateHttps.getForEntity(url, null, String.class);
        if (responseEntity != null && responseEntity.getStatusCodeValue() == 200) {
            message = responseEntity.getBody();
        }

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/post")
    public ResponseEntity postDemo(User user) {
        return new ResponseEntity(null, HttpStatus.OK);
    }

    private void showSslProtocols() throws Exception {
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, null, null);

        SSLSocketFactory factory = (SSLSocketFactory)context.getSocketFactory();
        SSLSocket socket = (SSLSocket)factory.createSocket();

        String[] protocols = socket.getSupportedProtocols();

        System.out.println("Supported Protocols: " + protocols.length);
        for (int i = 0; i < protocols.length; i++) {
            System.out.println(" " + protocols[i]);
        }
        
        protocols = socket.getEnabledProtocols();
        System.out.println("Enabled Protocols: " + protocols.length);
        for (int i = 0; i < protocols.length; i++) {
            System.out.println(" " + protocols[i]);
        }
        System.out.println("----changed----:");
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2,SSLv3");
        protocols = socket.getEnabledProtocols();
        System.out.println("Enabled Protocols: " + protocols.length);
        for (int i = 0; i < protocols.length; i++) {
            System.out.println(" " + protocols[i]);
        }
    }

}
