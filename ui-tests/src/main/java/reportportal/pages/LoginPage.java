package reportportal.pages;

import org.openqa.selenium.By;
import reportportal.browser.Browser;
import reportportal.components.Button;
import reportportal.components.PopUp;
import reportportal.components.TextField;

public class LoginPage extends BasePage {
    public static final String ESSENTIAL_URL = "http://localhost:8080/ui/";
    private final By loginTextFieldLocator = By.xpath("//input[@name='login']");
    private final By passwordFieldLocator = By.xpath("//input[@name='password']");
    private final By loginButtonLocator = By.xpath("//button[@type='submit']");
    private final By signedInSuccessfullyLocator = By.xpath(String
            .format(anyElementContainsSpecifiedTextLocator, successfulLoginPopUp));


    public LoginPage typeLogin(String login) {
        TextField loginTextField = new TextField(loginTextFieldLocator);
        loginTextField.clear();
        loginTextField.type(login);
        return this;
    }

    public LoginPage typePassword(String password) {
        TextField passwordTextField = new TextField(passwordFieldLocator);
        passwordTextField.clear();
        passwordTextField.type(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        Button button = new Button(loginButtonLocator);
        button.click();
        return this;
    }

    public boolean isSignedSuccessfully() {
        try {
            PopUp signedInSuccessfully = new PopUp(signedInSuccessfullyLocator);
            return signedInSuccessfully.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    public LoginPage open() {
        Browser.getInstance().getDriver().get(ESSENTIAL_URL);
        Browser.getInstance().getDriver().manage().window().maximize();
        return this;
    }
}
