package tests;

import static data.DataHelper.getUniqueScreenshotName;
import static data.DataHelper.waitElement;
import static screens.AuthorizationScreen.checkLogInAndLogInIfNot;
import static steps.MainSteps.goToNewsPageStep;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import steps.MainSteps;
import steps.NewsEditScreenSteps;
import steps.NewsScreenSteps;
import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@Epic("Тестирование страницы Новости")
@RunWith(AllureAndroidJUnit4.class)

public class NewsScreenTest {


    @Before
    public void setUp() {
        checkLogInAndLogInIfNot();
        goToNewsPageStep();
    }

    @Rule
    public ActivityScenarioRule<AppActivity> myActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule =
            new ScreenshotRule(ScreenshotRule.Mode.FAILURE, getUniqueScreenshotName());

    @Test
    @DisplayName("Сортировка новостей в списке новостей")
    public void testSortingNews() {
        int itemCount = NewsEditScreenSteps.getItemCount();
        String firstDateBeforeSorting = NewsScreenSteps.getFirstDateBeforeSortingNewsPage();
        NewsEditScreenSteps.scrollNewsToLastPosition(itemCount - 1);
        String lastDateBeforeSorting = NewsScreenSteps.getLastDateBeforeSortingNewsPage(itemCount - 1);
        NewsEditScreenSteps.sortingNewsStep();
        NewsEditScreenSteps.scrollNewsToFirstPosition();
        String firstDateAfterSorting = NewsScreenSteps.getFirstDateAfterSortingNewsPage();
        NewsEditScreenSteps.scrollNewsToLastPosition(itemCount - 1);
        String lastDateAfterSorting = NewsScreenSteps.getLastDateAfterSortingNewsPage(itemCount - 1);
        NewsEditScreenSteps.checkDateAfterSortingOne(firstDateBeforeSorting, lastDateAfterSorting);
        NewsEditScreenSteps.checkDateAfterSortingTwo(lastDateBeforeSorting, firstDateAfterSorting);
    }

    @Test
    @DisplayName("Развернуть новость")
    public void testExpandNews() {
        waitElement(R.id.news_list_recycler_view);
        ViewInteraction recyclerView = NewsScreenSteps.getRecyclerViewAndScrollToFirstPosition();
        int heightBeforeClick = NewsScreenSteps.getHeightBeforeClick(recyclerView);
        NewsScreenSteps.clickFirstItem(recyclerView);
        int heightAfterClick = NewsScreenSteps.getHeightAfterClick(recyclerView);
        NewsScreenSteps.checkHeightAfterClick(heightBeforeClick, heightAfterClick);
    }

    @Test
    @DisplayName("Свернуть новость")
    public void testCollapseNews() {
        waitElement(R.id.news_list_recycler_view);
        ViewInteraction recyclerView = NewsScreenSteps.getRecyclerViewAndScrollToFirstPosition();
        int heightBeforeClick = NewsScreenSteps.getHeightBeforeClick(recyclerView);
        NewsScreenSteps.doubleClickFirstItem(recyclerView);
        int heightAfterClick = NewsScreenSteps.getHeightAfterClick(recyclerView);
        NewsScreenSteps.checkHeightAfterDoubleClick(heightBeforeClick, heightAfterClick);
    }

    @Test
    @DisplayName("Фильтрация новостей по дате")
    public void testFilterNewsByDate() {
        MainSteps.goToNewsEditingPageStep();
        String expectedDate = NewsScreenSteps.addingNewsAndReturnPublishDate();
        MainSteps.goToNewsPageStep();
        NewsScreenSteps.filterNewsByDateStep(expectedDate);
        int itemCount = NewsEditScreenSteps.getItemCount();
        NewsScreenSteps.checkPublishDateNews(itemCount, expectedDate);
    }

}
