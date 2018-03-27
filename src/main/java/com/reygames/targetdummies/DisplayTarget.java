package com.reygames.targetdummies;

import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

public class DisplayTarget {
    public static void updateDisplayTarget(Block b) {
        Zombie entity = getEntity(b);
        if (entity == null) {
            entity = (Zombie) b.getWorld().spawnEntity(b.getLocation().add(0.5, 0.5, 0.5), EntityType.ZOMBIE);
            entity.setVelocity(new Vector(0, 0, 0));
            entity.setCustomNameVisible(true);
            entity.setGravity(false);
            entity.setAI(false);
            entity.setFireTicks(0);
            entity.setBaby(false);
            entity.setCanPickupItems(false);
            entity.setMaxHealth(9999999999.0);
            entity.setMetadata("location", new FixedMetadataValue(TargetDummies.getPlugin(), b.getLocation().toString()));
        }

        EntityDamageEvent damageEvent = entity.getLastDamageCause();

        if (damageEvent != null) {
            entity.setCustomName("Damage: " + Math.round(damageEvent.getDamage() * 10)/10);
        } else {
            entity.setCustomName("HIT ME");
        }

        entity.setFireTicks(0);
        entity.setHealth(entity.getMaxHealth());
    }

    public static void removeEntity(Block b) {
        for (Entity n : b.getChunk().getEntities()) {
            if (n instanceof Zombie) {
                if (b.getLocation().add(0.5, 0.5, 0.5).distanceSquared(n.getLocation()) < 1D && (((Zombie) n).getCustomName().startsWith("Damage: ") || ((Zombie) n).getCustomName().startsWith("HIT ME")) && n.getMetadata("location").equals(b.getLocation().toString()))
                    n.remove();
            }
        }
    }

    private static Zombie getEntity(Block b) {
        for (Entity n : b.getChunk().getEntities()) {
            if (n instanceof Zombie) {
                if (b.getLocation().add(0.5, 0.5, 0.5).distanceSquared(n.getLocation()) < 1D && (((Zombie) n).getCustomName().startsWith("Damage: ") || ((Zombie) n).getCustomName().startsWith("HIT ME")))
                    return (Zombie) n;
            }
        }
        return null;
    }
}
