package tests;

import static data.DataHelper.getUniqueScreenshotName;
import static screens.AuthorizationScreen.checkLogInAndLogInIfNot;
import static steps.MainSteps.goToNewsEditingPageStep;
import static steps.MainSteps.goToNewsPageStep;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import steps.MainSteps;
import steps.NewsEditScreenSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование страницы редактирования новостей")
@RunWith(AllureAndroidJUnit4.class)

public class NewsEditScreenTest {

    @Before
    public void setUp() {
        checkLogInAndLogInIfNot();
        goToNewsPageStep();
        goToNewsEditingPageStep();
    }


    @Rule
    public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());

    @Test
    @DisplayName("Сортировка новостей в разделе редактирования новостей")
    public void testSortingNewsInEditingNews() {
        int itemCount = NewsEditScreenSteps.getItemCount();
        String firstDateBeforeSorting = NewsEditScreenSteps.getFirstDateBeforeSorting();
        NewsEditScreenSteps.scrollNewsToLastPosition(itemCount - 1);
        String lastDateBeforeSorting = NewsEditScreenSteps.getLastDateBeforeSorting(itemCount - 1);
        NewsEditScreenSteps.sortingNewsStep();
        NewsEditScreenSteps.scrollNewsToFirstPosition();
        String firstDateAfterSorting = NewsEditScreenSteps.getFirstDateAfterSorting();
        NewsEditScreenSteps.scrollNewsToLastPosition(itemCount - 1);
        String lastDateAfterSorting = NewsEditScreenSteps.getLastDateAfterSorting(itemCount - 1);
        NewsEditScreenSteps.checkDateAfterSortingOne(firstDateBeforeSorting, lastDateAfterSorting);
        NewsEditScreenSteps.checkDateAfterSortingTwo(lastDateBeforeSorting, firstDateAfterSorting);
    }

    @Test
    @DisplayName("Добавление новости")
    public void testAddingNews() {
        NewsEditScreenSteps.addingNews();
        NewsEditScreenSteps.scrollToNewsWithTittleAndClick();
        NewsEditScreenSteps.editingNews();
        NewsEditScreenSteps.checkAttributesNews();
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу Активна")
    public void testFilterNewsByStatusActive() {
        NewsEditScreenSteps.filterNewsByStatusActive();
        int itemCount = NewsEditScreenSteps.getItemCount();
        NewsEditScreenSteps.isStatusActive(itemCount);
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу Неактивна")
    public void testFilterNewsByStatusNotActive() {
        NewsEditScreenSteps.filterNewsByStatusNotActive();
        int itemCount = NewsEditScreenSteps.getItemCount();
        NewsEditScreenSteps.isStatusNotActive(itemCount);
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу Активна и дате публикации")
    public void testFilterNewsByStatusActiveAndDatePublish() {
        NewsEditScreenSteps.addingNews();
        NewsEditScreenSteps.filterNewsByStatusActiveAndPublishDate();
        int itemCount = NewsEditScreenSteps.getItemCount();
        NewsEditScreenSteps.isStatusActiveAndPublishDateEqualsFilterDate(itemCount);
    }

    @Test
    @DisplayName("Смена статуса новости")
    public void testChangeNewsStatus() {
        NewsEditScreenSteps.addingNews();
        NewsEditScreenSteps.changeStatusNewsToNotActive();
        NewsEditScreenSteps.editingNews();
        NewsEditScreenSteps.checkNotActiveStatus();
    }

    @Test
    @DisplayName("Фильтрация новостей по статусу Неактивна и дате публикации")
    public void testFilterNewsByStatusNotActiveAndDatePublish() {
        NewsEditScreenSteps.addingNews();
        NewsEditScreenSteps.changeStatusNewsToNotActive();
        NewsEditScreenSteps.filterNewsByStatusNotActiveAndPublishDate();
        int itemCount = NewsEditScreenSteps.getItemCount();
        NewsEditScreenSteps.isStatusNotActiveAndPublishDateEqualsFilterDate(itemCount);
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Категория")
    public void testRefusalAddingNewsWithEmptyFieldCategory() {
        NewsEditScreenSteps.addNewsWithEmptyFieldCategory();
        NewsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Заголовок")
    public void testRefusalAddingNewsWithEmptyFieldTittle() {
        NewsEditScreenSteps.addNewsWithEmptyFieldTittle();
        NewsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Дата")
    public void testRefusalAddingNewsWithEmptyFieldDate() {
        NewsEditScreenSteps.addNewsWithEmptyFieldDate();
        NewsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Время")
    public void testRefusalAddingNewsWithEmptyFieldTime() {
        NewsEditScreenSteps.addNewsWithEmptyFieldTime();
        NewsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отказ в добавление новости при незаполненном поле Описание")
    public void testRefusalAddingNewsWithEmptyFieldDescription() {
        NewsEditScreenSteps.addNewsWithEmptyFieldDescription();
        NewsEditScreenSteps.neverFieldsDoesntBeEmptyMessage();
    }

    @Test
    @DisplayName("Отмена добавление новости при нажатии кнопки Отмена")
    public void testCancelAddingNewsWithPressCancel() {
        NewsEditScreenSteps.fillingAllFieldsNews();
        NewsEditScreenSteps.pressCancelButton();
        NewsEditScreenSteps.confirmCancelAddingNews();
        MainSteps.pressBack();
        int itemCount = NewsEditScreenSteps.getItemCount();
        NewsEditScreenSteps.isNewsNotCreated(itemCount);
    }

    @Test
    @DisplayName("Отмена добавление новости при нажатии кнопки Назад")
    public void testCancelAddingNewsWithPressBack() {
        NewsEditScreenSteps.fillingAllFieldsNews();
        MainSteps.pressBack();
        int itemCount = NewsEditScreenSteps.getItemCount();
        NewsEditScreenSteps.isNewsNotCreated(itemCount);
    }

    @Test
    @DisplayName("Удаление новости")
    public void testDeleteNews() {
        NewsEditScreenSteps.addingNews();
        NewsEditScreenSteps.deleteAddedNews();
        int itemCount = NewsEditScreenSteps.getItemCount();
        NewsEditScreenSteps.isNewsDeleted(itemCount);
    }

    @Test
    @DisplayName("Редактирование атрибутов новости")
    public void testChangeNewsAttribute() {
        NewsEditScreenSteps.addingNews();
        NewsEditScreenSteps.editingNews();
        NewsEditScreenSteps.changeCreatedNewsAttributes();
        NewsEditScreenSteps.editingNews();
        NewsEditScreenSteps.checkChangedNewsAttributes();
    }
}