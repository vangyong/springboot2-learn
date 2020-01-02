package cn.segema.learn.springboot2.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import cn.segema.learn.springboot2.handler.OauthHandler;
import cn.segema.learn.springboot2.handler.UserHandler;

@Configuration
public class RouterConfig {
	@Resource
	private UserHandler userHandler;
	
	@Resource
	private OauthHandler oauthHandler;

	@Bean
	public RouterFunction<?> routerFunction() {
		return RouterFunctions.route(RequestPredicates.GET("/user/id"), userHandler::getUserById)
				.andRoute(RequestPredicates.GET("/user/page"), userHandler::findByPage)
				.andRoute(RequestPredicates.GET("/oauth/render/github"), oauthHandler::renderAuth)
				.andRoute(RequestPredicates.GET("/oauth/callback/github"), oauthHandler::login);
	}
}
