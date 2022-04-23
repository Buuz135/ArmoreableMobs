package net.witixin.armoreablemobs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;

public class Reference implements ModInitializer {
    
    @Override
    public void onInitialize() {
        ServerEntityEvents.ENTITY_LOAD.register(((entity, world) -> CommonClass.onSpawn(entity)));
        ServerEntityEvents.ENTITY_UNLOAD.register(((entity, world) -> CommonClass.onSpawn(entity)));
    }


}
