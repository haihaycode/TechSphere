package techsphere.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String description;


//        // Một danh mục có thể có nhiều bài viết
//        @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//        private List<Post> posts;

    @Column(nullable = false)
    private boolean isActive;
}
