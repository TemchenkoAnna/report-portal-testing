package reportportal.credentials;

public class Credentials {
    private static  String login;
    private static  String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Credentials(String login, String password) {
        Credentials.login = login;
        Credentials.password = password;
    }

}
