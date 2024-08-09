package net.witixin.armoreablemobs.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.witixin.armoreablemobs.ArmoreableMobsCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(EntityType.class)
public class MobFinalizeSpawnMixin {

    @Inject(method = "Lnet/minecraft/world/entity/EntityType;spawn(Lnet/minecraft/server/level/ServerLevel;" + "Lnet/minecraft/nbt" +
            "/CompoundTag;Ljava/util/function/Consumer;Lnet/minecraft/core/BlockPos;" + "Lnet/minecraft/world/entity/MobSpawnType;ZZ)" +
            "Lnet/minecraft/world/entity/Entity;", at = @At(value = "TAIL", target = "Lnet/minecraft/server/level/ServerLevel;" +
            "addFreshEntityWithPassengers" + "(Lnet/minecraft/world/entity/Entity;)V"))
    private void armoreableMobs$inject(ServerLevel level, CompoundTag tag, Consumer<Entity> consumer, BlockPos pos, MobSpawnType type,
                                       boolean b1, boolean b2, CallbackInfoReturnable<Entity> callbackInfo) {
        if (callbackInfo.getReturnValue() instanceof LivingEntity livingEntity) {
            ArmoreableMobsCommon.onSpawn(livingEntity);
        }
    }
}
