package org.randomlima.recipeone.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.randomlima.recipeone.RecipeOne;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;

public class ModEntities {
    static Identifier id = Identifier.of(RecipeOne.MOD_ID, "recipecar");
    static RegistryKey<EntityType<?>> key = RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
    public static final EntityType<RecipeCarEntity> RECIPECAR = Registry.register(Registries.ENTITY_TYPE,
            id,
            EntityType.Builder.create(RecipeCarEntity::new, SpawnGroup.MISC)
                    .dimensions(2f,1f).build(key));



    public static void registerModEntities() {
        //RecipeOne.LOGGER.info("Registering Mod Entities for " + RecipeOne.MOD_ID);
    }
}
