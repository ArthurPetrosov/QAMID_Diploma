package tests;


import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import steps.AuthorizationScreenSteps;
import data.Data;
import data.DataHelper;
import screens.AuthorizationScreen;
import screens.MainScreen;
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
        AuthorizationScreen.checkLogOutAndLogOutIfNot();
    }


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, DataHelper.getUniqueScreenshotName());





    @Test
    @DisplayName("Авторизация с валидными логином и паролем")
    public void correctLoginAndPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login(Data.correctLogin, Data.correctPassword);
        DataHelper.waitElement(MainScreen.LogOutId);
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
        AuthorizationScreenSteps.login(Data.correctLogin, Data.wrongPassword);
        AuthorizationScreenSteps.loginOrPasswordIsWrong();
    }

    @Test
    @DisplayName("Ввод невалидного логина и валидого пароля при авторизации")
    public void wrongLoginWrongCorrectPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login(Data.wrongLogin, Data.correctPassword);
        AuthorizationScreenSteps.loginOrPasswordIsWrong();
    }

    @Test
    @DisplayName("Ввод валидного логина и пустого пароля при авторизации")
    public void correctLoginEmptyPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login(Data.correctLogin, "");
        AuthorizationScreenSteps.loginOrPasswordDoesntBeEmpty();
    }

    @Test
    @DisplayName("Ввод пустого логина и валидного пароля при авторизации")
    public void emptyLoginCorrectPasswordAuthorizationTest() {
        AuthorizationScreenSteps.login("", Data.correctPassword);
        AuthorizationScreenSteps.loginOrPasswordDoesntBeEmpty();
    } //

}