package com.reygames.targetdummies;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunBlockHandler;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.UnregisterReason;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class TargetDummy extends SlimefunItem {

    public TargetDummy(Category category, ItemStack item, String name, RecipeType recipeType, final ItemStack[] recipe) {
        super(category, item, name, recipeType, recipe);

        registerBlockHandler(name, new SlimefunBlockHandler() {
            @Override
            public void onPlace(Player player, Block b, SlimefunItem slimefunItem) {
                BlockStorage.addBlockInfo(b, "owner", player.getUniqueId().toString());
                BlockStorage.addBlockInfo(b, "whitelist", " ");

                DisplayTarget.updateDisplayTarget(b);
                // DONT DO ANYTHING - Inventory is not yet loaded
            }

            @Override
            public boolean onBreak(Player player, Block b, SlimefunItem slimefunItem, UnregisterReason unregisterReason) {
                if (unregisterReason.equals(UnregisterReason.EXPLODE)) {
                    return false;
                } else if (unregisterReason.equals(UnregisterReason.PLAYER_BREAK)) {
                    if (!BlockStorage.getBlockInfo(b, "owner").equals(player.getUniqueId().toString()))
                        return false;
                }

                DisplayTarget.removeEntity(b);
                return true;
            }
        });
    }

    @Override
    public void register(boolean slimefun) {
        addItemHandler(new BlockTicker() {

            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void uniqueTick() {

            }

            @Override
            public void tick(Block b, SlimefunItem slimefunItem, Config config) {
                updateTargetDummy(b);
            }
        });

        super.register(false);
    }

    private void updateTargetDummy(Block b) {


        DisplayTarget.updateDisplayTarget(b);
    }

}
