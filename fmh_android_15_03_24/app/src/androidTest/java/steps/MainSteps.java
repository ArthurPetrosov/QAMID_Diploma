package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static data.DataHelper.waitElement;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import data.DataHelper;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import screens.AboutScreen;
import screens.MainScreen;
import screens.QuotesScreen;
import screens.NewsEditScreen;

public class MainSteps {
    public static int mainMenuButtonId = R.id.main_menu_image_button;
    public static int allNewsButton = R.id.all_news_text_view;
    public static int LoginOutId = R.id.authorization_image_button;
    public static int quotesButtID = R.id.our_mission_image_button;
    public static int title = android.R.id.title;
    public static int allNewsButtonId = R.id.all_news_text_view;
    MainScreen mainScreen = new MainScreen();
    AboutScreen aboutScreen = new AboutScreen();
    QuotesScreen quotesScreen = new QuotesScreen();
    NewsEditScreen newsEditScreen = new NewsEditScreen();

    @Step("Выход из аккаунта")
    public void logOut() {
        Allure.step("Выход из аккаунта");
        onView(withId(LoginOutId)).perform(click());
        onView(withId(title)).check(matches(isDisplayed()));
        onView(withId(title)).perform(click());
    }

    @Step("Переход в Новости через главное меню")
    public void goToNewsPage() {
        Allure.step("Переход в Новости через главное меню");
        waitElement(mainMenuButtonId);
        mainScreen.mainMenuButton.perform(click());
        mainScreen.newsButton.check(matches(isDisplayed()));
        mainScreen.newsButton.perform(click());
    }

    @Step("Переходим в раздел Новости с помощью кнопки в меню навигации приложения ")
    public void goToNewsPageWithPressNavigationMenuButton() {
        Allure.step("Переходим в раздел Новости с помощью кнопки в меню навигации приложения");
        waitElement(allNewsButton);
        mainScreen.allNewsButton.perform(click());
    }

    @Step("Переходим в раздел О приложении с помощью кнопки в меню навигации приложения ")
    public void goToAboutPage() {
        Allure.step("Переходим в раздел О приложении с помощью кнопки в меню навигации приложения ");
        waitElement(mainMenuButtonId);
        mainScreen.mainMenuButton.perform(click());
        mainScreen.aboutButton.check(matches(isDisplayed()));
        mainScreen.aboutButton.perform(click());
    }

    @Step("Переходим в раздел Цитаты с помощью кнопки на главной странице приложения ")
    public void goToQuotesPage() {
        Allure.step("Переходим в раздел Цитаты с помощью кнопки на главной странице приложения ");
        waitElement(quotesButtID);
        mainScreen.quotesButton.perform(click());
    }

    @Step("Переходим в раздел Новости с помощью кнопки на главной странице приложения ")
    public void goToNewsPageWithPressButtonOnMainPage() {
        Allure.step("Переходим в раздел Новости с помощью кнопки на главной странице приложения");
        goToNewsPage();
    }

    @Step("Проверка видимости кнопки выхода из аккаунта.")
    public void logOutIsVisible() {
        mainScreen.logOutButton.check(matches(isDisplayed()));
    }

    @Step("Проверяем, что видна информация о разработчике приложения")
    public void isDeveloperTextViewDisplayed() {
        Allure.step("Проверяем, что видна информация о разработчике приложения");
        aboutScreen.aboutInfo.check(matches(isDisplayed()));
    }

    @Step("Проверяем, что виден заголовок раздела Цитаты")
    public void isHeaderQuotesPageDisplayed() {
        Allure.step("Проверяем, что виден заголовок раздела Цитаты");
        quotesScreen.header.check(matches(isDisplayed()));
    }
    @Step("Нажимаем системную кнопку Назад")
    public void pressBack() {
        Espresso.pressBack();
    }

    @Step("Получаем высоту первого элемента списка до клика")
    public int getHeightBeforeClick(ViewInteraction recyclerView) {
        int[] heightBeforeClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
        return heightBeforeClick[0];
    }
    @Step("Переходим в раздел редактирования новостей")
    public void goToNewsEditingPageStep(){
    newsEditScreen.editNewsButton.perform(click());
   }

    @Step("Кликаем на первом элементе списка, чтобы элемент развернулся")
    public void clickFirstItem(ViewInteraction recyclerView) {
        Allure.step("Кликаем на первом элементе списка, чтобы элемент развернулся");
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }
    @Step("Получаем высоту первого элемента списка после клика")
    public int getHeightAfterClick(ViewInteraction recyclerView) {
        int[] heightAfterClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
        return heightAfterClick[0];
    }

    @Step("Проверяем, что высота первого элемента списка увеличилась после клика")
    public void checkHeightAfterClick(int heightBeforeClick, int heightAfterClick) {
        Allure.step("Проверяем, что высота первого элемента списка увеличилась после клика");
        Assert.assertTrue(heightBeforeClick < heightAfterClick);
    }

    @Step("Кликаем дважды на первом элементе списка, чтобы элемент развернулся и свернулся")
    public void doubleClickFirstItem(ViewInteraction recyclerView) {
        Allure.step("Кликаем дважды на первом элементе списка, чтобы элемент развернулся и свернулся");
        recyclerView.perform(actionOnItemAtPosition(0, doubleClick()));
    }

    @Step("Проверяем, что высота первого элемента списка осталась той же после двойного клика")
    public void checkHeightAfterDoubleClick(int heightBeforeClick, int heightAfterClick) {
        Allure.step("Проверяем, что высота первого элемента списка осталась той же после двойного клика");
        assertEquals(heightBeforeClick, heightAfterClick);
    }
}