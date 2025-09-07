package org.randomlima.recipeone;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import org.randomlima.recipeone.entity.ModEntities;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;

public class RecipeOne implements ModInitializer {
    public static final String MOD_ID = "recipeone";
    public static final EntityType<RecipeCarEntity> CAR = ModEntities.RECIPECAR;
    @Override
    public void onInitialize() {
        //FabricDefaultAttributeRegistry.register(CAR, RecipeCarEntity.createMobAttributes());
        ModEntities.registerModEntities();

    }
}

