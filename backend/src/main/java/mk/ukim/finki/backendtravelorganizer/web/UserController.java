package mk.ukim.finki.backendtravelorganizer.web;

import mk.ukim.finki.backendtravelorganizer.model.User;
import mk.ukim.finki.backendtravelorganizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        try {
            User createdUser = userService.register(user);
            return ResponseEntity.ok(createdUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // or return a proper message
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            String token = userService.verify(user); // returns JWT
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
