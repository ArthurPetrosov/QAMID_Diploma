package tests;

import static data.DataHelper.getUniqueScreenshotName;
import static screens.AuthorizationScreen.checkLogInAndLogInIfNot;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import steps.MainScreenSteps;

@Epic("Тестирование главной страницы приложения")
@RunWith(AllureAndroidJUnit4.class)

public class MainScreenTest {

    @Before
    public void setUp() {
        checkLogInAndLogInIfNot();
    }


    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());


    @Test
    @DisplayName("Переход в раздел Новости с помощью кнопки в меню навигации приложения")
    public void isItPossibleToGoToNewsSectionWithNavigationMenuButton() {
        MainScreenSteps.goToNewsPageWithPressNavigationMenuButton();
        MainScreenSteps.isEditingNewsButtonDisplayed();
    }

    @Test
    @DisplayName("Переход в раздел Новости с помощью кнопки на главной странице")
    public void isItPossibleToGoToNewsSectionWithMainPageButton() {
        MainScreenSteps.goToNewsPageWithPressButtonOnMainPage();
        MainScreenSteps.isEditingNewsButtonDisplayed();
    }

    @Test
    @DisplayName("Переход в раздел О приложении с помощью кнопки в меню навигации приложения")
    public void isItPossibleToGoToAboutSectionWithNavigationMenuButton() {
        MainScreenSteps.goToAboutPageWithPressNavigationMenuButton();
        MainScreenSteps.isDeveloperTextViewDisplayed();
    }

    @Test
    @DisplayName("Переход в раздел Цитаты с помощью кнопки на главной странице")
    public void isItPossibleToGoToQuotesSectionWithMainPageButton() {
        MainScreenSteps.goToQuotesPageWithPressButtonOnMainPage();
        MainScreenSteps.isHeaderQuotesPageDisplayed();
    }

}
