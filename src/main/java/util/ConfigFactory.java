package util;

import io.vertx.core.json.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by lanhnguyen on 08/03/2016.
 */
public final class ConfigFactory {
    private static JsonObject dbConfig;

    public static void loadDBConfig(){
        URI path = null;
        try {
            path = ClassLoader.getSystemResource("database.json").toURI();
            String dbConfigString = new String(Files.readAllBytes(Paths.get(path)));
            dbConfig = new JsonObject(dbConfigString);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static JsonObject getDbConfig() {
        return dbConfig;
    }
}
