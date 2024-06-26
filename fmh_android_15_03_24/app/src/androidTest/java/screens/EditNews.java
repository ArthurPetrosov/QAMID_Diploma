package screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import data.DataHelper;
import ru.iteco.fmhandroid.R;

public class EditNews {

    public ViewInteraction categoryField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction tittleField = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction dateField = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction timeField = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction descriptionField = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction statusSwitcher = onView(withId(R.id.switcher));
    public ViewInteraction saveButton = onView(withId(R.id.save_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));


    public void changeNewsAttribute(String newTittle, String newDate,
                                    String newTime, String newDescription) {
        tittleField.perform(replaceText(newTittle));
        dateField.perform(replaceText(newDate));
        timeField.perform(replaceText(newTime));
        descriptionField.perform(replaceText(newDescription));
        saveButton.perform(click());
        DataHelper.waitElement(R.id.news_list_recycler_view);
    }
}