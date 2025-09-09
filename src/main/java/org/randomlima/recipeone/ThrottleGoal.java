package org.randomlima.recipeone;

import net.minecraft.entity.player.PlayerEntity;

public class ThrottleGoal {
    private PlayerEntity player;
    private float throttle; // smoothed forward input [0, 1]
    private float brake;    // smoothed braking input [0, 1]

    public ThrottleGoal(PlayerEntity player) {
        this.player = player;
    }

    public void updateGoal() {
        double targetThrottle = 0.0;
        double targetBrake = 0.0;

        if (player.forwardSpeed > 0) {
            targetThrottle = 1.0;
        } else if (player.forwardSpeed < 0) {
            targetBrake = 1.0;
        }

        double response = 0.15;

        // Smooth throttle
        throttle += (targetThrottle - throttle) * response;
        // Smooth brake
        brake += (targetBrake - brake) * response;

        // --- Deadzone clamp ---
        double epsilon = 0.01;
        if (Math.abs(throttle - targetThrottle) < epsilon) {
            throttle = (float) targetThrottle;
        }
        if (Math.abs(brake - targetBrake) < epsilon) {
            brake = (float) targetBrake;
        }
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public float getThrottle() {
        return applyCurve(throttle);
    }

    public float getBrake() {
        return applyCurve(brake);
    }

    private float applyCurve(float x) {
        float abs = Math.abs(x);
        float curved = abs * abs * (3 - 2 * abs); // smoothstep curve
        return Math.copySign(curved, x);
    }
}
