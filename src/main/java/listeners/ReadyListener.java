// Author: Tancred423 (https://github.com/Tancred423)
package listeners;

import feo.Feo;
import feo.Signup;
import feo.SignupEndTask;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import static feo.Database.closeQuietly;

public class ReadyListener extends ListenerAdapter {
    public void onReady(@Nonnull ReadyEvent event) {
        // Check database connection
        Connection connection = null;
        try {
            connection = Feo.db.getHikari().getConnection();
        } catch (SQLException e) {
            System.out.println("Couldn't reach database.");
            return;
        } finally {
            closeQuietly(connection);
        }

        // Emotes
        var emoteGuild = event.getJDA().getGuildById(627243953611341831L);
        if (emoteGuild != null) {
            Feo.emotes = new LinkedHashMap<>();
            Feo.emotes.put("tank", emoteGuild.getEmoteById(627244149741191188L));
            Feo.emotes.put("pld", emoteGuild.getEmoteById(627244149476950018L));
            Feo.emotes.put("war", emoteGuild.getEmoteById(627244149959032842L));
            Feo.emotes.put("drk", emoteGuild.getEmoteById(627244148776239117L));
            Feo.emotes.put("gnb", emoteGuild.getEmoteById(627244149250195480L));

            Feo.emotes.put("heal", emoteGuild.getEmoteById(627244149095137300L));
            Feo.emotes.put("whm", emoteGuild.getEmoteById(627244149929934878L));
            Feo.emotes.put("sch", emoteGuild.getEmoteById(627244149590065153L));
            Feo.emotes.put("ast", emoteGuild.getEmoteById(627244148050755625L));

            Feo.emotes.put("dps", emoteGuild.getEmoteById(627244149040742419L));
            Feo.emotes.put("mdps", emoteGuild.getEmoteById(666675246723497984L));
            Feo.emotes.put("mnk", emoteGuild.getEmoteById(627244149472755722L));
            Feo.emotes.put("drg", emoteGuild.getEmoteById(627244148759592977L));
            Feo.emotes.put("nin", emoteGuild.getEmoteById(627244149573287936L));
            Feo.emotes.put("sam", emoteGuild.getEmoteById(627244149652979712L));

            Feo.emotes.put("rdps", emoteGuild.getEmoteById(666675246845132810L));
            Feo.emotes.put("brd", emoteGuild.getEmoteById(627244148629438466L));
            Feo.emotes.put("mch", emoteGuild.getEmoteById(627244149296463872L));
            Feo.emotes.put("dnc", emoteGuild.getEmoteById(627244148793278494L));

            Feo.emotes.put("cdps", emoteGuild.getEmoteById(666675245683572777L));
            Feo.emotes.put("blm", emoteGuild.getEmoteById(627244148222590978L));
            Feo.emotes.put("smn", emoteGuild.getEmoteById(627244149535408159L));
            Feo.emotes.put("rdm", emoteGuild.getEmoteById(627244149694791711L));
            Feo.emotes.put("blu", emoteGuild.getEmoteById(627244148604534794L));

            Feo.emotes.put("fill", emoteGuild.getEmoteById(666698369519058956L));
        }

        // Signup
        startSignup(event.getJDA());

        System.out.println("Ready!");
    }

    private void startSignup(JDA jda) {
        var signupList = Signup.getList(jda);
        for (var signup : signupList) {
            var end = new Date(signup.getEventTime().getTime());
            end.setTime(end.getTime() - (30 * 60 * 1000)); // Remove 30 minutes to close event earlier.
            var delayInMillis = end.toInstant().toEpochMilli() - System.currentTimeMillis();
            Feo.signupService.schedule(new SignupEndTask(jda, signup), delayInMillis, TimeUnit.MILLISECONDS);
        }
    }
}
