import com.armoreablemobs.ArmorHandler;
import com.armoreablemobs.ArmorEntity;

var group = ArmorHandler.createArmorGroup("testGroup", 0.4);
var entity = ArmorEntity.createArmorEntity("minecraft:zombie").withNBTCheck("Health", 10.0, "GREATER");
group.addEntity(entity);