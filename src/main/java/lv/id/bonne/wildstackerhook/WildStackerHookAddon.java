package lv.id.bonne.wildstackerhook;

import org.bukkit.Material;

import lv.id.bonne.wildstackerhook.events.WildStackerListener;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.api.flags.clicklisteners.CycleClick;
import world.bentobox.bentobox.managers.RanksManager;
import lv.id.bonne.wildstackerhook.events.BlockInteractListener;


/**
 * This class inits WildStackerHook addon.
 */
public final class WildStackerHookAddon extends Addon
{
    /**
     * Executes code when loading the addon. This is called before {@link #onEnable()}.
     * This <b>must</b> be used to setup configuration, worlds and commands.
     */
    public void onLoad()
    {
    }


    /**
     * Executes code when enabling the addon. This is called after {@link #onLoad()}.
     * <br/> Note that commands and worlds registration <b>must</b> be done in {@link
     * #onLoad()}, if need be. Failure to do so <b>will</b> result in issues such as
     * tab-completion not working for commands.
     */
    public void onEnable()
    {
        // Check if it is enabled - it might be loaded, but not enabled.
        if (this.getPlugin() == null || !this.getPlugin().isEnabled())
        {
            this.logError("BentoBox is not available or disabled!");
            this.setState(State.DISABLED);
            return;
        }

        // Check if RoseStacker exists.
        if (!this.getServer().getPluginManager().isPluginEnabled("WildStacker"))
        {
            this.logError("WildStacker is not available or disabled!");
            this.setState(State.DISABLED);
            return;
        }

        // Register listener
        this.registerListener(new BlockInteractListener());
        this.registerListener(new WildStackerListener());
        this.registerFlag(WILD_STACKER_GUI);
        this.registerFlag(WILD_STACKER_BARREL_ACCESS);
        this.registerFlag(WILD_STACKER_SPAWNER_ACCESS);
        this.registerFlag(WILD_STACKER);
        this.registerFlag(WILD_STACKER_ENTITIES);
        this.registerFlag(WILD_STACKER_ITEMS);
        this.registerFlag(WILD_STACKER_BLOCKS);
        this.registerFlag(WILD_STACKER_SPAWNERS);
    }


    /**
     * Executes code when disabling the addon.
     */
    public void onDisable()
    {
    }


    /**
     * This flag allows to change who have access to WildStackerGUI option. Owner can change it from
     * visitor rank till owner rank. Default value is set to member.
     */
    public final static Flag WILD_STACKER_GUI =
        new Flag.Builder("WILD_STACKER_GUI", Material.WRITTEN_BOOK).
            type(Flag.Type.PROTECTION).
            defaultRank(RanksManager.MEMBER_RANK).
            clickHandler(new CycleClick("WILD_STACKER_GUI",
                RanksManager.VISITOR_RANK,
                RanksManager.OWNER_RANK)).
            build();

    /**
     * This flag allows to change who have access to WildStacker barrel items. Owner can change it from
     * visitor rank till owner rank. Default value is set to member.
     */
    public final static Flag WILD_STACKER_BARREL_ACCESS =
        new Flag.Builder("WILD_STACKER_BARREL_ACCESS", Material.BARREL).
            type(Flag.Type.PROTECTION).
            defaultRank(RanksManager.MEMBER_RANK).
            clickHandler(new CycleClick("WILD_STACKER_BARREL_ACCESS",
                RanksManager.VISITOR_RANK,
                RanksManager.OWNER_RANK)).
            build();

    /**
     * This flag allows to change who have access to WildStacker spawner items. Owner can change it from
     * visitor rank till owner rank. Default value is set to member.
     */
    public final static Flag WILD_STACKER_SPAWNER_ACCESS =
        new Flag.Builder("WILD_STACKER_SPAWNER_ACCESS", Material.SPAWNER).
            type(Flag.Type.PROTECTION).
            defaultRank(RanksManager.MEMBER_RANK).
            clickHandler(new CycleClick("WILD_STACKER_SPAWNER_ACCESS",
                RanksManager.VISITOR_RANK,
                RanksManager.OWNER_RANK)).
            build();

    /**
     * This flag allows toggling block stacking.
     */
    public final static Flag WILD_STACKER_BLOCKS =
        new Flag.Builder("WILD_STACKER_BLOCKS", Material.CAULDRON).
            type(Flag.Type.SETTING).
            defaultSetting(true).
            build();

    /**
     * This flag allows toggling item stacking.
     */
    public final static Flag WILD_STACKER_ITEMS =
        new Flag.Builder("WILD_STACKER_ITEMS", Material.POPPY).
            type(Flag.Type.SETTING).
            defaultSetting(true).
            build();

    /**
     * This flag allows toggling entity stacking.
     */
    public final static Flag WILD_STACKER_ENTITIES =
        new Flag.Builder("WILD_STACKER_ENTITIES", Material.SKELETON_SKULL).
            type(Flag.Type.SETTING).
            defaultSetting(true).
            build();

    /**
     * This flag allows toggling spawner stacking.
     */
    public final static Flag WILD_STACKER_SPAWNERS =
        new Flag.Builder("WILD_STACKER_SPAWNERS", Material.SPAWNER).
            type(Flag.Type.SETTING).
            defaultSetting(true).
            build();

    /**
     * This flag allows toggling all stacking with one button.
     */
    public static final Flag WILD_STACKER =
        new Flag.Builder("WILD_STACKER", Material.CAULDRON).
            mode(Flag.Mode.BASIC).
            type(Flag.Type.SETTING).
            defaultSetting(true).
            subflags(WILD_STACKER_BLOCKS, WILD_STACKER_SPAWNERS, WILD_STACKER_ENTITIES, WILD_STACKER_ITEMS).
            build();
}
