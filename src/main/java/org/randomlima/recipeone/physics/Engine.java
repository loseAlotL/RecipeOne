package org.randomlima.recipeone.physics;

import org.randomlima.recipeone.Gear;

public class Engine {

    private final double gravity = 9.81;
    private final double deltaT = 0.05;
    private final double maxRPM = 16000;
    private final double idleRPM = 3500;
    private final double driveTrainEff = 0.95;

    private double airDens = 1.225;
    private double dragCoef = 1;
    private double wheelFrictionCoef = 2.0; // racing slicks
    private double frontalArea = 1.5;
    private final double wheelRadius = 0.36;
    private final double wheelMass = 1.4;

    private static final double[] RPM_TABLE = {0, 2000, 4000, 6000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000};
    private static final double[] TORQUE_TABLE = {0, 200, 400, 520, 600, 640, 670, 700, 690, 680, 660, 600, 500};
    private static final int MAX_INDEX = RPM_TABLE.length - 1;

    private double mass = 800;
    private double rpm = idleRPM;
    private double torque;
    private Gear gear;
    private double wheelForce;
    private double netForce;
    private double rollResistance;
    private double acceleration;
    private double velocity;
    private double downforce;

    private double throttle;
    private double braking;

    private BrakeEngine brakeEngine; // weight transfer + lockup

    public Engine() {
        brakeEngine = new BrakeEngine(this); // pass engine to BrakeEngine
    }
    public BrakeEngine getBreakEngine(){return brakeEngine;}

    public void update(float throttle, float braking, Gear gear) {
        this.throttle = throttle;
        this.braking = braking;
        this.gear = gear;

        // --- Engine updates ---
        updateRPM();
        updateTorque();
        updateWheelForce();
        updateDrags();
        updateWheelInertia();
        updateAcceleration();

        // --- BrakeEngine updates weight transfer & lock detection ---
        brakeEngine.update();

        // --- Apply aerodynamic downforce ---
        downforce = 0.5 * airDens * dragCoef * frontalArea * velocity * velocity;
        double normalFront = brakeEngine.getNormalFront() + downforce * 0.5; // assume 50/50 split
        double normalRear  = brakeEngine.getNormalRear()  + downforce * 0.5;

        // --- Compute max brake forces with downforce ---
        double maxLongFront = wheelFrictionCoef * normalFront;
        double maxLongRear  = wheelFrictionCoef * normalRear;

        // --- Split brake torque per axle ---
        double totalBrakeForce = brakeEngine.getTotalBrakeForce();
        double brakeForceFront = totalBrakeForce * 0.55; // front bias
        double brakeForceRear  = totalBrakeForce * 0.45; // rear bias

        // --- Limit brake force by available traction (lockup) ---
        if (brakeForceFront > maxLongFront) brakeForceFront = maxLongFront * 0.7;
        if (brakeForceRear  > maxLongRear)  brakeForceRear  = maxLongRear  * 0.7;

        totalBrakeForce = brakeForceFront + brakeForceRear;

        // --- Combine with throttle & drag/rolling resistance ---
        netForce = wheelForce - totalBrakeForce - getDragForce() - rollResistance;

        // --- Update velocity ---
        velocity += acceleration * deltaT;
        velocity = Math.max(velocity, 0);
    }

    // ---- Existing engine methods ----
    public void updateRPM() {
        double rpmTarget = idleRPM + (maxRPM - idleRPM) * throttle;
        rpm += (rpmTarget - rpm) * deltaT * 5.0;
        rpm = Math.min(rpm, maxRPM);
        rpm = Math.max(rpm, idleRPM);
    }

    public void updateTorque() {
        if (rpm <= RPM_TABLE[0]) {
            torque = TORQUE_TABLE[0] * throttle;
            return;
        } else if (rpm >= RPM_TABLE[MAX_INDEX]) {
            torque = TORQUE_TABLE[MAX_INDEX] * throttle;
            return;
        }
        for (int i = 0; i < MAX_INDEX; i++) {
            if (rpm >= RPM_TABLE[i] && rpm <= RPM_TABLE[i + 1]) {
                double t = (rpm - RPM_TABLE[i]) / (RPM_TABLE[i + 1] - RPM_TABLE[i]);
                torque = (TORQUE_TABLE[i] + t * (TORQUE_TABLE[i + 1] - TORQUE_TABLE[i])) * throttle;
                return;
            }
        }
    }

    public void updateWheelForce() {
        wheelForce = (torque * gear.getRatio() * driveTrainEff) / wheelRadius;
        double maxTractionAccel = wheelFrictionCoef * mass * gravity; // remove half-weight cap
        wheelForce = Math.min(wheelForce, maxTractionAccel);
    }

    public void updateDrags() {
        double aeroDrag = 0.5 * airDens * dragCoef * frontalArea * velocity * velocity;
        double rollResCoef = 0.01;
        rollResistance = rollResCoef * mass * gravity;
    }

    public void updateWheelInertia() {
        double geometryFactor = 0.6;
        double wheelInertia = geometryFactor * (wheelMass * 2) * (wheelRadius * wheelRadius);
    }

    public void updateAcceleration() {
        double equivalent = 0.5 * wheelMass * wheelRadius * wheelRadius / (wheelRadius * wheelRadius);
        acceleration = netForce / (mass + equivalent);
    }

    // ---- Getters ----
    public double getVelocity() { return velocity; }
    public double getNetForce() { return netForce; }
    public double getAcceleration() { return acceleration; }
    public double getWheelForce() { return wheelForce; }
    public BrakeEngine getBrakeEngine() { return brakeEngine; }
    public double getDragForce() { return 0.5 * airDens * dragCoef * frontalArea * velocity * velocity; }
    public double getMaxBrakeTorque() { return wheelFrictionCoef * mass * gravity * wheelRadius; }
    public double getBrakeInput() { return braking; }
    public double getMass() { return mass; }
    public double getWheelRadius() { return wheelRadius; }
    public double getWheelFrictionCoef() { return wheelFrictionCoef; }
    public double getDownforce() { return downforce; }
    public double getMaxTractionForce() { return wheelFrictionCoef * mass * gravity; }
    public double getRPM(){ return rpm; }
    public Gear getGear(){return gear;}
    public double getIdleRPM(){ return idleRPM; }
    public double getMaxRPM(){ return maxRPM; }
    public double getThrottleInput(){ return throttle; }

}
