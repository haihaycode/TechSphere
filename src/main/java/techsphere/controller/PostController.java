package techsphere.controller;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import techsphere.entity.Post;
import techsphere.service.ImageService;
import techsphere.service.PostService;

import java.util.List;

@MultipartConfig(fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private  PostService postService;
    private final ImageService imageService;
    @Autowired
    public PostController(PostService postService, ImageService imageService) {
        this.postService = postService;
        this.imageService = imageService;
    }

    // Endpoint để lấy danh sách tất cả các bài viết
    @GetMapping("/")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Endpoint để lấy một bài viết dựa trên ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint để tạo một bài viết mới
    @PostMapping("/")
    public ResponseEntity<Post> createPost(@RequestBody Post post,  @RequestParam("imagePost") MultipartFile imagePost) {
        post.setViews("0");
        post.setImage(imageService.uploadImage(imagePost));
        Post createdPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    // Endpoint để cập nhật một bài viết đã tồn tại
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody Post updatedPost) {
        Post post = postService.updatePost(id, updatedPost);
        if (post != null) {
            return ResponseEntity.ok(post);
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
