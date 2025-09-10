package org.randomlima.recipeone.physics;

public class BrakeEngine {

    private final Engine engine;
    private final double gravity = 9.81;

    private double frontWeightFraction = 0.5; // static weight distribution
    private double rearWeightFraction  = 0.5;
    private double frontBrakeBias      = 0.55; // 55% front brake bias
    private double rearBrakeBias       = 0.45; // 45% rear brake bias

    private double normalFront;
    private double normalRear;

    private double totalBrakeForce;
    private double brakeForceFront;
    private double brakeForceRear;

    public BrakeEngine(Engine engine) {
        this.engine = engine;
    }

    public void update() {
        double brakingInput = engine.getBrakeInput(); // 0.0-1.0

        // --- 1. Calculate weight transfer ---
        double totalWeight = engine.getMass() * gravity;
        // simple longitudinal weight transfer: deltaWeight = (h / L) * m * a
        // assume center of mass height h = 0.5 m, wheelbase L = 2 m
        double h = 0.5;
        double L = 2.0;
        double longitudinalAccel = engine.getAcceleration(); // m/s²
        double deltaWeight = (h / L) * engine.getMass() * longitudinalAccel;

        normalFront = totalWeight * frontWeightFraction + deltaWeight * 0.5 + engine.getDownforce() * frontWeightFraction;
        normalRear  = totalWeight * rearWeightFraction  - deltaWeight * 0.5 + engine.getDownforce() * rearWeightFraction;

        // --- 2. Compute max longitudinal force per axle ---
        double mu = engine.getWheelFrictionCoef();
        double maxLongFront = mu * normalFront;
        double maxLongRear  = mu * normalRear;

        // --- 3. Total requested brake force from input ---
        double maxBrakeTorque = engine.getMaxBrakeTorque(); // already includes wheel radius
        totalBrakeForce = brakingInput * maxBrakeTorque / engine.getWheelRadius();

        // --- 4. Split brake force across axles ---
        brakeForceFront = totalBrakeForce * frontBrakeBias;
        brakeForceRear  = totalBrakeForce * rearBrakeBias;

        // --- 5. Clamp brake force to available traction (simulate lockup) ---
        if (brakeForceFront > maxLongFront) brakeForceFront = maxLongFront * 0.7; // slide factor
        if (brakeForceRear  > maxLongRear)  brakeForceRear  = maxLongRear  * 0.7;

        // --- 6. Combine final brake force ---
        totalBrakeForce = brakeForceFront + brakeForceRear;
    }

    // --- Getters ---
    public double getTotalBrakeForce() { return totalBrakeForce; }
    public double getBrakeForceFront() { return brakeForceFront; }
    public double getBrakeForceRear()  { return brakeForceRear; }
    public double getNormalFront() { return normalFront; }
    public double getNormalRear()  { return normalRear; }

}
