package usg.lostlink.server.service;

import usg.lostlink.server.dto.CategoryDto;
import usg.lostlink.server.dto.CreateCategoryDto;

import java.util.List;

public interface CategoryService {
    void createCategory(CreateCategoryDto categoryDto);
    List<CategoryDto> getAllCategories();
    void deleteCategory(Long id);
    void updateCategory(CategoryDto categoryDto);

}
