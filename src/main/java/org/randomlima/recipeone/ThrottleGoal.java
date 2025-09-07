package org.randomlima.recipeone;

import net.minecraft.entity.player.PlayerEntity;

public class ThrottleGoal {
    private PlayerEntity player;
    private float throttle; // smoothed raw input [-1, 1]

    public ThrottleGoal(PlayerEntity player) {
        this.player = player;
    }

    public void updateGoal() {
        double target = 0.0;

        if (player.forwardSpeed > 0) {
            target = 1.0;
        } else if (player.forwardSpeed < 0) {
            target = -1.0;
        }

        double response = 0.15;
        throttle += (target - throttle) * response;

        // --- Deadzone clamp ---
        double epsilon = 0.01; // tweak
        if (Math.abs(throttle - target) < epsilon) {
            throttle = (float) target;
        }
    }

    public void setPlayer(PlayerEntity player){
        this.player = player;
    }

    public float getThrottle() {
        float x = throttle;
        float abs = Math.abs(x);

        float curved = abs * abs * (3 - 2 * abs);

        return Math.copySign(curved, x);
    }
}
