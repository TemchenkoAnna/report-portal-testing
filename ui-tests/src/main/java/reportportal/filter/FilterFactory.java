package reportportal.filter;

import org.apache.commons.lang3.RandomStringUtils;

public class FilterFactory {
    private FilterFactory(){
        throw new IllegalStateException("It is not allowed to create instance of " + FilterFactory.class);
    }
    public static Filter withRandomTestData() {
        String filterName = "FilterName".concat(RandomStringUtils.randomAlphabetic(5));
        String launchName = "LaunchName".concat(RandomStringUtils.randomAlphabetic(5));
        return new Filter(filterName, launchName);
    }
}
