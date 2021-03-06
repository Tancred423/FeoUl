// Author: Tancred423 (https://github.com/Tancred423)
package commands;

import feo.Feo;
import feo.Signup;
import feo.SignupEndTask;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import util.Emotes;
import util.Error;
import util.RoleJobs;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.concurrent.TimeUnit;

public class SignupCommand {
    public static void run(GuildMessageReceivedEvent event, Member member, String[] args) {
        var color = member.getColor();
        var channel = event.getChannel();
        // Usage
        if (args.length == 0) {
            var emotes = Emotes.get(event.getJDA());
            event.getChannel().sendMessage(
                    new EmbedBuilder()
                            .setColor(color)
                            .setTitle("\uD83D\uDD0E Signup")
                            .addField("Command", "`feo signup [title] / [time] / [roles and/or jobs]`", false)
                            .addField("Example", "`feo signup Titania Ex / 1d 12h 30m / 2tank 1whm 1heal 2mdps 2rdps`", false)
                            .addField("Times",
                                    "d = day(s)\n" +
                                            "h = hour(s)\n" +
                                            "m = minute(s)",
                                    true)

                            .addField("Roles",
                                    "tank = " + emotes.get("tank").getAsMention() + " Tank\n" +
                                            "heal = " + emotes.get("heal").getAsMention() + " Healer\n" +
                                            "dps = " + emotes.get("dps").getAsMention() + " DPS\n" +
                                            "mdps = " + emotes.get("mdps").getAsMention() + " Melee DPS\n" +
                                            "rdps = " + emotes.get("rdps").getAsMention() + " Ranged DPS\n" +
                                            "cdps = " + emotes.get("cdps").getAsMention() + " Caster DPS\n",
                                    true)

                            .addField("Jobs",
                                    "pld = " + emotes.get("pld").getAsMention() + " Paladin\n" +
                                            "war = " + emotes.get("war").getAsMention() + " Warrior\n" +
                                            "drk = " + emotes.get("drk").getAsMention() + " Dark Knight\n" +
                                            "gnb = " + emotes.get("gnb").getAsMention() + " Gunbreaker\n" +

                                            "whm = " + emotes.get("whm").getAsMention() + " White Mage\n" +
                                            "sch = " + emotes.get("sch").getAsMention() + " Scholar\n" +
                                            "ast = " + emotes.get("ast").getAsMention() + " Astrologian\n" +

                                            "mnk = " + emotes.get("mnk").getAsMention() + " Monk\n" +
                                            "drg = " + emotes.get("drg").getAsMention() + " Dragoon\n" +
                                            "nin = " + emotes.get("nin").getAsMention() + " Ninja\n" +
                                            "sam = " + emotes.get("sam").getAsMention() + " Samurai\n" +

                                            "brd = " + emotes.get("brd").getAsMention() + " Bard\n" +
                                            "mch = " + emotes.get("mch").getAsMention() + " Mechanist\n" +
                                            "dnc = " + emotes.get("dnc").getAsMention() + " Dancer\n" +

                                            "blm = " + emotes.get("blm").getAsMention() + " Black Mage\n" +
                                            "smn = " + emotes.get("smn").getAsMention() + " Summoner\n" +
                                            "rdm = " + emotes.get("rdm").getAsMention() + " Red Mage\n" +
                                            "blu = " + emotes.get("blu").getAsMention() + " Blue Mage",
                                    true)
                            .build()
            ).queue();
            return;
        }

        // Prepare Args
        var sb = new StringBuilder();
        for (var arg : args) sb.append(arg).append(" ");
        var parameters = sb.toString().split("/");
        if (parameters.length != 3) {
            Error.sendInvalidUsage(channel, color, "You need to provide [title], [time] and [classes]!");
            return;
        }

        // Title
        var title = parameters[0].trim().replaceAll(" +", " ");

        // Time
        var time = parameters[1].trim().replaceAll(" +", " "); // 1d 12h 30m
        var timeArray = time.split(" "); // [1d,12h,30m]
        var now = OffsetDateTime.now();
        var eventTime = OffsetDateTime.now();
        // Parsing input
        var days = 0;
        var hours = 0;
        var minutes = 0;
        for (String s : timeArray) {
            var timeValue = s.substring(0, s.length() - 1);
            var timeUnit = s.substring(s.length() - 1); // d or h or m
            // Validating input
            try {
                Integer.parseInt(timeValue);
            } catch (Exception e) {
                Error.sendInvalidUsage(channel, color, "Your time value(s) is/are invalid!");
                return;
            }
            if (!timeUnit.equals("d") && !timeUnit.equals("h") && !timeUnit.equals("m")) {
                Error.sendInvalidUsage(channel, color, "Your time unit(s) is/are invalid!");
                return;
            }
            switch (timeUnit.toLowerCase()) {
                case "d":
                    days += Integer.parseInt(timeValue);
                    break;
                case "h":
                    hours += Integer.parseInt(timeValue);
                    break;
                case "m":
                    minutes += Integer.parseInt(timeValue);
                    break;
            }
        }
        eventTime = eventTime.plusDays(days).plusHours(hours).plusMinutes(minutes);

        var end = eventTime.minusMinutes(30); // Remove 30 minutes to close event earlier.

        // Date has to be in the future
        if (now.toInstant().toEpochMilli() >= end.toInstant().toEpochMilli()) {
            Error.sendInvalidUsage(channel, color, "The given time must be in the future!\n" +
                    "Note that signups will be closed 30 minutes prior to the scheduled event!");
            return;
        }

        // Date must not be further in the future than 30 days
        var nowIn30Days = OffsetDateTime.now().plusDays(30);
        if (end.toInstant().toEpochMilli() > nowIn30Days.toInstant().toEpochMilli()) {
            Error.sendInvalidUsage(channel, color, "The given time must not be further in the future than 30 days!");
            return;
        }

        // Roles/Jobs
        var roles = parameters[2].trim().replaceAll(" +", " "); // 2tank 1whm 1heal 2mdps 2rdps
        var rolesArray = roles.split(" "); // [2tank,1whm,1heal,2mdps,2rdps]

        // Not allowed to use more than 10 different roles/classes
        if (rolesArray.length > 10) {
            Error.sendInvalidUsage(channel, color, "You must not use more then 10 different roles or classes!");
            return;
        }

        var signup = new Signup(0);
        signup.setGuildId(event.getGuild().getIdLong());
        signup.setChannelId(event.getChannel().getIdLong());
        signup.setAuthorId(event.getAuthor().getIdLong());
        signup.setEventTime(Timestamp.from(eventTime.toInstant()));

        var totalSpots = 0;

        for (var s : rolesArray) {
            int i;
            for(i = 0; i < s.length(); i++){
                var c = String.valueOf(s.charAt(i));
                if(!c.matches("-?\\d+(\\.\\d+)?") )
                    break;
            }
            var amountString = s.substring(0, i);

            if (!amountString.matches("[0-9]+")) {
                Error.sendInvalidUsage(channel, color, "Your roles and/or jobs are invalid.\n" +
                        "Format: [amount][role/job]\n" +
                        "Example: 3tank 5heal 1whm 15dps");
                return;
            }

            var amount = Integer.parseInt(amountString);
            var role = s.substring(i);
            switch (role.toLowerCase()) {
                case "tank":
                    signup.setTank(amount);
                    break;
                case "pld":
                    signup.setPld(amount);
                    break;
                case "war":
                    signup.setWar(amount);
                    break;
                case "drk":
                    signup.setDrk(amount);
                    break;
                case "gnb":
                    signup.setGnb(amount);
                    break;

                case "heal":
                    signup.setHeal(amount);
                    break;
                case "whm":
                    signup.setWhm(amount);
                    break;
                case "sch":
                    signup.setSch(amount);
                    break;
                case "ast":
                    signup.setAst(amount);
                    break;

                case "dps":
                    signup.setDps(amount);
                    break;
                case "mdps":
                    signup.setMdps(amount);
                    break;
                case "mnk":
                    signup.setMnk(amount);
                    break;
                case "drg":
                    signup.setDrg(amount);
                    break;
                case "nin":
                    signup.setNin(amount);
                    break;
                case "sam":
                    signup.setSam(amount);
                    break;

                case "rdps":
                    signup.setRdps(amount);
                    break;
                case "brd":
                    signup.setBrd(amount);
                    break;
                case "mch":
                    signup.setMch(amount);
                    break;
                case "dnc":
                    signup.setDnc(amount);
                    break;

                case "cdps":
                    signup.setCdps(amount);
                    break;
                case "blm":
                    signup.setBlm(amount);
                    break;
                case "smn":
                    signup.setSmn(amount);
                    break;
                case "rdm":
                    signup.setRdm(amount);
                    break;
                case "blu":
                    signup.setBlu(amount);
                    break;
            }
            totalSpots += amount;
        }

        // Post Embed
        var embed = new EmbedBuilder()
                .setColor(color)
                .setTitle("Signup for " + title)
                .setDescription(
                        "**Click on the corresponding reaction to join!**\n" +
                                "The signup will close 30 minutes prior to the scheduled event.")
                .addField("Spots left", totalSpots + "/" + totalSpots, false)
                .setFooter("Event at", "https://i.imgur.com/PA3Xzgu.png")
                .setTimestamp(eventTime.toInstant());

        var countProps = signup.getProperties();

        var emotes = Emotes.get(event.getJDA());
        for (var countProp : countProps.entrySet()) {
            if (countProp.getValue() == 0) continue;
            var description = "**" + countProp.getValue() + "x** " + emotes.get(countProp.getKey()).getAsMention() + " " + RoleJobs.getFancyName(countProp.getKey()) + "\n";
            embed.addField(description, "Nobody", true);
        }

        var emoteGuild = event.getJDA().getGuildById(627243953611341831L);
        if (emoteGuild == null) {
            System.out.println("Emote Guild not found");
            return;
        }
        var fillEmote = emoteGuild.getEmoteById(666698369519058956L);
        if (fillEmote == null) {
            System.out.println("Fill Emote not found");
            return;
        }

        embed.addField(fillEmote.getAsMention() + " Fill", "Nobody", false);

        var finalTotalSpots = totalSpots;
        channel.sendMessage(embed.build()).queue(msg -> {
            for (var countProp : countProps.entrySet()) {
                if (countProp.getValue() == 0) continue;
                msg.addReaction(emotes.get(countProp.getKey())).queue();
            }
            msg.addReaction(fillEmote).queue();
            msg.addReaction("❌").queue();

            signup.setMessageId(msg.getIdLong());
            signup.addToDb(event.getJDA(), finalTotalSpots);
        });

        var delayInMillis = end.toInstant().toEpochMilli() - System.currentTimeMillis();
        Feo.signupService.schedule(
                new SignupEndTask(event.getJDA(), signup),
                delayInMillis,
                TimeUnit.MILLISECONDS
        );
    }
}
