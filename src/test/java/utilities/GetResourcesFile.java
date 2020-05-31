package utilities;

import java.io.File;
import java.util.Objects;

public class GetResourcesFile {

    public static File getFile(String path) {
        File file = new File((Objects.requireNonNull(GetResourcesFile.class.getClassLoader()
                .getResource(path))).getFile());
        return file;
    }

    public static String getPath(String path) {
        return Objects.requireNonNull(GetResourcesFile.class.getClassLoader()
                .getResource(path)).getPath();
    }
}
