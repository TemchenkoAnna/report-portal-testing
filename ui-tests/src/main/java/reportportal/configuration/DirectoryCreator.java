package reportportal.configuration;

import java.io.File;

public class DirectoryCreator {
    private DirectoryCreator(){
        throw new IllegalStateException("It is prohibited to create instance of " + DirectoryCreator.class);
    }
    public static String create(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return directory.getAbsolutePath();
    }

}
