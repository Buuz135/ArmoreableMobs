package net.witixin.armoreablemobs;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod(CommonClass.MOD_ID)
public class Reference {
    
    public Reference() {
        MinecraftForge.EVENT_BUS.addListener(this::doSpawn);
        MinecraftForge.EVENT_BUS.addListener(this::reloadListener);
    }

    public void doSpawn(final LivingSpawnEvent.SpecialSpawn event){
        CommonClass.onSpawn(event.getEntityLiving(), GameStagesUtil::matchesConditions);
    }

    public void reloadListener(final AddReloadListenerEvent event){
        CommonClass.onReload();
    }


    private static class GameStagesUtil {
        private static boolean matchesConditions(LivingEntity entity, List<String> stages){
            for (ServerPlayer playerEntity : entity.getServer().getPlayerList().getPlayers()){
                return playerEntity.position().distanceTo(entity.position()) < 256 && GameStageHelper.hasAllOf(playerEntity, stages);
            }
            return true;
        }
    }
}