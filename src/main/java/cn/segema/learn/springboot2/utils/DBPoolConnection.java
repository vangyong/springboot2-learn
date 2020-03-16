//package cn.segema.cloud.demo.utils;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.sql.SQLException;
//import java.util.Properties;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import com.alibaba.druid.pool.DruidPooledConnection;
//
//public class DBPoolConnection {
//    private static DBPoolConnection dbPoolConnection = null;
//    private static DruidDataSource druidDataSource = null;
//
//    static {
//        Properties properties = loadPropertiesFile("db_server.properties");
//        try {
//            // DruidDataSrouce工厂模式
//            druidDataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(properties);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 数据库连接池单例
//     * 
//     * @return
//     */
//    public static synchronized DBPoolConnection getInstance() {
//        if (null == dbPoolConnection) {
//            dbPoolConnection = new DBPoolConnection();
//        }
//        return dbPoolConnection;
//    }
//
//    /**
//     * 返回druid数据库连接
//     * 
//     * @return
//     * @throws SQLException
//     */
//    public DruidPooledConnection getConnection() throws SQLException {
//        return druidDataSource.getConnection();
//    }
//
//    /**
//     * @param string
//     *            配置文件名
//     * @return Properties对象
//     */
//    private static Properties loadPropertiesFile(String fullFile) {
//        String webRootPath = null;
//        if (null == fullFile || fullFile.equals("")) {
//            throw new IllegalArgumentException("Properties file path can not be null" + fullFile);
//        }
//        webRootPath = DBPoolConnection.class.getClassLoader().getResource("").getPath();
//        webRootPath = new File(webRootPath).getParent();
//        InputStream inputStream = null;
//        Properties p = null;
//        try {
//            inputStream = new FileInputStream(new File(webRootPath + File.separator + fullFile));
//            p = new Properties();
//            p.load(inputStream);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (null != inputStream) {
//                    inputStream.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return p;
//    }
//}
