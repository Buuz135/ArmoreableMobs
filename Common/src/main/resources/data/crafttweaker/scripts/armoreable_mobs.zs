import mods.armoreablemobs.ArmorGroup;


//Adds an ArmorGroup for zombies to spawn with full iron armor
new ArmorGroup("zombie_test_1")
.inSlot(<constant:minecraft:equipmentslot:head>, <item:minecraft:iron_helmet>)
.inSlot(<constant:minecraft:equipmentslot:chest>, <item:minecraft:iron_chestplate>)
.inSlot(<constant:minecraft:equipmentslot:legs>, <item:minecraft:iron_leggings>)
.inSlot(<constant:minecraft:equipmentslot:feet>, <item:minecraft:iron_boots>)
.setWeight(10.0)
.register(<entitytype:minecraft:zombie>);

ArmorGroup.overrideExistingArmor(<entitytype:minecraft:zombie>, {<constant:minecraft:equipmentslot:chest> : <item:minecraft:netherite_chestplate>, <constant:minecraft:equipmentslot:mainhand> : <item:minecraft:netherite_sword>}, <blockstate:minecraft:sand>);

//Adds an ArmorGroup for zombies to spawn with full netherite armor
new ArmorGroup("zombie_test_2")
.inSlot(<constant:minecraft:equipmentslot:head>, <item:minecraft:netherite_helmet>)
.inSlot(<constant:minecraft:equipmentslot:chest>, <item:minecraft:netherite_chestplate>)
.inSlot(<constant:minecraft:equipmentslot:legs>, <item:minecraft:netherite_leggings>)
.inSlot(<constant:minecraft:equipmentslot:feet>, <item:minecraft:netherite_boots>)
.setWeight(1.0)
.addStages("yeet", "start")
.register(<entitytype:minecraft:zombie>);

new ArmorGroup("horse_showcase")
.inSlot(<constant:minecraft:equipmentslot:chest>, <item:minecraft:diamond_horse_armor>)
.setWeight(2.0)
.register(<entitytype:minecraft:horse>);