package dev.al.PWork.config;

import dev.al.PWork.entity.User;
import dev.al.PWork.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.getAllUsers().isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");

            admin.setPassword(passwordEncoder.encode("adminpass"));
            admin.setRole(User.Role.RECRUITER);

            userService.createUser(admin);
            System.out.println("Admin user created.");
        }
    }
}
