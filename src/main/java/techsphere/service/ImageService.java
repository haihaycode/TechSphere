package techsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import techsphere.Utils.CharacterUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {
    @Value("${upload.dir.post.image}")
    private String UPLOAD_DIR;
    @Autowired
    private ResourceLoader resourceLoader;

    public ResponseEntity<Resource> getImage(String imageName) {
        Path imagePath = Paths.get(UPLOAD_DIR, imageName);
        Resource resource = null;
        try {
            resource = new UrlResource(imagePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    }


    public String uploadImage(MultipartFile file ) {
        try {
            // Tạo đường dẫn lưu trữ
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu ảnh vào thư mục lưu trữ
            String fileName = CharacterUtils.removeSpecialCharacters(file.getOriginalFilename());
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println(fileName);
            return fileName; // Trả về tên tệp đã tải lên
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }
    public String getImagePath(String imageName) {
        Path imagePath = Paths.get(UPLOAD_DIR, imageName);
        // Trả về đường dẫn dưới dạng chuỗ  i
        return imagePath.toString();
    }
}

