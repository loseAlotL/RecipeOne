package org.randomlima.recipeone.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.randomlima.recipeone.entity.ModEntities;
import org.randomlima.recipeone.entity.client.RecipeCarEntityModel;
import org.randomlima.recipeone.entity.client.RecipeCarRenderer;

// minecraft f1 car sim mod
public class RecipeOneClient implements ClientModInitializer {
    public static final EntityModelLayer RECIPECAR = new EntityModelLayer(
            Identifier.of("recipeone", "recipecar"), "main"
    );
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(RECIPECAR, RecipeCarEntityModel::getTexturedModelData);

        EntityRendererRegistry.register(ModEntities.RECIPECAR, ctx -> new RecipeCarRenderer(ctx));

    }
}