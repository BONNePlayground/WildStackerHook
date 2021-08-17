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
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if (event.getClickedBlock() == null)
        {
            // Ignore clicking in air.
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

        if (WildStackerAPI.getStackedBarrel(event.getClickedBlock()) == null)
        {
            // Ignore non-stacked blocks
            return;
        }

        if (event.getClickedBlock().getType() == Material.SPAWNER &&
            WildStackerAPI.getStackedSpawner((CreatureSpawner) event.getClickedBlock()) == null)
        {
            // Ignore non-stacked spawners.
            return;
        }

        // Use BentoBox flag processing system to validate usage.
        // Technically not necessary as internally it should be cancelled by BentoBox.
        event.setCancelled(!this.checkIsland(event, player, player.getLocation(), WildStackerHookAddon.WILD_STACKER_GUI));
    }
}
