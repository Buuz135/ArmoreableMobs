package net.witixin.armoreablemobs;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class Reference {
    
    public Reference() {
        // Use Forge to bootstrap the Common mod.
        Constants.LOG.info("Hello Forge world!");
        CommonClass.init();
        MinecraftForge.EVENT_BUS.addListener(this::doSpawn);
        MinecraftForge.EVENT_BUS.addListener(this::reloadListener);
    }

    public void doSpawn(final LivingSpawnEvent.SpecialSpawn event){
        CommonClass.onSpawn(event.getEntityLiving());
    }

    public void reloadListener(final AddReloadListenerEvent event){
        CommonClass.onReload();
    }

}