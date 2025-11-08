package usg.lostlink.server.service;

import usg.lostlink.server.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Long id);
    void updateCategory(Long id, CategoryDto categoryDto);

}
