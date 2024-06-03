package reportportal.components;

import org.openqa.selenium.By;
import reportportal.browser.Browser;

import java.util.Objects;

public class PopUp extends CommonPageElement {
    public PopUp(By locator) {
        Objects.requireNonNull(locator, Browser.LOCATOR_NOT_NULL_MESSAGE);
        this.locator = locator;
    }

    public void waitUntilPopUpDisappeared(){
        waitForPageElementInvisibilityLocated(locator);
    }
    public boolean isDisplayed() {
        waitForPageElementVisibilityLocated(locator);
        return Browser.getInstance().isDisplayed(locator);
    }
}

