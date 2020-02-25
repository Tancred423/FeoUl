// Author: Tancred423 (https://github.com/Tancred423)
package file;

import java.io.*;
import java.util.Properties;

public class Config {
    private String token;
    private String ownerId;
    private String prefix;

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Config() throws IOException {
        var currentDir = System.getProperty("user.dir");
        var resourcesDir = currentDir + "/resources";
        var configDir = resourcesDir + "/config.ini";

        // Create ./resources if it does not exist already.
        var resources = new File(resourcesDir);
        if (!resources.exists()) resources.mkdir();

        // Create ./resources/config.ini if it does not exist already.
        var config = new File(configDir);
        if (!config.exists()) create(resourcesDir);

        readConfig(configDir);
    }

    private void create(String resourcesDir) throws IOException {
        var config = new Properties();
        var os = new FileOutputStream(resourcesDir + "/config.ini");
        config.setProperty("token", "");
        config.setProperty("ownerId", "");
        config.setProperty("prefix", "");
        config.store(os,
                "Project: Feo Ul\n" +
                        "Author: Tancred#0001\n" +
                        "GitHub: https://github.com/Tancred423/FeoUl");
        os.close();
    }

    private void readConfig(String configDir) throws IOException {
        var config = new Properties();
        var is = new FileInputStream(configDir);
        config.load(is);

        this.token = config.getProperty("token");
        this.ownerId = config.getProperty("ownerId");
        this.prefix = config.getProperty("prefix");
    }

    public String getToken() { return token; }
    public String getOwnerId() { return ownerId; }
    public String getPrefix() { return prefix; }
}
