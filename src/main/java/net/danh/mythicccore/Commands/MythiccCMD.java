package net.danh.mythicccore.Commands;

import net.danh.dcore.Commands.CMDBase;
import net.danh.mythicccore.MythiccCore;
import net.danh.mythicccore.Utils.Resources;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.stream.Collectors;

import static net.danh.dcore.Utils.Chat.colorize;
import static net.danh.dcore.Utils.Player.sendConsoleMessage;
import static net.danh.dcore.Utils.Player.sendPlayerMessage;


public class MythiccCMD extends CMDBase {

    public MythiccCMD(MythiccCore core) {
        super(core, "MythiccCore");
    }

    @Override
    public void playerexecute(Player p, @NotNull String[] args) {
        if (args.length == 1) {
            if (p.hasPermission("MythiccCore.admin")) {
                if (args[0].equalsIgnoreCase("vanish")) {
                    if (MythiccCore.getInvisible_list().contains(p)) {
                        for (Player pp : Bukkit.getOnlinePlayers()) {
                            pp.showPlayer(MythiccCore.get(), p);
                        }
                        MythiccCore.getInvisible_list().remove(p);
                        sendPlayerMessage(p, "&aBạn đã hết tàng hình");
                    } else {
                        for (Player pp : Bukkit.getOnlinePlayers()) {
                            pp.hidePlayer(MythiccCore.get(), p);
                        }
                        MythiccCore.getInvisible_list().add(p);
                        sendPlayerMessage(p, "&aBạn đã tàng hình");
                    }
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    Resources.reloadfiles();
                    sendPlayerMessage(p, "&aĐã tải lại files");
                }
                if (args[0].equalsIgnoreCase("logs")) {
                    try {
                        File latest = new File("logs/latest.log");
                        URL url = new URL("https://pastebin.com/api/api_post.php");
                        URLConnection con = url.openConnection();
                        HttpURLConnection http = (HttpURLConnection) con;
                        http.setRequestMethod("POST");
                        http.setDoOutput(true);
                        http.setDoInput(true);
                        Map<String, String> arguments = new HashMap<>();

                        String latestText = new String(Files.readAllBytes(latest.toPath()));
                        arguments.put("api_dev_key", "oXmoTDKbITpVxK2KlXdgSV4kKCUykmPy");
                        arguments.put("api_option", "paste");
                        arguments.put("api_paste_code", latestText);

                        arguments.put("api_paste_format", "text");
                        arguments.put("api_paste_expire_date", "N");
                        arguments.put("api_paste_private", "1");

                        StringJoiner sj = new StringJoiner("&");
                        for (Map.Entry<String, String> entry : arguments.entrySet())
                            sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                                    + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
                        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                        http.connect();
                        OutputStream os = http.getOutputStream();
                        os.write(out);
                        InputStream is = http.getInputStream();
                        String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                        MythiccCore.get().getLogger().log(Level.INFO, colorize("&aYour pastebin link: " + text));
                    } catch (IOException urlException) {
                        urlException.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void consoleexecute(ConsoleCommandSender c, @NotNull String[] args) {
        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("vanish")) {
                Player p = Bukkit.getPlayer(args[1]);
                if (p == null) {
                    return;
                }
                if (MythiccCore.getInvisible_list().contains(p)) {
                    for (Player pp : Bukkit.getOnlinePlayers()) {
                        pp.showPlayer(MythiccCore.get(), p);
                    }
                    MythiccCore.getInvisible_list().remove(p);
                    sendPlayerMessage(p, "&aBạn đã hết tàng hình");
                } else {
                    for (Player pp : Bukkit.getOnlinePlayers()) {
                        pp.hidePlayer(MythiccCore.get(), p);
                    }
                    MythiccCore.getInvisible_list().add(p);
                    sendPlayerMessage(p, "&aBạn đã tàng hình");
                }
            }
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                Resources.reloadfiles();
                sendConsoleMessage(c, "&aĐả tải lại files");
            }
            if (args[0].equalsIgnoreCase("logs")) {
                try {
                    File latest = new File("logs/latest.log");
                    URL url = new URL("https://pastebin.com/api/api_post.php");
                    URLConnection con = url.openConnection();
                    HttpURLConnection http = (HttpURLConnection) con;
                    http.setRequestMethod("POST");
                    http.setDoOutput(true);
                    http.setDoInput(true);
                    Map<String, String> arguments = new HashMap<>();

                    String latestText = new String(Files.readAllBytes(latest.toPath()));
                    arguments.put("api_dev_key", "oXmoTDKbITpVxK2KlXdgSV4kKCUykmPy");
                    arguments.put("api_option", "paste");
                    arguments.put("api_paste_code", latestText);

                    arguments.put("api_paste_format", "text");
                    arguments.put("api_paste_expire_date", "N");
                    arguments.put("api_paste_private", "1");

                    StringJoiner sj = new StringJoiner("&");
                    for (Map.Entry<String, String> entry : arguments.entrySet())
                        sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "="
                                + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                    byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
                    http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                    http.connect();
                    OutputStream os = http.getOutputStream();
                    os.write(out);
                    InputStream is = http.getInputStream();
                    String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                    sendConsoleMessage(c, "&aYour pastebin link: " + text);
                } catch (IOException urlException) {
                    urlException.printStackTrace();
                }
            }
        }
    }
}
