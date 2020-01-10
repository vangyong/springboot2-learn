package cn.segema.learn.springboot2.handler;

import java.math.BigInteger;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.repository.UserRepository;
import cn.segema.learn.springboot2.vo.UserVO;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {

	//@Resource
	//private ReactiveRedisConnection connection;
	
	@Resource
	private UserRepository userRepository;

	public Mono<ServerResponse> getUserById(ServerRequest request) {
		System.out.println("获取参数");
//		System.out.println(request.pathVariable("userId"));
//		String userId = request.pathVariable("userId");
		UserVO user = new UserVO();
		Optional<User> userOption = userRepository.findById(new BigInteger(request.pathVariable("userId")));
		user.setId(new BigInteger("1"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(user));
	}

	public Mono<ServerResponse> findByPage(ServerRequest request) {
		UserVO user = new UserVO();
		user.setId(new BigInteger("2"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(user));
	}

}
