import mods.armoreablemobs.ArmorGroup;

//Disarm right handed strays
new ArmorGroup("stray_1")
.inSlot(<constant:minecraft:equipmentslot:chest>, <item:minecraft:iron_chestplate>)
.inSlot(<constant:minecraft:equipmentslot:mainhand>, <item:minecraft:air>)
.setWeight(100.0)
.register(<entitytype:minecraft:stray>);

//Always arm skeletons in the right hand with a diamond sword and no extra armor
//This disables any chance of them spawning with any armor in any spot.
new ArmorGroup("skeleton_1")
.inSlot(<constant:minecraft:equipmentslot:head>, <item:minecraft:air>)
.inSlot(<constant:minecraft:equipmentslot:chest>, <item:minecraft:air>)
.inSlot(<constant:minecraft:equipmentslot:legs>, <item:minecraft:air>)
.inSlot(<constant:minecraft:equipmentslot:feet>, <item:minecraft:air>)
.inSlot(<constant:minecraft:equipmentslot:mainhand>, <item:minecraft:diamond_sword>)
.inSlot(<constant:minecraft:equipmentslot:offhand>, <item:minecraft:air>)
.setWeight(100.0)
.register(<entitytype:minecraft:skeleton>);


//Adds an ArmorGroup for zombies to spawn with full iron armor
new ArmorGroup("zombie_test_1")
.inSlot(<constant:minecraft:equipmentslot:head>, <item:minecraft:iron_helmet>)
.inSlot(<constant:minecraft:equipmentslot:chest>, <item:minecraft:iron_chestplate>)
.inSlot(<constant:minecraft:equipmentslot:legs>, <item:minecraft:iron_leggings>)
.inSlot(<constant:minecraft:equipmentslot:feet>, <item:minecraft:iron_boots>)
.setWeight(10.0)
.register(<entitytype:minecraft:zombie>);


//Adds an ArmorGroup for zombies to spawn with full netherite armor
new ArmorGroup("zombie_test_2")
.inSlot(<constant:minecraft:equipmentslot:head>, <item:minecraft:netherite_helmet>)
.inSlot(<constant:minecraft:equipmentslot:chest>, <item:minecraft:netherite_chestplate>)
.inSlot(<constant:minecraft:equipmentslot:legs>, <item:minecraft:netherite_leggings>)
.inSlot(<constant:minecraft:equipmentslot:feet>, <item:minecraft:netherite_boots>)
.setWeight(1.0)
.register(<entitytype:minecraft:zombie>);

//The above ArmorGroup combination results in one out of eleven zombies spawning equipped with netherite


