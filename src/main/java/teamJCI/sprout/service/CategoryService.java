package teamJCI.sprout.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamJCI.sprout.domain.Category;
import teamJCI.sprout.repository.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    public final CategoryRepository categoryRepository;

    public Long save(Category category) {
        categoryRepository.save(category);
        return category.getId();
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
