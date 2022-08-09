package de.cubeattack.serverswitcher;

import de.cubeattack.serverswitcher.command.SwitchCMD;
import de.cubeattack.serverswitcher.listener.CommandListener;
import de.cubeattack.serverswitcher.listener.TabCompleter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import org.bstats.bungeecord.Metrics;

import java.util.HashMap;

public final class ServerSwitcher extends Plugin {

    private static final int id = 15863;
    private static HashMap<String, String> serverCommandList = new HashMap<>();
    private static Metrics metric;
    private static FileUtils fileUtils;
    private static ServerSwitcher plugin;
    private static String prefix;
    private static String lobbyServer;
    private static String switchPermission;


    @Override
    public void onEnable() {
        plugin = this;
        metric = new Metrics(this, 15863);
        fileUtils = new FileUtils(this, getResourceAsStream("config.yml"), "config", "yml");

        PluginManager pm = this.getProxy().getPluginManager();
        pm.registerListener(this, new CommandListener(this));
        pm.registerListener(this, new TabCompleter());

        pm.registerCommand(this, new SwitchCMD("lobby", ServerSwitcher.lobbyServer,null , "hub"));
        for (String s: serverCommandList.keySet()) {
            pm.registerCommand(this, new SwitchCMD(s, serverCommandList.get(s)));
        }

        log("Plugin was enabled successfully by CubeAttack!");
    }

    @Override
    public void onDisable() {

    }

    public void log(String messages){
        this.getLogger().info( messages);
    }

    public static ServerSwitcher getPlugin() {
        return plugin;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getLobbyServer() {
        return lobbyServer;
    }

    public static String getSwitchPermission() {
        return switchPermission;
    }

    public static void setPrefix(String prefix) {
        ServerSwitcher.prefix = prefix;
    }

    public static void setLobbyServer(String lobbyServer) {
        ServerSwitcher.lobbyServer = lobbyServer;
    }

    public static void setSwitchPermission(String switchPermission) {
        ServerSwitcher.switchPermission = switchPermission;
    }

    public static void addServerCommandList(String command, String server) {
        ServerSwitcher.serverCommandList.put(command, server);
    }
}
