package techsphere.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techsphere.entity.Category;
import techsphere.entity.User;
import techsphere.service.CategoryService;

import java.util.List;


@RestController
@RequestMapping("/api/category/")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public List<Category> getAllCategory() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") int id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/")
    public Category createCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }
    @PostMapping("/all")
    public List<Category> createAllCategory(@RequestBody List<Category> category) {
        return categoryService.saveAllCategory(category);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        category.setCategoryId(id);
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable("id") int id) {
        categoryService.deleteCategory(id);
        return "Category with id: " + id + " has been deleted successfully";
    }
}
