package reportportal.components;

import org.openqa.selenium.By;
import reportportal.browser.Browser;

import java.util.Objects;

public class Link extends CommonPageElement {

    public Link(By locator) {
        Objects.requireNonNull(locator, Browser.LOCATOR_NOT_NULL_MESSAGE);
        this.locator = locator;
    }

    public void click() {
        waitForPageElementToBeClickable(locator);
        Browser.getInstance().click(locator);
    }

    public String getText() {
        waitForPageElementVisibilityLocated(locator);
        return Browser.getInstance().getText(locator);
    }
}