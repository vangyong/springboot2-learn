package cn.segema.learn.springboot2.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.vo.UserVO;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

	private static Logger logger = LoggerFactory.getLogger(RedisController.class);

//	@Resource
//	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private RedisTemplate redisTemplate;

	@GetMapping("/string")
	public ResponseEntity string(User user) {
		String string_key = "stringkey";
		ValueOperations<String, Object> stringOps = redisTemplate.opsForValue();
		stringOps.set(string_key, "value of 1");
		String vo = (String) stringOps.get(string_key);
		return new ResponseEntity(vo, HttpStatus.OK);
	}

	@GetMapping("/hash")
	public ResponseEntity hash(User user) {
		UserVO user1 = new UserVO();
		user1.setUserId(new BigInteger("1"));
		user1.setUserName("a1");
		UserVO user2 = new UserVO();
		user2.setUserId(new BigInteger("2"));
		user2.setUserName("a2");

		String hash_key = "hashkey";
		HashOperations<String, String, UserVO> hashOps = redisTemplate.opsForHash();
		hashOps.put(hash_key, "hashKey1", user1);
		hashOps.put(hash_key, "hashKey2", user2);

		HashOperations<String, String, UserVO> returnHashValue = redisTemplate.opsForHash();
		UserVO key1user1 =  returnHashValue.get(hash_key, "hashKey1");
		return new ResponseEntity(key1user1, HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity list(User user) {
		UserVO user1 = new UserVO();
		user1.setUserId(new BigInteger("1"));
		user1.setUserName("a1");
		UserVO user2 = new UserVO();
		user2.setUserId(new BigInteger("2"));
		user2.setUserName("a2");

		List<UserVO> userList = new ArrayList<UserVO>();
		userList.add(user1);
		userList.add(user2);

		String list_key = "listkey";

		ListOperations<String, UserVO> listOps = redisTemplate.opsForList();

		listOps.rightPush(list_key, user1);
		listOps.rightPush(list_key, user2);

		ListOperations<String, UserVO> returnListValue = redisTemplate.opsForList();
		List<UserVO> returnUserList = returnListValue.range("listkey", 0, 2);

		return new ResponseEntity(returnUserList, HttpStatus.OK);
	}

	@GetMapping("/set")
	public ResponseEntity set(User user) {
		UserVO user1 = new UserVO();
		user1.setUserId(new BigInteger("1"));
		user1.setUserName("a1");
		UserVO user2 = new UserVO();
		user2.setUserId(new BigInteger("2"));
		user2.setUserName("a2");

		Set<UserVO> userSet = new HashSet<UserVO>();
		userSet.add(user1);
		String set_key = "setkey";
		
		SetOperations<String, UserVO> setOps = redisTemplate.opsForSet();
		setOps.add(set_key, user1);
		SetOperations<String, UserVO> returnSetValue = redisTemplate.opsForSet();
		Set<UserVO> returnUserSet = returnSetValue.members(set_key);
		return new ResponseEntity(returnUserSet, HttpStatus.OK);
	}

	@GetMapping("/zset")
	public ResponseEntity zset(User user) {
		UserVO user1 = new UserVO();
		user1.setUserId(new BigInteger("1"));
		user1.setUserName("a1");
		UserVO user2 = new UserVO();
		user2.setUserId(new BigInteger("2"));
		user2.setUserName("a2");

		//Set<TypedTuple<UserVO>> userzSet = new HashSet<TypedTuple<UserVO>>();

		String zset_key = "zsetkey";

		ZSetOperations<String, UserVO> zSetOps = redisTemplate.opsForZSet();
		zSetOps.add(zset_key, user1, 0);
		zSetOps.add(zset_key, user2, 1);

		ZSetOperations<String, UserVO> returnZSetValue = redisTemplate.opsForZSet();
		Set<UserVO> returnUserzSet =  returnZSetValue.rangeByScore(zset_key, 0, 2);

		return new ResponseEntity(returnUserzSet, HttpStatus.OK);
	}

}
