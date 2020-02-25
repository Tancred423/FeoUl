// Author: Tancred423 (https://github.com/Tancred423)
package listeners;

import feo.Feo;
import feo.Signup;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class GuildMessageReactionRemoveListener extends ListenerAdapter {
    public void onGuildMessageReactionRemove(@Nonnull GuildMessageReactionRemoveEvent event) {
        // No bots
        if (event.getUser().isBot()) return;

        // Message has to be a signup
        var jda = event.getJDA();
        var messageId = event.getMessageIdLong();
        if (!Signup.isSignup(jda, messageId)) return;

        var signup = new Signup(messageId);
        signup.initialize(jda);

        // Ignore reactions the bot removed
        if (event.getReactionEmote().isEmote() &&
                (signup.getEntryEmoteId(jda, event.getUser().getIdLong()) != event.getReactionEmote().getIdLong()))
            return;

        // User has to react with valid emote
        if (event.getReactionEmote().isEmote() &&
                Feo.emotes.containsValue(event.getReactionEmote().getEmote())) {
            // Reaction emote is valid for this signup
            var reactionEmote = event.getReactionEmote().getEmote();
            var roleJobName = "";
            if (!reactionEmote.equals(Feo.emotes.get("fill"))) {
                for (var emote : Feo.emotes.entrySet()) {
                    if (emote.getValue().equals(reactionEmote)) {
                        if (signup.isValidReaction(jda, emote.getKey())) {
                            roleJobName = emote.getKey();
                            break;
                        } else return;
                    }
                }
            } else roleJobName = "fill";

            // User has to have an entry to remove
            if (!signup.entryHasEntry(jda, event.getUser().getIdLong())) return;

            // Update DB and Embed
            signup.removeUserFromSlot(jda, signup, roleJobName, event.getUser().getIdLong());
        }
    }
}
