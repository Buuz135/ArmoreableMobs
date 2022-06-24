package net.witixin.armoreablemobs;

import com.blamejared.crafttweaker.platform.Services;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.function.BiPredicate;

public class GameStagesHelper {
    //eventually if GS is on Fabric this will be used ig
        public static boolean entityPlayerStageNearby(LivingEntity entity, List<String> stages){
        if (Services.PLATFORM.isModLoaded("gamestages")){
            for (ServerPlayer playerEntity : entity.getServer().getPlayerList().getPlayers()){
                    return playerEntity.position().distanceTo(entity.position()) < 256 /*  && GameStageHelper.hasAllOf(playerEntity, stages) */;
            }
        }
        return true;
    }

    public static boolean delegatePredicate(LivingEntity livingEntity, List<String> stagesToCheckAgainst, BiPredicate<LivingEntity, List<String>> predicate){
            if (Services.PLATFORM.isModLoaded("gamestages")) return predicate.test(livingEntity, stagesToCheckAgainst);
            else return true;
    }
}
