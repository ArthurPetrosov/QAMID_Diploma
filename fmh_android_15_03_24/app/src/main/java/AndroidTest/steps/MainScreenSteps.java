
package AndroidTest.steps;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static org.junit.Assert.assertEquals;
import static AndroidTest.screens.AuthorizationScreen.successLogin;
import static AndroidTest.screens.MainScreen.goToAboutPage;
import static AndroidTest.screens.MainScreen.goToNewsPageByNavigationMenu;
import static AndroidTest.screens.MainScreen.goToQuotesPage;
import static AndroidTest.screens.MainScreen.logOut;
import static AndroidTest.screens.NewScreen.goToNewsEditScreen;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;

import org.junit.Assert;

import AndroidTest.data.DataHelper;import io.qameta.allure.kotlin.Allure;import io.qameta.allure.kotlin.Step;

public class MainScreenSteps {

    @Step("Логинимся в валидными логином и паролем")
    public static void successLoginStep() {Allure.step("Логинимся в валидными логином и паролем");
        successLogin();
    }
    @Step("Нажимаем системную кнопку Назад")
    public static void pressBack() {Allure.step("Нажимаем системную кнопку Назад");
        Espresso.pressBack();
    }
    @Step("Переходим в раздел редактирования новостей")
    public static void goToNewsEditingPageStep() {Allure.step("Переходим в раздел редактирования новостей");
        goToNewsEditScreen();
    }
    @Step("Переходим в раздел Новости")
    public static void goToNewsPageStep() {Allure.step("Переходим в раздел Новости");
        goToNewsPageByNavigationMenu();
    }
    @Step("Переходим в раздел О приложении")
    public static void goToAboutPageStep() {Allure.step("Переходим в раздел О приложении");
        goToAboutPage();
    }
    @Step("Переходим в раздел Цитаты")
    public static void goToQuotesPageStep() {Allure.step("Переходим в раздел Цитаты");
        goToQuotesPage();
    }

    @Step("Разлогиниваемся из приложения")
    public static void logOutFromApp() {Allure.step("Разлогиниваемся из приложения");
        logOut();
    }@Step("Получаем высоту первого элемента списка до клика")
    public static int getHeightBeforeClick(ViewInteraction recyclerView) {Allure.step("Получаем высоту первого элемента списка до клика");
        int[] heightBeforeClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightBeforeClick));
        return heightBeforeClick[0];
    }
    @Step("Кликаем на первом элементе списка, чтобы элемент развернулся")
    public static void clickFirstItem(ViewInteraction recyclerView) {Allure.step("Кликаем на первом элементе списка, чтобы элемент развернулся");
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }
    @Step("Получаем высоту первого элемента списка после клика")
    public static int getHeightAfterClick(ViewInteraction recyclerView) {Allure.step("Получаем высоту первого элемента списка после клика");
        int[] heightAfterClick = {0};
        recyclerView.perform(new DataHelper.GetHeightAfterClickViewAction(heightAfterClick));
        return heightAfterClick[0];
    }
    @Step("Проверяем, что высота первого элемента списка увеличилась после клика")
    public static void checkHeightAfterClick(int heightBeforeClick, int heightAfterClick) {Allure.step("Проверяем, что высота первого элемента списка увеличилась после клика");
        Assert.assertTrue(heightBeforeClick < heightAfterClick);
    }
    @Step("Кликаем дважды на первом элементе списка, чтобы элемент развернулся и свернулся")
    public static void doubleClickFirstItem(ViewInteraction recyclerView) {Allure.step("Кликаем дважды на первом элементе списка, чтобы элемент развернулся и свернулся");
        recyclerView.perform(actionOnItemAtPosition(0, doubleClick()));
    }
    @Step("Проверяем, что высота первого элемента списка осталась той же после двойного клика")
    public static void checkHeightAfterDoubleClick(int heightBeforeClick, int heightAfterClick) {Allure.step("Проверяем, что высота первого элемента списка осталась той же после двойного клика");
        assertEquals(heightBeforeClick, heightAfterClick);
    }
}