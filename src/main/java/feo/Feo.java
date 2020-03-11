// Author: Tancred423 (https://github.com/Tancred423)
package feo;

import file.Config;
import listeners.GuildMessageReactionAddListener;
import listeners.GuildMessageReactionRemoveListener;
import listeners.GuildMessageReceiveListener;
import listeners.ReadyListener;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Feo {
    public static long ownerId;
    public static String prefix;
    public static ExecutorService fixedThreadPool;
    public static ScheduledExecutorService signupService;
    public static Database db;

    public static void main(String[] args) throws IOException, LoginException {
        // Config
        var config = new Config();
        if (config.getToken().isEmpty() || config.getOwnerId().isEmpty() || config.getPrefix().isEmpty()) {
            System.out.println("Fill config.ini");
            return;
        }
        ownerId = Long.parseLong(config.getOwnerId());
        prefix = config.getPrefix();

        // Database
        db = new Database();
        if (!db.connectToDatabase()) return;

        // Thread Pool
        var availProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available Processors: " + availProcessors);
        fixedThreadPool = Executors.newFixedThreadPool(availProcessors + 1);
        signupService = Executors.newScheduledThreadPool(1);

        var builder = new DefaultShardManagerBuilder();
        builder.setToken(config.getToken());
        builder.setActivity(Activity.playing("FFXIV | " + prefix + " help"));
        builder.setAutoReconnect(true);
        builder.addEventListeners(
                new ReadyListener(),
                new GuildMessageReceiveListener(),
                new GuildMessageReactionAddListener(),
                new GuildMessageReactionRemoveListener()
        );
        builder.build();
    }
}
