package com.example.plain_jdbc.Plain.JDBC;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlainJdbcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(PlainJdbcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		// Create the database if it doesn't exist
		DatabaseConnection.createDatabase();

		// Create the users table if it doesn't exist
		DatabaseConnection.createUserTable();
	}

}
