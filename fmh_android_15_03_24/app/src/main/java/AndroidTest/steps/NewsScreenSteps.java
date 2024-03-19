package AndroidTest.steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static AndroidTest.data.Data.categoryForth;
import static AndroidTest.data.Data.dateNews;
import static AndroidTest.data.Data.descriptionNews;
import static AndroidTest.data.Data.timeNews;
import static AndroidTest.data.Data.tittleNews;
import static AndroidTest.data.DataHelper.getTextFromNews;
import static AndroidTest.screens.AddingNewsScreen.addNews;
import static AndroidTest.screens.NewEditScreem.scrollNews;
import static AndroidTest.screens.NewScreen.filterNewsByDate;

import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import AndroidTest.data.DataHelper;
import ru.iteco.fmhandroid.R;

public class NewsScreenSteps {


    public static String getFirstDateBeforeSortingNewsPage() {
        String firstDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, 0);
        return firstDateBeforeSorting;
    }

    public static String getLastDateBeforeSortingNewsPage(int position) {
        String lastDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, position);
        return lastDateBeforeSorting;
    }

    public static String getFirstDateAfterSortingNewsPage() {
        String firstDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, 0);
        return firstDateBeforeSorting;
    }

    public static String getLastDateAfterSortingNewsPage(int position) {
        String lastDateBeforeSorting = getTextFromNews(R.id.news_item_date_text_view, position);
        return lastDateBeforeSorting;
    }

    public static ViewInteraction getRecyclerViewAndScrollToFirstPosition() {
        ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
        return recyclerView;
    }

    public static int getHeightBeforeClick(ViewInteraction recyclerView) {
        int[] heightBeforeClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
        return heightBeforeClick[0];
    }

    public static void clickFirstItem(ViewInteraction recyclerView) {
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    public static int getHeightAfterClick(ViewInteraction recyclerView) {
        int[] heightAfterClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
        return heightAfterClick[0];
    }

    public static void checkHeightAfterClick(int heightBeforeClick, int heightAfterClick) {
        Assert.assertTrue(heightBeforeClick < heightAfterClick);
    }

    public static void doubleClickFirstItem(ViewInteraction recyclerView) {
        recyclerView.perform(actionOnItemAtPosition(0, doubleClick()));
    }

    public static void checkHeightAfterDoubleClick(int heightBeforeClick, int heightAfterClick) {
        assertEquals(heightBeforeClick, heightAfterClick);
    }

    public static void filterNewsByDateStep(String date) {
        filterNewsByDate(date, date);
    }

    public static String addingNewsAndReturnPublishDate() {
        addNews(categoryForth, tittleNews, dateNews, timeNews, descriptionNews);
        return dateNews;
    }
    public static void checkPublishDateNews(int itemCount, String expectedDate) {
        for (int i = 0; i < itemCount; i++) {
            scrollNews(i);
            String actualDate = getTextFromNews(R.id.news_item_date_text_view, i);
            assertEquals(expectedDate, actualDate);
        }
    }
}