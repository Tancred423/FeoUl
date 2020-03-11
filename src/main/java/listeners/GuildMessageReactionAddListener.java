package listeners;

import feo.Feo;
import feo.Signup;
import feo.SignupEndTask;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.Emotes;

import javax.annotation.Nonnull;

public class GuildMessageReactionAddListener extends ListenerAdapter {
    public void onGuildMessageReactionAdd(@Nonnull GuildMessageReactionAddEvent event) {
        // No bots
        if (event.getUser().isBot()) return;

        // Only act if Feo has enough permissions
        var selfMember = event.getGuild().getMember(event.getJDA().getSelfUser());
        if (selfMember == null) return;
        if (GuildMessageReceiveListener.getMissingPermissions(selfMember) != null) return;

        // Message has to be a signup
        var jda = event.getJDA();
        var messageId = event.getMessageIdLong();
        if (!Signup.isSignup(jda, messageId)) return;

        var signup = new Signup(messageId);
        signup.initialize(jda);

        // User has to react with valid emote
        var emotes = Emotes.get(jda);
        if (event.getReactionEmote().isEmoji()) {
            if (event.getReactionEmote().getName().equals("❌")) {
                if (signup.getAuthorId() == event.getUser().getIdLong())
                    Feo.fixedThreadPool.submit(new SignupEndTask(jda, signup));
                else event.getReaction().removeReaction(event.getUser()).queue();
            }
        } else if (emotes.containsValue(event.getReactionEmote().getEmote())) {
            // Reaction emote is valid for this signup
            var reactionEmote = event.getReactionEmote().getEmote();
            var roleJobName = "";
            if (!reactionEmote.equals(emotes.get("fill"))) {
                for (var emote : emotes.entrySet()) {
                    if (emote.getValue().equals(reactionEmote)) {
                        if (signup.isValidReaction(jda, emote.getKey())) {
                            roleJobName = emote.getKey();
                            break;
                        } else return;
                    }
                }
            } else roleJobName = "fill";

            // Check if there is still a free spot in general and for this role/job
            if (!signup.hasFreeSpot(jda, roleJobName)) {
                event.getReaction().removeReaction(event.getUser()).queue();
                return;
            }

            // Update DB and Embed
            var wasSet = signup.addUserToSlot(jda, signup, roleJobName, event.getUser().getIdLong(), reactionEmote.getIdLong());

            if (!wasSet) {
                event.getReaction().removeReaction(event.getUser()).queue();
            }
        }
    }
}
