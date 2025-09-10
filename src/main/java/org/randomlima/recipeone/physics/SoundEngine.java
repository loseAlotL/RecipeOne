package org.randomlima.recipeone.physics;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import org.randomlima.recipeone.Gear;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;

public class SoundEngine {
    private RecipeCarEntity car;
    private Engine engine;
    private Gear gear;
    private double rpm;
    private double baseVolume = 0.3;
    private double throttle;

    private float pitch;
    private float volume;
    private SoundEvent sound;
    public SoundEngine(RecipeCarEntity car){
        this.car = car;
        engine = car.getF1Engine();

        gear = engine.getGear();

        sound = gear.getSound();
    }
    public void update(){
        throttle = engine.getThrottleInput();
        rpm = engine.getRPM();

        gear = engine.getGear();

        sound = gear.getSound();

        float t = (float) ((rpm - engine.getIdleRPM()) / (engine.getMaxRPM() - engine.getIdleRPM())); // normalize
        pitch = 0.5f + t * (2.0f - 0.5f);

        float throttleFactor = Math.max(0f, Math.min(1f, (float)throttle));
        volume = (float) (baseVolume + (0.7 * throttleFactor));
        volume += 0.2f * t;
        volume = Math.min(volume, 2f); // clamp
    }
    public void playSound(PlayerEntity player){
        player.playSound(sound,volume,pitch);
    }
    public RecipeCarEntity getCar(){return car;}
    public SoundEvent getSound(){return sound;}
    public float getVolume(){return volume;}
    public float getPitch(){return pitch;}
}
