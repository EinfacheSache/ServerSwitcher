package de.cubeattack.serverswitcher.listener;

import de.cubeattack.serverswitcher.ServerSwitcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.Locale;

public class CommandListener implements Listener {

    ServerSwitcher serverSwitcher;

    public CommandListener(ServerSwitcher serverSwitcher){
        this.serverSwitcher = serverSwitcher;
    }

    @EventHandler
    public void onCommand(ChatEvent event){
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();
        if(!event.isProxyCommand())return;
        if(!event.getMessage().toLowerCase().startsWith("/server"))return;

        try {
            if (event.getMessage().split(" ")[1].equalsIgnoreCase(ServerSwitcher.getLobbyServer()) || ((ProxiedPlayer) event.getSender()).hasPermission(ServerSwitcher.getSwitchPermission())) return;
                event.setCancelled(true);
                ((ProxiedPlayer) event.getSender()).sendMessage(ServerSwitcher.getPrefix() + "§cYou don't have the permission");

        }catch (Exception ignored){}
    }
}
