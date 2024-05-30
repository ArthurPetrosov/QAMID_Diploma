package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static data.DataHelper.waitUntilVisible;
import static screens.NewsScreen.errorAddingMessageId;

import data.Data;
import data.DataHelper;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import screens.AddingNewsScreen;
import screens.EditNews;
import screens.NewsEditScreen;
import screens.NewsScreen;

public class NewsEditScreenSteps {

    EditNews editNews = new EditNews();
    AddingNewsScreen addingNewsScreen = new AddingNewsScreen();
    NewsEditScreen newsEditScreen = new NewsEditScreen();
    NewsScreen newsScreen = new NewsScreen();

    @Step("Проверяем сообщение о недопустимости наличия пустых полей при создании новости")
    public void neverFieldsDoesntBeEmptyMessage() {
        Allure.step("Проверяем сообщение о недопустимости наличия пустых полей при создании новости");
        waitUntilVisible(DataHelper.checkToast(errorAddingMessageId, true));
    }

    @Step("Получаем количество элементов в списке новостей")
    public int getItemCount() {
        Allure.step("Получаем количество элементов в списке новостей");
        int itemCount = DataHelper.getRecyclerViewItemCount(R.id.news_list_recycler_view);
        return itemCount;
    }

    @Step("Получаем дату первой новости из списка до сортировки")
    public String getFirstDateBeforeSorting() {
        Allure.step("Получаем дату первой новости из списка до сортировки");
        String firstDateBeforeSorting = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, 0);
        return firstDateBeforeSorting;
    }

    @Step("Получаем дату последней новости из списка до сортировки")
    public String getLastDateBeforeSorting(int position) {
        Allure.step("Получаем дату последней новости из списка до сортировки");
        String lastDateBeforeSorting = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, position);
        return lastDateBeforeSorting;
    }

    @Step("Прокручиваем список новостей до последнего элемента")
    public void scrollNewsToLastPosition(int itemCount) {
        Allure.step("Прокручиваем список новостей до последнего элемента");
        newsScreen.scrollNewsToPosition(itemCount);
    }

    @Step("Производим сортировку новостей ")
    public void sortingNewsStep() {
        Allure.step("Производим сортировку новостей");
        newsScreen.sortingNews();
    }

    @Step("Прокручиваем список новостей до первого элемента")
    public void scrollNewsToFirstPosition() {
        Allure.step("Прокручиваем список новостей до первого элемента");
        newsScreen.scrollNewsToPosition(0);
    }

    @Step("Получаем дату первой новости из списка после сортировки")
    public String getFirstDateAfterSorting() {
        Allure.step("Получаем дату первой новости из списка после сортировки");
        String firstDateBeforeSorting = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, 0);
        return firstDateBeforeSorting;
    }

    @Step("Получаем дату последней новости из списка после сортировки")
    public String getLastDateAfterSorting(int position) {
        Allure.step("Получаем дату последней новости из списка после сортировки");
        String lastDateBeforeSorting = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, position);
        return lastDateBeforeSorting;
    }

    @Step("Проверяем, что дата первой новости до сортировки равна дате последней новости после сортировки")
    public void checkDateAfterSortingOne(String firstDateBeforeSorting, String lastDateAfterSorting) {
        Allure.step("Проверяем, что дата первой новости до сортировки равна дате последней новости после сортировки");
        assertEquals(firstDateBeforeSorting, lastDateAfterSorting);
    }

    @Step("Проверяем, что дата последней новости до сортировки равна дате первой новости после сортировки")
    public void checkDateAfterSortingTwo(String lastDateBeforeSorting, String firstDateAfterSorting) {
        Allure.step("Проверяем, что дата последней новости до сортировки равна дате первой новости после сортировки");
        assertEquals(lastDateBeforeSorting, firstDateAfterSorting);
    }

    @Step("Добавляем новость")
    public void addingNews() {
        Allure.step("Добавляем новость");
        addingNewsScreen.addNews(Data.categoryForth, Data.tittleNews, Data.dateNews, Data.timeNews, Data.descriptionNews);
        DataHelper.waitElement(R.id.news_list_recycler_view);
        newsEditScreen.refreshListOfNews();
    }

    @Step("Прокручиваем список до созданной новости и кликаем на нее")
    public void scrollToNewsWithTittleAndClick() {
        Allure.step("Прокручиваем список до созданной новости и кликаем на нее");
        newsEditScreen.scrollAndClickToNewsWithTittle(Data.tittleNews);
    }

    @Step("Открываем новость на редактирование")
    public void editingNews() {
        Allure.step("Открываем новость на редактирование");
        newsEditScreen.editNews(Data.tittleNews);
    }

    @Step("Проверяем, что все атрибуты новости соответствуют заданным при ее создании")
    public void checkAttributesNews() {
        Allure.step("Проверяем, что все атрибуты новости соответствуют заданным при ее создании");
        onView(withText(Data.tittleNews)).check(matches(isDisplayed()));
        onView(withText(Data.dateNews)).check(matches(isDisplayed()));
        onView(withText(Data.timeNews)).check(matches(isDisplayed()));
        onView(withText(Data.descriptionNews)).check(matches(isDisplayed()));
    }

    @Step("Производим фильтрацию новостей по статусу Активна")
    public void filterNewsByStatusActive() {
        Allure.step("Производим фильтрацию новостей по статусу Активна");
        newsScreen.filterNewsByStatus(true, false);
    }

    @Step("Проверяем, что во всех элементах списка новостей статус соответствует Активна")
    public void isStatusActive(int itemCount) {
        Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Активна");
        for (int i = 0; i < itemCount; i++) {
            NewsEditScreen.scrollNews(i);
            String actualStatus = DataHelper.getTextFromNews(R.id.news_item_published_text_view, i);
            assertEquals(Data.statusActive, actualStatus);
        }
    }

    @Step("Производим фильтрацию новостей по статусу Неактивна")
    public void filterNewsByStatusNotActive() {
        Allure.step("Производим фильтрацию новостей по статусу Неактивна");
        newsScreen.filterNewsByStatus(false, true);
    }

    @Step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна")
    public void isStatusNotActive(int itemCount) {
        Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна");
        for (int i = 0; i < itemCount; i++) {
            NewsEditScreen.scrollNews(i);
            String actualStatus = DataHelper.getTextFromNews(R.id.news_item_published_text_view, i);
            assertEquals(Data.statusNotActive, actualStatus);
        }
    }

    @Step("Производим фильтрацию новостей по статусу Активна и дате публикации")
    public void filterNewsByStatusActiveAndPublishDate() {
        Allure.step("Производим фильтрацию новостей по статусу Активна и дате публикации");
        newsScreen.filterNewsByStatusAndDate(true, false, Data.dateNews, Data.dateNews);
    }

    @Step("Проверяем, что во всех элементах списка новостей статус соответствует Активна и дата публикации равна дате фильтрации")
    public void isStatusActiveAndPublishDateEqualsFilterDate(int itemCount) {
        Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Активна и дата публикации равна дате фильтрации");
        for (int i = 0; i < itemCount; i++) {
            NewsEditScreen.scrollNews(i);
            String actualStatus = DataHelper.getTextFromNews(R.id.news_item_published_text_view, i);
            String actualDate = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, i);
            assertEquals(Data.statusActive, actualStatus);
            assertEquals(Data.dateNews, actualDate);
        }
    }

    @Step("Производим смену статуса новости на Неактивна")
    public void changeStatusNewsToNotActive() {
        Allure.step("Производим смену статуса новости на Неактивна");
        newsEditScreen.changeNewsStatus(Data.tittleNews);
    }

    @Step("Проверяем, что статус новости соответствует Неактивна")
    public void checkNotActiveStatus() {
        Allure.step("Проверяем, что статус новости соответствует Неактивна");
        onView(withText(Data.statusNotActive)).check(matches(isDisplayed()));
    }

    @Step("Производим фильтрацию новостей по статусу Неактивна и дате публикации")
    public void filterNewsByStatusNotActiveAndPublishDate() {
        Allure.step("Производим фильтрацию новостей по статусу Неактивна и дате публикации");
        newsScreen.filterNewsByStatusAndDate(false, true, Data.dateNews, Data.dateNews);
    }

    @Step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна и дата публикации равна дате фильтрации")
    public void isStatusNotActiveAndPublishDateEqualsFilterDate(int itemCount) {
        Allure.step("Проверяем, что во всех элементах списка новостей статус соответствует Неактивна и дата публикации равна дате фильтрации");
        for (int i = 0; i < itemCount; i++) {
            NewsEditScreen.scrollNews(i);
            String actualStatus = DataHelper.getTextFromNews(R.id.news_item_published_text_view, i);
            String actualDate = DataHelper.getTextFromNews(R.id.news_item_publication_date_text_view, i);
            assertEquals(Data.statusNotActive, actualStatus);
            assertEquals(Data.dateNews, actualDate);
        }
    }

    @Step("Пытаемся создать новость с незаполненным полем Категория")
    public void addNewsWithEmptyFieldCategory() {
        Allure.step("Пытаемся создать новость с незаполненным полем Категория");
        addingNewsScreen.addNews("", Data.tittleNews, Data.dateNews, Data.timeNews, Data.descriptionNews);
    }

    @Step("Пытаемся создать новость с незаполненным полем Заголовок")
    public void addNewsWithEmptyFieldTittle() {
        Allure.step("Пытаемся создать новость с незаполненным полем Заголовок");
        addingNewsScreen.addNews(Data.categoryForth, "", Data.dateNews, Data.timeNews, Data.descriptionNews);
    }

    @Step("Пытаемся создать новость с незаполненным полем Дата")
    public void addNewsWithEmptyFieldDate() {
        Allure.step("Пытаемся создать новость с незаполненным полем Дата");
        addingNewsScreen.addNews(Data.categoryForth, Data.tittleNews, "", Data.timeNews, Data.descriptionNews);
    }

    @Step("Пытаемся создать новость с незаполненным полем Время")
    public void addNewsWithEmptyFieldTime() {
        Allure.step("Пытаемся создать новость с незаполненным полем Время");
        addingNewsScreen.addNews(Data.categoryForth, Data.tittleNews, Data.dateNews, "", Data.descriptionNews);
    }

    @Step("Пытаемся создать новость с незаполненным полем Описание")
    public void addNewsWithEmptyFieldDescription() {
        Allure.step("Пытаемся создать новость с незаполненным полем Описание");
        addingNewsScreen.addNews(Data.categoryForth, Data.tittleNews, Data.dateNews, Data.timeNews, "");
    }

    @Step("Заполняем все поля создаваемой новости")
    public void fillingAllFieldsNews() {
        Allure.step("Заполняем все поля создаваемой новости");
        addingNewsScreen.fillingNewsFields(Data.categoryForth, Data.tittleNews, Data.dateNews, Data.timeNews, Data.descriptionNews);
    }

    @Step("Нажимаем Отмена")
    public void pressCancelButton() {
        Allure.step("Нажимаем Отмена");
        addingNewsScreen.cancelButton.perform(click());
    }

    @Step("Подтверждаем отмену создания новости")
    public void confirmCancelAddingNews() {
        Allure.step("Подтверждаем отмену создания новости");
        addingNewsScreen.cancelMessage.check(matches(isDisplayed()));
        addingNewsScreen.confirmCancelAddingNewsButton.perform(click());
    }

    @Step("Проверяем, что новость не создана")
    public void isNewsNotCreated(int itemCount) {
        Allure.step("Проверяем, что новость не создана");
        for (int i = 0; i < itemCount; i++) {
            NewsEditScreen.scrollNews(i);
            String actualTittle = DataHelper.getTextFromNews(R.id.news_item_title_text_view, i);
            assertNotEquals(Data.tittleNews, actualTittle);
        }
    }

    @Step("Удаляем созданную новость")
    public void deleteAddedNews() {
        Allure.step("Удаляем созданную новость");
        newsEditScreen.deleteNews(Data.tittleNews);
        DataHelper.waitElement(R.id.news_list_recycler_view);
    }

    @Step("Проверяем, что новость удалена")
    public void isNewsDeleted(int itemCount) {
        Allure.step("Проверяем, что новость удалена");
        for (int i = 0; i < itemCount; i++) {
            NewsEditScreen.scrollNews(i);
            String actualTittle = DataHelper.getTextFromNews(R.id.news_item_title_text_view, i);
            assertNotEquals(Data.tittleNews, actualTittle);
        }
    }

    @Step("Изменяем атрибуты созданной новости")
    public void changeCreatedNewsAttributes() {
        Allure.step("Изменяем атрибуты созданной новости");
        editNews.changeNewsAttribute(Data.newTittleNews, Data.dateNewsNextDay, Data.newTimeNews, Data.newDescriptionNews);
    }

    @Step("Проверяем, что атрибуты созданной новости изменились")
    public void checkChangedNewsAttributes() {
        Allure.step("Проверяем, что атрибуты созданной новости изменились");
        onView(withText(Data.newTittleNews)).check(matches(isDisplayed()));
        onView(withText(Data.dateNewsNextDay)).check(matches(isDisplayed()));
        onView(withText(Data.newTimeNews)).check(matches(isDisplayed()));
        onView(withText(Data.newDescriptionNews)).check(matches(isDisplayed()));
    }

}
