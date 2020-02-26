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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {

	// @Resource
	// private ReactiveRedisConnection connection;

	@Resource
	private UserRepository userRepository;

	@Resource
	private UserService userService;

	public Mono<ServerResponse> getById(ServerRequest request) {
		Mono<User> userMono = userService.findById(new BigInteger(request.pathVariable("userId")));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		return userMono
				.flatMap(person -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(person)))
				.switchIfEmpty(notFound);
	}

	public Mono<ServerResponse> add(ServerRequest request) {
		// Mono<String> string = request.body(BodyExtractors.toMono(String.class);
		// Flux<Person> people = request.body(BodyExtractors.toFlux(Person.class);

		Mono<User> user = request.bodyToMono(User.class);
		return ServerResponse.ok().build(userService.create(user));
	}

	public Mono<ServerResponse> getByPage(ServerRequest request) {
		UserVO user = new UserVO();
		Optional<Object> userIdOption = request.attribute("userId");
		user.setUserId(BigInteger.valueOf(Long.valueOf(String.valueOf(userIdOption.get()))));
		Sort sortOrder = new Sort(Sort.Direction.DESC, "userId");
		Pageable pageable = PageRequest.of(1 - 1, 10, sortOrder);
		Page<User> userPage = userRepository.findByPage(user, pageable);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(BodyInserters.fromObject(userPage));
	}

}
