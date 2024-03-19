package AndroidTest.tests;


import static AndroidTest.data.Data.correctLogin;
import static AndroidTest.data.Data.correctPassword;
import static AndroidTest.data.Data.wrongLogin;
import static AndroidTest.data.Data.wrongPassword;
import static AndroidTest.data.DataHelper.getUniqueScreenshotName;
import static AndroidTest.data.DataHelper.waitElement;
import static AndroidTest.screens.AuthorizationScreen.checkLogOutAndLogOutIfNot;
import static AndroidTest.screens.MainScreen.LogOutId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import AndroidTest.steps.AuthorizationScreenSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;


@Epic("Тестирование страницы авторизации приложения")
@RunWith(AllureAndroidJUnit4.class)


public class AuthorizationTest {

    @Before
    public void setUp() {
        checkLogOutAndLogOutIfNot();
    }


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());



    @Test
    @DisplayName("Авторизация с валидными логином и паролем")
    public void correctLoginAndPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login(correctLogin, correctPassword);
        waitElement(LogOutId);
        AuthorizationScreenSteps.logOutIsVisible();
    }


    @Test
    @DisplayName("Авторизация с незаполненными полями логина и пароля")
    public void emptyLoginAndPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login("", "");
        AuthorizationScreenSteps.loginOrPasswordDoesntBeEmpty();
    }

    @Test
    @DisplayName("Ввод валидного логина и невалидого пароля при авторизации")
    public void correctLoginWrongPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login(correctLogin, wrongPassword);
        AuthorizationScreenSteps.loginOrPasswordIsWrong();
    }

    @Test
    @DisplayName("Ввод невалидного логина и валидого пароля при авторизации")
    public void wrongLoginWrongCorrectPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login(wrongLogin, correctPassword);
        AuthorizationScreenSteps.loginOrPasswordIsWrong();
    }

    @Test
    @DisplayName("Ввод валидного логина и пустого пароля при авторизации")
    public void correctLoginEmptyPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login(correctLogin, "");
        AuthorizationScreenSteps.loginOrPasswordDoesntBeEmpty();
    }

    @Test
    @DisplayName("Ввод пустого логина и валидного пароля при авторизации")
    public void emptyLoginCorrectPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login("", correctPassword);
        AuthorizationScreenSteps.loginOrPasswordDoesntBeEmpty();
    }


}