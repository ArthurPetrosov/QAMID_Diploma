package AndroidTest.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.data.DataHelper.waitUntilVisible;
import static AndroidTest.screens.AuthorizationScreen.loginButton;
import static AndroidTest.screens.AuthorizationScreen.loginField;
import static AndroidTest.screens.AuthorizationScreen.passwordField;
import static AndroidTest.screens.MainScreen.logOutButton;

import AndroidTest.data.DataHelper;
import AndroidTest.screens.AuthorizationScreen;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class AuthorizationScreenSteps {

    public static void login(String login, String password) {
        waitElement(AuthorizationScreen.idSignInButton);
        loginField.perform(replaceText(login));
        passwordField.perform(replaceText(password));
        loginButton.check(matches(isDisplayed())).perform(click());
    }

    @Step("Проверяем открытие главной страницы - видимость кнопки LogOut")
    public static void logOutIsVisible() {
        Allure.step("Проверяем открытие главной страницы - видимость кнопки LogOut");
        logOutButton.check(matches(isDisplayed()));
    }

    @Step("Проверяем сообщение о недопустимости незаполненных логина или пароля")
    public static void loginOrPasswordDoesntBeEmpty() {
        Allure.step("Проверяем сообщение о недопустимости незаполненных логина или пароля");
        waitUntilVisible(DataHelper.checkToast(R.string.empty_login_or_password, true));
    }

    @Step("Проверяем сообщение о неверном логине или пароле")
    public static void loginOrPasswordIsWrong() {
        Allure.step("Проверяем сообщение о неверном логине или пароле");
        waitUntilVisible(DataHelper.checkToast(R.string.wrong_login_or_password, true));
    }

}
