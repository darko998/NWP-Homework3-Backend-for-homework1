package rs.edu.raf.homework3.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.homework3.model.User;
import rs.edu.raf.homework3.model.UserType;
import rs.edu.raf.homework3.services.UserTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/types")
public class UserTypeController {

    private final UserTypeService userTypeService;

    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserType> save(@RequestBody UserType userType) {

        if(userType != null && userType.getId() != null) {
            Optional<UserType> optionalUserType = userTypeService.findById(userType.getId());

            if(optionalUserType.isPresent()) {
                return ResponseEntity.badRequest().build();
            }
        }

        if(userType.getName() == null) {
            return ResponseEntity.badRequest().build();
        }

        UserType createdUserType = userTypeService.save(userType);

        return ResponseEntity.ok(createdUserType);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserType> getAllUserTypes() {
        return userTypeService.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserTypeById(@RequestParam("id") Long id) {
        Optional<UserType> optionalUserType = userTypeService.findById(id);

        if(optionalUserType.isPresent()) {
            return ResponseEntity.ok(optionalUserType.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserType updateUserType(@RequestBody UserType userType) {
        return userTypeService.save(userType);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {

        Optional<UserType> optionalUserType = userTypeService.findById(id);

        if(optionalUserType.isPresent()){
            List<User> users = optionalUserType.get().getUsers();

            if(users != null) {
                for(int i = 0 ; i < users.size() ; i++){
                    users.get(i).setUserType(null);
                }
            }
        }

        userTypeService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
