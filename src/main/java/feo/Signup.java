// Author: Tancred423 (https://github.com/Tancred423)
package feo;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static feo.Database.closeQuietly;

public class Signup {
    private long messageId;
    private long guildId;
    private long channelId;
    private long authorId;
    private Timestamp eventTime;

    private int tank;
    private int pld;
    private int war;
    private int drk;
    private int gnb;

    private int heal;
    private int whm;
    private int sch;
    private int ast;

    private int dps;
    private int mdps;
    private int mnk;
    private int drg;
    private int nin;
    private int sam;

    private int rdps;
    private int brd;
    private int mch;
    private int dnc;

    private int cdps;
    private int blm;
    private int smn;
    private int rdm;
    private int blu;

    public Signup(long messageId) {
        this.messageId = messageId;
        this.guildId = 0;
        this.channelId = 0;
        this.authorId = 0;
        this.eventTime = null;

        this.tank = 0;
        this.pld = 0;
        this.war = 0;
        this.drk = 0;
        this.gnb = 0;

        this.heal = 0;
        this.whm = 0;
        this.sch = 0;
        this.ast = 0;

        this.dps = 0;
        this.mdps = 0;
        this.mnk = 0;
        this.drg = 0;
        this.nin = 0;
        this.sam = 0;

        this.rdps = 0;
        this.brd = 0;
        this.mch = 0;
        this.dnc = 0;

        this.cdps = 0;
        this.blm = 0;
        this.smn = 0;
        this.rdm = 0;
        this.blu = 0;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getGuildId() {
        return guildId;
    }

    public void setGuildId(long guildId) {
        this.guildId = guildId;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public int getTank() {
        return tank;
    }

    public void setTank(int tank) {
        this.tank = tank;
    }

    public int getPld() {
        return pld;
    }

    public void setPld(int pld) {
        this.pld = pld;
    }

    public int getWar() {
        return war;
    }

    public void setWar(int war) {
        this.war = war;
    }

    public int getDrk() {
        return drk;
    }

    public void setDrk(int drk) {
        this.drk = drk;
    }

    public int getGnb() {
        return gnb;
    }

    public void setGnb(int gnb) {
        this.gnb = gnb;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getWhm() {
        return whm;
    }

    public void setWhm(int whm) {
        this.whm = whm;
    }

    public int getSch() {
        return sch;
    }

    public void setSch(int sch) {
        this.sch = sch;
    }

    public int getAst() {
        return ast;
    }

    public void setAst(int ast) {
        this.ast = ast;
    }

    public int getDps() {
        return dps;
    }

    public void setDps(int dps) {
        this.dps = dps;
    }

    public int getMdps() {
        return mdps;
    }

    public void setMdps(int mdps) {
        this.mdps = mdps;
    }

    public int getMnk() {
        return mnk;
    }

    public void setMnk(int mnk) {
        this.mnk = mnk;
    }

    public int getDrg() {
        return drg;
    }

    public void setDrg(int drg) {
        this.drg = drg;
    }

    public int getNin() {
        return nin;
    }

    public void setNin(int nin) {
        this.nin = nin;
    }

    public int getSam() {
        return sam;
    }

    public void setSam(int sam) {
        this.sam = sam;
    }

    public int getRdps() {
        return rdps;
    }

    public void setRdps(int rdps) {
        this.rdps = rdps;
    }

    public int getBrd() {
        return brd;
    }

    public void setBrd(int brd) {
        this.brd = brd;
    }

    public int getMch() {
        return mch;
    }

    public void setMch(int mch) {
        this.mch = mch;
    }

    public int getDnc() {
        return dnc;
    }

    public void setDnc(int dnc) {
        this.dnc = dnc;
    }

    public int getCdps() {
        return cdps;
    }

    public void setCdps(int cdps) {
        this.cdps = cdps;
    }

    public int getBlm() {
        return blm;
    }

    public void setBlm(int blm) {
        this.blm = blm;
    }

    public int getSmn() {
        return smn;
    }

    public void setSmn(int smn) {
        this.smn = smn;
    }

    public int getRdm() {
        return rdm;
    }

    public void setRdm(int rdm) {
        this.rdm = rdm;
    }

    public int getBlu() {
        return blu;
    }

    public void setBlu(int blu) {
        this.blu = blu;
    }

    public LinkedHashMap<String, Integer> getProperties() {
        var map = new LinkedHashMap<String, Integer>();
        map.put("tank", tank);
        map.put("pld", pld);
        map.put("war", war);
        map.put("drk", drk);
        map.put("gnb", gnb);

        map.put("heal", heal);
        map.put("whm", whm);
        map.put("sch", sch);
        map.put("ast", ast);

        map.put("dps", dps);
        map.put("mdps", mdps);
        map.put("mnk", mnk);
        map.put("drg", drg);
        map.put("nin", nin);
        map.put("sam", sam);

        map.put("rdps", rdps);
        map.put("brd", brd);
        map.put("mch", mch);
        map.put("dnc", dnc);

        map.put("cdps", cdps);
        map.put("blm", blm);
        map.put("smn", smn);
        map.put("rdm", rdm);
        map.put("blu", blu);

        return map;
    }

    public void initialize(JDA jda) {
        Connection connection = null;

        try {
            connection = Feo.db.getHikari().getConnection();
            var select = connection.prepareStatement("SELECT * FROM signup WHERE messageid=?");
            select.setLong(1, messageId);
            var resultSet = select.executeQuery();
            if (resultSet.first()) {
                do {
                    this.guildId = resultSet.getLong("guildid");
                    this.channelId = resultSet.getLong("channelid");
                    this.authorId = resultSet.getLong("authorid");
                    this.eventTime = resultSet.getTimestamp("eventtime");

                    this.tank = resultSet.getInt("tank");
                    this.pld = resultSet.getInt("pld");
                    this.war = resultSet.getInt("war");
                    this.drk = resultSet.getInt("drk");
                    this.gnb = resultSet.getInt("gnb");

                    this.heal = resultSet.getInt("heal");
                    this.whm = resultSet.getInt("whm");
                    this.sch = resultSet.getInt("sch");
                    this.ast = resultSet.getInt("ast");

                    this.dps = resultSet.getInt("dps");
                    this.mdps = resultSet.getInt("mdps");
                    this.mnk = resultSet.getInt("mnk");
                    this.drg = resultSet.getInt("drg");
                    this.nin = resultSet.getInt("nin");
                    this.sam = resultSet.getInt("sam");

                    this.rdps = resultSet.getInt("rdps");
                    this.brd = resultSet.getInt("brd");
                    this.mch = resultSet.getInt("mch");
                    this.dnc = resultSet.getInt("dnc");

                    this.cdps = resultSet.getInt("cdps");
                    this.blm = resultSet.getInt("blm");
                    this.smn = resultSet.getInt("smn");
                    this.rdm = resultSet.getInt("rdm");
                    this.blu = resultSet.getInt("blu");
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#initialize"));
        } finally {
            closeQuietly(connection);
        }
    }

    public static List<Signup> getList(JDA jda) {
        Connection connection = null;
        var signups = new ArrayList<Signup>();

        try {
            connection = Feo.db.getHikari().getConnection();
            var select = connection.prepareStatement("SELECT * FROM signup");
            var resultSet = select.executeQuery();
            if (resultSet.first()) {
                do {
                    var signup = new Signup(resultSet.getLong("messageid"));

                    signup.setGuildId(resultSet.getLong("guildid"));
                    signup.setChannelId(resultSet.getLong("channelid"));
                    signup.setAuthorId(resultSet.getLong("authorid"));
                    signup.setEventTime(resultSet.getTimestamp("eventtime"));

                    signup.setTank(resultSet.getInt("tank"));
                    signup.setPld(resultSet.getInt("pld"));
                    signup.setWar(resultSet.getInt("war"));
                    signup.setDrk(resultSet.getInt("drk"));
                    signup.setGnb(resultSet.getInt("gnb"));

                    signup.setHeal(resultSet.getInt("heal"));
                    signup.setWhm(resultSet.getInt("whm"));
                    signup.setSch(resultSet.getInt("sch"));
                    signup.setAst(resultSet.getInt("ast"));

                    signup.setDps(resultSet.getInt("dps"));
                    signup.setMdps(resultSet.getInt("mdps"));
                    signup.setMnk(resultSet.getInt("mnk"));
                    signup.setDrg(resultSet.getInt("drg"));
                    signup.setNin(resultSet.getInt("nin"));
                    signup.setSam(resultSet.getInt("sam"));

                    signup.setRdps(resultSet.getInt("rdps"));
                    signup.setBrd(resultSet.getInt("brd"));
                    signup.setMch(resultSet.getInt("mch"));
                    signup.setDnc(resultSet.getInt("dnc"));

                    signup.setCdps(resultSet.getInt("cdps"));
                    signup.setBlm(resultSet.getInt("blm"));
                    signup.setSmn(resultSet.getInt("smn"));
                    signup.setRdm(resultSet.getInt("rdm"));
                    signup.setBlu(resultSet.getInt("blu"));

                    signups.add(signup);
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#getList"));
        } finally {
            closeQuietly(connection);
        }

        return signups;
    }

    public void addToDb(JDA jda, int totalSpots) {
        Connection connection = null;

        try {
            connection = Feo.db.getHikari().getConnection();

            // Signup
            var insert = connection.prepareStatement(
                    "INSERT INTO signup " +
                            "(messageid,guildid,channelid,authorid,eventtime,tank,pld,war,drk,gnb,heal,whm,sch,ast,dps,mdps,mnk,drg,nin,sam,rdps,brd,mch,dnc,cdps,blm,smn,rdm,blu) " +
                            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
            );
            insert.setLong(1, messageId);
            insert.setLong(2, guildId);
            insert.setLong(3, channelId);
            insert.setLong(4, authorId);
            insert.setTimestamp(5, eventTime);

            insert.setInt(6, tank);
            insert.setInt(7, pld);
            insert.setInt(8, war);
            insert.setInt(9, drk);
            insert.setInt(10, gnb);

            insert.setInt(11, heal);
            insert.setInt(12, whm);
            insert.setInt(13, sch);
            insert.setInt(14, ast);

            insert.setInt(15, dps);
            insert.setInt(16, mdps);
            insert.setInt(17, mnk);
            insert.setInt(18, drg);
            insert.setInt(19, nin);
            insert.setInt(20, sam);

            insert.setInt(21, rdps);
            insert.setInt(22, brd);
            insert.setInt(23, mch);
            insert.setInt(24, dnc);

            insert.setInt(25, cdps);
            insert.setInt(26, blm);
            insert.setInt(27, smn);
            insert.setInt(28, rdm);
            insert.setInt(29, blu);

            insert.executeUpdate();

            // Spots
            insert = connection.prepareStatement(
                    "INSERT INTO spots " +
                            "(messageid,spots) " +
                            "VALUES (?,?)"
            );
            insert.setLong(1, messageId);
            insert.setLong(2, totalSpots);

            insert.executeUpdate();
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#addToDb"));
        } finally {
            closeQuietly(connection);
        }
    }

    public static boolean isSignup(JDA jda, long messageId) {
        Connection connection = null;
        var isSignup = false;

        try {
            connection = Feo.db.getHikari().getConnection();
            var select = connection.prepareStatement(
                    "SELECT * " +
                            "FROM signup " +
                            "WHERE messageid=?"
            );
            select.setLong(1, messageId);
            var resultSet = select.executeQuery();
            if (resultSet.first()) isSignup = true;
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#isSignup"));
        } finally {
            closeQuietly(connection);
        }

        return isSignup;
    }

    public void removeFromDb(JDA jda) {
        Connection connection = null;

        try {
            if (isSignup(jda, messageId)) {
                connection = Feo.db.getHikari().getConnection();
                // Signup
                var delete = connection.prepareStatement("DELETE FROM signup WHERE messageid=?");
                delete.setLong(1, messageId);
                delete.executeUpdate();

                // Entry
                delete = connection.prepareStatement("DELETE FROM entry WHERE messageid=?");
                delete.setLong(1, messageId);
                delete.executeUpdate();

                // Spots
                delete = connection.prepareStatement("DELETE FROM spots WHERE messageid=?");
                delete.setLong(1, messageId);
                delete.executeUpdate();
            }
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#removeFromDb"));
        } finally {
            closeQuietly(connection);
        }
    }

    public boolean isValidReaction(JDA jda, String key) {
        Connection connection = null;
        var isValidReaction = false;

        try {
            connection = Feo.db.getHikari().getConnection();
            var select = connection.prepareStatement(
                    "SELECT * " +
                            "FROM signup " +
                            "WHERE messageid=?"
            );
            select.setLong(1, messageId);
            var resultSet = select.executeQuery();
            if (resultSet.first()) if (resultSet.getInt(key) > 0) isValidReaction = true;
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#isValidReaction"));
        } finally {
            closeQuietly(connection);
        }

        return isValidReaction;
    }

    public boolean hasFreeSpot(JDA jda, String roleJobName) {
        var hasFreeSpot = true;

        // Check role/job specific spots
        if (!roleJobName.equals("fill")) {
            Connection connection = null;
            var isValidReaction = false;

            try {
                connection = Feo.db.getHikari().getConnection();
                var select = connection.prepareStatement(
                        "SELECT * " +
                                "FROM entry " +
                                "WHERE messageid=? " +
                                "AND emoteid=?"
                );
                select.setLong(1, messageId);
                select.setLong(2, Feo.emotes.get(roleJobName).getIdLong());
                var resultSet = select.executeQuery();
                if (resultSet.first()) {
                    if (resultSet.getFetchSize() >= getProperties().get(roleJobName))
                        hasFreeSpot = false;
                }
            } catch (SQLException e) {
                Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#isValidReaction"));
            } finally {
                closeQuietly(connection);
            }
        }

        // Check total spots
        if (getSpotsLeft(jda) == 0) hasFreeSpot = false;

        return hasFreeSpot;
    }

    public boolean addUserToSlot(JDA jda, Signup signup, String roleJobName, long userId, long emoteId) {
        // Update DB
        var wasSet = setEntry(jda, userId, emoteId);
        if (!wasSet) return false;
        var spotsLeft = getSpotsLeft(jda) - 1;
        setSpotsLeft(jda, spotsLeft);
        initialize(jda);

        // Update Embed
        var guild = jda.getGuildById(signup.getGuildId());
        if (guild != null) {
            var channel = guild.getTextChannelById(signup.getChannelId());
            if (channel != null) {
                channel.retrieveMessageById(signup.getMessageId()).queue(message -> {
                    var currentEmbed = message.getEmbeds().get(0);
                    var newEmbed = new EmbedBuilder()
                            .setColor(currentEmbed.getColor())
                            .setTitle(currentEmbed.getTitle())
                            .setDescription(currentEmbed.getDescription())
                            .setTimestamp(currentEmbed.getTimestamp());

                    var footer = currentEmbed.getFooter();
                    if (footer != null) newEmbed.setFooter(footer.getText(), footer.getIconUrl());

                    var fields = currentEmbed.getFields();
                    for (var field : fields) {
                        var fieldName = field.getName();
                        if (fieldName != null && fieldName.equalsIgnoreCase("spots left")) {
                            newEmbed.addField(fieldName, spotsLeft + "/" + getTotalSpots(), false);
                        } else if (fieldName != null && fieldName.contains(Feo.emotes.get(roleJobName).getId())) {
                            var fieldValue = field.getValue();
                            if (fieldValue != null) {
                                var hasNobody = field.getValue().equalsIgnoreCase("nobody");
                                if (hasNobody) newEmbed.addField(fieldName, "<@" + userId + ">", !fieldName.contains("fill"));
                                else newEmbed.addField(fieldName, fieldValue + "\n<@" + userId + ">", !fieldName.contains("fill"));
                            }
                        } else newEmbed.addField(field);
                    }

                    message.editMessage(newEmbed.build()).queue();
                }, f -> { });
            }
        }

        return true;
    }

    private boolean setEntry(JDA jda, long userId, long emoteId) {
        Connection connection = null;
        var wasSet = false;

        try {
            connection = Feo.db.getHikari().getConnection();
            if (!entryHasEntry(jda, userId)) {
                var insert = connection.prepareStatement(
                        "INSERT INTO entry " +
                                "(messageid,userid,emoteid) " +
                                "VALUES (?,?,?)"
                );
                insert.setLong(1, messageId);
                insert.setLong(2, userId);
                insert.setLong(3, emoteId);
                insert.executeUpdate();
                wasSet = true;
            }
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#setEntry"));
        } finally {
            closeQuietly(connection);
        }

        return wasSet;
    }

    private boolean unsetEntry(JDA jda, long userId) {
        Connection connection = null;
        var wasUnset = false;

        try {
            connection = Feo.db.getHikari().getConnection();
            if (entryHasEntry(jda, userId)) {
                var insert = connection.prepareStatement(
                        "DELETE FROM entry " +
                                "WHERE messageid=? " +
                                "AND userid=?"
                );
                insert.setLong(1, messageId);
                insert.setLong(2, userId);
                insert.executeUpdate();
                wasUnset = true;
            }
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#unsetEntry"));
        } finally {
            closeQuietly(connection);
        }

        return wasUnset;
    }

    public boolean entryHasEntry(JDA jda, long userId) {
        Connection connection = null;
        var hasEntry = false;

        try {
            connection = Feo.db.getHikari().getConnection();
            var select = connection.prepareStatement(
                    "SELECT * " +
                            "FROM entry " +
                            "WHERE messageid=? " +
                            "AND userid=?"
            );
            select.setLong(1, messageId);
            select.setLong(2, userId);
            var resultSet = select.executeQuery();
            hasEntry = resultSet.first();
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#entryHasEntry"));
        } finally {
            closeQuietly(connection);
        }

        return hasEntry;
    }

    private void setSpotsLeft(JDA jda, int spotsLeft) {
        Connection connection = null;

        try {
            connection = Feo.db.getHikari().getConnection();

            if (spotsHasEntry(jda)) {
                var update = connection.prepareStatement(
                        "UPDATE spots " +
                                "SET spots=? " +
                                "WHERE messageid=?"
                );
                update.setInt(1, spotsLeft);
                update.setLong(2, messageId);
                update.executeUpdate();
            } else {
                var insert = connection.prepareStatement(
                        "INSERT INTO spots " +
                                "(messageid,spots) " +
                                "VALUES (?,?)"
                );
                insert.setLong(1, messageId);
                insert.setLong(2, spotsLeft);
                insert.executeUpdate();
            }
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#setSpotsLeft"));
        } finally {
            closeQuietly(connection);
        }
    }

    private boolean spotsHasEntry(JDA jda) {
        Connection connection = null;
        var hasEntry = false;

        try {
            connection = Feo.db.getHikari().getConnection();
            var select = connection.prepareStatement(
                    "SELECT * " +
                            "FROM spots " +
                            "WHERE messageid=?"
            );
            select.setLong(1, messageId);
            var resultSet = select.executeQuery();
            hasEntry = resultSet.first();
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#spotsHasEntry"));
        } finally {
            closeQuietly(connection);
        }

        return hasEntry;
    }

    public int getSpotsLeft(JDA jda) {
        Connection connection = null;
        var spotsLeft = 0;

        try {
            connection = Feo.db.getHikari().getConnection();
            var select = connection.prepareStatement(
                    "SELECT * " +
                            "FROM spots " +
                            "WHERE messageid=?"
            );
            select.setLong(1, messageId);
            var resultSet = select.executeQuery();
            if (resultSet.first()) spotsLeft = resultSet.getInt("spots");
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#getSpotsLeft"));
        } finally {
            closeQuietly(connection);
        }

        return spotsLeft;
    }

    public void removeUserFromSlot(JDA jda, Signup signup, String roleJobName, long userId) {
        // Update DB
        var wasUnset = unsetEntry(jda, userId);
        if (!wasUnset) return;
        var spotsLeft = getSpotsLeft(jda) + 1;
        setSpotsLeft(jda, spotsLeft);
        initialize(jda);

        // Update Embed
        var guild = jda.getGuildById(signup.getGuildId());
        if (guild != null) {
            var channel = guild.getTextChannelById(signup.getChannelId());
            if (channel != null) {
                channel.retrieveMessageById(signup.getMessageId()).queue(message -> {
                    var currentEmbed = message.getEmbeds().get(0);
                    var newEmbed = new EmbedBuilder()
                            .setColor(currentEmbed.getColor())
                            .setTitle(currentEmbed.getTitle())
                            .setDescription(currentEmbed.getDescription())
                            .setTimestamp(currentEmbed.getTimestamp());

                    var footer = currentEmbed.getFooter();
                    if (footer != null) newEmbed.setFooter(footer.getText(), footer.getIconUrl());

                    var fields = currentEmbed.getFields();
                    for (var field : fields) {
                        var fieldName = field.getName();
                        if (fieldName != null && fieldName.equalsIgnoreCase("spots left")) {
                            newEmbed.addField(fieldName, spotsLeft + "/" + getTotalSpots(), false);
                        } else if (fieldName != null && fieldName.contains(Feo.emotes.get(roleJobName).getId())) {
                            var fieldValue = field.getValue();
                            if (fieldValue != null) {
                                var hasNobody = field.getValue().equalsIgnoreCase("nobody");
                                if (hasNobody) newEmbed.addField(field);
                                else {
                                    var newFieldValue = fieldValue.replace("<@" + userId + ">", "");
                                    if (newFieldValue.equals("\n") || newFieldValue.isEmpty()) newFieldValue = "Nobody";
                                    newEmbed.addField(fieldName, newFieldValue, !fieldName.contains("fill"));
                                }
                            }
                        } else newEmbed.addField(field);
                    }

                    message.editMessage(newEmbed.build()).queue();
                }, f -> { });
            }
        }
    }

    public int getTotalSpots() {
        return tank + pld + war + drk + gnb +
                heal + whm + sch + ast +
                dps + mdps + mnk + drg + nin + sam +
                rdps + brd + mch + dnc +
                cdps + blm + smn + rdm + blu;
    }

    public long getEntryEmoteId(JDA jda, long userId) {
        Connection connection = null;
        var emoteId = 0L;

        try {
            connection = Feo.db.getHikari().getConnection();
            var select = connection.prepareStatement(
                    "SELECT * " +
                            "FROM entry " +
                            "WHERE messageid=? " +
                            "AND userid=?"
            );
            select.setLong(1, messageId);
            select.setLong(2, userId);
            var resultSet = select.executeQuery();
            if (resultSet.first()) emoteId = resultSet.getLong("emoteid");
        } catch (SQLException e) {
            Feo.fixedThreadPool.submit(new LoggingTask(e, jda, "Signup#getEntryEmoteId"));
        } finally {
            closeQuietly(connection);
        }

        return emoteId;
    }
}
