// Author: Tancred423 (https://github.com/Tancred423)
package util;

import feo.Feo;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Emote;

import java.util.LinkedHashMap;

public class Emotes {
    public static LinkedHashMap<String, Emote> get(JDA jda) {
        var emotes = new LinkedHashMap<String, Emote>();
        var emoteGuild = jda.getGuildById(627243953611341831L);
        if (emoteGuild != null) {
            emotes.put("tank", emoteGuild.getEmoteById(627244149741191188L));
            emotes.put("pld", emoteGuild.getEmoteById(627244149476950018L));
            emotes.put("war", emoteGuild.getEmoteById(627244149959032842L));
            emotes.put("drk", emoteGuild.getEmoteById(627244148776239117L));
            emotes.put("gnb", emoteGuild.getEmoteById(627244149250195480L));

            emotes.put("heal", emoteGuild.getEmoteById(627244149095137300L));
            emotes.put("whm", emoteGuild.getEmoteById(627244149929934878L));
            emotes.put("sch", emoteGuild.getEmoteById(627244149590065153L));
            emotes.put("ast", emoteGuild.getEmoteById(627244148050755625L));

            emotes.put("dps", emoteGuild.getEmoteById(627244149040742419L));
            emotes.put("mdps", emoteGuild.getEmoteById(666675246723497984L));
            emotes.put("mnk", emoteGuild.getEmoteById(627244149472755722L));
            emotes.put("drg", emoteGuild.getEmoteById(627244148759592977L));
            emotes.put("nin", emoteGuild.getEmoteById(627244149573287936L));
            emotes.put("sam", emoteGuild.getEmoteById(627244149652979712L));

            emotes.put("rdps", emoteGuild.getEmoteById(666675246845132810L));
            emotes.put("brd", emoteGuild.getEmoteById(627244148629438466L));
            emotes.put("mch", emoteGuild.getEmoteById(627244149296463872L));
            emotes.put("dnc", emoteGuild.getEmoteById(627244148793278494L));

            emotes.put("cdps", emoteGuild.getEmoteById(666675245683572777L));
            emotes.put("blm", emoteGuild.getEmoteById(627244148222590978L));
            emotes.put("smn", emoteGuild.getEmoteById(627244149535408159L));
            emotes.put("rdm", emoteGuild.getEmoteById(627244149694791711L));
            emotes.put("blu", emoteGuild.getEmoteById(627244148604534794L));

            emotes.put("fill", emoteGuild.getEmoteById(666698369519058956L));
        }

        return emotes;
    }
}
