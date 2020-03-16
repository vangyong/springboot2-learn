//package cn.segema.cloud.demo.repository;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.stereotype.Repository;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.pool.DruidPooledConnection;
//import com.alibaba.druid.util.JdbcUtils;
//
//import cn.segema.cloud.demo.utils.DBPoolConnection;
//
//@Repository
//public class DemoClickhouseRepository<T> {
//
//    public void exeDruidPoolSql(String sql, Object[] params) {
//        DBPoolConnection dbp = DBPoolConnection.getInstance(); // 获取数据连接池单例
//        DruidPooledConnection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = dbp.getConnection();
//            pstmt = conn.prepareStatement(sql);
//            for (int i = 0; i < params.length; i++) {
//                pstmt.setObject(i + 1, params[i]);
//            }
//
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                System.out.println(rs.getInt("state_province") + "   " + rs.getString("province_name"));
//            }
//
//            // ResultSetMetaData rsmd = rs.getMetaData();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.close(rs);
//            JdbcUtils.close(pstmt);
//            JdbcUtils.close(conn);
//        }
//    }
//
//    public void exeDruidSql(String sql) {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName("ru.yandex.clickhouse.ClickHouseDriver");
//        dataSource.setUrl("jdbc:clickhouse://10.4.5.138:8123/default");
//        dataSource.setUsername("default");
//        dataSource.setPassword("swad@clickhouse");
//        try {
//            conn = dataSource.getConnection();
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                System.out.println(rs.getInt("state_province") + "   " + rs.getString("province_name"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            JdbcUtils.close(rs);
//            JdbcUtils.close(pstmt);
//            JdbcUtils.close(conn);
//        }
//    }
//
//    public void exeSql(String sql) {
//
//        String address = "jdbc:clickhouse://10.4.5.138:8123/default";
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet results = null;
//        try {
//            Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
//            connection = DriverManager.getConnection(address);
//            statement = connection.createStatement();
//            long begin = System.currentTimeMillis();
//            results = statement.executeQuery(sql);
//            long end = System.currentTimeMillis();
//            System.out.println("执行（" + sql + "）耗时：" + (end - begin) + "ms");
//
//            ResultSetMetaData rsmd = results.getMetaData();
//            List<Map> list = new ArrayList();
//            while (results.next()) {
//                Map map = new HashMap();
//                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//                    map.put(rsmd.getColumnName(i), results.getString(rsmd.getColumnName(i)));
//                }
//                list.add(map);
//            }
//            for (Map map : list) {
//                System.err.println(map);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {// 关闭连接
//            try {
//                if (results != null) {
//                    results.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public List<T> findList() {
//
//        return null;
//    }
//
//    public T findById(Integer id) {
//        return null;
//    }
//
//    public void save(String sql) {
//        DBPoolConnection dbp = DBPoolConnection.getInstance(); // 获取数据连接池单例
//        DruidPooledConnection conn = null;
//        PreparedStatement ps = null;
//        try {
//            conn = dbp.getConnection(); // 从数据库连接池中获取数据库连接
//            ps = conn.prepareStatement(sql);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != ps) {
//                    ps.close();
//                }
//                if (null != conn) {
//                    conn.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public T update(T entity) {
//        return null;
//    }
//
//    public void deleteById(Integer id) {
//
//    }
//
//}
