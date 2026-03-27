package net.witixin.armoreablemobs.actions;

import com.blamejared.crafttweaker.api.action.base.IUndoableAction;
import com.blamejared.crafttweaker.api.item.IItemStack;
import com.blamejared.crafttweaker.natives.block.ExpandBlockState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.level.block.state.BlockState;
import net.witixin.armoreablemobs.ArmoreableMobsCommon;
import net.witixin.armoreablemobs.Utilities;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class AddBlockOverrideAction implements IUndoableAction {

    private final EntityType<Entity> entityType;
    private final @Nullable BlockState state;
    private final Map<EquipmentSlot, IItemStack> equipment;

    public AddBlockOverrideAction(EntityType<Entity> entityType, @Nullable BlockState state, Map<EquipmentSlot, IItemStack> equipment) {
        this.entityType = entityType;
        this.state = state;
        this.equipment = equipment;
    }

    @Override
    public void apply() {
        ArmoreableMobsCommon.entityBlockStateMapOverrides.put(entityType, state);
        ArmoreableMobsCommon.blockstateArmorOverries.put(state, equipment);
    }

    @Override
    public String describe() {
        if (state == null) {
            return String.format("Adding override for entity: %s", Utilities.toCraftTweakerBEP(entityType));
        }
        return String.format("Adding Block spawning override for entity: %s, when standing on block: %s",
                Utilities.toCraftTweakerBEP(entityType), ExpandBlockState.getCommandString(state));
    }

    @Override
    public void undo() {
        ArmoreableMobsCommon.entityBlockStateMapOverrides.remove(entityType, state);
        ArmoreableMobsCommon.blockstateArmorOverries.remove(state, equipment);
    }

    @Override
    public String describeUndo() {
        return String.format("Removing Block spawning override for entity: %s, when standing on block: %s",
                Utilities.toCraftTweakerBEP(entityType), ExpandBlockState.getCommandString(state));
    }

    @Override
    public String systemName() {
        return ArmoreableMobsCommon.MOD_NAME;
    }
}
