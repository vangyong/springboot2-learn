package cn.segema.learn.springboot2.controller;

import java.math.BigInteger;

import javax.validation.Valid;

import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.service.UserService;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

//	@Autowired
//	private UserRepository userRepository;
//	
	@Autowired
	private UserService userService;

//	@GetMapping("/v2/users")
//	public Flux<User> getAllUsers() {
//		return userRepository.findAll();
//	}
//
	@PostMapping("/v2/user")
	public Mono<void> createUser(@Valid Publisher<Person> userStream) {
		userService.create(userStream);
		return his.repository.save(personStream).then();
	}

	@GetMapping("/v2/user/{userId}")
	public Mono<ResponseEntity<User>> getById(@PathVariable(value = "userId") BigInteger userId) {
		return userRepository.findById(userId).map(userOption -> ResponseEntity.ok(userOption));
				//.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PutMapping("/v2/user/{userId}")
	public Mono<ResponseEntity<User>> update(@Valid @RequestBody User user) {
		return userRepository.findById(user.getUserId()).flatMap(existingUser -> {
			existingUser.setUserName(user.getUserName());
			//return userRepository.save(existingTweet);
		}).map(updateUser -> new ResponseEntity<>(updateUser, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
//
//	@DeleteMapping("/v2/user/{userId}")
//	public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") BigInteger userId) {
//		return userRepository.findById(userId).get()
//				.flatMap(existingTweet -> tweetRepository.delete(existingTweet)
//						.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
//				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//	}
//
//	@GetMapping(value = "/v2/user/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//	public Flux<User> streamAllTweets() {
//		return userRepository.findAll();
//	}
}
