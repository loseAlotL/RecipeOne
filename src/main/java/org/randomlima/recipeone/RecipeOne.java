package org.randomlima.recipeone;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import org.randomlima.recipeone.entity.ModEntities;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;
import org.randomlima.recipeone.sound.ModSounds;

import java.util.logging.Logger;

public class RecipeOne implements ModInitializer {
    public static final String MOD_ID = "recipeone";
    public static final EntityType<RecipeCarEntity> CAR = ModEntities.RECIPECAR;
    @Override
    public void onInitialize() {
        //FabricDefaultAttributeRegistry.register(CAR, RecipeCarEntity.createMobAttributes());
        ModEntities.registerModEntities();
        ModSounds.init();
    }
}

