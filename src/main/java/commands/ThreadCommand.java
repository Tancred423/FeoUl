// Author: Tancred423 (https://github.com/Tancred423)
package commands;

import feo.Feo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import util.ThreadPoolDescription;

import java.lang.management.ManagementFactory;

public class ThreadCommand {
    public static void run(GuildMessageReceivedEvent event, Member member, String[] args) {
        var fixedThreadPool = new ThreadPoolDescription(Feo.fixedThreadPool.toString());
        var remindMeService = new ThreadPoolDescription(Feo.signupService.toString());

        event.getChannel().sendMessage(new EmbedBuilder()
                .setColor(member.getColor())
                .setTitle("Stats for Nerds")
                .addField("General Stats", "Available Processors: " + Runtime.getRuntime().availableProcessors() +
                        "\nCurrent Threads: " + ManagementFactory.getThreadMXBean().getThreadCount(), true)

                .addField("Database", "Active Connections: " + Feo.db.getHikari().getHikariPoolMXBean().getActiveConnections() +
                        "\nTotal Connections: " + Feo.db.getHikari().getHikariPoolMXBean().getTotalConnections() +
                        "\nMaximum Pool Size: " + Feo.db.getHikari().getMaximumPoolSize(), true)

                .addField("Fixed Thread Pool", "Is Running: " + fixedThreadPool.isRunning() +
                        "\nPool Size: " + fixedThreadPool.getPoolSize() +
                        "\nActive Threads: " + fixedThreadPool.getActiveThreads() +
                        "\nQueued Tasks: " + fixedThreadPool.getQueuedTasks() +
                        "\nCompleted Tasks: " + fixedThreadPool.getCompletedTasks(), true)

                .addField("RemindMe Service", "Is Running: " + remindMeService.isRunning() +
                        "\nPool Size: " + remindMeService.getPoolSize() +
                        "\nActive Threads: " + remindMeService.getActiveThreads() +
                        "\nQueued Tasks: " + remindMeService.getQueuedTasks() +
                        "\nCompleted Tasks: " + remindMeService.getCompletedTasks(), true)
                .build()
        ).queue();
    }
}
