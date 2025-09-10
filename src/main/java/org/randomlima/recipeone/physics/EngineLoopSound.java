package org.randomlima.recipeone.physics;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.sound.SoundCategory;

@Environment(EnvType.CLIENT)
public class EngineLoopSound extends MovingSoundInstance {
    private final SoundEngine soundEngine;

    public EngineLoopSound(SoundEngine soundEngine) {
        super(soundEngine.getSound(), SoundCategory.NEUTRAL, SoundInstance.createRandom());
        this.soundEngine = soundEngine;
        this.repeat = true;
        this.repeatDelay = 0;
    }

    @Override
    public void tick() {
        if (!soundEngine.getCar().isAlive()) {
            this.setDone();
            return;
        }

        soundEngine.update();
        this.pitch = soundEngine.getPitch();
        this.volume = soundEngine.getVolume();
        this.x = (float) soundEngine.getCar().getX();
        this.y = (float) soundEngine.getCar().getY();
        this.z = (float) soundEngine.getCar().getZ();
    }
}
