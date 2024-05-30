package steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static data.Data.correctLogin;
import static data.Data.correctPassword;
import static data.DataHelper.checkToast;
import static data.DataHelper.waitElement;
import static data.DataHelper.waitUntilVisible;

import androidx.test.espresso.NoMatchingViewException;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import screens.AuthorizationScreen;

public class AuthorizationSteps {
    public int enterButtonId = R.id.enter_button;
    AuthorizationScreen authorizationScreen = new AuthorizationScreen();
    MainSteps mainStep = new MainSteps();

    @Step("Авторизация валидными данными")
    public void login(String login, String password) {
        Allure.step("Авторизация валидными данными");
        waitElement(enterButtonId);
        authorizationScreen.loginField.perform(replaceText(login));
        authorizationScreen.passwordField.perform(replaceText(password));
        authorizationScreen.loginButton.check(matches(isDisplayed())).perform(click());
    }

    public void checkLogInAndLogInIfNot() {
        if (logIn()) {
            login(correctLogin, correctPassword);
        }
    }

    @Step("Выход из аккаунта")
    public void checkLogOutAndLogOutIfNot() {
        if (logOut()) {
            mainStep.logOut();
        }
    }

    @Step("Проверяем сообщение о недопустимости незаполненных логина или пароля")
    public void emptyLoginAndPassword() {
        Allure.step("Проверка всплывающего сообщения при пустом логине и пароле");
        waitUntilVisible(checkToast(R.string.empty_login_or_password, true));
    }

    @Step("Проверяем сообщение о неверном логине или пароле")
    public void loginOrPasswordIsWrong() {
        Allure.step("Проверка всплывающего сообщения при невалидном логине и пароле");
        waitUntilVisible(checkToast(R.string.wrong_login_or_password, true));
    }

    public boolean logIn() {
        boolean check = false;
        try {
            waitElement(enterButtonId);
            authorizationScreen.loginField.check(matches(isDisplayed()));
            check = true;
            return check;
        } catch (NoMatchingViewException e) {
            check = false;
            return check;
        } finally {
            return check;
        }
    }

    public boolean logOut() {
        boolean check = false;
        try {
            waitElement(MainSteps.LoginOutId);
            mainStep.logOutIsVisible();
            check = true;
            return check;
        } catch (NoMatchingViewException e) {
            check = false;
            return check;
        } finally {
            return check;
        }
    }

}