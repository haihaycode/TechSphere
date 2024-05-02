package techsphere.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techsphere.entity.User;
import techsphere.repository.userRepostory;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private userRepostory userRepostory;

    public User saveUser(User user){
        return userRepostory.save(user);
    }
    public List<User> saveAllUser(List<User> user){
        return userRepostory.saveAll(user);
    }
    public List<User> getAllUsers() {
        return userRepostory.findAll();
    }

    public User getUserById(int userId) {
        Optional<User> optionalUser = userRepostory.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }
    }

    public User updateUser(int userId, User userDetails) {
        User user = getUserById(userId);
        user.setUsername(userDetails.getUsername());
        user.setFullname(userDetails.getFullname());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        // Set other fields as needed
        return userRepostory.save(user);
    }

    public User deleteUser(int userId) {
        userRepostory.deleteById(userId);
        return getUserById(userId);
    }



}
