package net.witixin.armoreablemods;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;

import java.util.List;

public class GameStagesHelper {
        public static boolean entityPlayerStageNearby(LivingEntity entity, List<String> stages){
        if (ModList.get().isLoaded("gamestages")){
            for (ServerPlayer playerEntity : entity.getServer().getPlayerList().getPlayers()){
                    return playerEntity.position().distanceTo(entity.position()) < 256 /*  && GameStageHelper.hasAllOf(playerEntity, stages) */;
            }
        }
        return true;
    }


}
