package com.buuz135.armoreablemobs;

import com.buuz135.armoreablemobs.entity.ArmorEntity;
import com.buuz135.armoreablemobs.handler.ArmorGroup;
import com.buuz135.armoreablemobs.handler.ArmorHandler;
import com.buuz135.armoreablemobs.handler.ArmorSlot;
import com.buuz135.armoreablemobs.util.GameStagesSupport;
import com.buuz135.armoreablemobs.util.ZenWeightedRandom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.stream.Collectors;

@Mod(
        modid = ArmoreableMobs.MOD_ID,
        name = ArmoreableMobs.MOD_NAME,
        version = ArmoreableMobs.VERSION,
        dependencies = "required-after:crafttweaker@[1.12-4.0.9,)"
)
public class ArmoreableMobs {

    public static final String MOD_ID = "armoreablemobs";
    public static final String MOD_NAME = "ArmoreableMobs";
    public static final String VERSION = "1.1.2";
    public static final String GAMESTAGES = "gamestages";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ArmoreableMobs INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        if (event.getEntity() instanceof EntityLiving) {
            for (ArmorGroup group : ArmorHandler.ARMOR_GROUPS) {
                for (ArmorEntity entity : group.getEntities()) {
                    if (!entity.checkEntity(event.getEntity()) || event.getWorld().rand.nextDouble() > group.getChance() || !isSomeoneInStage(event.getEntity(), group))
                        continue;
                    for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
                        List<ArmorSlot> armorSlots = group.getSlots().stream().filter(slot1 -> slot1.getSlot().equals(slot.getName())).collect(Collectors.toList());
                        if (armorSlots.size() <= 0) continue;
                        ArmorSlot winner = ZenWeightedRandom.getRandomItem(event.getWorld().rand, armorSlots);
                        event.getEntity().setItemStackToSlot(slot, winner.getStack() == null ? ItemStack.EMPTY :  ((ItemStack) winner.getStack().getInternal()).copy());
                        ((EntityLiving) event.getEntity()).setDropChance(slot, (float) winner.getChanceToDrop());
                    }
                }
            }
        }
    }

    public boolean isSomeoneInStage(Entity entity, ArmorGroup group) {
        if (!Loader.isModLoaded(GAMESTAGES) || group.getGameStages().size() == 0) return true;
        return GameStagesSupport.checkForStages(entity, group);
    }
}
