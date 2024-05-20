package tests;

import static data.DataHelper.getUniqueScreenshotName;
import static screens.AuthorizationScreen.checkLogInAndLogInIfNot;
import static steps.MainSteps.goToQuotesPageStep;

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
import steps.MainSteps;
import steps.QuotesScreenSteps;

@Epic("Тестирование страницы Цитаты")
@RunWith(AllureAndroidJUnit4.class)

public class QuotesPageTest {

    @Before
    public void setUp() {
        checkLogInAndLogInIfNot();
        goToQuotesPageStep();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());

    @Test
    @DisplayName("Развернуть цитату")
    public void testExpandQuote() {
        ViewInteraction recyclerView = QuotesScreenSteps.getQuotesRecyclerViewAndScrollToFirstPosition();
        int heightBeforeClick = MainSteps.getHeightBeforeClick(recyclerView);
        MainSteps.clickFirstItem(recyclerView);
        int heightAfterClick = MainSteps.getHeightAfterClick(recyclerView);
        MainSteps.checkHeightAfterClick(heightBeforeClick, heightAfterClick);
    }

    @Test
    @DisplayName("Свернуть цитату")
    public void testCollapseQuote() {
        ViewInteraction recyclerView = QuotesScreenSteps.getQuotesRecyclerViewAndScrollToFirstPosition();
        int heightBeforeClick = MainSteps.getHeightBeforeClick(recyclerView);
        MainSteps.doubleClickFirstItem(recyclerView);
        int heightAfterDoubleClick = MainSteps.getHeightAfterClick(recyclerView);
        MainSteps.checkHeightAfterDoubleClick(heightBeforeClick, heightAfterDoubleClick);
    }
}