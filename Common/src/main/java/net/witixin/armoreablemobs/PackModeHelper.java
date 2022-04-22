package net.witixin.armoreablemobs;

import com.blamejared.crafttweaker.platform.Services;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

public class PackModeHelper {
        public static boolean playerPackmodeNearby(LivingEntity entity, String packmode){
        if (Services.PLATFORM.isModLoaded("packmode") && !packmode.isEmpty()){
            for (ServerPlayer playerEntity : entity.getServer().getPlayerList().getPlayers()){
                    return playerEntity.position().distanceTo(entity.position()) < 256 /* && PackModeAPIImpl.getInstance().getPackMode().equalsIgnoreCase(packmode) */;
            }
        }
        return true;
    }
}
