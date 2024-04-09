package AndroidTest.steps;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static AndroidTest.screens.AboutScreen.aboutInfo;
import static AndroidTest.screens.AboutScreen.versionInfo;
import static AndroidTest.screens.AboutScreen.versionText;

import org.hamcrest.Matchers;

import io.qameta.allure.kotlin.Allure;

public class AboutScreenSteps {

    public static void isAppVersionDisplayed() {
        Allure.step("Проверка наличия сведений о версии приложения");
        versionText.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
        versionInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    }

    public static void isAppDeveloperDisplayed() {
        Allure.step("Проверка наличия сведений о разработчике приложения");
        aboutInfo.check(matches(allOf(isDisplayed(), withText(Matchers.not("")))));
    }
}