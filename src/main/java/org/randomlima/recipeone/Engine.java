package org.randomlima.recipeone;

import net.minecraft.entity.MovementType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;

public class Engine {

    private final double gravity = 9.81;
    private final double deltaT = 0.05;
    private final double maxRPM = 16000;
    private final double idleRPM = 3500;
    private final double driveTrainEff = 0.95;
    private double airDens = 1.225;
    private double dragCoef = 0.3; // 0.25-0.45?
    private double frictionCoef = 1;
    private double frontalArea = 1.5; // 1.2-1.7m^2?
    private final double k = 5.0;
    private final double wheelRadius = 0.36;
    private final double wheelMass = 1.4;
    private static final double[] RPM_TABLE = {
            0, 2000, 4000, 6000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000, 16000
    };

    private static final double[] TORQUE_TABLE = {
            0, 200, 400, 520, 600, 640, 670, 700, 690, 680, 660, 600, 500
    };
    private static final int MAX_INDEX = RPM_TABLE.length - 1;
    private PlayerEntity player;
    private double throttle;
    private double rpm = idleRPM;
    private double torque;
    private Gear gear;
    private double wheelForce;
    private double netForce;
    private double mass = 800;
    private double wheelInertia;
    private double maxTraction;
    private double acceleration;
    private double velocity;

    public Engine(){

    }
    public void update(float throttle, Gear gear){
        this.throttle = throttle;
        this.gear = gear;

        updateRPM();
        updateTorque();
        updateWheelTraction();
        updateWheelForce();
        updateDrags();
        updateWheelInertia();
        updateAcceleration();
        velocity += acceleration * deltaT;
    }
    public void updateRPM() {
        double rpmTarget = idleRPM + (maxRPM - idleRPM) * throttle;
        rpm += (rpmTarget - rpm) * deltaT * k;
        rpm = Math.min(rpm, maxRPM);  // clamp to maxRPM
        rpm = Math.max(rpm, idleRPM); // clamp to idleRPM
    }


    public void updateTorque() {
        if (rpm <= RPM_TABLE[0]) {
            torque = TORQUE_TABLE[0] * throttle;
            return;
        } else if (rpm >= RPM_TABLE[MAX_INDEX]) {
            torque = TORQUE_TABLE[MAX_INDEX] * throttle;
            return;
        }

        // interpolate between table values
        for (int i = 0; i < MAX_INDEX; i++) {
            if (rpm >= RPM_TABLE[i] && rpm <= RPM_TABLE[i + 1]) {
                double t = (rpm - RPM_TABLE[i]) / (RPM_TABLE[i + 1] - RPM_TABLE[i]);
                double interpTorque = TORQUE_TABLE[i] + t * (TORQUE_TABLE[i + 1] - TORQUE_TABLE[i]);
                torque = interpTorque * throttle;
                return;
            }
        }
    }
    public void updateWheelTraction(){
        maxTraction = frictionCoef * mass * gravity * 0.5;
    }
    public void updateWheelForce(){
        wheelForce = (torque * gear.getRatio() * driveTrainEff)/wheelRadius;
        wheelForce = Math.min(wheelForce, maxTraction);
    }
    public void updateDrags(){
        double aeroDrag = 1.2 * airDens * dragCoef * frontalArea * velocity * velocity;
        double rollResCoef = 0.01;
        netForce = wheelForce - (aeroDrag + (rollResCoef*mass*gravity));
    }
    public void updateWheelInertia(){
        double geometryFactor = 0.6; // geometry factor
        wheelInertia = geometryFactor * (wheelMass * 2) * (wheelRadius * wheelRadius);
        // wheelMass * 2 cuz 2 driving wheels
    }
    public void updateAcceleration(){
        double equivalent = wheelInertia / (wheelRadius * wheelRadius);
        acceleration = netForce / (mass + equivalent);
    }
    public double getVelocity(){
        return velocity;
    }
    public void printData(){
        System.out.println("[Engine] Torque: ["+torque+"] | RPM: ["+rpm+"] | Throttle: ["+throttle+"]");
        System.out.println("    |    Velocity: ["+velocity+"] | Acceleration: ["+acceleration+"]");
        System.out.println("==============================================");
    }
}
