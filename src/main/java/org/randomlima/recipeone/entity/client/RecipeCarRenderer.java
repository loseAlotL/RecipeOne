package org.randomlima.recipeone.entity.client;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MinecartEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.MinecartEntityModel;
import net.minecraft.client.render.entity.state.BoatEntityRenderState;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.MinecartEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.randomlima.recipeone.GroundEngine;
import org.randomlima.recipeone.RecipeOne;
import org.randomlima.recipeone.entity.RecipeCarEntityRenderState;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;

public class RecipeCarRenderer extends EntityRenderer<RecipeCarEntity, RecipeCarEntityRenderState> {

    private static final Identifier TEXTURE =
            Identifier.of(RecipeOne.MOD_ID, "textures/entity/recipecar.png");

    public static final EntityModelLayer LAYER =
            new EntityModelLayer(Identifier.of(RecipeOne.MOD_ID, "recipecar"), "main");
    private final RecipeCarEntityModel model;
    private GroundEngine groundEngine;
    public RecipeCarRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new RecipeCarEntityModel(ctx.getPart(LAYER));
        this.groundEngine = new GroundEngine(null);
    }


    @Override
    public void updateRenderState(RecipeCarEntity entity, RecipeCarEntityRenderState state, float tickProgress) {
        super.updateRenderState(entity, state, tickProgress);
        state.yaw = MathHelper.lerpAngleDegrees(
                tickProgress,
                entity.lastYaw,
                entity.getYaw()
        );

        // Optional: pitch (for slopes)

        groundEngine.setCar(entity);
        groundEngine.update();
        //state.pitch += MathHelper.lerp(tickProgress, 0, (float) groundEngine.getPitch());
        //state.roll = MathHelper.lerp(tickProgress, 0, (float) groundEngine.getRoll());
        float smoothing = 0.1f; // tweak 0.0-1.0 for interpolation speed
        state.pitch += ((float) groundEngine.getPitch() - state.pitch) * smoothing;
        state.roll += ((float) -groundEngine.getRoll() - state.roll) * smoothing;
    }

    @Override
    public void render(RecipeCarEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.multiply(new Quaternionf().rotateY((float) Math.toRadians(-state.yaw)));

        float rollFactor = -1f;
        float pitchFactor = -1f;
        matrices.multiply(new Quaternionf().rotateZ((float) Math.toRadians(state.roll * rollFactor)));
        matrices.multiply(new Quaternionf().rotateX((float) Math.toRadians(state.pitch * pitchFactor)));


        matrices.multiply(new Quaternionf().rotateX((float)Math.PI));
        matrices.translate(0, -1.5, 0);

        model.getRootPart().render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(TEXTURE)), light, OverlayTexture.DEFAULT_UV);
        matrices.pop();

        //super.render(state, matrices, vertexConsumers, light);
    }


    @Override
    public boolean shouldRender(RecipeCarEntity entity, Frustum frustum, double x, double y, double z) {
        return super.shouldRender(entity, frustum, x, y, z);
    }

    @Override
    public Vec3d getPositionOffset(RecipeCarEntityRenderState state) {
        return super.getPositionOffset(state);
    }

    @Override
    public RecipeCarEntityRenderState createRenderState() {
        return new RecipeCarEntityRenderState();
    }
}

