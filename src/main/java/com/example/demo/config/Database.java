package com.example.demo.config;

import com.example.demo.DemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@ComponentScan(value = "com")
public class Database {
//    private String[] data = getDataconfig();
//    private String driver =data[0];
//    private String url = data[1];
//    private String user = data[2];
//    private String password = data[3];


//    private static String[] getDataconfig() {
//        String[] data = new String[4];
//        System.out.println("Укажите параметры подключения");
//        Scanner pathScanner = new Scanner(System.in);
//        int i;
//        for (i = 0; i < data.length; i++) {
//            data[i] = pathScanner.next();
//        }
//        return data;
//    }



@Bean
public DataSource getDataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    return dataSource;
}
public Connection getConnection() throws SQLException {
        return  getDataSource().getConnection();
}

}
