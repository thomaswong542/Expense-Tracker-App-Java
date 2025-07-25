package com.project.expenseTrackerAPI;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerApiApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ExpenseTrackerApiApplication.class);
		application.setBannerMode(Banner.Mode.OFF);
		application.run(args);
	}

}
