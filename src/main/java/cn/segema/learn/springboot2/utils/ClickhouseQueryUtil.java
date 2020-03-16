//package cn.segema.cloud.demo.utils;
//
//import java.io.IOException;
//import java.lang.reflect.Field;
//import java.math.BigInteger;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
//import javax.persistence.Column;
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import ru.yandex.clickhouse.ClickHouseDataSource;
//import ru.yandex.clickhouse.settings.ClickHouseProperties;
//
//@Component
//public class ClickhouseQueryUtil<T> {
//	
//	 private static Properties props ;
//	
//	// static JdbcPool jdbcPool = PoolManager.getInstance();
//	
//    //@Value("${clickhouse.datasource.url}")
//    private String url;
//    //@Value("${clickhouse.datasource.database}")
//    private String database;
//    //@Value("${clickhouse.datasource.username}")
//    private String username;
//    //@Value("${clickhouse.datasource.password}")
//    private String password;
//    //@Value("${clickhouse.datasource.socketTimeout}")
//    private int socketTimeout;
//    //@Value("${clickhouse.datasource.connectionTimeout}")
//    private int connectionTimeout;
//    //@Value("${clickhouse.datasource.dataTransferTimeout}")
//    private int dataTransferTimeout;
//    //@Value("${clickhouse.datasource.keepAliveTimeout}")
//    private int keepAliveTimeout;
//    //@Value("${clickhouse.datasource.timeToLiveMillis}")
//    private int timeToLiveMillis;
//    
//    private ClickHouseDataSource dataSource;
//    private Connection connection;
//    private PreparedStatement pst;
//    
//    public ClickhouseQueryUtil(){
//        try {
//            Resource resource = new ClassPathResource("clickhouse.properties");
//            props = PropertiesLoaderUtils.loadProperties(resource);
//            url =  props.getProperty("clickhouse.datasource.url","jdbc:clickhouse://10.4.5.138:8123");
//            database = props.getProperty("clickhouse.datasource.database","NTA_DB");
//            username = props.getProperty("clickhouse.datasource.username","default");
//            password = props.getProperty("clickhouse.datasource.password","swad@clockhouse");
//            socketTimeout = Integer.valueOf(props.getProperty("clickhouse.datasource.socketTimeout","120000"));
//            connectionTimeout = Integer.valueOf(props.getProperty("clickhouse.datasource.connectionTimeout","120000"));
//            dataTransferTimeout = Integer.valueOf(props.getProperty("clickhouse.datasource.dataTransferTimeout","120000"));
//            keepAliveTimeout = Integer.valueOf(props.getProperty("clickhouse.datasource.keepAliveTimeout","120000"));
//            timeToLiveMillis = Integer.valueOf(props.getProperty("clickhouse.datasource.keepAliveTimeout","120000"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    
//   // private PoolConnection connection;
//    
//    private synchronized ClickHouseDataSource getDataSource(){
//        if(dataSource==null){
//            ClickHouseProperties properties = new ClickHouseProperties();
//            properties.setDatabase(database);
//            properties.setUser(username);
//            properties.setPassword(password);
//            properties.setSocketTimeout(socketTimeout);
//            properties.setConnectionTimeout(connectionTimeout);
//            properties.setDataTransferTimeout(dataTransferTimeout);
//            properties.setKeepAliveTimeout(keepAliveTimeout);
//            properties.setTimeToLiveMillis(timeToLiveMillis);
//            dataSource = new ClickHouseDataSource(url, properties);
//        }
//        return dataSource;
//    }
//
////    private PoolConnection getConnection() throws Exception{
////    	PoolConnection conn = jdbcPool.getConnection();
////        return conn;
////    }
//    
//    private synchronized Connection getConnection() throws Exception{
//    	  dataSource = getDataSource();
//          connection = dataSource.getConnection();
//          return connection;
//    }
//
//    /**
//     *得到sql语句执行后的resultSet
//     * @param sql
//     * @return
//     */
//    private synchronized ResultSet getResultSet(String sql,Object[] params) throws Exception{
//        connection = getConnection();
//        pst = connection.prepareStatement(sql);
//        if(params!=null){
//            for(int i=0;i<params.length;i++){
//                pst.setObject(i+1,params[i]);
//            }
//        }
//        ResultSet rst = pst.executeQuery();
//        connection.close();
//        return rst;
//    }
//
//    /**
//     * 非分页列表查询
//     * @param sql
//     * @param params
//     * @param outputClass
//     * @return
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    public synchronized List<T> listQuery (String sql,Object[] params,Class outputClass) throws Exception{
//        List<T> outputList = getDataList(sql,params,null,null,outputClass);
//        connection.close();
//        pst.close();
//        return outputList;
//    }
//
//    /**
//     * 分页列表查询
//     * @param querySql
//     * @param countSql
//     * @param params
//     * @param pageSize
//     * @param pageRows
//     * @param outputClass
//     * @return
//     * @throws Exception
//     */
//    public synchronized PageInfo listPageQuery(String querySql,String countSql,Object[] params,Integer pageSize,Integer pageRows,Class outputClass) throws Exception{
//        PageInfo pageInfo = new PageInfo();
//        BigInteger total = getDataTotalNum(countSql,params);
//        List<T> outputList = getDataList(querySql,params,pageSize,pageRows,outputClass);
//        pageInfo.setList(outputList);
//        pageInfo.setPageNum(pageSize);
//        pageInfo.setPageSize(pageRows);
//        pageInfo.setTotal(total.longValue());
//        connection.close();
//        pst.close();
//        return pageInfo;
//    }
//
//    private synchronized BigInteger getDataTotalNum(String countSql,Object[] params) throws Exception{
//    	BigInteger count=new BigInteger("0");
//        ResultSet rs = getResultSet(countSql,params);
//        if(rs.next()){
//            count=rs.getBigDecimal(1).toBigInteger();
//        }
//        return count;
//    }
//
//    public synchronized List<T> getDataList(String querySql,Object[] params,Integer pageSize,Integer pageRows,Class outputClass) throws Exception{
//        List<T> outputList = new ArrayList<>();
//        if(pageSize!=null&&pageRows!=null){
//            querySql = querySql+" limit "+(pageSize-1)*pageRows+","+pageRows;
//        }
//        ResultSet rs = getResultSet(querySql,params);
//        // resultset非空判断
//        if (rs != null) {
//            //判断outputClass是否有'Entity'注解（注：此处我们比进行注解判断）
//            if (true) {
//                // 获取resultset的metadata
//                ResultSetMetaData rsmd = rs.getMetaData();
//                //获取outputClass类的所有属性
//                Field[] fields = outputClass.getDeclaredFields();
//                while (rs.next()) {
//                    T bean = (T) outputClass.newInstance();
//                    for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
//                        // 获取查询到的column名称
//                        String columnName = rsmd.getColumnName(_iterator + 1);
//                        // 获取column值
//                        Object columnValue = rs.getObject(_iterator + 1);
//                        //遍历匹配column与field，然后赋值
//                        for (Field field : fields) {
//                            if (field.isAnnotationPresent(Column.class)) {
//                                Column column = field.getAnnotation(Column.class);
//                                if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {
//                                    BeanUtils.setProperty(bean, field.getName(), columnValue);
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                    outputList.add(bean);
//                }
//            }
//        } 
//        connection.close();
//        pst.close();
//        return outputList;
//    }
//
//    public String sqlCondition(T bean) throws Exception{
//        String sqlConditon = null;
//        if(bean==null){
//            return sqlConditon;
//        }else{
//            Field[] fields = bean.getClass().getDeclaredFields();
//            for(Field field:fields){
//                if(field.isAnnotationPresent(Column.class)){
//                    Column column = field.getAnnotation(Column.class);
//                    String beanPro = BeanUtils.getProperty(bean,field.getName());
//                    if(!StringUtils.isEmpty(beanPro)){
//                        if(sqlConditon==null){
//                            sqlConditon = "and "+column.name()+"=?";
//                        }else{
//                            sqlConditon += "and "+column.name()+"=?";
//                        }
//                    }
//                }
//            }
//            return sqlConditon;
//        }
//    }
//
//    public Object[] sqlParmas(T bean) throws Exception{
//        List list = null;
//        if(bean==null){
//            return null;
//        }else{
//            Field[] fields = bean.getClass().getDeclaredFields();
//            for(Field field:fields){
//                if(field.isAnnotationPresent(Column.class)){
//                    Column column = field.getAnnotation(Column.class);
//                    Object beanPro = null;
//                    String value = BeanUtils.getProperty(bean,field.getName());
//                    String type = field.getGenericType().toString();
//                    if(type.equals("class java.lang.Integer")){
//                        if(!StringUtils.isEmpty(value)){
//                            beanPro = Integer.parseInt(BeanUtils.getProperty(bean,field.getName()));
//                        }
//                    }else if(type.equals("class java.math.BigInteger")){
//                        if(!StringUtils.isEmpty(value)){
//                            beanPro = BigInteger.valueOf(Long.parseLong(BeanUtils.getProperty(bean,field.getName())));
//                        }
//                    }else if(type.equals("class java.lang.String")){
//                        if(!StringUtils.isEmpty(value)){
//                            beanPro = BeanUtils.getProperty(bean,field.getName());
//                        }
//                    }else if(type.equals("class java.lang.Long")){
//                        if(!StringUtils.isEmpty(value)){
//                            beanPro = Long.parseLong(BeanUtils.getProperty(bean,field.getName()));
//                        }
//                    }
//                    //Object beanPro = BeanUtils.getProperty(bean,field.getName());
//                    if(!org.springframework.util.StringUtils.isEmpty(beanPro)){
//                        if(list==null){
//                            list = new ArrayList();
//                            list.add(beanPro);
//                        }else{
//                            list.add(beanPro);
//                        }
//                    }
//                }
//            }
//            if(list!=null){
//                return list.toArray();
//            }else{
//                return null;
//            }
//        }
//    }
//
//}
