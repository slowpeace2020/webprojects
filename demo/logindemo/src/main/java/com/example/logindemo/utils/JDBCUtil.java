package com.example.logindemo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

public class JDBCUtil {
    private static DataSource ds;

    static {
        //加载配置文件
        Properties properties = new Properties();
       InputStream is =  JDBCUtil.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(is);

            //初始化连接池
          //创建数据源对象
          DruidDataSource dataSource = new DruidDataSource();
          dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
          dataSource.setUrl("jdbc:mysql://localhost:3306/test");
          dataSource.setUsername("root");
          dataSource.setPassword("123456test!");

//            ds = DruidDataSourceFactory.createDataSource(properties);
            ds = dataSource;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 获取连接池对象
     */

    public static DataSource getDatasource(){
        return ds;
    }

    /**
     * 获取连接Connection对象
     */

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
