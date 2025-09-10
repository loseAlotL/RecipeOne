package org.randomlima.recipeone;

import net.minecraft.sound.SoundEvent;
import org.randomlima.recipeone.sound.ModSounds;

public enum Gear {
    REVERSE(-6, ModSounds.ENGINE_LOOP_1),
    FIRST(6, ModSounds.ENGINE_LOOP_1),
    SECOND(3.9, ModSounds.ENGINE_LOOP_2),
    THIRD(2.7, ModSounds.ENGINE_LOOP_3),
    FOURTH(1.9, ModSounds.ENGINE_LOOP_4),
    FIFTH(1.4, ModSounds.ENGINE_LOOP_5),
    SIXTH(1.1, ModSounds.ENGINE_LOOP_6),
    SEVENTH(0.9, ModSounds.ENGINE_LOOP_7),
    EIGHTH(0.7, ModSounds.ENGINE_LOOP_8);



    private final double ratio;
    private final SoundEvent sound;
    Gear(double ratio, SoundEvent sound){
        this.ratio = ratio;
        this.sound = sound;
    }
    public double getRatio(){
        return ratio;
    }
    public SoundEvent getSound(){
        return sound;
    }
}
