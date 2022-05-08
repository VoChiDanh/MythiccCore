package net.danh.mythicccore.Utils;

import net.danh.mythicccore.NMS.NMSAssistant;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chat {
    private final static char COLOR_CHAR = ChatColor.COLOR_CHAR;

    public static String colorize(String input) {

        input = ChatColor.translateAlternateColorCodes('&', input);
        NMSAssistant nmsAssistant = new NMSAssistant();
        if (nmsAssistant.isVersionGreaterThan(15)) {
            input = translateHexColorCodes("\\&#", "", input);
        }

        return input;
    }

    public static String translateHexColorCodes(String startTag, String endTag, String message) {

        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

        while (matcher.find()) {

            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x" + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1) + COLOR_CHAR
                    + group.charAt(2) + COLOR_CHAR + group.charAt(3) + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5));

        }

        return matcher.appendTail(buffer).toString();
    }

    public static List<String> Lore(List<String> input) {
        List<String> output = new ArrayList<>();
        for (String string : input) {
            output.add(ChatColor.translateAlternateColorCodes('&', string));
        }
        return output;
    }

    public static void sendPlayerMessage(Player p, String... msg) {
        for (String string : msg) {
            p.sendMessage(colorize(string));
        }
    }
}
