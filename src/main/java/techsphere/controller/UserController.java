package techsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import techsphere.entity.User;
import techsphere.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @PostMapping("/addAll")
    public List<User> addAllUser(@RequestBody List<User> user) {
        return userService.saveAllUser(user);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    public User updateUserById(@RequestBody User user) {
        return userService.UpdateUserById(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        return userService.deleteUserById(id);
    }
    @GetMapping
    public User getUserByName(@RequestParam("name") String fullName) {
        return userService.getUserByName(fullName);
    }

}
