package cn.segema.learn.springboot2.service;

import java.math.BigInteger;

import cn.segema.learn.springboot2.domain.User;
import reactor.core.publisher.Mono;

public interface UserService {
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	Mono<User> create(Mono<User> user);

	/**
	 * 用户登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	Mono<User> find(String username, String password);

	/**
	 * 获取所有用户
	 *
	 * @return
	 */
	Mono<User> findAll();

	/**
	 * 根据id获取
	 * 
	 * @param userId
	 * @return
	 */
	Mono<User> findById(BigInteger userId);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 */
	Mono<User> remove(String userId);
}