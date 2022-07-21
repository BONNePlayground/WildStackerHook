//
// Created by BONNe
// Copyright - 2021
//


package lv.id.bonne.wildstackerhook.events;


import com.bgsoftware.wildstacker.api.events.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import lv.id.bonne.wildstackerhook.WildStackerHookAddon;
import world.bentobox.bentobox.api.flags.FlagListener;


/**
 * This listener checks if WildStacker should stack objects based on island settings.
 */
public class WildStackerListener extends FlagListener
{
    /**
     * Check if Blocks can be stacked.
     *
     * @param event BlockStackEvent.
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockStackingPlace(BarrelPlaceEvent event)
    {
        // Cancel stacking if BlockStacking is disabled in island settings.
        if (!this.checkIsland(event,
            null,
            event.getBarrel().getLocation(),
            WildStackerHookAddon.WILD_STACKER_BLOCKS))
        {
            this.noGo(event, WildStackerHookAddon.WILD_STACKER_BLOCKS);
        }
    }


    /**
     * Check if Blocks can be stacked.
     *
     * @param event BlockStackEvent.
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBlockStackingStack(BarrelStackEvent event)
    {
        // Cancel stacking if BlockStacking is disabled in island settings.
        if (!this.checkIsland(event,
            null,
            event.getBarrel().getLocation(),
            WildStackerHookAddon.WILD_STACKER_BLOCKS))
        {
            this.noGo(event, WildStackerHookAddon.WILD_STACKER_BLOCKS);
        }
    }


    /**
     * Check if Items can be stacked.
     *
     * @param event the event
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onItemStacking(ItemStackEvent event)
    {
        // Cancel stacking if ItemStacking is disabled in island settings.
        event.setCancelled(!this.checkIsland(event,
            null,
            event.getItem().getLocation(),
            WildStackerHookAddon.WILD_STACKER_ITEMS));
    }


    /**
     * Check if Entities can be stacked.
     *
     * @param event the event
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntityStacking(EntityStackEvent event)
    {
        // Cancel stacking if ItemStacking is disabled in island settings.
        event.setCancelled(!this.checkIsland(event,
            null,
            event.getEntity().getLocation(),
            WildStackerHookAddon.WILD_STACKER_ENTITIES));
    }


    /**
     * Check if Entities can be stacked.
     *
     * @param event the event
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onEntitySpawnStacking(SpawnerStackedEntitySpawnEvent event)
    {
        if (event.shouldBeStacked())
        {
            // Disable stacked entity spawning if it is disabled.
            event.setShouldBeStacked(this.checkIsland(event,
                null,
                event.getSpawner().getLocation(),
                WildStackerHookAddon.WILD_STACKER_ENTITIES));
        }
    }


    /**
     * Check if Spawners can be stacked.
     *
     * @param event the event
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSpawnerStackingPlace(SpawnerPlaceEvent event)
    {
        // Cancel stacking if ItemStacking is disabled in island settings.
        if (!this.checkIsland(event,
            null,
            event.getSpawner().getLocation(),
            WildStackerHookAddon.WILD_STACKER_SPAWNERS))
        {
            this.noGo(event, WildStackerHookAddon.WILD_STACKER_SPAWNERS);
        }
    }


    /**
     * Check if Spawners can be stacked.
     *
     * @param event the event
     */
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onSpawnerStacking(SpawnerStackEvent event)
    {
        // Cancel stacking if ItemStacking is disabled in island settings.
        if (!this.checkIsland(event,
            null,
            event.getSpawner().getLocation(),
            WildStackerHookAddon.WILD_STACKER_SPAWNERS))
        {
            this.noGo(event, WildStackerHookAddon.WILD_STACKER_SPAWNERS);
        }
    }
}
