package tests;

import static data.DataHelper.generateScreenshotName;
import androidx.test.espresso.ViewInteraction;
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
import steps.AuthorizationSteps;
import steps.MainSteps;
import steps.QuotesSteps;

@Epic("Тестирование страницы Цитаты")
@RunWith(AllureAndroidJUnit4.class)

public class QuotesPageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, generateScreenshotName("Failed"));
    MainSteps mainSteps = new MainSteps();
    AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    QuotesSteps quotesSteps = new QuotesSteps();

    @Before
    public void setUp() {
        authorizationSteps.checkLogInAndLogInIfNot();
        mainSteps.goToQuotesPage();
    }

    @Test
    @DisplayName("Развернуть цитату")
    public void testExpandQuote() {
        ViewInteraction recyclerView = quotesSteps.getQuotesRecyclerViewAndScrollToFirstPosition();
        int heightBeforeClick = mainSteps.getHeightBeforeClick(recyclerView);
        mainSteps.clickFirstItem(recyclerView);
        int heightAfterClick = mainSteps.getHeightAfterClick(recyclerView);
        mainSteps.checkHeightAfterClick(heightBeforeClick, heightAfterClick);
    }

    @Test
    @DisplayName("Свернуть цитату")
    public void testCollapseQuote() {
        ViewInteraction recyclerView = quotesSteps.getQuotesRecyclerViewAndScrollToFirstPosition();
        int heightBeforeClick = mainSteps.getHeightBeforeClick(recyclerView);
        mainSteps.doubleClickFirstItem(recyclerView);
        int heightAfterDoubleClick = mainSteps.getHeightAfterClick(recyclerView);
        mainSteps.checkHeightAfterDoubleClick(heightBeforeClick, heightAfterDoubleClick);
    }
}