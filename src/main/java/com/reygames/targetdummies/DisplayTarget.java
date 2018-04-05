package com.reygames.targetdummies;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class DisplayTarget {

    public static Entity initEntity(Block b) {
        Zombie entity = (Zombie) getEntity(b);

        if (entity == null) {
            entity = (Zombie) b.getWorld().spawnEntity(b.getLocation().add(0.5, 0.5, 0.5), EntityType.ZOMBIE);
            entity.setVelocity(new Vector(0, 0, 0));
            entity.setCustomName("HIT ME");
            entity.setCustomNameVisible(true);
            entity.setGravity(false);
            entity.setAI(false);
            entity.setFireTicks(0);
            entity.setBaby(false);
            entity.setCanPickupItems(false);
            entity.setMaxHealth(9999999999.0);

            entity.getEquipment().setHelmet(new ItemStack(Material.STONE_BUTTON, 1));

            entity.setMetadata("TargetDummyEntity", new FixedMetadataValue(TargetDummies.getPlugin(), true));
        }

        return entity;
    }

    public static void removeEntity(Block b) {
        Entity entity = getEntity(b);
        if (entity != null) {
            getEntity(b).remove();
        }
    }

    public static Entity getEntity(Block b) {
        for (Entity n : b.getChunk().getEntities()) {
            if (n instanceof Zombie) {
                if (b.getLocation().add(0.5, 0.5, 0.5).distanceSquared(n.getLocation()) < 1D && (n.getCustomName().startsWith("Damage: ") || n.getCustomName().startsWith("HIT ME")))
                    return n;
            }
        }
        return null;
    }
}
