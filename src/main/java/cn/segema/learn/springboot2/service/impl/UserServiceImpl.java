package cn.segema.learn.springboot2.service.impl;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.segema.learn.springboot2.component.MysqlConnectionPool;
import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.service.UserService;
import io.vertx.sqlclient.SqlConnection;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

//	@Resource
//	private final ReactiveTransactionTemplete transactionTemplate;

	@Resource
	private MysqlConnectionPool mysqlConnectionPool;
	
	
	@Override
	public Mono<User> findById(BigInteger userId) {
		return null;
	}

	@Override
	public Mono<User> findAll() {
		return null;
	}

	@Override
	public Mono<User> create(Mono<User> user) {
		mysqlConnectionPool.getMySQLPoolClient().getConnection(ar1 -> {
			if (ar1.succeeded()) {
				SqlConnection conn = ar1.result();
				conn.prepare("INSERT INTO tb_user(user_id,user_name,nick_name) VALUES(1001,'xiaoming','mingming')", ar2 -> {
					if (ar2.succeeded()) {
						System.out.println("添加成功");
					} else {
						conn.close();
					}
				});
			} else {
				System.out.println("Could not connect: " + ar1.cause().getMessage());
			}
		});
		return null;
	}

	@Override
	public Mono<User> update(Mono<User> user) {
		mysqlConnectionPool.getMySQLPoolClient().getConnection(ar1 -> {
			if (ar1.succeeded()) {
				SqlConnection conn = ar1.result();
				conn.prepare("UPDATE tb_user SET user_id=1001,user_name='xiaoxiao',nick_name='nick' ", ar2 -> {
					if (ar2.succeeded()) {
						System.out.println("更新成功");
					} else {
						conn.close();
					}
				});
			} else {
				System.out.println("Could not connect: " + ar1.cause().getMessage());
			}
		});
		return null;
	}

	@Override
	public Mono<User> delete(BigInteger userId) {
		return null;
	}


}
