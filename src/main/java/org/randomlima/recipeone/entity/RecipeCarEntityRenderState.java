package org.randomlima.recipeone.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class RecipeCarEntityRenderState extends EntityRenderState {
    public float lerpedPitch;
    public float lerpedYaw;
    public long hash;
    public int damageWobbleSide;
    public float damageWobbleTicks;
    public float damageWobbleStrength;
    public int blockOffset;
    public BlockState containedBlock;
    public boolean usesExperimentalController;
    @Nullable
    public Vec3d lerpedPos;
    @Nullable
    public Vec3d presentPos;
    @Nullable
    public Vec3d futurePos;
    @Nullable
    public Vec3d pastPos;

    public RecipeCarEntityRenderState() {
        this.containedBlock = Blocks.AIR.getDefaultState();
    }
}