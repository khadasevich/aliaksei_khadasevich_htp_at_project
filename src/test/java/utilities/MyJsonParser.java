package utilities;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.http.util.EntityUtils;
import testobjects.ResponseBodyJsonObject;
import testobjects.SearchRequestJsonObject;
import testobjects.TestUser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyJsonParser <T> {

    private static final Gson gson = new Gson();

    public static ResponseBodyJsonObject parseExpectedJson(String path) throws IOException {
        File pathToJson = GetResourcesFile.getFile(path);
        ResponseBodyJsonObject expected = gson.fromJson(new JsonReader(new FileReader(pathToJson)),
                ResponseBodyJsonObject.class);
        return expected;
    }

    public static String searchJson(String user, boolean strict) {
        SearchRequestJsonObject searchRequestJson = new SearchRequestJsonObject(user, strict);
        return gson.toJson(searchRequestJson);
    }

    public static ResponseBodyJsonObject parseActualJson(String json) {
        return gson.fromJson(json, ResponseBodyJsonObject.class);
    }

    public static <T, json> void saveJsonToFile(String path, json jsonObject) throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get(path));
        gson.toJson(jsonObject, writer);
        writer.flush();
        writer.close();
    }

    public static TestUser getTestUser() throws IOException {
        TestUser testUser = gson.fromJson(new JsonReader(new FileReader("src/test/resources/testdata/testuser")),
                TestUser.class);
        return testUser;
    }
}
