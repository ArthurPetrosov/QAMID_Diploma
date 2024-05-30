package tests;

import static data.DataHelper.generateScreenshotName;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Attachment;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.AppActivity;
import steps.AboutSteps;
import steps.AuthorizationSteps;
import steps.MainSteps;

@Epic("Тестирование страницы О приложении")
@RunWith(AllureAndroidJUnit4.class)

public class AboutScreenTest {

    @Rule
    public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    AboutSteps aboutStep = new AboutSteps();
    MainSteps mainStep = new MainSteps();

    @Before
    public void setUp() {
        authorizationSteps.checkLogInAndLogInIfNot();
        mainStep.goToAboutPage();
    }

    @Test
    @DisplayName("Видимость сведений о версии приложения")
    @Attachment
    public void testVisibleVersion() {
        aboutStep.isAppVersionDisplayed();
    }

    @Test
    @DisplayName("Видимость сведений о  разработчике приложения")
    public void testVisibleDeveloper() {
        aboutStep.isAppDeveloperDisplayed();
    }


}