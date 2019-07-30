package za.co.simm.treefall_spigot.treefall_spigot;

import org.bukkit.plugin.java.JavaPlugin;

public final class Treefall_spigot extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[Treefall] Enabling the plugin");
        new Treefall_spigotBlockListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[Treefall] Disabling the plugin");
    }
}
