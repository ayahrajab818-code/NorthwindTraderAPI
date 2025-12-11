package com.pluralsight.NorthwindTraderAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NorthwindTraderApiApplication {

	public static void main(String[] args) {
        ApplicationContext app =  SpringApplication.run(NorthwindTraderApiApplication.class, args);	}

}
