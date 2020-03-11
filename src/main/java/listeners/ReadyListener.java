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
