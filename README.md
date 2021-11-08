#Armoreable Mobs

A mod that allows you to give armor on spawn to living mobs.
Methods can be found in the Crafttweaker documentation page(LINK).


#Example

```zenscript
import mods.armoreablemods.ArmorGroup;

new ArmorGroup("zombie_test_1")
.inSlot(<equipmentslottype:head>, <item:minecraft:iron_helmet>.withTag({RepairCost: 1 as int, Enchantments: [{lvl: 1 as short, id: "minecraft:mending" as string}]}))
.inSlot(<equipmentslottype:chest>, <item:minecraft:iron_chestplate>.withTag({RepairCost: 1 as int, Enchantments: [{lvl: 1 as short, id: "minecraft:mending" as string}]}))
.inSlot(<equipmentslottype:legs>, <item:minecraft:iron_leggings>.withTag({RepairCost: 1 as int, Enchantments: [{lvl: 1 as short, id: "minecraft:mending" as string}]}))
.inSlot(<equipmentslottype:feet>, <item:minecraft:iron_boots>.withTag({RepairCost: 1 as int, Enchantments: [{lvl: 1 as short, id: "minecraft:mending" as string}]}))
.addStages("start")
.setWeight(10.0)
.register(<entitytype:minecraft:zombie>);

new ArmorGroup("zombie_test_2")
.inSlot(<equipmentslottype:head>, <item:minecraft:diamond_helmet>.withTag({RepairCost: 1 as int, Enchantments: [{lvl: 1 as short, id: "minecraft:mending" as string}]}))
.inSlot(<equipmentslottype:chest>, <item:minecraft:diamond_chestplate>.withTag({RepairCost: 1 as int, Enchantments: [{lvl: 1 as short, id: "minecraft:mending" as string}]}))
.inSlot(<equipmentslottype:legs>, <item:minecraft:diamond_leggings>.withTag({RepairCost: 1 as int, Enchantments: [{lvl: 1 as short, id: "minecraft:mending" as string}]}))
.inSlot(<equipmentslottype:feet>, <item:minecraft:diamond_boots>.withTag({RepairCost: 1 as int, Enchantments: [{lvl: 1 as short, id: "minecraft:mending" as string}]}))
.addStages("start")
.setWeight(1.0)
.register(<entitytype:minecraft:zombie>);
```