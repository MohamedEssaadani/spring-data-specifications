package com.essaadani.springspecifications;

import com.essaadani.springspecifications.entities.AppUser;
import com.essaadani.springspecifications.repositories.UserRepository;
import org.junit.Before;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSpecificationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSpecificationsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserRepository userRepository){
        return args -> {

           AppUser mohamed = new AppUser();
            mohamed.setFirstName("Mohamed");
            mohamed.setLastName("ES-SAADANI");
            mohamed.setEmail("essaadani80@gmail.com");
            mohamed.setAge(24);
            userRepository.save(mohamed);

            AppUser hicham = new AppUser();
            hicham.setFirstName("Hicham");
            hicham.setLastName("ES-SAADANI");
            hicham.setEmail("essaadani@gmail.com");
            hicham.setAge(34);
            userRepository.save(hicham);
        };
    }
}
