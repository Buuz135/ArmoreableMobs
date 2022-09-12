package net.witixin.armoreablemobs;

import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

public class CommonClass {

    public static final String MOD_ID = "armoreablemobs";
    public static final String MOD_NAME = "Armoreable_Mobs";

    public static Map<EntityType, List<ArmorGroup>> armorList = new HashMap<>();

    public static Map<EntityType, BlockState> entityBlockStateMapOverrides = new HashMap<>();
    public static Map<BlockState, Map<EquipmentSlot, IItemStack>> blockstateArmorOverries = new HashMap<>();


    //TODO fix bug with overrides

    public static ArmorGroup onSpawn(Entity fakeEntity, BiPredicate<LivingEntity, List<String>> predicate){
        if (fakeEntity instanceof LivingEntity entity){
            if (armorList.containsKey(entity.getType())){
                ArmorGroup selectedGroup = rollGroup(armorList.get(entity.getType()));
                if (GameStagesHelper.delegatePredicate(entity, selectedGroup.getStages(), predicate) && PackModeHelper.playerPackmodeNearby(entity, selectedGroup.getPackmode())){
                    if (ArmorGroup.overrideArmorGroups.containsKey(entity.getType())){
                        ArmorGroup.overrideArmorGroups.get(entity.getType()).forEach((equipmentSlot, iItemStack) -> entity.setItemSlot(equipmentSlot, iItemStack.getInternal()));
                        return new ArmorGroup(ArmorGroup.overrideArmorGroups.get(entity.getType()).values().stream().map(IItemStack::getInternal).iterator());
                    }
                    attachItems(selectedGroup, entity);
                    if (entityBlockStateMapOverrides.containsKey(entity.getType()) && entityBlockStateMapOverrides.get(entity.getType()) != null && entity.getLevel().getBlockState(entity.blockPosition().below()).equals((entityBlockStateMapOverrides.get(entity.getType())))){
                        ArmorGroup g = new ArmorGroup(EntityType.getKey(entity.getType()) + entityBlockStateMapOverrides.get(entity.getType()).getBlock().toString());
                        blockstateArmorOverries.get(entity.getLevel().getBlockState(entity.blockPosition().below())).forEach(g::inSlot);
                        attachItems(g, entity);
                        return g;
                    }
                    return selectedGroup;
                }
            }
        }
        return null;
    }
    public static void onReload(){
        armorList.clear();
    }
    private static ArmorGroup rollGroup(List<ArmorGroup> group){
        double maxWeight = 0.0;
        for (ArmorGroup individual : group){
            maxWeight += individual.getWeight();
        }
        int position = 0;
        for (double r = Math.random() * maxWeight; position < group.size(); ++position) {
            r -= group.get(position).getWeight();
            if (r <= 0.0) break;
        }
        return group.get(position);
    }
    private static void attachItems(ArmorGroup g, LivingEntity livingEntity){
        Map<EquipmentSlot, ItemStack> slotMap = new ArmorGroup(livingEntity.getArmorSlots().iterator(), livingEntity.getMainHandItem(), livingEntity.getOffhandItem()).getMap();
        for (EquipmentSlot eqslt: g.getMap().keySet()){
            final ItemStack stack = g.getStackInSlot(eqslt);
            if (slotMap.get(eqslt).isEmpty()){
                livingEntity.setItemSlot(eqslt, stack);
            }
        }
    }


}