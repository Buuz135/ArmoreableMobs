package net.witixin.armoreablemobs;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.ClientboundSetEquipmentPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArmoreableMobsCommon {

    public static final String MOD_ID = "armoreablemobs";
    public static final String MOD_NAME = "ArmoreableMobs";

    public static Map<EntityType<?>, List<ArmorGroup>> armorList = new HashMap<>();
    public static Map<EntityType<?>, BlockState> entityBlockStateMapOverrides = new HashMap<>();
    public static Map<BlockState, Map<EquipmentSlot, IItemStack>> blockstateArmorOverries = new HashMap<>();

    public static void onSpawn(Mob livingEntity) {
        if (armorList.containsKey(livingEntity.getType())) {
            ArmorGroup selectedGroup = rollGroup(armorList.get(livingEntity.getType()));
            if (GameStagesHelper.entityPlayerStageNearby(livingEntity, selectedGroup.getStages()) && PackModeHelper.playerPackmodeNearby(livingEntity, selectedGroup.getPackmode())) {
                if (entityBlockStateMapOverrides.containsKey(livingEntity.getType()) && entityBlockStateMapOverrides.get(livingEntity.getType()) != null && livingEntity.level().getBlockState(livingEntity.blockPosition().below()).equals((entityBlockStateMapOverrides.get(livingEntity.getType())))) {
                    ArmorGroup g =
                            new ArmorGroup(EntityType.getKey(livingEntity.getType()) + entityBlockStateMapOverrides.get(livingEntity.getType()).getBlock().toString());
                    blockstateArmorOverries.get(livingEntity.level().getBlockState(livingEntity.blockPosition().below())).forEach(g::inSlot);
                    attachItems(g, livingEntity);
                } else {
                    attachItems(selectedGroup, livingEntity);
                }
            }
        }
    }

    private static ArmorGroup rollGroup(List<ArmorGroup> pool) {
        double maxWeight = 0.0;
        for (ArmorGroup individual : pool) {
            maxWeight += individual.getWeight();
        }
        int position = 0;
        for (double r = Math.random() * maxWeight; position < pool.size(); ++position) {
            r -= pool.get(position).getWeight();
            if (r <= 0.0) break;
        }
        return pool.get(position);
    }

    private static void attachItems(ArmorGroup group, LivingEntity livingEntity) {
        for (EquipmentSlot equipmentSlot : group.getEquipment().keySet()) {
            final ItemStack stack = group.getStackinSlot(equipmentSlot);
            livingEntity.setItemSlot(equipmentSlot, stack);
            livingEntity.gameEvent(GameEvent.EQUIP);
        }
        List<Pair<EquipmentSlot, ItemStack>> list = group.getEquipment().entrySet().stream().map(entry -> Pair.of(entry.getKey(),
                entry.getValue())).toList();
        ((ServerLevel) livingEntity.level()).getChunkSource().broadcast(livingEntity,
                new ClientboundSetEquipmentPacket(livingEntity.getId(), list));
    }

}