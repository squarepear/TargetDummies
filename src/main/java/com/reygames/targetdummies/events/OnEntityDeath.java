package com.reygames.targetdummies.events;

import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class OnEntityDeath implements Listener {

    @EventHandler
    public void OnEntityDeath(EntityDeathEvent e) {

        if (e.getEntity() instanceof Zombie && e.getEntity().hasMetadata("TargetDummyEntity")) {

            e.getEntity().setCustomName(null);
            e.getEntity().setCustomNameVisible(false);
            e.getDrops().clear();
        }
    }
}
