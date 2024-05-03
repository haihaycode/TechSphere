package techsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techsphere.entity.Category;
import techsphere.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private  CategoryRepository categoryRepository;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        return categoryOptional.orElse(null);
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    public List<Category> saveAllCategory(List<Category> category) {
        return categoryRepository.saveAll(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    public Category updateCategory(int id, Category newCategory) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            Category existingCategory = categoryOptional.get();
            existingCategory.setName(newCategory.getName());
            // Cập nhật các trường khác tùy theo yêu cầu
            return categoryRepository.save(existingCategory);
        }
        return null;
    }


}
