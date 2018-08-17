package org.apocgaming.apocsync.bungee;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;
import org.apocgaming.apocsync.bungee.CommandClass;

public final class apocsyncmain extends Plugin {

    @Override
    public void onEnable() {
        BungeeCord.getInstance().getPluginManager().registerCommand(this, new CommandClass());
        BungeeCord.getInstance().registerChannel("Return");
    }

    @Override
    public void onDisable() {
       // Plugin shutdown logic
    }
}