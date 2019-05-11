package cn.segema.learn.springboot2.service.impl;

import java.math.BigInteger;
import javax.annotation.Resource;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import cn.segema.learn.springboot2.service.UserService;
import cn.segema.learn.springboot2.vo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserServiceImpl implements UserService {
	@Resource
    private ReactiveRedisOperations<String, User> redisOperations;

    @Override
    public Mono<Boolean> add(String id, String username) {
        User user = new User();
        user.setId(new BigInteger(id));
        user.setName(username);
        user.setPassword("123456");
        return redisOperations.opsForValue().set(String.valueOf(id), user);
    }

    @Override
    public Mono<User> find(String username, String password) {
        return redisOperations.opsForValue().get(username);
    }

    @Override
    public Flux<User> getAll() {
        return redisOperations.keys("*")
                .flatMap(redisOperations.opsForValue()::get);
    }

    @Override
    public Mono<Boolean> remove(String id) {
        return redisOperations.opsForValue().delete(id);
    }
}
