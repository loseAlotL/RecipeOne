package org.randomlima.recipeone.physics;

import net.minecraft.block.ShapeContext;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;

public class GroundEngine {
    private RecipeCarEntity car;
    private final double maxStepHeight = 1.0;
    private Vec3d position;
    private double pitch;
    private double roll;
    private Vec3d vertAdj;
    private Vec3d forwardVec;
    private Vec3d rightVec;
    private double carWidth;
    private double carLength;
    private final double rayLength = 2;
    public GroundEngine(RecipeCarEntity car){
        this.car = car;
        carWidth = 2;
        carLength = 5;
    }
    public void setCar(RecipeCarEntity car){
        this.car = car;
    }
    public void update(){
        position = car.getPos();
        forwardVec = car.getForwardVec();
        if(forwardVec == null) return;

        rightVec = forwardVec.crossProduct(new Vec3d(0,1,0)).normalize();

        updatePlaneAlignment();
    }
    public void updatePlaneAlignment() {
        Vec3d[] points = new Vec3d[] {
                new Vec3d(-carWidth/2, 0, -carLength/2),  // back-left
                new Vec3d(0, 0, -carLength/2),           // back-center
                new Vec3d(carWidth/2, 0, -carLength/2),  // back-right
                new Vec3d(-carWidth/2, 0, 0),            // middle-left
                new Vec3d(0, 0, 0),                       // center
                new Vec3d(carWidth/2, 0, 0),             // middle-right
                new Vec3d(-carWidth/2, 0, carLength/2),  // front-left
                new Vec3d(0, 0, carLength/2),            // front-center
                new Vec3d(carWidth/2, 0, carLength/2)    // front-right
        };

        double sumY = 0;
        double[][] hitYs = new double[3][3];

        for (int i = 0; i < points.length; i++) {
            // Rotate point into car orientation
            Vec3d rotatedOffset = forwardVec.multiply(points[i].z).add(rightVec.multiply(points[i].x));
            Vec3d start = position.add(rotatedOffset).add(0, 0.5, 0); // start slightly above car bottom
            Vec3d end = start.add(0, -rayLength, 0);

            RaycastContext context = new RaycastContext(
                    start,
                    end,
                    RaycastContext.ShapeType.COLLIDER,
                    RaycastContext.FluidHandling.NONE,
                    car
            );

            BlockHitResult result = car.getWorld().raycast(context);

            double hitY = (result.getType() == HitResult.Type.BLOCK)
                    ? result.getPos().y
                    : start.y - rayLength;

            sumY += hitY;

            hitYs[i / 3][i % 3] = hitY;
        }
        double avgY = sumY / points.length;

        // Compute pitch and roll using hitYs
        double frontAvg = (hitYs[2][0] + hitYs[2][1] + hitYs[2][2]) / 3.0;
        double backAvg = (hitYs[0][0] + hitYs[0][1] + hitYs[0][2]) / 3.0;
        pitch = Math.toDegrees(Math.atan2(frontAvg - backAvg, carLength));

        double leftAvg = (hitYs[0][0] + hitYs[1][0] + hitYs[2][0]) / 3.0;
        double rightAvg = (hitYs[0][2] + hitYs[1][2] + hitYs[2][2]) / 3.0;
        roll = Math.toDegrees(Math.atan2(leftAvg - rightAvg, carWidth));
    }
    public double getPitch(){
        return pitch;
    }
    public double getRoll(){
        return roll;
    }
}
