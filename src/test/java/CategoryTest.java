import com.demo.entity.category.Category;
import com.demo.entity.category.SingleCategory;
import com.demo.entity.category.TeamCategory;
import com.demo.repository.CategoryRepository;
import com.demo.service.CategoryService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.demo.util.Constant.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Clasa de test pentru Categorie
 */
public class CategoryTest {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Testez metoda de inserare cu tip corect de categorie
     */
    @Test
    public void insertCorrectCategoryTypeTest() {

        Category category = new Category();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        //String status = categoryService.create(TEAM, "KATA TEAM FEMALE - JUNIOR", "16-17", "Female", KATA, 6, "", 3, 1);
        //assertEquals(status, SUCCES);
    }

    /**
     * Testez metoda de inserare cu tip gresit de categorie
     */
    @Test
    public void insertWrongCategoryTypeTest() {

        Category category = new Category();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        //String status = categoryService.create("Mixt", "KATA TEAM FEMALE - JUNIOR", "16-17", "Female", KATA, 6, "", 3, 1);
       //assertEquals("[ERROR]:Unknown/unsupported category-type [Mixt]", status);
    }

    /**
     * Testez metoda de inserare avand campuri goale
     */
    @Test
    public void insertBlackFieldTest() {

        Category category = new Category();
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        //String status = categoryService.create(TEAM, "KATA TEAM FEMALE - JUNIOR", "", "Female", KATA, 6, "", 3, 1);
        //assertEquals(EMPTY_FIELD, status);
    }

    /**
     * Testez metoda de update cu succes
     */
    @Test
    public void passedUpdateTest() {

        Category category = new TeamCategory(0, "KATA", "16-17", "Female", KATA, 5, 3, 1);
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(java.util.Optional.of(category));
        //String status = categoryService.updateCategory(TEAM, 0, "KATA TEAM FEMALE - JUNIOR", "16-17", "Female", KATA, 6, "", 3, 1);
        //assertEquals(SUCCES, status);
    }

    /**
     * Testez metoda de update cu eroare
     */
    @Test
    public void failedUpdateTest() {

        Category category = new SingleCategory(10, "KATA", "16-17", "Female", KATA, 5, "");
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(java.util.Optional.of(category));
        //String status = categoryService.updateCategory(TEAM, 10, "KATA TEAM FEMALE - JUNIOR", "16-17", "Female", KATA, 6, "", 3, 1);
        //assertEquals("[ERROR]:The category does not have the same type.", status);
    }

    /**
     * Testez metoda de stergere dupa id
     */
    @Test
    public void deleteByIdTest() {

        Category category = new SingleCategory(10, "KATA", "16-17", "Female", KATA, 5, "");
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(java.util.Optional.of(category));
        //String status = categoryService.deleteById(10);
        //assertEquals(SUCCES, status);
    }
}
