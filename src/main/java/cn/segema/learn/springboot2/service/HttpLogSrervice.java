package cn.segema.learn.springboot2.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.stereotype.Service;
import cn.segema.learn.springboot2.vo.HttpLogVO;

@Service
public class HttpLogSrervice{

    public List<HttpLogVO> findList() {

        return null;
    }

    public HttpLogVO findById(Integer id) {
        return null;
    }

    public void save(HttpLogVO httpLogVO) {
    	Connection connection;
		try {
			connection = DriverManager.getConnection("jdbc:clickhouse://127.0.0.1:9000");
			Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT (number % 3 + 1) as n, sum(number) FROM numbers(10000000) GROUP BY n");

	        while (rs.next()) {
	          System.out.println(rs.getInt(1) + "\t" + rs.getLong(2));
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public HttpLogVO update(HttpLogVO entity) {
    	
    	
        return null;
    }

    public void deleteById(Integer id) {

    }

}
