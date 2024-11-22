# CansteinSupportUtils

A plugin designed to simplify managing player deaths by saving essential data and providing tools for restoration and review.

## Features

- **Automatic Data Saving**: Inventory, location, and experience levels are saved upon death
- **Death History**: View the last 10 deaths, complete with timestamps.
- **Restoration Tools**: Reset inventory and levels, review saved inventory, or teleport to the exact death location.

## Commands

- `/deathHistory <offlinePlayer>`: Shows the last ten deaths with timestamp
- `/tpdeath <player> <timestamp>`: Teleports you to the death of the given timestamp
- `/showInventory <player> <timestamp>`: Display the inventory of the given timestamp
- `/resetInventory <player> <timestamp>`: Resets the inventory and the level

## Permissions

```properties
# Commands
cansteinsupportutils.deathHistory
cansteinsupportutils.tpDeath
cansteinsupportutils.inventoryShow
cansteinsupportutils.inventoryReset
```
