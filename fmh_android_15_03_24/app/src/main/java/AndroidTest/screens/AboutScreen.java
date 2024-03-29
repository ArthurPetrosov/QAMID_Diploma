package AndroidTest.screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import static org.hamcrest.CoreMatchers.allOf;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class AboutScreen {

    public static ViewInteraction versionText = onView(allOf(withId(R.id.about_version_title_text_view)));
    public static ViewInteraction versionInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
    public static ViewInteraction aboutInfo = onView(allOf(withId(R.id.about_company_info_label_text_view)));
    public static ViewInteraction backButton = onView(allOf(withId(R.id.about_back_image_button)));
}
