package steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import screens.NewsScreen;

public class NewsSteps {
    public static int newsListId = R.id.news_list_recycler_view;
    NewsScreen newsScreen = new NewsScreen();

    @Step("Редактировать новость")
    public void goToNewsEditingPage() {
        Allure.step("Редактировать новость");
        newsScreen.editNewsButton.perform(click());
    }

    @Step("Проверка видимости страницы Все новости")
    public void checkNewsPage() {
        Allure.step("Проверка видимости страницы Все новости");
        newsScreen.filterNewsButton.check(matches(isDisplayed()));
    }

}