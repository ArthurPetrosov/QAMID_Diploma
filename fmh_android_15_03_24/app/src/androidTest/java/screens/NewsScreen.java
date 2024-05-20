package screens;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static screens.FilterNews.categoryNewsField;
import static screens.FilterNews.checkboxActive;
import static screens.FilterNews.checkboxNotActive;
import static screens.FilterNews.dateEndPublish;
import static screens.FilterNews.dateStartPublish;
import static screens.FilterNews.filterButton;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;

import data.DataHelper;
import ru.iteco.fmhandroid.R;

public class NewsScreen {
    public static ViewInteraction sortingNewsButton = onView(withId(R.id.sort_news_material_button));
    public static ViewInteraction editNewsButton = onView(withId(R.id.edit_news_material_button));
    public static ViewInteraction filterNewsButton = onView(withId(R.id.filter_news_material_button));
    public static int errorAddingMessageId = R.string.empty_fields;

    public static ViewInteraction tittleText = onView(withId(R.id.news_item_title_text_view));
    public static ViewInteraction descriptionText = onView(withId(R.id.news_item_description_text_view));

    public static void sortingNews() {
        DataHelper.waitElement(R.id.news_list_recycler_view);
        sortingNewsButton.perform(click());
    }

    public static void filterNewsByDate(String startDate, String endDate) {
        filterNewsButton.perform(click());
        dateStartPublish.perform(replaceText(startDate));
        dateEndPublish.perform(replaceText(endDate));
        filterButton.perform(click());
    }

    public static void filterNewsByStatus(boolean active, boolean notActive) {
        filterNewsButton.perform(click());
        if (!active) {
            checkboxActive.perform(click());
        }
        if (!notActive) {
            checkboxNotActive.perform(click());
        }
        filterButton.perform(click());
        DataHelper.waitElement(R.id.news_list_recycler_view);
    }

    public static void filterNewsByCategory(String category) {
        filterNewsButton.perform(click());
        categoryNewsField.perform(replaceText(category));
        filterButton.perform(click());
    }

    public static void filterNewsByStatusAndDate(boolean active, boolean notActive, String startDate, String endDate) {
        filterNewsButton.perform(click());
        if (!active) {
            checkboxActive.perform(click());
        }
        if (!notActive) {
            checkboxNotActive.perform(click());
        }
        dateStartPublish.perform(replaceText(startDate));
        dateEndPublish.perform(replaceText(endDate));
        filterButton.perform(click());
    }

    public static void goToNewsEditScreen() {
        editNewsButton.perform(click());
    }

    public static void scrollNewsToPosition(int position) {
        DataHelper.waitElement(R.id.news_list_recycler_view);
        onView(withId(R.id.news_list_recycler_view)).perform(RecyclerViewActions.scrollToPosition(position));
    }


}