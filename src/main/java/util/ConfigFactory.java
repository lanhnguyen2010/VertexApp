package util;

import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author lanhnguyen on 08/03/2016.
 */
public final class ConfigFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigFactory.class);

    private static final String DATABASE_CONFIG_FILE = "database.json";

    private static JsonObject dbConfig;

    public static void loadDBConfig(){
        URI path;
        try {
            path = ClassLoader.getSystemResource(DATABASE_CONFIG_FILE).toURI();
            String dbConfigString = new String(Files.readAllBytes(Paths.get(path)));
            dbConfig = new JsonObject(dbConfigString);
        } catch (URISyntaxException | IOException e) {
            LOGGER.error("Cannot get database config, resource/database.json", e);
        }
    }

    public static JsonObject getDbConfig() {
        return dbConfig;
    }
}
