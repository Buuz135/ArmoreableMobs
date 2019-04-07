import mods.armoreablemobs.ArmorHandler;
import mods.armoreablemobs.ArmorEntity;
import mods.armoreablemobs.ArmorSlot;
import mods.armoreablemobs.ArmorGroup;

var group = ArmorHandler.createArmorGroup("miner", 1);
var entity = ArmorHandler.createArmorEntity("minecraft:enderman");
group.addEntity(entity);
group.addArmor(ArmorHandler.createArmorSlot("head", <minecraft:leather_helmet>.withTag({display: {color: 16383998}}).setCanReplace(false), 1, 0));
group.addArmor(ArmorHandler.createArmorSlot("chest", <minecraft:leather_chestplate>.withTag({display: {color: 10133972}}), 1, 0));
group.addArmor(ArmorHandler.createArmorSlot("legs", <minecraft:leather_leggings>.withTag({display: {color: 10133972}}), 1, 0));
group.addArmor(ArmorHandler.createArmorSlot("feet", <minecraft:leather_boots>.withTag({display: {color: 16318205}}), 1, 0));
group.addArmor(ArmorHandler.createArmorSlot("mainhand", <minecraft:iron_pickaxe>, 1, 0));
group.addArmor(ArmorHandler.createArmorSlot("offhand", <minecraft:minecart>, 1, 0.5));

var group2 = ArmorHandler.createArmorGroup("builder", 1);
var entity2 = ArmorHandler.createArmorEntity("minecraft:skeleton");
group2.addEntity(entity2);
group2.addArmor(ArmorHandler.createArmorSlot("head", <minecraft:leather_helmet>.withTag({display: {color: 16701501}}), 1, 0));
group2.addArmor(ArmorHandler.createArmorSlot("chest", <minecraft:leather_chestplate>.withTag({display: {color: 16701501}}), 1, 0));
group2.addArmor(ArmorHandler.createArmorSlot("legs", <minecraft:leather_leggings>.withTag({display: {color: 16701501}}), 1, 0));
group2.addArmor(ArmorHandler.createArmorSlot("feet", <minecraft:leather_boots>.withTag({display: {color: 16701501}}), 1, 0));
group2.addArmor(ArmorHandler.createArmorSlot("offhand", <minecraft:clock>, 1, 0));
group2.addArmor(ArmorHandler.createArmorSlot("mainhand", <minecraft:bow>, 1, 0));

var group3 = ArmorHandler.createArmorGroup("bomber", 1);
var entity3 = ArmorHandler.createArmorEntity("minecraft:creeper");
group3.addEntity(entity3);
group3.addArmor(ArmorHandler.createArmorSlot("offhand", <minecraft:redstone>, 1, 0.5));
group3.addArmor(ArmorHandler.createArmorSlot("mainhand",<minecraft:fire_charge>, 1, 0.5));

var group4 = ArmorHandler.createArmorGroup("teleporter", 1);
var entity4 = ArmorHandler.createArmorEntity("minecraft:wither_skeleton");
group4.addEntity(entity4);
group4.addArmor(ArmorHandler.createArmorSlot("mainhand", <minecraft:diamond_sword>, 1, 0.5));
group4.addArmor(ArmorHandler.createArmorSlot("offhand", <minecraft:jukebox>, 1, 0.5));

var group5 = ArmorHandler.createArmorGroup("climber", 1);
var entity5 = ArmorHandler.createArmorEntity("minecraft:spider");
group5.addEntity(entity5);
group5.addArmor(ArmorHandler.createArmorSlot("offhand", <minecraft:leather_helmet>.withTag({display: {color: 3949738}}), 1, 0.5));