package screens;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import data.DataHelper;
import ru.iteco.fmhandroid.R;

public class NewsScreen {
    public static int errorAddingMessageId = R.string.empty_fields;
    public ViewInteraction sortingNewsButton = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public ViewInteraction tittleText = onView(withId(R.id.news_item_title_text_view));
    public ViewInteraction descriptionText = onView(withId(R.id.news_item_description_text_view));

    FilterNews filterNews = new FilterNews();

    public void sortingNews() {
        DataHelper.waitElement(R.id.news_list_recycler_view);
        sortingNewsButton.perform(click());
    }

    public void filterNewsByDate(String startDate, String endDate) {
        filterNewsButton.perform(click());
        filterNews.dateStartPublish.perform(replaceText(startDate));
        filterNews.dateEndPublish.perform(replaceText(endDate));
        filterNews.filterButton.perform(click());
    }

    public void filterNewsByStatus(boolean active, boolean notActive) {
        filterNewsButton.perform(click());
        if (!active) {
            filterNews.checkboxActive.perform(click());
        }
        if (!notActive) {
            filterNews.checkboxNotActive.perform(click());
        }
        filterNews.filterButton.perform(click());
        DataHelper.waitElement(R.id.news_list_recycler_view);
    }

    public void filterNewsByCategory(String category) {
        filterNewsButton.perform(click());
        filterNews.categoryNewsField.perform(replaceText(category));
        filterNews.filterButton.perform(click());
    }

    public void filterNewsByStatusAndDate(boolean active, boolean notActive, String startDate, String endDate) {
        filterNewsButton.perform(click());
        if (!active) {
            filterNews.checkboxActive.perform(click());
        }
        if (!notActive) {
            filterNews.checkboxNotActive.perform(click());
        }
        filterNews.dateStartPublish.perform(replaceText(startDate));
        filterNews.dateEndPublish.perform(replaceText(endDate));
        filterNews.filterButton.perform(click());
    }

    public void goToNewsEditScreen() {
        editNewsButton.perform(click());
    }

    public void scrollNewsToPosition(int position) {
        DataHelper.waitElement(R.id.news_list_recycler_view);
        onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(position));
    }


}