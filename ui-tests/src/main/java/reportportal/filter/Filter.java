package reportportal.filter;

public class Filter {
    private final String filterName;
    private final String launchName;

    public Filter(String filterName, String launchName) {
        this.filterName = filterName;
        this.launchName = launchName;
    }

    public String getFilterName() {
        return filterName;
    }

    public String getLaunchName() {
        return launchName;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Filter{}";
    }

}
