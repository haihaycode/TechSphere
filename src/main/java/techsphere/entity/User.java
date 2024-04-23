package techsphere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private int userId;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String location;
    private String profilePictureUrl;
    private String bio;
    private String role;
    private String dateJoined;
    private String lastLogin;
    private String socialMediaLinks;
}
