package cn.segema.learn.springboot2.component;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;

@Service
public class MysqlConnectionPool {

	public MySQLPool getMySQLPoolClient() {
		MySQLConnectOptions connectOptions = new MySQLConnectOptions().setPort(3306).setHost("localhost")
				.setDatabase("mytest").setUser("root").setPassword("root");
		PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
		MySQLPool client = MySQLPool.pool(connectOptions, poolOptions);
		return client;
	}
}
