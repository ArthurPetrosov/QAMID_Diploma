package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import data.Data;
import data.DataHelper;
import screens.AddingNewsScreen;
import screens.EditNews;
import screens.NewEditScreem;
import screens.NewScreen;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class NewEditScreenSteps {


    @Step("Получаем количество элементов в списке новостей")
    public static int getItemCount() {
        Allure.step("Получаем количество элементов в списке новостей");
        int itemCount = DataHelper.getRecyclerViewItemCount(R.id.news_list_recycler_view);
        return itemCount;
    }

    @Step("Получаем дату первой новости из списка до сортировки")
    public static String getFirstDateBeforeSorting() {
        Allure.step("Получаем дату первой новости из списка до сортировки");
        String firstDateBeforeSorting = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, 0);
        return firstDateBeforeSorting;
    }

    @Step("Получаем дату последней новости из списка до сортировки")
    public static String getLastDateBeforeSorting(int position) {
        Allure.step("Получаем дату последней новости из списка до сортировки");
        String lastDateBeforeSorting = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, position);
        return lastDateBeforeSorting;
    }

    @Step("Прокручиваем список новостей до последнего элемента")
    public static void scrollNewsToLastPosition(int itemCount) {
        Allure.step("Прокручиваем список новостей до последнего элемента");
        NewScreen.scrollNewsToPosition(itemCount);
    }

    @Step("Производим сортировку новостей ")
    public static void sortingNewsStep() {
        Allure.step("Производим сортировку новостей");
        NewScreen.sortingNews();
    }

    @Step("Прокручиваем список новостей до первого элемента")
    public static void scrollNewsToFirstPosition() {
        Allure.step("Прокручиваем список новостей до первого элемента");
        NewScreen.scrollNewsToPosition(0);
    }

    @Step("Получаем дату первой новости из списка после сортировки")
    public static String getFirstDateAfterSorting() {
        Allure.step("Получаем дату первой новости из списка после сортировки");
        String firstDateBeforeSorting = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, 0);
        return firstDateBeforeSorting;
    }

    @Step("Получаем дату последней новости из списка после сортировки")
    public static String getLastDateAfterSorting(int position) {
        Allure.step("Получаем дату последней новости из списка после сортировки");
        String lastDateBeforeSorting = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, position);
        return lastDateBeforeSorting;
    }

    @Step("Проверяем, что дата первой новости до сортировки равна дате последней новости после сортировки")
    public static void checkDateAfterSortingOne(String firstDateBeforeSorting, String lastDateAfterSorting) {
        Allure.step("Проверяем, что дата первой новости до сортировки равна дате последней новости после сортировки");
        assertEquals(firstDateBeforeSorting, lastDateAfterSorting);
    }

    @Step("Проверяем, что дата последней новости до сортировки равна дате первой новости после сортировки")
    public static void checkDateAfterSortingTwo(String lastDateBeforeSorting, String firstDateAfterSorting) {
        Allure.step("Проверяем, что дата последней новости до сортировки равна дате первой новости после сортировки");
        assertEquals(lastDateBeforeSorting, firstDateAfterSorting);
    }

    @Step("Добавляем новость")
    public static void addingNews() {
       Allure.step("Добавляем новость");
        AddingNewsScreen.addNews(Data.categoryForth, Data.tittleNews, Data.dateNews, Data.timeNews, Data.descriptionNews);
        DataHelper.waitElement(R.id.news_list_recycler_view);
        NewEditScreem.refreshListOfNews();
    }

    @Step("Прокручиваем список до созданной новости и кликаем на нее")
    public static void scrollToNewsWithTittleAndClick() {
        Allure.step("Прокручиваем список до созданной новости и кликаем на нее");
        NewEditScreem.scrollAndClickToNewsWithTittle(Data.tittleNews);
    }

    @Step("Открываем новость на редактирование")
    public static void editingNews() {
        Allure.step("Открываем новость на редактирование");
        NewEditScreem.editNews(Data.tittleNews);
    }

    @Step("Проверяем, что все атрибуты новости соответствуют заданным при ее создании")
    public static void checkAttributesNews() {
        Allure.step("Проверяем, что все атрибуты новости соответствуют заданным при ее создании");
        onView(withText(Data.tittleNews)).check(matches(isDisplayed()));
        onView(withText(Data.dateNews)).check(matches(isDisplayed()));
        onView(withText(Data.timeNews)).check(matches(isDisplayed()));
        onView(withText(Data.descriptionNews)).check(matches(isDisplayed()));
    }

    @Step("Производим фильтрацию новостей по статусу Активна")
    public static void filterNewsByStatusActive() {
        Allure.step("Производим фильтрацию новостей по статусу Активна");
        NewScreen.filterNewsByStatus(true, false);
    }

    @Step("Проверяем, что во всех элементах списка новостей статус соответствует Активна")
    public static void isStatusActive(int itemCount) {
        Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Активна");
        for (int i = 0; i < itemCount; i++) {
            NewEditScreem.scrollNews(i);
            String actualStatus = DataHelper.getTextFromNews(R.id.news_item_published_text_view, i);
            assertEquals(Data.statusActive, actualStatus);
        }
    }

    @Step("Производим фильтрацию новостей по статусу Неактивна")
    public static void filterNewsByStatusNotActive() {
        Allure.step("Производим фильтрацию новостей по статусу Неактивна");
        NewScreen.filterNewsByStatus(false, true);
    }

    @Step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна")
    public static void isStatusNotActive(int itemCount) {
        Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна");
        for (int i = 0; i < itemCount; i++) {
            NewEditScreem.scrollNews(i);
            String actualStatus = DataHelper.getTextFromNews(R.id.news_item_published_text_view, i);
            assertEquals(Data.statusNotActive, actualStatus);
        }
    }

    @Step("Производим фильтрацию новостей по статусу Активна и дате публикации")
    public static void filterNewsByStatusActiveAndPublishDate() {
        Allure.step("Производим фильтрацию новостей по статусу Активна и дате публикации");
        NewScreen.filterNewsByStatusAndDate(true, false, Data.dateNews, Data.dateNews);
    }

    @Step("Проверяем, что во всех элементах списка новостей статус соответствует Активна и дата публикации равна дате фильтрации")
    public static void isStatusActiveAndPublishDateEqualsFilterDate(int itemCount) {
        Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Активна и дата публикации равна дате фильтрации");
        for (int i = 0; i < itemCount; i++) {
            NewEditScreem.scrollNews(i);
            String actualStatus = DataHelper.getTextFromNews(R.id.news_item_published_text_view, i);
            String actualDate = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, i);
            assertEquals(Data.statusActive, actualStatus);
            assertEquals(Data.dateNews, actualDate);
        }
    }

    @Step("Производим смену статуса новости на Неактивна")
    public static void changeStatusNewsToNotActive() {
        Allure.step("Производим смену статуса новости на Неактивна");
        NewEditScreem.changeNewsStatus(Data.tittleNews);
    }

    @Step("Проверяем, что статус новости соответствует Неактивна")
    public static void checkNotActiveStatus() {
        Allure.step("Проверяем, что статус новости соответствует Неактивна");
        onView(withText(Data.statusNotActive)).check(matches(isDisplayed()));
    }

    @Step("Производим фильтрацию новостей по статусу Неактивна и дате публикации")
    public static void filterNewsByStatusNotActiveAndPublishDate() {
        Allure.step("Производим фильтрацию новостей по статусу Неактивна и дате публикации");
        NewScreen.filterNewsByStatusAndDate(false, true, Data.dateNews, Data.dateNews);
    }

    @Step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна и дата публикации равна дате фильтрации")
    public static void isStatusNotActiveAndPublishDateEqualsFilterDate(int itemCount) {
        Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна и дата публикации равна дате фильтрации");
        for (int i = 0; i < itemCount; i++) {
            NewEditScreem.scrollNews(i);
            String actualStatus = DataHelper.getTextFromNews(R.id.news_item_published_text_view, i);
            String actualDate = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, i);
            assertEquals(Data.statusNotActive, actualStatus);
            assertEquals(Data.dateNews, actualDate);
        }
    }

    @Step("Пытаемся создать новость с незаполненным полем Категория")
    public static void addNewsWithEmptyFieldCategory() {
        Allure.step("Пытаемся создать новость с незаполненным полем Категория");
        AddingNewsScreen.addNews("", Data.tittleNews, Data.dateNews, Data.timeNews, Data.descriptionNews);
    }

    @Step("Пытаемся создать новость с незаполненным полем Заголовок")
    public static void addNewsWithEmptyFieldTittle() {
        Allure.step("Пытаемся создать новость с незаполненным полем Заголовок");
        AddingNewsScreen.addNews(Data.categoryForth, "", Data.dateNews, Data.timeNews, Data.descriptionNews);
    }

    @Step("Пытаемся создать новость с незаполненным полем Дата")
    public static void addNewsWithEmptyFieldDate() {
        Allure.step("Пытаемся создать новость с незаполненным полем Дата");
        AddingNewsScreen.addNews(Data.categoryForth, Data.tittleNews, "", Data.timeNews, Data.descriptionNews);
    }

    @Step("Пытаемся создать новость с незаполненным полем Время")
    public static void addNewsWithEmptyFieldTime() {
        Allure.step("Пытаемся создать новость с незаполненным полем Время");
        AddingNewsScreen.addNews(Data.categoryForth, Data.tittleNews, Data.dateNews, "", Data.descriptionNews);
    }

    @Step("Пытаемся создать новость с незаполненным полем Описание")
    public static void addNewsWithEmptyFieldDescription() {
        Allure.step("Пытаемся создать новость с незаполненным полем Описание");
        AddingNewsScreen.addNews(Data.categoryForth, Data.tittleNews, Data.dateNews, Data.timeNews, "");
    }

    /*@Step("Проверяем сообщение о недопустимости наличия пустых полей при создании новости")
    public static void neverFieldsDoesntBeEmptyMessage() {
        Allure.step("Проверяем сообщение о недопустимости наличия пустых полей при создании новости");
        waitUntilVisible(DataHelper.checkToast(errorAddingMessageId, true));
    }*/

    @Step("Заполняем все поля создаваемой новости")
    public static void fillingAllFieldsNews() {
        Allure.step("Заполняем все поля создаваемой новости");
        AddingNewsScreen.fillingNewsFields(Data.categoryForth, Data.tittleNews, Data.dateNews, Data.timeNews, Data.descriptionNews);
    }

    @Step("Нажимаем Отмена")
    public static void pressCancelButton() {
       Allure.step("Нажимаем Отмена");
        AddingNewsScreen.cancelButton.perform(click());
    }

    @Step("Подтверждаем отмену создания новости")
    public static void confirmCancelAddingNews() {
        Allure.step("Подтверждаем отмену создания новости");
        AddingNewsScreen.cancelMessage.check(matches(isDisplayed()));
        AddingNewsScreen.confirmCancelAddingNewsButton.perform(click());
    }

    @Step("Проверяем, что новость не создана")
    public static void isNewsNotCreated(int itemCount) {
        Allure.step("Проверяем, что новость не создана");
        for (int i = 0; i < itemCount; i++) {
            NewEditScreem.scrollNews(i);
            String actualTittle = DataHelper.getTextFromNews(R.id.news_item_title_text_view, i);
            assertNotEquals(Data.tittleNews, actualTittle);
        }
    }

    @Step("Удаляем созданную новость")
    public static void deleteAddedNews() {
        Allure.step("Удаляем созданную новость");
        NewEditScreem.deleteNews(Data.tittleNews);
        DataHelper.waitElement(R.id.news_list_recycler_view);
    }

    @Step("Проверяем, что новость удалена")
    public static void isNewsDeleted(int itemCount) {
        Allure.step("Проверяем, что новость удалена");
        for (int i = 0; i < itemCount; i++) {
            NewEditScreem.scrollNews(i);
            String actualTittle = DataHelper.getTextFromNews(R.id.news_item_title_text_view, i);
            assertNotEquals(Data.tittleNews, actualTittle);
        }
    }

    @Step("Изменяем атрибуты созданной новости")
    public static void changeCreatedNewsAttributes() {
        Allure.step("Изменяем атрибуты созданной новости");
        EditNews.changeNewsAttribute(Data.newTittleNews, Data.dateNewsNextDay, Data.newTimeNews, Data.newDescriptionNews);
    }

   @Step("Проверяем, что атрибуты созданной новости изменились")
    public static void checkChangedNewsAttributes() {
        Allure.step("Проверяем, что атрибуты созданной новости изменились");
        onView(withText(Data.newTittleNews)).check(matches(isDisplayed()));
        onView(withText(Data.dateNewsNextDay)).check(matches(isDisplayed()));
        onView(withText(Data.newTimeNews)).check(matches(isDisplayed()));
        onView(withText(Data.newDescriptionNews)).check(matches(isDisplayed()));
    }

}
