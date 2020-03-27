package cn.segema.learn.springboot2.controller;

import java.math.BigInteger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.repository.UserRepository;
import cn.segema.learn.springboot2.service.UserService;
import cn.segema.learn.springboot2.vo.UserPersonVO;
import cn.segema.learn.springboot2.vo.UserVO;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@GetMapping("/v2/user/{userId}")
	public Mono<ResponseEntity<User>> getById(@PathVariable(value = "userId") BigInteger userId) {
		
		return userService.findById(userId).map(userOption -> ResponseEntity.ok(userOption))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/v2/user/list")
	public Mono<ResponseEntity<User>> getAll() {
		return userService.findAll().map(userOption -> ResponseEntity.ok(userOption))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping("/v2/user")
	public Mono<ResponseEntity<User>> add(@Valid @RequestBody Mono<User> user) {
		Mono<User> userMono = userService.create(user);
		return userMono.map(userOption -> ResponseEntity.ok(userOption));
	}

	@PutMapping("/v2/user")
	public Mono<ResponseEntity<User>> edit(@Valid @RequestBody Mono<User> user) {
		Mono<User> userMono = userService.update(user);
		return userMono.map(userOption -> ResponseEntity.ok(userOption));
	}

	@DeleteMapping("/v2/user/{userId}")
	public Mono<ResponseEntity<User>> deleteTweet(@PathVariable(value = "userId") BigInteger userId) {
		Mono<User> userMono = userService.delete(userId);
		return userMono.map(userOption -> ResponseEntity.ok(userOption))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
	}
	
	@GetMapping("/v2/user/person/page")
	public Mono<ServerResponse> getUserPersonByPage() {
		UserVO user = new UserVO();
		Sort sortOrder = Sort.by(Sort.Direction.DESC, "user_id");
		Pageable pageable = PageRequest.of(1 - 1, 10, sortOrder);
//		Page<UserPersonVO> userPersonPage = userRepository.findUserPersonByPage(user, pageable);
		Page<User> userPersonPage = userRepository.findUserPersonByPage(user,pageable);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(userPersonPage));
	}
}
