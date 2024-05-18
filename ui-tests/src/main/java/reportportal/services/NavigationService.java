package reportportal.services;

import reportportal.pages.LoginPage;

public class NavigationService {
    private final LoginPage loginPage = new LoginPage();

    public void navigateToLoginPage() {
        loginPage.open();
    }
}
