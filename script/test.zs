import mods.armoreablemobs.ArmorHandler;
import mods.armoreablemobs.ArmorEntity;
import mods.armoreablemobs.ArmorSlot;
import mods.armoreablemobs.ArmorGroup;

var group = ArmorHandler.createArmorGroup("draconic", 0.5);
var entity = ArmorHandler.createArmorEntity("minecraft:zombie").withNBTCheck("Health", 10.0, "GREATER");
group.addEntity(entity);
group.addArmor(ArmorHandler.createArmorSlot("head", <draconicevolution:draconic_helm>.withTag({Energy: 16000000}), 1, 0.5));
group.addArmor(ArmorHandler.createArmorSlot("chest", <draconicevolution:draconic_chest>.withTag({Energy: 16000000}), 1, 0.5));
group.addArmor(ArmorHandler.createArmorSlot("legs", <draconicevolution:draconic_legs>.withTag({Energy: 16000000}), 1, 0.5));
group.addArmor(ArmorHandler.createArmorSlot("feet", <draconicevolution:draconic_boots>.withTag({Energy: 16000000}), 1, 0.5));
group.addArmor(ArmorHandler.createArmorSlot("mainhand", <draconicevolution:draconic_sword>.withTag({Energy: 16000000}), 1, 0.5));
