package screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class FilterNews {

    public ViewInteraction filterNewsTittle = onView(withId(R.id.filter_news_title_text_view));
    public ViewInteraction categoryNewsButton = onView(allOf(withId(R.id.text_input_end_icon),
            withContentDescription("Show dropdown menu")));
    public ViewInteraction categoryNewsField = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction dateStartPublish = onView(withId(R.id.news_item_publish_date_start_text_input_edit_text));
    public ViewInteraction dateEndPublish = onView(withId(R.id.news_item_publish_date_end_text_input_edit_text));
    public ViewInteraction filterButton = onView(withId(R.id.filter_button));
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public ViewInteraction checkboxActive = onView(withId(R.id.filter_news_active_material_check_box));
    public ViewInteraction checkboxNotActive = onView(withId(R.id.filter_news_inactive_material_check_box));

}