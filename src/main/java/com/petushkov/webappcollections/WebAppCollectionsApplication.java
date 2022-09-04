package com.petushkov.webappcollections;

import com.petushkov.webappcollections.models.Collection;
import com.petushkov.webappcollections.models.ERole;
import com.petushkov.webappcollections.models.Role;
import com.petushkov.webappcollections.models.User;
import com.petushkov.webappcollections.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootApplication
@AllArgsConstructor
public class WebAppCollectionsApplication {

    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WebAppCollectionsApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(UserRepository repository) {
        return args -> {
            User user = new User();
            user.setUsername("peter");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setFullName("Peter");
            user.setEmail("admin@mail.ru");
            user.addRole(new Role(ERole.ROLE_USER));
            user.addRole(new Role(ERole.ROLE_ADMIN));
            if (!repository.existsByUsername("peter")) {
                repository.save(user);
            }

        };


    }
}
