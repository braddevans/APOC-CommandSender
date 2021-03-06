package org.apocgaming.apocsync.bungee;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

public class CommandClass extends Command{

    public CommandClass() {
        super("apocexec", "apocexec.admin", "ae");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /apocexec <command>");
            return;
        }

        StringBuilder cmd = new StringBuilder();

        for (int i = 0; i < args.length; i++) {
            cmd = cmd.append(args[i] + " ");
        }

        Map<String, ServerInfo> servers = BungeeCord.getInstance().getServers();

        for (Entry<String, ServerInfo> en : servers.entrySet()) {
            String name = en.getKey();
            ServerInfo all = BungeeCord.getInstance().getServerInfo(name);
            sendToBukkit("command", cmd.toString(), all);
        }

        System.out.println(cmd.toString().toLowerCase());
        sender.sendMessage("[ApocCommandSync]: command executed: " + cmd.toString());
    }

    private void sendToBukkit(String channel, String message, ServerInfo server) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(stream);
        try {
            out.writeUTF(channel);
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.sendData("Return", stream.toByteArray());

    }
}
