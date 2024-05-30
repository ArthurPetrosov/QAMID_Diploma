package steps;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class QuotesSteps {

    public ViewInteraction getQuotesRecyclerViewAndScrollToFirstPosition() {
        ViewInteraction recyclerView = onView(withId(R.id.our_mission_item_list_recycler_view));
        recyclerView.perform(scrollToPosition(0));
        return recyclerView;
    }
}