package reportportal.pages;

import org.openqa.selenium.By;
import reportportal.components.Button;
import reportportal.components.Link;
import reportportal.components.PopUp;

public class LogoutPage extends BasePage {
    private final By signedInSuccessfullyLocator = By.xpath("//*[contains(text(),'Signed in successfully')]");
    private final By defaultAvatarLocator = By.xpath("//img[@alt='avatar']");
    private final By logoutButtonLocator = By.xpath("//div[contains(text(),'Logout')]");
    private final By logoutPopUpLocator = By.xpath("//*[contains(text(),'You have been logged out')]");


    public LogoutPage clickAvatar(){
        PopUp signedInSuccessfullyPopUp = new PopUp(signedInSuccessfullyLocator);
        signedInSuccessfullyPopUp.waitUntilPopUpDisappeared();
        Link defaultAvatar = new Link(defaultAvatarLocator);
        defaultAvatar.click();
        return this;
    }
    public void clickLogoutButton(){
        Button logoutButton = new Button(logoutButtonLocator);
        logoutButton.click();
    }
    public boolean isSuccessfullyLogout(){
        PopUp logoutPopUp = new PopUp(logoutPopUpLocator);
        return logoutPopUp.isDisplayed();
    }
}
