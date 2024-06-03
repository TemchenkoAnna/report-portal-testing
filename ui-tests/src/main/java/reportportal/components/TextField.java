package reportportal.components;

import org.openqa.selenium.By;
import reportportal.browser.Browser;

import java.util.Objects;

public class TextField extends CommonPageElement {

    public TextField(By locator) {
        Objects.requireNonNull(locator, Browser.LOCATOR_NOT_NULL_MESSAGE);
        this.locator = locator;
    }

    public void clear() {
        waitForPageElementVisibilityLocated(locator);
        Browser.getInstance().clear(locator);
    }

    public void type(CharSequence... textForType) {
        Objects.requireNonNull(textForType, String.format(Browser.CAN_NOT_BE_NULL_MESSAGE,"Text"));
        waitForPageElementPresenceLocated(locator);
        Browser.getInstance().sendKeys(locator, textForType);
    }

}
