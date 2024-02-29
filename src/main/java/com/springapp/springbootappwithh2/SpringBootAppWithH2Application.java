package com.springapp.springbootappwithh2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringBootAppWithH2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppWithH2Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext context) {
//        return args -> {
//
//            System.out.println("Let's have a look at the beans offered by Spring Boot:");
//
//            String[] beanNames = context.getBeanDefinitionNames();
//            Arrays.stream(beanNames)
//                    .sorted() // Sorting
//                    .forEach(System.out::println); // Displaying all the beans where autoconfiguration has happened
//
//        };
//    }

}
