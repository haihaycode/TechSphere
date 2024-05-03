package techsphere.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private String title;
    private String description;
    private String content;
    private int authorId;
    private String tags;
    private Boolean active ;
    private int category_id;
    private MultipartFile imagePost;
}
