// Author: Tancred423 (https://github.com/Tancred423)
package commands;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.time.temporal.ChronoUnit;

public class PingCommand {
    public static void run(GuildMessageReceivedEvent event, Member member, String[] args) {
        event.getChannel().sendMessage("Ping: ...").queue(message -> {
            var ping = event.getMessage().getTimeCreated().until(message.getTimeCreated(), ChronoUnit.MILLIS);
            message.editMessage("Ping: " + ping + "ms | Websocket: " + event.getJDA().getGatewayPing() + "ms").queue();
        });
    }
}
