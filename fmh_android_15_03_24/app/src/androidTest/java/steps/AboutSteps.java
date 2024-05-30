package steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import screens.AboutScreen;

public class AboutSteps {
    AboutScreen aboutScreen = new AboutScreen();

    @Step("Проверка видимость страницы About")
    public void isAppVersionDisplayed() {
        Allure.step("Проверка наличия сведений о версии приложения");
        aboutScreen.versionText.check(matches(isDisplayed()));
        aboutScreen.versionInfo.check(matches(isDisplayed()));
    }

    @Step("Проверка наличия сведений о разработчике приложения")
    public void isAppDeveloperDisplayed() {
        Allure.step("Проверка кликабельности ссылки TermsOfUse");
        aboutScreen.aboutInfo.check(matches(isClickable()));
    }

}