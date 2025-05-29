package dev.al.PWork.controller;
import dev.al.PWork.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class CurrentUserController {

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Not authenticated");
        }

        Object principal = authentication.getPrincipal();


        if (principal instanceof User) {
            User user = (User) principal;
            return ResponseEntity.ok(user);
        } else {

            return ResponseEntity.status(401).body("Not authenticated");
        }
    }
}
