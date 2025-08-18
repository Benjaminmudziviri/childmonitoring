package com.radical.childmonitoring;

/**
 * This app is a backend for child monitoring and i will be using it as a backend for data persistence,
 * and storing the data of location sharing and stuff
 * @author Phantom Rusterking
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChildmonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChildmonitoringApplication.class, args);
	}

}
