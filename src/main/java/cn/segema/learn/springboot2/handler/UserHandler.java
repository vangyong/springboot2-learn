package cn.segema.learn.springboot2.handler;

import java.math.BigInteger;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import cn.segema.learn.springboot2.vo.User;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {

	//@Resource
	//private ReactiveRedisConnection connection;

	public Mono<ServerResponse> getUserById(ServerRequest request) {
		System.out.println("获取参数");
//		System.out.println(request.pathVariable("userId"));
//		String userId = request.pathVariable("userId");
		User user = new User();
		user.setId(new BigInteger("1"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(user));
	}

	public Mono<ServerResponse> findByPage(ServerRequest request) {
		User user = new User();
		user.setId(new BigInteger("2"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(user));
	}

}
