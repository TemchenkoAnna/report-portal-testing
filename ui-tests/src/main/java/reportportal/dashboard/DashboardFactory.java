package reportportal.dashboard;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

public class DashboardFactory {
    private static final SecureRandom random = new SecureRandom();
    private static final byte[] bytes = new byte[20];
    private DashboardFactory(){
        throw new IllegalStateException("It is not allowed to create instance of" + DashboardFactory.class);
    }
    public static Dashboard withRandomTestData() {
        random.nextBytes(bytes);
        String name = "Name".concat(RandomStringUtils.randomAlphabetic(5, 10));
        String description = "Description".concat(RandomStringUtils.randomAlphabetic(5, 10));
        boolean share = (1 == (random.nextInt() * 3));
        return new Dashboard(name, description, share);
    }
}