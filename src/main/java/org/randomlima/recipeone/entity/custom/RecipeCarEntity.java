package org.randomlima.recipeone.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RecipeCarEntity extends VehicleEntity {


    public RecipeCarEntity(EntityType<? extends RecipeCarEntity> entityType, World world) {
        super(entityType, world);

        this.noClip = false;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getFirstPassenger() instanceof PlayerEntity player) {
            this.setYaw(player.getYaw()); // face same way as player
        }
    }




    @Override
    public boolean canAddPassenger(Entity passenger) {
        // Allow any player to ride
        return this.getPassengerList().isEmpty() && passenger instanceof PlayerEntity;
    }

//    @Override
//    protected void updatePassengerPosition(Entity passenger, PositionUpdater positionUpdater) {
//        super.updatePassengerPosition(passenger,positionUpdater);
//        if (this.hasPassenger(passenger)) {
//            double offsetX = 0.0;
//            double offsetY = 0.5; // lift them above the entity hitbox
//            double offsetZ = 0.0;
//
//            positionUpdater.accept(
//                    passenger,
//                    this.getX() + offsetX,
//                    this.getY() + offsetY,
//                    this.getZ() + offsetZ
//            );
//        }
//    }
    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater updater) {
        super.updatePassengerPosition(passenger, updater);
        // Keep rider centered
        passenger.setYaw(this.getYaw());
        passenger.setHeadYaw(this.getYaw());
        passenger.setBodyYaw(this.getYaw());
        //passenger.setPosition(this.getX(), this.getY() - 0.5, this.getZ());
    }
    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof LivingEntity ? (LivingEntity) passenger : null;
    }

    @Override
    public ActionResult interact(PlayerEntity player, Hand hand) {
        System.out.println("interact() called: side=" + (this.getWorld().isClient() ? "CLIENT" : "SERVER"));
        if (!this.getWorld().isClient) {
            boolean mounted = player.startRiding(this);
            System.out.println("Mounted result=" + mounted);
            return mounted ? ActionResult.SUCCESS : ActionResult.PASS;
        }
        return ActionResult.SUCCESS;
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
    public boolean canHit() {
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
