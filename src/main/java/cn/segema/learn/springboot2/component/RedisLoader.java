//package cn.segema.learn.springboot2.component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
//import org.springframework.data.redis.core.ReactiveRedisOperations;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RedisLoader {
//	@Resource
//	private ReactiveRedisConnectionFactory factory;
//	@Resource
//	private ReactiveRedisOperations<String, Object> redisOperations;
//
//	@PostConstruct
//	public void loadData() {
//		
//		System.out.println("aaaa");
//		
////		factory.getReactiveConnection().serverCommands().flushAll()
////				.thenMany(Flux.just("Thor", "Hulk", "Tony")
////						.map(name -> new User(UUID.randomUUID().toString().substring(0, 5), name, "123456"))
////						.flatMap(user -> redisOperations.opsForValue().set(user.getId(), user)))
////				.thenMany(redisOperations.keys("*").flatMap(redisOperations.opsForValue()::get))
////				.subscribe(System.out::println);
//	}
//}
