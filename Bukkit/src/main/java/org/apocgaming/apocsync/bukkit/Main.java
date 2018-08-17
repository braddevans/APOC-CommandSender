package org.apocgaming.apocsync.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

public class Main extends JavaPlugin implements PluginMessageListener {

    public void onEnable() {
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "Return", this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] message) {

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));

        try {

            String sub = in.readUTF();

            if (sub.equals("command")) {

                String cmd = sub.toLowerCase();

                if (cmd.startsWith("op")) {
                    p.sendMessage("[ApocCommandSync]: Received a command, Command: /op, not executing it.");
                } else if (cmd.startsWith("stop")) {
                    p.sendMessage("[ApocCommandSync]: Received a command, Command: /stop, not executing it.");
                } else if (cmd.startsWith("restart")) {
                    p.sendMessage("[ApocCommandSync]: Received a command, Command: /restart, not executing it.");
                } else {
                    p.sendMessage("[Apoc Sync] Received a command message from BungeeCord, executing it.");
                    getServer().dispatchCommand(getServer().getConsoleSender(), sub);
                    p.sendMessage("cmd: " + cmd);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
