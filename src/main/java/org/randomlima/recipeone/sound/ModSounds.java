package org.randomlima.recipeone.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import org.randomlima.recipeone.RecipeOne;

import static org.randomlima.recipeone.RecipeOne.MOD_ID;

public class ModSounds {
    public static final SoundEvent ENGINE_LOOP_1 = register("engine_loop_1");
    public static final SoundEvent ENGINE_LOOP_2 = register("engine_loop_2");
    public static final SoundEvent ENGINE_LOOP_3 = register("engine_loop_3");
    public static final SoundEvent ENGINE_LOOP_4 = register("engine_loop_4");
    public static final SoundEvent ENGINE_LOOP_5 = register("engine_loop_5");
    public static final SoundEvent ENGINE_LOOP_6 = register("engine_loop_6");
    public static final SoundEvent ENGINE_LOOP_7 = register("engine_loop_7");
    public static final SoundEvent ENGINE_LOOP_8 = register("engine_loop_8");

    private static SoundEvent register(String name) {
        System.out.println("====================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================");

        Identifier id = Identifier.of(MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void init() {
        // Call this during your mod's initialization, can be empty; registration happens statically
    }
}
