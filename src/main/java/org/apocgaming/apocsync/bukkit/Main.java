package org.apocgaming.apocsync.bukkit;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements PluginMessageListener {
	
	public void onEnable() {
		Bukkit.getMessenger().registerIncomingPluginChannel(this, "Return", this);
	}

	@Override
	public void onPluginMessageReceived(String channel, Player p, byte[] message) {

		DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));

		 try{
			//strings
			String sub = in.readUTF();
			String cmd = in.readUTF();
			//end of strings

			if (cmd.toLowerCase().equals("op")) {
				System.out.println("[Apoc Sync] Received a command message from BungeeCord, Command: /op, not executing it.");
			}

			if (cmd.toLowerCase().equals("stop")) {
				System.out.println("[Apoc Sync] Received a command message from BungeeCord, Command: /stop , not executing it.");
			}

			if (cmd.toLowerCase().equals("restart")) {
				System.out.println("[Apoc Sync] Received a command message from BungeeCord, Command: /restart, not executing it.");
			}

			else if (sub.equals("command")) {
				System.out.println("[Apoc Sync] Received a command message from BungeeCord, executing it.");
				getServer().dispatchCommand(getServer().getConsoleSender(), cmd);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
