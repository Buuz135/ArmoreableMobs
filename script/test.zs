import mods.armoreablemobs.ArmorHandler;
import mods.armoreablemobs.ArmorEntity;
import mods.armoreablemobs.ArmorSlot;
import mods.armoreablemobs.ArmorGroup;

var group = ArmorHandler.createArmorGroup("testGroup", 0.5);
var entity = ArmorHandler.createArmorEntity("minecraft:zombie").withNBTCheck("Health", 10.0, "GREATER");
var armor = ArmorHandler.createArmorSlot("head", <minecraft:diamond_helmet>, 1, 0.5);
group.addEntity(entity);
group.addArmor(armor);
