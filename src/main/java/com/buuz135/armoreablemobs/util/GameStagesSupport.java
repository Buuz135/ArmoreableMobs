package com.buuz135.armoreablemobs.util;

import com.buuz135.armoreablemobs.handler.ArmorGroup;
import net.darkhax.bookshelf.util.EntityUtils;
import net.darkhax.bookshelf.util.PlayerUtils;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class GameStagesSupport {

    public static boolean checkForStages(Entity entity, ArmorGroup group) {
        for (EntityPlayer player : entity.getEntityWorld().playerEntities) {
            if (!PlayerUtils.isPlayerReal(player)) continue;
            if (GameStageHelper.hasAllOf(player, group.getGameStages()) && EntityUtils.getDistanceFromEntity(entity, player) < 256)
                return true;
        }
        return false;
    }
}
