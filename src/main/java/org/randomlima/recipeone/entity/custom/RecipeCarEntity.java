package org.randomlima.recipeone.entity.custom;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.randomlima.recipeone.entity.ModEntities;

public class RecipeCarEntity extends VehicleEntity {


    public RecipeCarEntity(EntityType<? extends RecipeCarEntity> entityType, World world) {
        super(entityType, world);

        this.noClip = false;
    }


//    public static DefaultAttributeContainer.Builder createAttributes() {
//        return MobEntity.createMobAttributes()
//                .add(EntityAttributes.MAX_HEALTH, 18)
//                .add(EntityAttributes.MOVEMENT_SPEED, 0.35)
//                .add(EntityAttributes.ATTACK_DAMAGE, 1)
//                .add(EntityAttributes.FOLLOW_RANGE, 20);
//    }


    @Override
    public boolean canAddPassenger(Entity passenger) {
        // Allow any player to ride
        return this.getPassengerList().isEmpty() && passenger instanceof PlayerEntity;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
        super.updatePassengerPosition(passenger, positionUpdater);
        double offsetX = 0.0;
        double offsetY = 0.0; // adjust for your model height
        double offsetZ = 0.0;

        passenger.setPosition(
                this.getX() + offsetX,
                this.getY() + offsetY,
                this.getZ() + offsetZ
        );
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            if (!player.hasVehicle()) {
                player.startRiding(this);
                return ActionResult.CONSUME;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public ActionResult interactAt(PlayerEntity player, Vec3d hitPos, Hand hand) {
        System.out.println("INTERACTAT()");
        if (!this.getWorld().isClient) {
            if (!player.hasVehicle()) { // Only allow if player isn't already riding
                player.startRiding(this);
                return ActionResult.CONSUME;
            }
        }
        return ActionResult.PASS;
    }
    @Override
    public EntityDimensions getDimensions(EntityPose pose) {
        return EntityDimensions.fixed(2f, 1f); // width 2, height 1
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
    }

    @Override
    protected Item asItem() {
        return null;
    }

    @Override
    public void tick(){
        super.tick();
    }

    @Override
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
