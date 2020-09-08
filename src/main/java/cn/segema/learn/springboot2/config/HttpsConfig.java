package cn.segema.learn.springboot2.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import cn.segema.learn.springboot2.utils.HttpClientUtil;

@Configuration
public class HttpsConfig {

	@Bean
	public RestTemplate httpsRestTemplate(HttpComponentsClientHttpRequestFactory httpsFactory) {
		RestTemplate restTemplate = new RestTemplate(httpsFactory);
		restTemplate.setErrorHandler(new ResponseErrorHandler() {
			@Override
			public boolean hasError(ClientHttpResponse clientHttpResponse) {
				return false;
			}

			@Override
			public void handleError(ClientHttpResponse clientHttpResponse) {
				// 默认处理非200的返回，会抛异常
			}
		});
		return restTemplate;
	}

	@Bean(name = "httpsFactory")
	public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() throws Exception {
		CloseableHttpClient httpClient = HttpClientUtil.acceptsUntrustedCertsHttpClient();
		HttpComponentsClientHttpRequestFactory httpsFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		httpsFactory.setReadTimeout(40000);
		httpsFactory.setConnectTimeout(40000);
		return httpsFactory;
	}
}
