package com.learning.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.*;

@SpringBootApplication(scanBasePackages = {"com.learning.practice"})
public class PracticeApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(PracticeApplication.class, args);

        System.out.println("Server Up");

//		Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlfiles","root","password");
//
//		Statement statement= connection.createStatement();
//
//		ResultSet resultSet =  statement.executeQuery("select * from patient");
//
//		ResultSetMetaData rsmd = resultSet.getMetaData();
//		int columnsNumber = rsmd.getColumnCount();
//
//		while(resultSet.next()) {
//			for (int i = 1; i <= columnsNumber; i++) {
//				String columnValue = resultSet.getString(i);
//				System.out.println(rsmd.getColumnName(i) + ": " + columnValue);
//			}
//			System.out.println(" ");
//		}
    }

}
