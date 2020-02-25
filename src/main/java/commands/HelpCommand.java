// Author: Tancred423 (https://github.com/Tancred423)
package commands;

import feo.Feo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class HelpCommand {
    public static void run(GuildMessageReceivedEvent event, Member member, String[] args) {
        event.getChannel().sendMessage(
                new EmbedBuilder()
                        .setColor(member.getColor())
                        .setTitle("Feo Ul Commands")
                        .setDescription(Feo.prefix + " help\n" +
                                Feo.prefix + " ping\n" +
                                Feo.prefix + " signup")
                        .build()
        ).queue();
    }
}
