package com.qingyunxi.hotbarfix;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class HotbarFix extends JavaPlugin implements Listener, CommandExecutor {

    private int defaultSlot;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();

        getServer().getPluginManager().registerEvents(this, this);
        getCommand("hotbarfix").setExecutor(this);
        getLogger().info("HotbarFix enabled — default hotbar slot: " + defaultSlot);
    }

    private void loadConfig() {
        reloadConfig();
        defaultSlot = getConfig().getInt("default-slot", 4);

        if (defaultSlot < 0 || defaultSlot > 8) {
            getLogger().warning("default-slot must be 0-8, resetting to 4");
            defaultSlot = 4;
            getConfig().set("default-slot", 4);
            saveConfig();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("hotbarfix.reload")) {
                sender.sendMessage(Component.text("你没有权限执行此命令 / No permission.", NamedTextColor.RED));
                return true;
            }
            loadConfig();
            sender.sendMessage(Component.text("HotbarFix 配置已重载 / Config reloaded. default-slot: " + defaultSlot, NamedTextColor.GREEN));
            return true;
        }
        sender.sendMessage(Component.text("HotbarFix v" + getDescription().getVersion(), NamedTextColor.YELLOW));
        sender.sendMessage(Component.text("/hotbarfix reload — 重载配置 / Reload config", NamedTextColor.GRAY));
        return true;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int slot = this.defaultSlot;

        getServer().getScheduler().runTaskLater(this, () -> {
            if (player.isOnline()) {
                player.getInventory().setHeldItemSlot(slot);
            }
        }, 2L);
    }
}
