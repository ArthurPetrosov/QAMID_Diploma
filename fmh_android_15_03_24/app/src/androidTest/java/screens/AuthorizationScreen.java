package screens;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.allOf;
import static screens.MainScreen.logOut;

import androidx.test.espresso.ViewInteraction;

import data.Data;
import data.DataHelper;
import ru.iteco.fmhandroid.R;

public class AuthorizationScreen {

    public static ViewInteraction loginField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.login_text_input_layout))));
    public static ViewInteraction passwordField = onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.password_text_input_layout))));
    public static ViewInteraction loginButton = onView(allOf(withId(R.id.enter_button)));
    public static int idSignInButton = R.id.enter_button;

    public static void login(String login, String password) {
        DataHelper.waitElement(AuthorizationScreen.idSignInButton);
        loginField.perform(replaceText(login));
        passwordField.perform(replaceText(password));
        loginButton.check(matches(isDisplayed())).perform(click());
    }

    public static void successLogin() {
        login(Data.correctLogin, Data.correctPassword);
    }

    public static void checkLogInAndLogInIfNot() {
        if (DataHelper.isLogIn()) {
            successLogin();
        }
    }

    public static void checkLogOutAndLogOutIfNot() {
        if (DataHelper.isLogOut()) {
            logOut();
        }
    }

}


