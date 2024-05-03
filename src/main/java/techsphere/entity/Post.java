package techsphere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String image;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String content;

//    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "author_id", nullable = false)
    private int author;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Date updatedAt;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = true)
    private String tags;

    @Column(nullable = true)
    private String views ;

    // Thêm các trường mới
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id") // Ánh xạ với khóa ngoại của bảng Category
    private Category category;


}
