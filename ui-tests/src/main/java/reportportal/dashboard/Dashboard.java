package reportportal.dashboard;

import lombok.Getter;

public class Dashboard {
    @Getter
    private final String name;
    @Getter
    private final String description;
    private boolean share;

    public Dashboard(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Dashboard(String name, String description, boolean share) {
        this.name = name;
        this.description = description;
        this.share = share;
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
        return "Dashboard{}";
    }
}
