package com.peng.BackManagement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.peng.BackManagement.module.*")
public class BackManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackManagementApplication.class, args);
	}
}
