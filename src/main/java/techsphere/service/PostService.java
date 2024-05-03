package techsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techsphere.entity.Post;
import techsphere.repository.PostRepository;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;


    public PostService(PostRepository postRepository, ImageService imageService) {
        this.postRepository = postRepository;

    }

    // Phương thức để lấy danh sách tất cả các bài viết
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Phương thức để lấy một bài viết theo ID
    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    // Phương thức để tạo một bài viết mới
    public Post createPost(Post post) {
        return postRepository.save(post);
    }
    public List<Post> createAllPost(List<Post> post) {
        return postRepository.saveAll(post);
    }
    public Post updatePost(int id, Post updatedPost) {
        Post existingPost = postRepository.findById(id).orElse(null);
        if (existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setContent(updatedPost.getContent());
            existingPost.setUpdatedAt(new Date());
            existingPost.setActive(updatedPost.isActive());
            existingPost.setTags(updatedPost.getTags());
            existingPost.setCategory(updatedPost.getCategory()); // Cập nhật category
            existingPost.setDescription(updatedPost.getDescription());
            // Cập nhật author nếu cần thiết

            existingPost.setAuthor(updatedPost.getAuthor());


            return postRepository.save(existingPost);
        }
        return null;
    }
    public void deleteCategory(int id) {
        postRepository.deleteById(id);
    }
}
