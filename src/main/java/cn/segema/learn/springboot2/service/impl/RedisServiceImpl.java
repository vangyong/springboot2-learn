package cn.segema.learn.springboot2.service.impl;

import cn.segema.learn.springboot2.service.RedisService;

public class RedisServiceImpl implements RedisService {
	
//	@Resource
//    private ReactiveRedisOperations<String, User> redisOperations;
//
//    @Override
//    public Mono<Boolean> add(User user) {
//        user.setId(new BigInteger(id));
//        user.setName(username);
//        user.setPassword("123456");
//        return redisOperations.opsForValue().set(String.valueOf(id), user);
//    }
//
//    @Override
//    public Mono<User> find(String username, String password) {
//        return redisOperations.opsForValue().get(username);
//    }
//
//    @Override
//    public Flux<User> getAll() {
//        return redisOperations.keys("*")
//                .flatMap(redisOperations.opsForValue()::get);
//    }
//
//    @Override
//    public Mono<Boolean> remove(String id) {
//        return redisOperations.opsForValue().delete(id);
//    }
}
