package reportportal.credentials;

import reportportal.configuration.PropertyDataReader;

public class CredentialsFactory {
    private static final String LOGIN_KEY_VALUE_FOR_PROPERTY = "report-portal.bo.credentials.login";
    private static final String PASSWORD_KEY_VALUE_FOR_PROPERTY = "report-portal.bo.credentials.password";

    private static String login;
    private static String password;

    private CredentialsFactory() {
        throw new IllegalStateException("It is prohibited to create" + CredentialsFactory.class + "entity");
    }

    public static Credentials withDataFromProperty() {
        login = PropertyDataReader.getDataByKey(LOGIN_KEY_VALUE_FOR_PROPERTY);
        password = PropertyDataReader.getDataByKey(PASSWORD_KEY_VALUE_FOR_PROPERTY);
        return new Credentials(login, password);
    }
}