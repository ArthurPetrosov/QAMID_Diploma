package screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutScreen {

    public ViewInteraction versionText = onView(allOf(withId(R.id.about_version_title_text_view)));
    public ViewInteraction versionInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
    public ViewInteraction aboutInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
    public ViewInteraction backButton = onView(allOf(withId(R.id.about_back_image_button)));

}
