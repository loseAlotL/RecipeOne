package org.randomlima.recipeone.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;
import org.randomlima.recipeone.RecipeOne;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;

public class RecipeCarRenderer extends MobEntityRenderer<
        RecipeCarEntity,
        LivingEntityRenderState,
        RecipeCarEntityModel> {

    private static final Identifier TEXTURE =
            Identifier.of(RecipeOne.MOD_ID, "textures/entity/recipecar.png");

    public static final EntityModelLayer LAYER =
            new EntityModelLayer(Identifier.of(RecipeOne.MOD_ID, "recipecar"), "main");

    public RecipeCarRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RecipeCarEntityModel(ctx.getPart(LAYER)), 0.5f);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public void updateRenderState(RecipeCarEntity entity, LivingEntityRenderState state, float tickDelta) {
        super.updateRenderState(entity, state, tickDelta);
        state.ageScale = entity.isBaby() ? 0.5f : 1.0f;
        state.relativeHeadYaw = entity.getHeadYaw() - entity.getBodyYaw();
        //state.pitch = entity.getPitch(tickDelta);
        state.pitch = entity.getPitch();  // same idea, no tickDelta
    }


    @Override
    public Identifier getTexture(LivingEntityRenderState state) {
        return TEXTURE;
    }
}

