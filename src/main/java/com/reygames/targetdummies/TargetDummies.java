package com.reygames.targetdummies;

import me.mrCookieSlime.CSCoreLibPlugin.PluginUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.Categories;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Lists.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.Category;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import com.reygames.pearlib.api.configs.MultipleConfigs;

public final class TargetDummies extends JavaPlugin {

    public static JavaPlugin plugin;

    MultipleConfigs configs;
    FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        PluginUtils utils = new PluginUtils(plugin);
        utils.setupMetrics();
        // utils.setupUpdater(/* ID on curseforge */, getFile());

        configs = new MultipleConfigs(plugin);
        config = configs.create("config");


        setup();
        getLogger().info("TargetDummies v" + getDescription().getVersion() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void setup() {
        Category category = Categories.MAGIC;

        ItemStack TARGET_DUMMY1 = new CustomItem(new ItemStack(Material.STONE_SLAB2), "TARGET_DUMMY_1", "", "Hit me to see how much damage you do");

        new TargetDummy(
            category,
            TARGET_DUMMY1,
            "TARGET_DUMMY_1",
            RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                new ItemStack(Material.AIR), new ItemStack(Material.HAY_BLOCK), new ItemStack(Material.AIR),
                SlimefunItems.CLOTH, SlimefunItems.MAGIC_LUMP_2, SlimefunItems.CLOTH,
                SlimefunItems.CLOTH, new ItemStack(Material.STONE_SLAB2), SlimefunItems.CLOTH
            }
        ).register();
    }

    public static final JavaPlugin getPlugin() {
        return plugin;
    }
}
