package com.buuz135.armoreablemobs;

import com.buuz135.armoreablemobs.entity.ArmorEntity;
import com.buuz135.armoreablemobs.handler.ArmorGroup;
import com.buuz135.armoreablemobs.handler.ArmorHandler;
import com.buuz135.armoreablemobs.handler.ArmorSlot;
import crafttweaker.CraftTweakerAPI;
import net.minecraft.entity.EntityLiving;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.stream.Collectors;

@Mod(
        modid = Armoreablemobs.MOD_ID,
        name = Armoreablemobs.MOD_NAME,
        version = Armoreablemobs.VERSION,
        dependencies = "required-after:crafttweaker"
)
public class Armoreablemobs {

    public static final String MOD_ID = "armoreablemobs";
    public static final String MOD_NAME = "ArmoreableMobs";
    public static final String VERSION = "1.0-SNAPSHOT";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static Armoreablemobs INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        CraftTweakerAPI.registerClass(ArmorEntity.class);//
        CraftTweakerAPI.registerClass(ArmorSlot.class);
        CraftTweakerAPI.registerClass(ArmorGroup.class);
        CraftTweakerAPI.registerClass(ArmorHandler.class);
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
//        for (ArmorGroup group : ArmorHandler.ARMOR_GROUPS){
//            System.out.println(group.getName());
//            group.getEntityNames().forEach(System.out::println);
//        }
    }

    @SubscribeEvent
    public void onEntitySpawn(LivingSpawnEvent.SpecialSpawn event) {
        if (event.getEntity() instanceof EntityLiving) {
            for (ArmorGroup group : ArmorHandler.ARMOR_GROUPS) {
                for (ArmorEntity entity : group.getEntities()) {
                    if (!entity.checkEntity(event.getEntity()) || event.getWorld().rand.nextDouble() > group.getChance())
                        continue;
                    for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
                        ArmorSlot winner = WeightedRandom.getRandomItem(event.getWorld().rand, group.getSlots().stream().filter(slot1 -> slot1.getSlot().equals(slot)).collect(Collectors.toList()));
                        event.getEntity().setItemStackToSlot(slot, (ItemStack) winner.getStack().getInternal());
                        ((EntityLiving) event.getEntity()).setDropChance(slot, (float) winner.getChanceToDrop());
                    }
                }
            }
        }
    }

}
