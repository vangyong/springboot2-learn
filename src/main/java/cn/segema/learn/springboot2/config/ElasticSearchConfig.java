package cn.segema.learn.springboot2.config;

import java.util.Arrays;
import java.util.Objects;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description 一句话描述该类的功能
 * @author wangyong
 * @createDate 2020/05/19
 */
@Configuration
public class ElasticSearchConfig {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final int ADDRESS_LENGTH = 2;
    private static final String HTTP_SCHEME = "http";
    // 权限验证
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

    /**
     * 使用冒号隔开ip和端口
     */
    @Value("${elasticsearch.address}")
    private String[] address;

    // @Value("${elasticsearch.username}")
    // private String username;

    // @Value("${elasticsearch.password}")
    // private String password;

    @Bean
    public RestClientBuilder restClientBuilder() {
        HttpHost[] hosts =
            Arrays.stream(address).map(this::makeHttpHost).filter(Objects::nonNull).toArray(HttpHost[]::new);
        logger.debug("hosts:{}", Arrays.toString(hosts));
        // 配置权限验证
        // credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
        RestClientBuilder restClientBuilder =
            RestClient.builder(hosts).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                    return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                }
            });
        return restClientBuilder;
    }

    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient restHighLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        // restClientBuilder.setMaxRetryTimeoutMillis(60000);
        return new RestHighLevelClient(restClientBuilder);
    }

    /**
     * 处理请求地址
     * @param s
     * @return HttpHost
     */
    private HttpHost makeHttpHost(String s) {
        assert !org.springframework.util.StringUtils.isEmpty(s);
        String[] address = s.split(":");
        if (address.length == ADDRESS_LENGTH) {
            String ip = address[0];
            int port = Integer.parseInt(address[1]);
            return new HttpHost(ip, port, HTTP_SCHEME);
        } else {
            return null;
        }
    }

}
