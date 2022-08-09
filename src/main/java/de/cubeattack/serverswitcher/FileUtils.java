package de.cubeattack.serverswitcher;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;

import java.util.List;

public class FileUtils {

    private final ServerSwitcher serverSwitcher;
    private Configuration configuration;
    private final InputStream inputStream;
    private final String fileName;
    private final File file;

    public FileUtils(ServerSwitcher serverSwitcher, InputStream inputStream,  String fileName, String fileTyp) {
        this.fileName = fileName;
        this.inputStream = inputStream;
        this.serverSwitcher = serverSwitcher;
        this.file = new File("plugins/" + serverSwitcher.getDescription().getName() +  "/" + fileName + "." + fileTyp);
        copyToFile();
        loadFromFile();
    }

    private void copyToFile(){
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                Files.copy(inputStream, file.toPath());
                configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        }catch (IOException ex){
           serverSwitcher.getLogger().severe("Error whiles creating Config.yml : " + ex.getLocalizedMessage());
        }
    }

    private void loadFromFile(){
        ServerSwitcher.setPrefix(ChatColor.translateAlternateColorCodes("&".charAt(0), getString("Prefix")) + " ");
        ServerSwitcher.setLobbyServer(getString("LobbyServer"));
        ServerSwitcher.setSwitchPermission(getString("SwitchPermission"));

        for (String s : getConfig().getSection("Servers").getKeys()) {
            ServerSwitcher.addServerCommandList(s, getString("Servers." + s));
        }
    }

    public void set(String path, int value) {
        this.getConfig().set(path, value);
    }
    public void set(String path, String value) {
        this.getConfig().set(path, value);
    }
    public void set(String path, boolean value) {
        this.getConfig().set(path, value);
    }
    public void set(String path, ArrayList value) {
        this.getConfig().set(path, value);
    }


    public int getInt(String path) {
        return this.getConfig().getInt(path);
    }
    public boolean getBoolean(String path) {
        return this.getConfig().getBoolean(path);
    }
    public String getString(String path) {
        return this.getConfig().getString(path);
    }
    public double getDouble(String path) {
        return this.getConfig().getDouble(path);
    }
    public List<?> getList(String path) {
        return this.getConfig().getList(path);
    }
    public List<String> getListAsList(String path) {
        return this.getConfig().getStringList(path);
    }

    public int getInt(String path, int def) {
        return this.getConfig().getInt(path, def);
    }
    public boolean getBoolean(String path, boolean def) {
        return this.getConfig().getBoolean(path, def);
    }
    public String getString(String path, String def) {
        return this.getConfig().getString(path, def);
    }
    public double getDouble(String path, double def) {
        return this.getConfig().getDouble(path, def);
    }

    public Configuration getConfig() {
        return configuration;
    }
    public InputStream getInput() {
        return inputStream;
    }
    public String getFileName() {
        return fileName;
    }
    public File getFile() {
        return file;
    }
}
