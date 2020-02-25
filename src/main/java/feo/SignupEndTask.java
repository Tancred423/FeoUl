package feo;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;

public class SignupEndTask implements Runnable {
    private JDA jda;
    private Signup signup;

    public SignupEndTask(JDA jda, Signup signup) {
        this.jda = jda;
        this.signup = signup;
    }

    @Override
    public void run() {
        var spotsLeft = signup.getSpotsLeft(jda);
        var guild = jda.getGuildById(signup.getGuildId());
        if (guild != null) {
            var channel = guild.getTextChannelById(signup.getChannelId());
            if (channel != null) {
                channel.retrieveMessageById(signup.getMessageId()).queue(message -> {
                    message.clearReactions().queue();

                    var currentEmbed = message.getEmbeds().get(0);
                    var newEmbed = new EmbedBuilder()
                            .setColor(currentEmbed.getColor())
                            .setTitle(currentEmbed.getTitle())
                            .setFooter("Signup ended");

                    var fields = currentEmbed.getFields();
                    for (var field : fields) {
                        var fieldName = field.getName();
                        if (fieldName != null && fieldName.equalsIgnoreCase("spots left")) {
                            newEmbed.addField("Spots left", spotsLeft + "/" + signup.getTotalSpots(), false);
                        } else newEmbed.addField(field);
                    }

                    message.editMessage(newEmbed.build()).queue();
                }, f -> { });
            }
        }

        // unset
        signup.removeFromDb(jda);
    }
}
