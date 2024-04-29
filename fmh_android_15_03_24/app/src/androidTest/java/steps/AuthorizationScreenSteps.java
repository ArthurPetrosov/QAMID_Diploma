package steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import data.DataHelper;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import screens.AuthorizationScreen;
import screens.MainScreen;

public class AuthorizationScreenSteps {

    public static void login(String login, String password) {
        DataHelper.waitElement(AuthorizationScreen.idSignInButton);
        AuthorizationScreen.loginField.perform(replaceText(login));
        AuthorizationScreen.passwordField.perform(replaceText(password));
        AuthorizationScreen.loginButton.check(matches(isDisplayed())).perform(click());
    }

    @Step("Проверяем открытие главной страницы - видимость кнопки LogOut")
    public static void logOutIsVisible() {
        Allure.step("Проверяем открытие главной страницы - видимость кнопки LogOut");
        MainScreen.logOutButton.check(matches(isDisplayed()));
    }

    @Step("Проверяем сообщение о недопустимости незаполненных логина или пароля")
    public static void loginOrPasswordDoesntBeEmpty() {
        Allure.step("Проверяем сообщение о недопустимости незаполненных логина или пароля");
        DataHelper.waitUntilVisible(DataHelper.checkToast(R.string.empty_login_or_password, true));
    }

    @Step("Проверяем сообщение о неверном логине или пароле")
    public static void loginOrPasswordIsWrong() {
        Allure.step("Проверяем сообщение о неверном логине или пароле");
        DataHelper.waitUntilVisible(DataHelper.checkToast(R.string.wrong_login_or_password, true));
    }

}
