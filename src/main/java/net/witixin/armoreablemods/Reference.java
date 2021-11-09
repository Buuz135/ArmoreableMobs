package net.witixin.armoreablemods;

import com.blamejared.crafttweaker.api.item.IItemStack;
import com.teamacronymcoders.packmode.PackModeAPIImpl;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.witixin.armoreablemods.crt.ArmorGroup;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod(Reference.MODID)
public class Reference
{
    public static Map<EntityType<?>, List<ArmorGroup>> armorList = new HashMap<>();

    public static Map<EntityType<?>, BlockState> entityBlockStateMapOverrides = new HashMap<>();
    public static Map<BlockState, Map<EquipmentSlotType, IItemStack>> blockstateArmorOverries = new HashMap<>();

    public static final String MODID = "armoreablemods";
    public Reference() {
        MinecraftForge.EVENT_BUS.addListener(this::onSpawn);
        MinecraftForge.EVENT_BUS.addListener(this::onReload);

    }

    public void onSpawn(LivingSpawnEvent event){
        if (armorList.keySet().contains(event.getEntityLiving().getType())){
            ArmorGroup currentGroup = new ArmorGroup(event.getEntityLiving().getArmorSlots().iterator(), event.getEntityLiving().getItemBySlot(EquipmentSlotType.MAINHAND), event.getEntityLiving().getItemBySlot(EquipmentSlotType.OFFHAND));
            ArmorGroup selectedGroup = rollGroup(armorList.get(event.getEntity().getType()));
            if (entityPlayerStageNearby(event.getEntityLiving(), selectedGroup.getStages()) && playerPackmodeNearby(event.getEntityLiving(), selectedGroup.getPackmode())){
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
        for (EquipmentSlotType eqslt: g.getMap().keySet()){
            livingEntity.setItemSlot(eqslt, g.getStackinSlot(eqslt));
        }
    }
    private static boolean entityPlayerStageNearby(LivingEntity entity, List<String> stages){
        if (ModList.get().isLoaded("gamestages")){
            for (ServerPlayerEntity playerEntity : entity.getServer().getPlayerList().getPlayers()){
                    return playerEntity.position().distanceTo(entity.position()) < 256 && GameStageHelper.hasAllOf(playerEntity, stages);
            }
        }
        return true;
    }
    private static boolean playerPackmodeNearby(LivingEntity entity, String packmode){
        if (ModList.get().isLoaded("packmode") && packmode != null){
            for (ServerPlayerEntity playerEntity : entity.getServer().getPlayerList().getPlayers()){
                    return playerEntity.position().distanceTo(entity.position()) < 256 && PackModeAPIImpl.getInstance().getPackMode().equalsIgnoreCase(packmode);
            }
        }
        return true;
    }
}
