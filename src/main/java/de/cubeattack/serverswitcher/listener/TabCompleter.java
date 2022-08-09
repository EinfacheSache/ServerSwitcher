package de.cubeattack.serverswitcher.listener;

import de.cubeattack.serverswitcher.ServerSwitcher;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class TabCompleter implements Listener {

    @EventHandler
    public void onTabComplete(TabCompleteEvent event){
        if(!(event.getSender() instanceof ProxiedPlayer))return;
        if(!event.getCursor().startsWith("/server"))return;

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if(player.hasPermission(ServerSwitcher.getSwitchPermission()))return;

        if(!ServerSwitcher.getLobbyServer().toLowerCase().startsWith(event.getCursor().toLowerCase().replace("/server ", "")))return;
        event.getSuggestions().clear();
        event.getSuggestions().add(ServerSwitcher.getLobbyServer());
    }
}
