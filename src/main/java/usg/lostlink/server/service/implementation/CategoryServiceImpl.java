package usg.lostlink.server.service.implementation;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import usg.lostlink.server.dto.CategoryDto;
import usg.lostlink.server.dto.CreateCategoryDto;
import usg.lostlink.server.entity.Category;
import usg.lostlink.server.repository.CategoryRepository;
import usg.lostlink.server.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public void createCategory(CreateCategoryDto dto) {
    Category category = new Category();
    category.setName(dto.getName());
    categoryRepository.save(category);
  }

  @Override
  public List<CategoryDto> getAllCategories() {
    return categoryRepository.findAll()
        .stream()
        .map(cat -> new CategoryDto(cat.getId(), cat.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public void updateCategory(CategoryDto dto) {
    Category category = categoryRepository.findById(dto.getId())
        .orElseThrow(() -> new RuntimeException("Category not found with this id"));

    category.setName(dto.getName());
    categoryRepository.save(category);
  }

  @Override
  public void deleteCategory(Long id) {
    categoryRepository.deleteById(id);
  }
}
