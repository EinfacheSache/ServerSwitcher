package de.cubeattack.serverswitcher.command;

import de.cubeattack.serverswitcher.ServerSwitcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SwitchCMD extends Command {

    private final String server;

    public SwitchCMD(String name, String server ,String permission, String... aliases) {
        super(name, permission, aliases);
        this.server = server;
    }

    public SwitchCMD(String name, String server) {
        super(name);
        this.server = server;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer))return;
        ProxiedPlayer player = (ProxiedPlayer) sender;
        ServerInfo serverInfo = ProxyServer.getInstance().getServerInfo(server);
        if(serverInfo == null){
            player.sendMessage(new TextComponent(ServerSwitcher.getPrefix() + "§cThe Server " + server + " doesn't exist"));
            return;
        }
        if(serverInfo.getPlayers().contains(player)){
            player.sendMessage(new TextComponent(ServerSwitcher.getPrefix() + "§cYou already connected to " + serverInfo.getName()));
            return;
        }
        player.connect(serverInfo);
        player.sendMessage(new TextComponent(ServerSwitcher.getPrefix() + "You have been sent to " + serverInfo.getName() + " server"));
    }
}
