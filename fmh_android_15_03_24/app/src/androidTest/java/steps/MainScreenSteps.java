package steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import static screens.AboutScreen.aboutInfo;
import static screens.MainScreen.goToAboutPage;
import static screens.MainScreen.goToNewsPage;
import static screens.MainScreen.goToNewsPageByNavigationMenu;
import static screens.MainScreen.goToQuotesPage;
import static screens.NewsScreen.editNewsButton;
import static screens.QuotesScreen.header;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;

public class MainScreenSteps {

    @Step("Переходим в раздел Новости с помощью кнопки в меню навигации приложения ")
    public static void goToNewsPageWithPressNavigationMenuButton() {
        Allure.step("Переходим в раздел Новости с помощью кнопки в меню навигации приложения");
        goToNewsPageByNavigationMenu();
    }

    @Step("Переходим в раздел Новости с помощью кнопки на главной странице приложения ")
    public static void goToNewsPageWithPressButtonOnMainPage() {
        Allure.step("Переходим в раздел Новости с помощью кнопки на главной странице приложения");
        goToNewsPage();
    }

    @Step("Переходим в раздел О приложении с помощью кнопки в меню навигации приложения ")
    public static void goToAboutPageWithPressNavigationMenuButton() {
        Allure.step("Переходим в раздел О приложении с помощью кнопки в меню навигации приложения");
        goToAboutPage();
    }

    @Step("Переходим в раздел Цитаты с помощью кнопки на главной странице приложения ")
    public static void goToQuotesPageWithPressButtonOnMainPage() {
        Allure.step("Переходим в раздел Цитаты с помощью кнопки на главной странице приложения");
        goToQuotesPage();
    }

    @Step("Проверяем, что видна кнопка перехода в раздел редактирования новостей")
    public static void isEditingNewsButtonDisplayed() {
        Allure.step("Проверяем, что видна кнопка перехода в раздел редактирования новостей");
        editNewsButton.check(matches(isDisplayed()));
    }

    @Step("Проверяем, что видна информация о разработчике приложения")
    public static void isDeveloperTextViewDisplayed() {
        Allure.step("Проверяем, что видна информация о разработчике приложения");
        aboutInfo.check(matches(isDisplayed()));
    }

    @Step("Проверяем, что виден заголовок раздела Цитаты")
    public static void isHeaderQuotesPageDisplayed() {
        Allure.step("Проверяем, что виден заголовок раздела Цитаты");
        header.check(matches(isDisplayed()));
    }
}