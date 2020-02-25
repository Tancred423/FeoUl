// Author: Tancred423 (https://github.com/Tancred423)
package util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.awt.*;

public class Error {
    public static void sendInvalidUsage(MessageChannel channel, Color color, String description) {
        var embed = new EmbedBuilder()
                .setColor(color)
                .setTitle("❌ Invalid Usage")
                .setDescription(description)
                .appendDescription("\n\n" + "View `feo help` for usage.");
        channel.sendMessage(embed.build()).queue();
    }
}
