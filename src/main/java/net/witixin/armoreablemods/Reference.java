package net.witixin.armoreablemods;


import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.witixin.armoreablemods.crt.ArmorGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod(Reference.MODID)
public class Reference
{
    public static Map<EntityType, List<ArmorGroup>> armorList = new HashMap<>();

    public static Map<EntityType, BlockState> entityBlockStateMapOverrides = new HashMap<>();
    public static Map<BlockState, Map<EquipmentSlot, IItemStack>> blockstateArmorOverries = new HashMap<>();

    public static final String MODID = "armoreablemods";
    public Reference() {
        MinecraftForge.EVENT_BUS.addListener(this::onSpawn);
        MinecraftForge.EVENT_BUS.addListener(this::onReload);

    }

    public void onSpawn(LivingSpawnEvent.SpecialSpawn event){
        if (armorList.keySet().contains(event.getEntityLiving().getType())){
            ArmorGroup currentGroup = new ArmorGroup(event.getEntityLiving().getArmorSlots().iterator(), event.getEntityLiving().getItemBySlot(EquipmentSlot.MAINHAND), event.getEntityLiving().getItemBySlot(EquipmentSlot.OFFHAND));
            ArmorGroup selectedGroup = rollGroup(armorList.get(event.getEntity().getType()));
            if (GameStagesHelper.entityPlayerStageNearby(event.getEntityLiving(), selectedGroup.getStages()) && PackModeHelper.playerPackmodeNearby(event.getEntityLiving(), selectedGroup.getPackmode())){
                if (ArmorGroup.overrideArmorGroups.contains(event.getEntityLiving().getType())){
                    attachItems(selectedGroup, event.getEntityLiving());
                }
                if (currentGroup.isEmpty()){
                    attachItems(selectedGroup, event.getEntityLiving());
                }
                    if (entityBlockStateMapOverrides.containsKey(event.getEntityLiving().getType()) && entityBlockStateMapOverrides.get(event.getEntityLiving().getType()) != null && event.getWorld().getBlockState(event.getEntityLiving().blockPosition().below()).equals((entityBlockStateMapOverrides.get(event.getEntityLiving().getType())))){
                        ArmorGroup g = new ArmorGroup(event.getEntityLiving().getType().getRegistryName().toString() + entityBlockStateMapOverrides.get(event.getEntityLiving().getType()).getBlock().getRegistryName().toString());
                        blockstateArmorOverries.get(event.getWorld().getBlockState(event.getEntityLiving().blockPosition().below())).forEach((slot, item) -> {
                            g.inSlot(slot, item.getInternal());
                        });
                        attachItems(g, event.getEntityLiving());
                    }
            }
        }
    }
    public void onReload(AddReloadListenerEvent event){
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
            livingEntity.setItemSlot(eqslt, g.getStackinSlot(eqslt));
        }
    }


}
