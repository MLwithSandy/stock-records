package com.banking.mlwithsandy.stockrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.banking.mlwithsandy.stockrecords.repository")
@EnableScheduling
public class StockrecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockrecordsApplication.class, args);
	}

}
