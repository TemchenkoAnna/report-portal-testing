package reportportal.services;

import reportportal.pages.LoginPage;

public class AuthenticationService {
    public boolean login(String login, String password) {
        LoginPage loginPage = new LoginPage();
        loginPage
                .typeLogin(login)
                .typePassword(password)
                .clickLoginButton();
        return loginPage.isSignedSuccessfully();
    }
}