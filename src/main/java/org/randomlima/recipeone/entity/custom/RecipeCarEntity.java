package org.randomlima.recipeone.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.randomlima.recipeone.Engine;
import org.randomlima.recipeone.ThrottleGoal;
import org.randomlima.recipeone.Tyre;
import org.randomlima.recipeone.entity.RecipeCarEntityRenderState;

public class RecipeCarEntity extends VehicleEntity {
    private final ThrottleGoal throttleGoal;
//    Engine f1Engine = new Engine(
//            1000,       // hp (approx 1000 HP for modern F1 hybrid)
//            5.0,        // aeroCoeff (simplified coefficient for downforce calculation)
//            700,        // maxTorque in Nm
//            5000,       // downforce at top speed (N)
//            0.3,        // drag coefficient (simplified)
//            798,        // mass (kg, typical F1 car weight without fuel)
//            1.8,        // width (m)
//            4.7,        // length (m)
//            3.5,        // wheelBase (m)
//            0.35,       // cmHeight (center of mass height, m)
//            1.6,        // trackWidth (m)
//            30000,      // suspensionStiffness (N/m, approximate)
//            3000,       // suspensionDamping (Ns/m, approximate)
//            50          // rollResistance (N, simplified)
//    );
    Engine f1Engine = new Engine();




    public RecipeCarEntity(EntityType<? extends RecipeCarEntity> entityType, World world) {
        super(entityType, world);

        this.noClip = false;
        this.setNoGravity(false);
        this.applyGravity();

        this.throttleGoal = new ThrottleGoal(null);
    }

    @Override
    public void tick() {
        super.tick();

        if(this.getControllingPassenger() != null){
            LivingEntity controller = this.getControllingPassenger();
            if (controller instanceof PlayerEntity player) {
                this.setYaw(player.getYaw());
                this.setBodyYaw(player.getYaw());
                this.setHeadYaw(player.getYaw());
                //System.out.println("forwardspeed: "+player.forwardSpeed);

                throttleGoal.setPlayer(player);  // update player ref
                throttleGoal.updateGoal();

                float throttle = throttleGoal.getThrottle();

                player.sendMessage(Text.of("[Throttle: "+throttle+"]"),true);
                f1Engine.update(throttle);
                f1Engine.printData();




//                double baseSpeed = 2.25;
//                double radYaw = Math.toRadians(this.getYaw());
//                Vec3d forwardVec = new Vec3d(
//                        -Math.sin(radYaw),
//                        0,
//                        Math.cos(radYaw)
//                );
//                Vec3d velocity = forwardVec.multiply(throttle * baseSpeed);
//
//                this.setVelocity(velocity);
//                this.move(MovementType.SELF, velocity);


                // Update the engine
//                f1Engine.update(1.0 / 20.0, player, this); // assuming 20 ticks/sec for deltaTime
//
//                // Apply engine-calculated velocity
//                this.setVelocity(f1Engine.velocity);
//                this.move(MovementType.SELF, f1Engine.velocity);
            }
        }

    }


    @Override
    public boolean canAddPassenger(Entity passenger) {
        // Allow any player to ride
        return this.getPassengerList().isEmpty() && passenger instanceof PlayerEntity;
    }

    @Override
    protected void updatePassengerPosition(Entity passenger, PositionUpdater updater) {
        super.updatePassengerPosition(passenger, updater);
        // Keep rider centered

        passenger.setPosition(this.getX(), this.getY() - 0.6, this.getZ());
    }
    @Override
    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        return passenger instanceof LivingEntity ? (LivingEntity) passenger : null;
    }

    public void updateRenderState(RecipeCarEntityRenderState state, float tickDelta) {
        if (this.getControllingPassenger() instanceof LivingEntity driver) {
            state.yaw = MathHelper.lerp(tickDelta, driver.lastYaw, driver.getYaw());
            state.pitch = MathHelper.lerp(tickDelta, driver.lastPitch, driver.getPitch());
        } else {
            state.yaw = this.getYaw();
            state.pitch = this.getPitch();
        }
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
