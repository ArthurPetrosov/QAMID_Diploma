package AndroidTest.tests;

import static AndroidTest.screens.AuthorizationScreen.checkLogInAndLogInIfNot;
import static AndroidTest.steps.AboutScreenSteps.isAppDeveloperDisplayed;
import static AndroidTest.steps.AboutScreenSteps.isAppVersionDisplayed;
import static AndroidTest.steps.MainScreenSteps.goToAboutPageStep;
import static AndroidTest.data.DataHelper.getUniqueScreenshotName;

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

@Epic("Тестирование страницы О приложении")
@RunWith(AllureAndroidJUnit4.class)

public class AboutScreenTest {

    @Before
    public void setUp() {
        checkLogInAndLogInIfNot();
        goToAboutPageStep();
    }


    @Rule
    public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());


    @Test
    @DisplayName("Видимость сведений о версии приложения")
    @Attachment
    public void testVisibleVersion() {
        isAppVersionDisplayed();
    }

    @Test
    @DisplayName("Видимость сведений о  разработчике приложения")
    public void testVisibleDeveloper() {
        isAppDeveloperDisplayed();
    }
}
