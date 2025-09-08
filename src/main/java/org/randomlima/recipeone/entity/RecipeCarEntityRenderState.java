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
    public float yaw;
    public float pitch;
    public float roll;
    public int damageWobbleSide;
    public float damageWobbleTicks;
    public float damageWobbleStrength;
    public float bubbleWobble;
    public boolean submergedInWater;
    public float leftPaddleAngle;
    public float rightPaddleAngle;

    public RecipeCarEntityRenderState() {
    }
}