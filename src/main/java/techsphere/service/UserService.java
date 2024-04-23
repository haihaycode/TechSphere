package techsphere.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techsphere.entity.User;
import techsphere.repository.userRepostory;

import java.util.List;

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
    public List<User> getAllUser(){
        return userRepostory.findAll();
    }
    public User getUserById(int Id){
        try {
            return userRepostory.getById(Id);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }
    public User getUserByName(String fullName){
        return userRepostory.findByFullName(fullName);
    }
    public String deleteUserById(int Id){
        User existingUser = userRepostory.findById(Id).orElse(null);
        if(existingUser!=null){
            userRepostory.delete(existingUser);
            return  "delete successful with id "+ Id;
        }
        return "delete false id "+Id+", bec not found";
    }
    public User UpdateUserById(User user){
        User existingUser = userRepostory.findById(user.getUserId()).orElse(null);
        if(existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setFullName(user.getFullName());
            existingUser.setDateOfBirth(user.getDateOfBirth());
            existingUser.setGender(user.getGender());
            existingUser.setLocation(user.getLocation());
            existingUser.setProfilePictureUrl(user.getProfilePictureUrl());
            existingUser.setBio(user.getBio());
            existingUser.setRole(user.getRole());
            existingUser.setDateJoined(user.getDateJoined());
            existingUser.setLastLogin(user.getLastLogin());
            existingUser.setSocialMediaLinks(user.getSocialMediaLinks());

            return userRepostory.save(existingUser);
        } else{
            return null;
        }
    }

}
