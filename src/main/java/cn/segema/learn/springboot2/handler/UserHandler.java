package cn.segema.learn.springboot2.handler;

import java.math.BigInteger;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.repository.UserRepository;
import cn.segema.learn.springboot2.service.UserService;
import cn.segema.learn.springboot2.vo.UserVO;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {

	// @Resource
	// private RedisService redisService;

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserService userService;

	public Mono<ServerResponse> getById(ServerRequest request) {
//		Mono<User> userMono = userService.findById(new BigInteger(request.pathVariable("userId")));
		Optional<User> userOptional = userRepository.findById(new BigInteger(request.pathVariable("userId")));
		Mono<User> userMono = Mono.justOrEmpty(userOptional);
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return userMono.flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(person))).switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> getByPage(ServerRequest request) {
		UserVO user = new UserVO();
		String userId = request.pathVariable("userId");
		user.setUserId(BigInteger.valueOf(Long.valueOf(userId)));
		//Sort sortOrder = new Sort(Sort.Direction.DESC, "user_id");
		Sort sortOrder = Sort.by(Sort.Direction.DESC, "user_id");
		Pageable pageable = PageRequest.of(1 - 1, 10, sortOrder);
		Page<User> userPage = userRepository.findByPage(user, pageable);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(userPage));
	}

	public Mono<ServerResponse> add(ServerRequest request) {
		// Mono<String> string = request.body(BodyExtractors.toMono(String.class);
		// Flux<Person> people = request.body(BodyExtractors.toFlux(Person.class);
		Mono<User> user = request.bodyToMono(User.class);
		Mono<User> userMono = userService.create(user);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(userMono));
	}

	public Mono<ServerResponse> edit(ServerRequest request) {
		Mono<User> user = request.bodyToMono(User.class);
		Mono<User> userMono = userService.update(user);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(userMono));
	}

}
