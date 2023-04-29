package slexom.earthtojava.mixins;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Shadow
    private ClientWorld world;

    @Inject(at = @At("HEAD"), method = "onEntitySpawn")
    private void e2jOnEntitySpawn(EntitySpawnS2CPacket packet, CallbackInfo ci) {
        // int id = packet.getId();
        // double x = packet.getX();
        // double y = packet.getY();
        // double z = packet.getZ();
        // EntityType<?> entityType = packet.getEntityType();
        // Entity entity = null;
        // if (entityType == EntityTypesInit.BONE_SHARD_REGISTRY_OBJECT.get()) {
        //     entity = new BoneShardEntity(this.world, x, y, z);
        // }
        // if (entityType == EntityTypesInit.MELON_SEED_PROJECTILE_REGISTRY_OBJECT.get()) {
        //     entity = new MelonSeedProjectileEntity(this.world, x, y, z);
        // }
        // if (entityType == EntityTypesInit.ROTTEN_FLESH_PROJECTILE_REGISTRY_OBJECT.get()) {
        //     entity = new RottenFleshProjectileEntity(this.world, x, y, z);
        // }
        // if (entity != null) {
        //     entity.updateTrackedPosition(x, y, z);
        //     entity.refreshPositionAfterTeleport(x, y, z);
        //     entity.setPitch((packet.getPitch() * 360) / 256.0F);
        //     entity.setYaw((packet.getYaw() * 360) / 256.0F);
        //     entity.setId(id);
        //     entity.setUuid(packet.getUuid());
        //     this.world.addEntity(id, entity);
        // }
    }

}
