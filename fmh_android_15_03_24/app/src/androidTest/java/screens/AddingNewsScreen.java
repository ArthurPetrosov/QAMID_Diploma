package screens;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import data.DataHelper;
import ru.iteco.fmhandroid.R;

public class AddingNewsScreen {

    public ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction tittleField = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction dateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction statusSwitcher = onView(withId(R.id.switcher));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public ViewInteraction confirmCancelAddingNewsButton = onView(allOf(withId(android.R.id.button1)));
    public ViewInteraction cancelMessage = onView(withText(R.string.cancellation));
    public ViewInteraction confirmDeleteNewsButton = onView(allOf(withId(android.R.id.button1)));
    NewsEditScreen newsEditScreen = new NewsEditScreen();

    public void fillingNewsFields(String category, String tittle, String date, String time, String description) {
        newsEditScreen.addNewsButton.perform(click());
        categoryField.perform(replaceText(category));
        tittleField.perform(replaceText(tittle));
        dateField.perform(replaceText(date));
        timeField.perform(replaceText(time));
        descriptionField.perform(replaceText(description));
    }

    public void addNews(String category, String tittle, String date, String time, String description) {
        fillingNewsFields(category, tittle, date, time, description);
        saveButton.perform(click());
    }

    public void confirmDelete() {
        DataHelper.waitElement(android.R.id.button1);
        confirmDeleteNewsButton.perform(click());
    }
}