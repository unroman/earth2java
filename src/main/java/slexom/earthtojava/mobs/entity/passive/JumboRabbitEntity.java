package slexom.earthtojava.mobs.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import slexom.earthtojava.mobs.entity.base.E2JBaseRabbitEntity;
import slexom.earthtojava.mobs.init.SoundEventsInit;

public class JumboRabbitEntity extends E2JBaseRabbitEntity {

    public JumboRabbitEntity(EntityType<JumboRabbitEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected SoundEvent getJumpSound() {
        return SoundEventsInit.JUMBO_RABBIT_JUMP;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEventsInit.JUMBO_RABBIT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEventsInit.JUMBO_RABBIT_HURT;
    }

}
