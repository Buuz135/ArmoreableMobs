package net.witixin.armoreablemods;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;

public class PackModeHelper {
        public static boolean playerPackmodeNearby(LivingEntity entity, String packmode){
        if (ModList.get().isLoaded("packmode") && !packmode.isEmpty()){
            for (ServerPlayer playerEntity : entity.getServer().getPlayerList().getPlayers()){
                    return playerEntity.position().distanceTo(entity.position()) < 256 /* && PackModeAPIImpl.getInstance().getPackMode().equalsIgnoreCase(packmode) */;
            }
        }
        return true;
    }
}
