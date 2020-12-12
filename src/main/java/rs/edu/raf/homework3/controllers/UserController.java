package rs.edu.raf.homework3.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.homework3.model.User;
import rs.edu.raf.homework3.services.UserService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> save (@RequestBody User user) {

        if(user != null && user.getId() != null) {
            Optional<User> optionalUser = userService.findById(user.getId());

            if(optionalUser.isPresent()){
                return ResponseEntity.badRequest().build();
            }
        }

        if(user.getFirstName() == null || user.getLastName() == null || user.getUserType() == null) {
            return ResponseEntity.badRequest().build();
        }

        User createdUser = userService.save(user);

        return ResponseEntity.ok(createdUser);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@RequestParam("userId") Long id) {
        Optional<User> optionalUser = userService.findById(id);

        if(optionalUser.isPresent()){
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> searchUser(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String userType,
                                 @RequestParam String group) {

        return userService.findByFirstNameAndLastNameAndUserTypeAndGroup(firstName, lastName, userType, group);
    }

}
