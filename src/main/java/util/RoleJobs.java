// Author: Tancred423 (https://github.com/Tancred423)
package util;

public class RoleJobs {
    public static String getFancyName(String roleJob) {
        switch (roleJob.toLowerCase()) {
            case "tank":
                return "Tank";
            case "pld":
                return "Paladin";
            case "war":
                return "Warrior";
            case "drk":
                return "Dark Knight";
            case "gnb":
                return "Gunbreaker";

            case "heal":
                return "Healer";
            case "whm":
                return "White Mage";
            case "sch":
                return "Scholar";
            case "ast":
                return "Astrologian";

            case "dps":
                return "DPS";
            case "mdps":
                return "Melee DPS";
            case "mnk":
                return "Monk";
            case "drg":
                return "Dragoon";
            case "nin":
                return "Ninja";
            case "sam":
                return "Samurai";

            case "rdps":
                return "Ranged DPS";
            case "brd":
                return "Bard";
            case "mch":
                return "Mechanist";
            case "dnc":
                return "Dancer";

            case "cdps":
                return "Caster DPS";
            case "blm":
                return "Black Mage";
            case "smn":
                return "Summoner";
            case "rdm":
                return "Red Mage";
            case "blu":
                return "Blue Mage";

            default:
                return roleJob;
        }
    }
}
