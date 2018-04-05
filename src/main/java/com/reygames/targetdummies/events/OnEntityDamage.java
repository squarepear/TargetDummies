package com.reygames.targetdummies.events;

import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnEntityDamage implements Listener {

    @EventHandler
    public void OnEntityDamage(EntityDamageEvent e) {

        if (e.getEntity() instanceof Zombie && e.getEntity().hasMetadata("TargetDummyEntity")) {

            Zombie entity = (Zombie) e.getEntity();

            entity.setCustomName("Damage: " + Math.round(e.getDamage() * 10)/10);

            entity.setFireTicks(0);
            entity.setHealth(entity.getMaxHealth());
        }
    }
}
