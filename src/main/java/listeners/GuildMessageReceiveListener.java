// Author: Tancred423 (https://github.com/Tancred423)
package listeners;

import commands.HelpCommand;
import commands.PingCommand;
import commands.SignupCommand;
import commands.ThreadCommand;
import feo.Feo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GuildMessageReceiveListener extends ListenerAdapter {
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;

        var content = event.getMessage().getContentRaw().trim();
        if (content.startsWith(Feo.prefix)) {
            var missingPermissions = getMissingPermissions(event);
            if (missingPermissions != null) {
                var sb = new StringBuilder().append("❌ **Insufficient Permissions**\n")
                        .append("I am missing the following permissions:\n");
                for (var permission : missingPermissions) sb.append(permission.getName()).append("\n");
                event.getChannel().sendMessage(sb.toString()).queue();
                return;
            }


            var contentSplit = content.substring(Feo.prefix.length()).trim().split(" ");
            var command = contentSplit[0];
            var args = Arrays.copyOfRange(contentSplit, 1, contentSplit.length);
            var member = event.getMember();
            if (member != null) {
                switch (command.toLowerCase()) {
                    case "ping":
                    case "pong":
                        CompletableFuture.runAsync(() -> PingCommand.run(event, member, args), Feo.fixedThreadPool);
                        break;

                    case "help":
                    case "h":
                        CompletableFuture.runAsync(() -> HelpCommand.run(event, member, args), Feo.fixedThreadPool);
                        break;

                    case "signup":
                        CompletableFuture.runAsync(() -> SignupCommand.run(event, member, args), Feo.fixedThreadPool);
                        break;

                    case "threads":
                    case "thread":
                        if (event.getAuthor().getIdLong() == Feo.ownerId)
                            CompletableFuture.runAsync(() -> ThreadCommand.run(event, member, args), Feo.fixedThreadPool);
                        break;
                }
            }
        }
    }

    private List<Permission> getMissingPermissions(GuildMessageReceivedEvent event) {
        var selfMember = event.getGuild().getMemberById(event.getJDA().getSelfUser().getIdLong());
        if (selfMember == null) return null;

        var missingPermissions = new ArrayList<Permission>();

        if (!selfMember.hasPermission(Permission.VIEW_CHANNEL)) missingPermissions.add(Permission.VIEW_CHANNEL);
        if (!selfMember.hasPermission(Permission.MESSAGE_WRITE)) missingPermissions.add(Permission.MESSAGE_WRITE);
        if (!selfMember.hasPermission(Permission.MESSAGE_EMBED_LINKS)) missingPermissions.add(Permission.MESSAGE_EMBED_LINKS);
        if (!selfMember.hasPermission(Permission.MESSAGE_ADD_REACTION)) missingPermissions.add(Permission.MESSAGE_ADD_REACTION);
        if (!selfMember.hasPermission(Permission.MESSAGE_MANAGE)) missingPermissions.add(Permission.MESSAGE_MANAGE);
        if (!selfMember.hasPermission(Permission.MESSAGE_EXT_EMOJI)) missingPermissions.add(Permission.MESSAGE_EXT_EMOJI);
        if (!selfMember.hasPermission(Permission.MESSAGE_READ)) missingPermissions.add(Permission.MESSAGE_READ);
        if (!selfMember.hasPermission(Permission.MESSAGE_HISTORY)) missingPermissions.add(Permission.MESSAGE_HISTORY);

        return missingPermissions.isEmpty() ? null : missingPermissions;
    }
}
