package lv.id.bonne.wildstackerhook.events;


import com.bgsoftware.wildstacker.api.WildStackerAPI;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import lv.id.bonne.wildstackerhook.WildStackerHookAddon;
import world.bentobox.bentobox.api.flags.FlagListener;


/**
 * This listener checks every player interaction event and checks if player is shift+right-clicking
 * on the stacked block. In such situation, it cancels interaction if player rank does not allow that,
 * because it assumes that player will be able to see WildStackerGUI.
 */
public class BlockInteractListener extends FlagListener
{
    /**
     * This is just an GUI protector.
     * @param event PlayerInteractEvent that is triggered.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerGUIInteract(PlayerInteractEvent event)
    {
        if (event.getClickedBlock() == null)
        {
            // Ignore clicking in air.
            return;
        }

        if (event.getClickedBlock().getType() != Material.CAULDRON &&
            event.getClickedBlock().getType() != Material.SPAWNER)
        {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
        {
            // Ignore non-right click actions.
            return;
        }

        Player player = event.getPlayer();

        if (!player.isSneaking())
        {
            // WildStacker GUI opens only on sneaking. Weird.
            return;
        }

        if (event.getClickedBlock().getType() == Material.CAULDRON &&
            (WildStackerAPI.getStackedBarrel(event.getClickedBlock()) == null ||
                WildStackerAPI.getStackedBarrel(event.getClickedBlock()).getDisplayBlock() == null))
        {
            // Ignore non-stacked blocks
            return;
        }

        if (event.getClickedBlock().getType() == Material.SPAWNER &&
            WildStackerAPI.getStackedSpawner((CreatureSpawner) event.getClickedBlock().getState()) == null)
        {
            // Ignore non-stacked spawners.
            return;
        }

        // Use BentoBox flag processing system to validate usage.
        // Technically not necessary as internally it should be cancelled by BentoBox.
        event.setCancelled(!this.checkIsland(event, player, player.getLocation(), WildStackerHookAddon.WILD_STACKER_GUI));
    }


    /**
     * This is just a Barrel protector.
     * @param event PlayerInteractEvent that is triggered.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerBarrelInteract(PlayerInteractEvent event)
    {
        if (event.getClickedBlock() == null)
        {
            // Ignore clicking in air.
            return;
        }

        if (event.getClickedBlock().getType() != Material.CAULDRON)
        {
            // Ignore clicking on stacked spawner
            return;
        }

        if (WildStackerAPI.getStackedBarrel(event.getClickedBlock()) == null ||
            WildStackerAPI.getStackedBarrel(event.getClickedBlock()).getDisplayBlock() == null)
        {
            // Ignore non-stacked blocks
            return;
        }

        Player player = event.getPlayer();

        // Use BentoBox flag processing system to validate usage.
        // Technically not necessary as internally it should be cancelled by BentoBox.
        event.setCancelled(!this.checkIsland(event, player, player.getLocation(), WildStackerHookAddon.WILD_STACKER_BARREL_ACCESS));
    }


    /**
     * This is just a Spawner protector.
     * @param event PlayerInteractEvent that is triggered.
     */
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerSpawnerInteract(PlayerInteractEvent event)
    {
        if (event.getClickedBlock() == null)
        {
            // Ignore clicking in air.
            return;
        }

        if (event.getClickedBlock().getType() != Material.SPAWNER)
        {
            // Ignore non-spawner clicks.
            return;
        }

        if (WildStackerAPI.getStackedBarrel(event.getClickedBlock()) == null)
        {
            // Ignore non-stacked blocks
            return;
        }

        Player player = event.getPlayer();

        // Use BentoBox flag processing system to validate usage.
        // Technically not necessary as internally it should be cancelled by BentoBox.
        event.setCancelled(!this.checkIsland(event, player, player.getLocation(), WildStackerHookAddon.WILD_STACKER_SPAWNER_ACCESS));
    }
}
