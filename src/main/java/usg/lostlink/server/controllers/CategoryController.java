package usg.lostlink.server.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import usg.lostlink.server.dto.CategoryDto;
import usg.lostlink.server.dto.CreateCategoryDto;
import usg.lostlink.server.response.ApiResponse;
import usg.lostlink.server.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<Object>> createCategory(@RequestBody CreateCategoryDto dto) {
        categoryService.createCategory(dto);
        return ResponseEntity.ok(ApiResponse.success(null, "Category created.", HttpStatus.CREATED));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories() {
        return ResponseEntity.ok(ApiResponse.success(categoryService.getAllCategories(), "Categories retrieved.", HttpStatus.OK));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> updateCategory( @RequestBody CategoryDto dto) {
        categoryService.updateCategory(dto);
        return ResponseEntity.ok(ApiResponse.success(null, "Category updated.", HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Category deleted.", HttpStatus.NO_CONTENT));
    }
}
