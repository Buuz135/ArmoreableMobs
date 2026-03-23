package net.witixin.armoreablemobs.actions;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.witixin.armoreablemobs.ArmorGroup;
import net.witixin.armoreablemobs.ArmoreableMobsCommon;
import net.witixin.armoreablemobs.Utilities;

import java.util.List;

public class AddArmorGroupAction implements IUndoableAction {

    private final ArmorGroup group;
    private final EntityType<Entity> entityType;

    public AddArmorGroupAction(EntityType<Entity> entityType, ArmorGroup group) {
        this.entityType = entityType;
        this.group = group;
    }

    @Override
    public void apply() {
        ArmoreableMobsCommon.armorList.put(entityType, Utilities.mergeOrMakeList(ArmoreableMobsCommon.armorList.get(entityType), group));
    }

    @Override
    public String describe() {
        return String.format("Adding an ArmorGroup for entity: %s under the name: \"%s\"", Utilities.toCraftTweakerBEP(entityType),
                group.getName());
    }

    @Override
    public void undo() {
        List<ArmorGroup> groups = ArmoreableMobsCommon.armorList.get(entityType);
        if (groups.size() == 1) {
            ArmoreableMobsCommon.armorList.remove(entityType);
        } else {
            groups.remove(group);
            ArmoreableMobsCommon.armorList.put(entityType, groups);
        }
    }

    @Override
    public String describeUndo() {
        return String.format("Removing an ArmorGroup for entity: {} under the name: ", Utilities.toCraftTweakerBEP(entityType),
                group.getName());
    }

    @Override
    public String systemName() {
        return ArmoreableMobsCommon.MOD_NAME;
    }
}
