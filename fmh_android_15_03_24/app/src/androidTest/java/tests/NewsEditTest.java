package tests;

import static data.DataHelper.generateScreenshotName;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import steps.AuthorizationSteps;
import steps.MainSteps;
import steps.NewsEditScreenSteps;

@Epic("Тестирование страницы редактирования новостей")
@RunWith(AllureAndroidJUnit4.class)

public class NewsEditTest {

    @Before
    public void setUp() {
        authorizationSteps.checkLogInAndLogInIfNot();
        mainSteps.goToNewsPageWithPressNavigationMenuButton();
        mainSteps.goToNewsEditingPageStep();
    }


    @Rule
    public ActivityScenarioRule<AppActivity> activityScenarioRule = new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    MainSteps mainSteps = new MainSteps();
    NewsEditScreenSteps newsEditScreenSteps = new NewsEditScreenSteps();


    @Test
    @DisplayName("Сортировка новостей в разделе редактирования новостей")
    public void testSortingNewsInEditingNews() {
        int itemCount = newsEditScreenSteps.getItemCount();
        String firstDateBeforeSorting = newsEditScreenSteps.getFirstDateBeforeSorting();
        newsEditScreenSteps.scrollNewsToLastPosition(itemCount - 1);
        String lastDateBeforeSorting = newsEditScreenSteps.getLastDateBeforeSorting(itemCount - 1);
        newsEditScreenSteps.sortingNewsStep();
        newsEditScreenSteps.scrollNewsToFirstPosition();
        String firstDateAfterSorting = newsEditScreenSteps.getFirstDateAfterSorting();
        newsEditScreenSteps.scrollNewsToLastPosition(itemCount - 1);
        String lastDateAfterSorting = newsEditScreenSteps.getLastDateAfterSorting(itemCount - 1);
        newsEditScreenSteps.checkDateAfterSortingOne(firstDateBeforeSorting, lastDateAfterSorting);
        newsEditScreenSteps.checkDateAfterSortingTwo(lastDateBeforeSorting, firstDateAfterSorting);
    }

    @Test
    @DisplayName("Добавление новости")
    public void testAddingNews() {
        newsEditScreenSteps.addingNews();
        newsEditScreenSteps.scrollToNewsWithTittleAndClick();
        newsEditScreenSteps.editingNews();
        newsEditScreenSteps.checkAttributesNews();
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу Активна")
    public void testFilterNewsByStatusActive() {
        newsEditScreenSteps.filterNewsByStatusActive();
        int itemCount = newsEditScreenSteps.getItemCount();
        newsEditScreenSteps.isStatusActive(itemCount);
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу Неактивна")
    public void testFilterNewsByStatusNotActive() {
        newsEditScreenSteps.filterNewsByStatusNotActive();
        int itemCount = newsEditScreenSteps.getItemCount();
        newsEditScreenSteps.isStatusNotActive(itemCount);
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу Активна и дате публикации")
    public void testFilterNewsByStatusActiveAndDatePublish() {
        newsEditScreenSteps.addingNews();
        newsEditScreenSteps.filterNewsByStatusActiveAndPublishDate();
        int itemCount = newsEditScreenSteps.getItemCount();
        newsEditScreenSteps.isStatusActiveAndPublishDateEqualsFilterDate(itemCount);
    }

    @Test
    @DisplayName("Смена статуса новости")
    public void testChangeNewsStatus() {
        newsEditScreenSteps.addingNews();
        newsEditScreenSteps.changeStatusNewsToNotActive();
        newsEditScreenSteps.editingNews();
        newsEditScreenSteps.checkNotActiveStatus();
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу Неактивна и дате публикации")
    public void testFilterNewsByStatusNotActiveAndDatePublish() {
        newsEditScreenSteps.addingNews();
        newsEditScreenSteps.changeStatusNewsToNotActive();
        newsEditScreenSteps.filterNewsByStatusNotActiveAndPublishDate();
        int itemCount = newsEditScreenSteps.getItemCount();
        newsEditScreenSteps.isStatusNotActiveAndPublishDateEqualsFilterDate(itemCount);
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Категория")
    public void testRefusalAddingNewsWithEmptyFieldCategory() {
        newsEditScreenSteps.addNewsWithEmptyFieldCategory();
        newsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Заголовок")
    public void testRefusalAddingNewsWithEmptyFieldTittle() {
        newsEditScreenSteps.addNewsWithEmptyFieldTittle();
        newsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Дата")
    public void testRefusalAddingNewsWithEmptyFieldDate() {
        newsEditScreenSteps.addNewsWithEmptyFieldDate();
        newsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Время")
    public void testRefusalAddingNewsWithEmptyFieldTime() {
        newsEditScreenSteps.addNewsWithEmptyFieldTime();
        newsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Описание")
    public void testRefusalAddingNewsWithEmptyFieldDescription() {
        newsEditScreenSteps.addNewsWithEmptyFieldDescription();
        newsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отмена добавление новости при нажатии кнопки Отмена")
    public void testCancelAddingNewsWithPressCancel() {
        newsEditScreenSteps.fillingAllFieldsNews();
        newsEditScreenSteps.pressCancelButton();
        newsEditScreenSteps.confirmCancelAddingNews();
        mainSteps.pressBack();
        int itemCount = newsEditScreenSteps.getItemCount();
        newsEditScreenSteps.isNewsNotCreated(itemCount);
    }

    @Test
    @DisplayName("Отмена добавление новости при нажатии кнопки Назад")
    public void testCancelAddingNewsWithPressBack() {
        newsEditScreenSteps.fillingAllFieldsNews();
        mainSteps.pressBack();
        int itemCount = newsEditScreenSteps.getItemCount();
        newsEditScreenSteps.isNewsNotCreated(itemCount);
    }

    @Test
    @DisplayName("Удаление новости")
    public void testDeleteNews() {
        newsEditScreenSteps.addingNews();
        newsEditScreenSteps.deleteAddedNews();
        int itemCount = newsEditScreenSteps.getItemCount();
        newsEditScreenSteps.isNewsDeleted(itemCount);
    }

    @Test
    @DisplayName("Редактирование атрибутов новости")
    public void testChangeNewsAttribute() {
        newsEditScreenSteps.addingNews();
        newsEditScreenSteps.editingNews();
        newsEditScreenSteps.changeCreatedNewsAttributes();
        newsEditScreenSteps.editingNews();
        newsEditScreenSteps.checkChangedNewsAttributes();
    }
}