package ru.gb.market.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import ru.gb.market.core.entities.Category;
import ru.gb.market.core.repositories.CategoryRepository;

import java.util.List;
/**
 * Данный тест для объяснения работы с аннотацией @DataJpaTest,
 * несмотря на то что в первом тесте была добавлена дополнительная категория
 * второй тест проходит без ошибок, что показывает что тесты работают в рамках одной
 * транзакции и после выполнения теста база данных остается неизменной.
 * **/
@DataJpaTest//используя эту аннотацию, мы делаем тест в рамках одной транзакции, после выполнения теста транзакция откатывается и не влияет на базу данных
@ActiveProfiles("testCategories")//активируем профиль для тестовой базы данных (application-testCategories.yaml)
class CategoryRepositoryTest {//Тест для примера работы с @DataJpaTest
	@Autowired
	private CategoryRepository categoryRepository;
//	@Autowired
//	private TestEntityManager testEntityManager;
	@DisplayName("Test 1")//Присваимаем тесту имя
	@Test
	@Sql(scripts = "cat.sql")//добавляем категорию в базу данных из внешнего скрипта
	void CategoryTest() {
//		Category category = new Category();
//		category.setTitle("Water");
//		testEntityManager.persist(category);//добавляем category в контекст persistent
//		testEntityManager.flush();//записываем в базу
		List<Category> categories = categoryRepository.findAll();
		Assertions.assertEquals(5, categories.size());//проверяем что после добавленя в базе стало 5 категорий
		Assertions.assertEquals("Water", categories.get(4).getTitle());//проверяем что после добавленя в базе 5-я категория называется "Water"
	}
	@Test
	@DisplayName("Test 2")
	void countCategory(){
		List<Category> categories = categoryRepository.findAll();
		Assertions.assertEquals(4, categories.size());//проверяем что в базе данных 4 категории
	}

}
