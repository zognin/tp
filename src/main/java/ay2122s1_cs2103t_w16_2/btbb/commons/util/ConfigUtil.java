package ay2122s1_cs2103t_w16_2.btbb.commons.util;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.commons.core.Config;
import ay2122s1_cs2103t_w16_2.btbb.exception.DataConversionException;

/**
 * A class for accessing the Config File.
 */
public class ConfigUtil {
    public static Optional<Config> readConfig(Path configFilePath) throws DataConversionException {
        return JsonUtil.readJsonFile(configFilePath, Config.class);
    }

    public static void saveConfig(Config config, Path configFilePath) throws IOException {
        JsonUtil.saveJsonFile(config, configFilePath);
    }
}
