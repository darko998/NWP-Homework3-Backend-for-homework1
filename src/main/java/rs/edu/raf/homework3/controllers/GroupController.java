package rs.edu.raf.homework3.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.homework3.model.Group;
import rs.edu.raf.homework3.model.User;
import rs.edu.raf.homework3.services.GroupService;
import rs.edu.raf.homework3.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final UserService userService;

    public GroupController(GroupService groupService, UserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> save(@RequestBody Group group) {

        if(group != null && group.getId() != null) {
            Optional<Group> optionalGroup = groupService.findById(group.getId());

            if(optionalGroup.isPresent()){
                return ResponseEntity.badRequest().build();
            }
        }

        if(group.getName() == null) {
            return ResponseEntity.badRequest().build();
        }

        Group createdGroup = groupService.save(group);

        return ResponseEntity.ok(createdGroup);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Group> getAllGroups() {
        return groupService.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findGroupById(@RequestParam("id") Long id) {
        Optional<Group> optionalGroup = groupService.findById(id);

        if(optionalGroup.isPresent()){
            return ResponseEntity.ok(optionalGroup.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Group updateGroup(@RequestBody Group group) {
        return groupService.save(group);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") Long id) {
        Optional<Group> optionalGroup = groupService.findById(id);

        if(optionalGroup.isPresent()){
            List<User> users = optionalGroup.get().getUsers();

            if(users != null) {
                for(int i = 0 ; i < users.size() ; i++){
                    users.get(i).setGroup(null);
                }
            }
        }
        groupService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsersInGroup(@PathVariable("id") Long id) {
        Optional<Group> optionalGroup = groupService.findById(id);

        if(optionalGroup.isPresent()){
            List<User> users = optionalGroup.get().getUsers();

            return ResponseEntity.ok(users);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{groupId}/users/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUserToGroup(@PathVariable("groupId") Long groupId, @RequestParam Long userId){
        Optional<Group> optionalGroup = groupService.findById(groupId);

        if(optionalGroup.isPresent()){
            Optional<User> optionalUser = userService.findById(userId);

            if(optionalUser.isPresent()){
                optionalUser.get().setGroup(optionalGroup.get());
                optionalGroup.get().addUser(optionalUser.get());

                groupService.save(optionalGroup.get());
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/{groupId}/users/remove", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeUserFromGroup(@PathVariable("groupId") Long groupId, @RequestParam Long userId){
        Optional<Group> optionalGroup = groupService.findById(groupId);

        if(optionalGroup.isPresent()){
            Optional<User> optionalUser = userService.findById(userId);

            if(optionalUser.isPresent()){
                optionalUser.get().setGroup(null);
                optionalGroup.get().removeUser(optionalUser.get());

                groupService.save(optionalGroup.get());

                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
