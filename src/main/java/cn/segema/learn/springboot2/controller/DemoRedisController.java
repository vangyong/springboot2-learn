//package cn.segema.cloud.demo.controller;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.ListOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.SetOperations;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.data.redis.core.ZSetOperations;
//import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.segema.cloud.demo.domain.DemoUser;
//import cn.segema.cloud.demo.vo.DemoUserVO;
//
//@RestController
//@RequestMapping(value = "/demo/redis")
//public class DemoRedisController {
//  
//  @Resource
//  private StringRedisTemplate stringRedisTemplate;
//
//  @Resource
//  private RedisTemplate redisTemplate;
// 
//  
//  @GetMapping("/redis")
//	public List<DemoUserVO> redis(DemoUser user, Model model) {
//	  	DemoUserVO user1 = new DemoUserVO();
//	  	user1.setUserId("1");
//	  	user1.setUserName("a1");
//	  	DemoUserVO user2 = new DemoUserVO();
//	  	user2.setUserId("2");
//	  	user2.setUserName("a2");
//		DemoUserVO user3 = new DemoUserVO();
//	  	user3.setUserId("3");
//	  	user3.setUserName("a3");
//	  	
//		List<DemoUserVO> userList = new ArrayList<DemoUserVO>();
//		userList.add(user1);
//		userList.add(user2);
//		
//		Set<DemoUserVO> userSet = new HashSet<DemoUserVO>();
//		userSet.add(user1);
//		Set<TypedTuple<DemoUserVO>> userzSet = new HashSet<TypedTuple<DemoUserVO>>();
//		
//		String string_key ="stringkey";
//		String value_key ="valuekey";
//		String hash_key ="hashkey";
//		String list_key ="listkey";
//		String set_key ="setkey";
//		String zset_key ="zsetkey";
//		
//        ValueOperations<String, Object> stringOps = redisTemplate.opsForValue();
//        ValueOperations<String, DemoUserVO> valueOps = redisTemplate.opsForValue();
//		HashOperations<String,String, DemoUserVO> hashOps =  redisTemplate.opsForHash();
//		ListOperations<String, DemoUserVO> listOps =redisTemplate.opsForList();
//		SetOperations<String, DemoUserVO> setOps = redisTemplate.opsForSet();
//		ZSetOperations<String, DemoUserVO> zSetOps = redisTemplate.opsForZSet();
//		
//		stringOps.set(string_key, "value of 1");
//        System.out.println(stringOps.get(string_key));
//        
//        valueOps.set(value_key, user1);
//        System.out.println(valueOps.get(value_key));
//        
//        hashOps.put(hash_key, "hashKey", user1);
//        
//	    listOps.rightPush(list_key, user1);
//	    listOps.rightPush(list_key, user2);
//	    
//	    
//	    setOps.add(set_key, user1);
//	    
//	   // zSetOps.add(zset_key, userzSet);
//	    zSetOps.add(zset_key, user1, 0);
//	    zSetOps.add(zset_key, user2, 1);
//	    zSetOps.add(zset_key, user3, 2);
//		
//		ValueOperations<String, Object> returnStringValue = redisTemplate.opsForValue();
//		System.out.println(returnStringValue.get("1"));
//		
//		ValueOperations<String, DemoUserVO> returnValueValue = redisTemplate.opsForValue();
//		System.out.println(returnValueValue.get(value_key));
//		HashOperations<String,String, DemoUserVO> returnHashValue =  redisTemplate.opsForHash();
//		System.out.println(returnHashValue.get(hash_key, "hashKey"));
//		ListOperations<String, DemoUserVO> returnListValue =redisTemplate.opsForList();
//		System.out.println("LIST");
//		SetOperations<String, DemoUserVO> returnSetValue = redisTemplate.opsForSet();
//		System.out.println(returnSetValue.members(set_key));
//		ZSetOperations<String, DemoUserVO> returnZSetValue = redisTemplate.opsForZSet();
//		System.out.println(returnZSetValue.rangeByScore(zset_key, 0, 2));
//		
//		return userList;
//	}
//
//}
