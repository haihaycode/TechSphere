package techsphere.controller;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import techsphere.Utils.TimeUtils;
import techsphere.entity.Category;
import techsphere.entity.Post;
import techsphere.model.PostDTO;
import techsphere.service.CategoryService;
import techsphere.service.ImageService;
import techsphere.service.PostService;

import java.util.Date;
import java.util.List;

@MultipartConfig(fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private  PostService postService;
    private CategoryService categoryService;
    private  ImageService imageService;
    @Autowired
    public PostController(PostService postService, ImageService imageService, CategoryService categoryService) {
        this.postService = postService;
        this.imageService = imageService;
        this.categoryService= categoryService;

    }

    // Endpoint để lấy danh sách tất cả các bài viết
    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImagePost(@PathVariable int id) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return ResponseEntity.notFound().build();
        }
        return imageService.getImage(post.getImage());
    }

    // Endpoint để lấy một bài viết dựa trên ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            System.out.println(TimeUtils.formatTimeWithOffset(String.valueOf(post.getUpdatedAt()), 7));
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint để tạo một bài viết mới
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Post> createPost(@ModelAttribute PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());
        post.setTags(postDTO.getTags());
        post.setActive(postDTO.getActive());
        post.setViews("0");
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());

        post.setAuthor(postDTO.getAuthorId());
        Category category = categoryService.getCategoryById(postDTO.getCategory_id()) ;
        post.setCategory(category);
        post.setImage(imageService.uploadImage(postDTO.getImagePost()));

        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }
    // Endpoint để cập nhật một bài viết đã tồn tại
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @ModelAttribute PostDTO postDTO) {
        // Tìm kiếm đối tượng Post cần cập nhật từ cơ sở dữ liệu
        Post existingPost = postService.getPostById(id);

        if (existingPost != null) {
            // Cập nhật các trường của bài viết hiện có với dữ liệu mới từ postDTO
            existingPost.setActive(postDTO.getActive() ==null ? true : false );
            existingPost.setTitle(postDTO.getTitle());
            existingPost.setDescription(postDTO.getDescription());
            existingPost.setContent(postDTO.getContent());
            Category category = categoryService.getCategoryById(postDTO.getCategory_id()) ;
            existingPost.setCategory(category);
            existingPost.setUpdatedAt(new Date());
            existingPost.setCreatedAt(existingPost.getCreatedAt());
            if(postDTO.getImagePost()!=null){
                existingPost.setImage(imageService.uploadImage(postDTO.getImagePost()));
            }


            // Lưu trạng thái cập nhật vào cơ sở dữ liệu
            Post updatedPost = postService.updatePost(existingPost.getPostId(),existingPost);
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Endpoint để xóa một bài viết dựa trên ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
