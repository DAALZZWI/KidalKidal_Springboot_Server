package com.cos.kidalkidal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan( basePackages = "com.cos.kidalkidal.mapper" )
public class KidalkidalApplication {

	public static void main( String[] args ) {
		
		SpringApplication.run( KidalkidalApplication.class , args );
	}
}
