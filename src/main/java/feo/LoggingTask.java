package feo;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class LoggingTask implements Runnable {
    private Exception e;
    private JDA jda;
    private String function;
    private GuildMessageReceivedEvent event;

    public LoggingTask(Exception e, JDA jda, String function) {
        this.e = e;
        this.jda = jda;
        this.function = function;
        this.event = null;
    }

    public LoggingTask(Exception e, JDA jda, String function, GuildMessageReceivedEvent event) {
        this.e = e;
        this.jda = jda;
        this.function = function;
        this.event = event;
    }

    @Override
    public void run() {
        if (event != null) {
            // Notify User
            event.getChannel().sendMessage(
                    "Something went wrong, master!\n" +
                    "A report was sent to the bot owner."
            ).queue();
        }

        // Error Log
        if (e != null) e.printStackTrace();

        // Error DM
        if (jda != null) {
            var botOwner = jda.getUserById(Feo.ownerId);
            if (botOwner != null) {
                botOwner.openPrivateChannel().queue(privateChannel ->
                        privateChannel.sendMessage("```c\n" +
                                "Error\n" +
                                "-----\n" +
                                (function == null ? "" : "Function: " + function + "\n") +
                                (e == null ? "" : "Error: " + e.getMessage() + "\n") +
                                "```").queue());
            }
        }
    }
}