package techsphere.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageService {
    @Value("${upload.dir.post.image}")
    private String UPLOAD_DIR;

    public String uploadImage(MultipartFile file) {
        try {
            // Tạo đường dẫn lưu trữ
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu ảnh vào thư mục lưu trữ
            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            System.out.println(fileName);
            return fileName; // Trả về tên tệp đã tải lên
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }
}

