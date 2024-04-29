package steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import org.hamcrest.Matchers;

import screens.AboutScreen;
import io.qameta.allure.kotlin.Allure;

public class AboutScreenSteps {

    public static void isAppVersionDisplayed() {
        Allure.step("Проверка наличия сведений о версии приложения");
        AboutScreen.versionText.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
        AboutScreen.versionInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    }

    public static void isAppDeveloperDisplayed() {
        Allure.step("Проверка наличия сведений о разработчике приложения");
        AboutScreen.aboutInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    }
}