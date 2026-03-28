package net.witixin.armoreablemobs.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.ServerLevelAccessor;
import net.witixin.armoreablemobs.ArmoreableMobsCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Mob.class)
public class MobFinalizeSpawnMixin {

    @Inject(method = "Lnet/minecraft/world/entity/Mob;finalizeSpawn(Lnet/minecraft/world/level/ServerLevelAccessor;" +
            "Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/MobSpawnType;Lnet/minecraft/world/entity/SpawnGroupData;)" +
            "Lnet/minecraft/world/entity/SpawnGroupData;",
            at = @At(value = "RETURN"))
    private void armoreableMobs$inject(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType spawnType,
                                       SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> callbackInfoReturnable) {
        Mob livingEntity = (Mob)((Object)this);
        ArmoreableMobsCommon.onSpawn(livingEntity);
    }
}
