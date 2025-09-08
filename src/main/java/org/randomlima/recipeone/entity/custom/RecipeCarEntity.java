package org.randomlima.recipeone.entity.custom;

import net.minecraft.block.BlockState;
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
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.randomlima.recipeone.*;
import org.randomlima.recipeone.entity.RecipeCarEntityRenderState;

public class RecipeCarEntity extends VehicleEntity {
    private final ThrottleGoal throttleGoal;
    Engine f1Engine = new Engine();
    private double verticalVelocity = 0;
    private double velocity;
    private double kmh;
    private Vec3d forwardVec;
    private final GroundEngine groundEngine = new GroundEngine(this);



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

        if (this.getControllingPassenger() instanceof PlayerEntity player) {
            // Update car rotation to match player
            this.setYaw(player.getYaw());
            this.setBodyYaw(player.getYaw());
            this.setHeadYaw(player.getYaw());

            // Update throttle
            throttleGoal.setPlayer(player);
            throttleGoal.updateGoal();
            float throttle = throttleGoal.getThrottle();

            // Only run physics client-side
            if (getWorld().isClient()) {
                // --- Engine update ---
                f1Engine.update(throttle, Gear.FIRST);
                velocity = f1Engine.getVelocity(); // m/s

                // Display km/h
                kmh = Math.round(velocity * 3.6 * 10.0) / 10.0;
                player.sendMessage(Text.of("[KM/H: " + kmh + "]"), true);

                double deltaT = 0.05; // 1 tick = 0.05s
                double radYaw = Math.toRadians(this.getYaw());
                forwardVec = new Vec3d(-Math.sin(radYaw), 0, Math.cos(radYaw));

                // --- Horizontal motion ---
                Vec3d horizontalMotion = forwardVec.multiply(velocity * deltaT);

                // --- Block climbing check (1-block obstacles) ---
                if(blockStep(-.5) && blockStep(0) && blockStep(.5)){
                    this.setPosition(this.getPos().offset(Direction.UP,1));
                }

                // --- Gravity ---
                if (!this.isOnGround()) {
                    verticalVelocity -= 9.81 * deltaT;
                } else if (verticalVelocity < 0) {
                    verticalVelocity = 0;
                }

                // --- Combine motion ---
                Vec3d motion = horizontalMotion.add(0, verticalVelocity * deltaT, 0);

                // --- Apply movement ---
                this.move(MovementType.SELF, motion);
            }
        }
        groundEngine.update();
        System.out.println("Pitch: "+groundEngine.getPitch());
        System.out.println("Roll: "+groundEngine.getRoll());
    }
    public Vec3d getForwardVec(){
        return forwardVec;
    }
    public boolean blockStep(double rightOffset){
        boolean step = false;

        Vec3d start = this.getPos().add(0, 0.1, 0);

        Vec3d rightVec = new Vec3d(forwardVec.z, 0, -forwardVec.x).normalize();
        start = start.add(rightVec.multiply(rightOffset));

        Vec3d end = start.add(forwardVec.multiply(Math.max(1, kmh / 15)));

        RaycastContext context = new RaycastContext(
                start,
                end,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                this
        );
        HitResult result = this.getWorld().raycast(context);

        if (result.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHit = (BlockHitResult) result;
            BlockPos hitPos = blockHit.getBlockPos();
            BlockState above = this.getWorld().getBlockState(new BlockPos(hitPos.getX(), hitPos.getY()+1, hitPos.getZ()));
            if(above.isAir()){
                step = true;
            }
        }
        return step;
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
