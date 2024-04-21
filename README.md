# CansteinSupportUtils

a plugin to better help Levin with his deaths.

## Features

- Automatic saving of inventory, location and levels on death
- View of the last 10 deaths
- Resetting the respective inventory and leveling, viewing the inventory and teleporting to the point of death

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