

package steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static org.junit.Assert.assertEquals;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import data.DataHelper;
import screens.AuthorizationScreen;
import screens.MainScreen;
import screens.NewsScreen;

public class MainSteps {

    public static void successLoginStep() {
        AuthorizationScreen.successLogin();
    }

    public static void pressBack() {
        Espresso.pressBack();
    }

    public static void goToNewsEditingPageStep() {
        NewsScreen.goToNewsEditScreen();
    }

    public static void goToNewsPageStep() {
        MainScreen.goToNewsPageByNavigationMenu();
    }

    public static void goToAboutPageStep() {
        MainScreen.goToAboutPage();
    }

    public static void goToQuotesPageStep() {
        MainScreen.goToQuotesPage();
    }

    public static void logOutFromApp() {
        MainScreen.logOut();
    }

    public static int getHeightBeforeClick(ViewInteraction recyclerView) {
        int[] heightBeforeClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
        return heightBeforeClick[0];
    }

    public static void clickFirstItem(ViewInteraction recyclerView) {
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

    public static int getHeightAfterClick(ViewInteraction recyclerView) {
        int[] heightAfterClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
        return heightAfterClick[0];
    }

    public static void checkHeightAfterClick(int heightBeforeClick, int heightAfterClick) {
        Assert.assertTrue(heightBeforeClick < heightAfterClick);
    }

    public static void doubleClickFirstItem(ViewInteraction recyclerView) {
        recyclerView.perform(actionOnItemAtPosition(0, doubleClick()));
    }

    public static void checkHeightAfterDoubleClick(int heightBeforeClick, int heightAfterClick) {
        assertEquals(heightBeforeClick, heightAfterClick);
    }
}