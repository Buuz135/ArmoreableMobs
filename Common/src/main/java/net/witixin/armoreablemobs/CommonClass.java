package net.witixin.armoreablemobs;

import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonClass {

    public static final String MOD_ID = "armoreablemobs";
    public static final String MOD_NAME = "Armoreable_Mobs";

    public static Map<EntityType, List<ArmorGroup>> armorList = new HashMap<>();

    public static Map<EntityType, BlockState> entityBlockStateMapOverrides = new HashMap<>();
    public static Map<BlockState, Map<EquipmentSlot, IItemStack>> blockstateArmorOverries = new HashMap<>();

    public static void onSpawn(Entity fakeEntity){

        System.out.println(armorList);

        if (fakeEntity instanceof LivingEntity entity){
            if (armorList.containsKey(entity.getType())){
                System.out.println(armorList.get(entity.getType()).get(0).getMap());
                ArmorGroup currentGroup = new ArmorGroup(entity.getArmorSlots().iterator(), entity.getItemBySlot(EquipmentSlot.MAINHAND), entity.getItemBySlot(EquipmentSlot.OFFHAND));
                ArmorGroup selectedGroup = rollGroup(armorList.get(entity.getType()));
                if (GameStagesHelper.entityPlayerStageNearby(entity, selectedGroup.getStages()) && PackModeHelper.playerPackmodeNearby(entity, selectedGroup.getPackmode())){
                    if (ArmorGroup.overrideArmorGroups.contains(entity.getType())){
                        attachItems(selectedGroup, entity);
                    }
                    if (currentGroup.isEmpty()){
                        attachItems(selectedGroup, entity);
                    }
                    if (entityBlockStateMapOverrides.containsKey(entity.getType()) && entityBlockStateMapOverrides.get(entity.getType()) != null && entity.getLevel().getBlockState(entity.blockPosition().below()).equals((entityBlockStateMapOverrides.get(entity.getType())))){
                        ArmorGroup g = new ArmorGroup(EntityType.getKey(entity.getType()) + entityBlockStateMapOverrides.get(entity.getType()).getBlock().toString());
                        blockstateArmorOverries.get(entity.getLevel().getBlockState(entity.blockPosition().below())).forEach((slot, item) -> {
                            g.inSlot(slot, item.getInternal());
                        });
                        attachItems(g, entity);
                    }
                }
            }
        }
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
        for (EquipmentSlot eqslt: g.getMap().keySet()){
            final ItemStack stack = g.getStackinSlot(eqslt);
            livingEntity.setItemSlot(eqslt, stack);
        }
    }


}