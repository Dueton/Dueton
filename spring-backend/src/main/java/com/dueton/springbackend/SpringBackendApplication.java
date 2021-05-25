package com.dueton.springbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class SpringBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBackendApplication.class, args);
  }

}
