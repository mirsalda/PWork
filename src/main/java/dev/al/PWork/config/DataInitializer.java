package dev.al.PWork.config;

import dev.al.PWork.entity.User;
import dev.al.PWork.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.getAllUsers().isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setPassword("adminpass");
            admin.setRole(User.Role.RECRUITER);

            userService.createUser(admin);
            System.out.println("Admin user created.");
        }
    }
}