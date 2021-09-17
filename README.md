# WildStacker Hook Addon
[![Discord](https://img.shields.io/discord/272499714048524288.svg?logo=discord)](https://discord.bentobox.world)
[![Build Status](https://ci.codemc.io/buildStatus/icon?job=BONNePlayground/WildStackerHook)](https://ci.codemc.io/job/BONNePlayground/job/WildStackerHook/)

This is WildStackerHook Addon for BentoBox plugin.  

## How to install

1. Place the addon jar in the addons folder of the BentoBox plugin.
2. Start the server.

## Configuration

This addon introduces multiple flags which allows to customize WildStacker plugin for each island.
Added flags:

    - WILD_STACKER_GUI: Island Protection flag allows island owner to disable/enable access to WildStacker GUI's.
    - WILD_STACKER: Island Setting flag that allows to toggle all stackings with one button.
        - WILD_STACKER_ITEMS: Island Settings flag that allows toggle item stacking on island. 
        - WILD_STACKER_BLOCKS: Island Settings flag that allows toggle barrel stacking on island. 
        - WILD_STACKER_ENTITIES: Island Settings flag that allows toggle entity stacking on island. 
        - WILD_STACKER_SPAWNERS: Island Settings flag that allows toggle spawner stacking on island. 

## Compatibility

- [x] BentoBox 1.17
- [x] Spigot 1.17
- [x] [WildStacker](https://www.spigotmc.org/resources/60648/)

Addon is not compatible with Older BentoBox and Spigot version. It requires Java 16+.
