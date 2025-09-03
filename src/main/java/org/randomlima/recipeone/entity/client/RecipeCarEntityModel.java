package org.randomlima.recipeone.entity.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import org.randomlima.recipeone.entity.custom.RecipeCarEntity;

@Environment(EnvType.CLIENT)
public class RecipeCarEntityModel extends EntityModel<LivingEntityRenderState> { // Replace RecipeCarEntity with your entity class
    // Top-level parts
    private final ModelPart asdf;
    private final ModelPart car;
    private final ModelPart chasis;
    private final ModelPart body;
    private final ModelPart chonk;
    private final ModelPart shield;
    private final ModelPart bone2;
    private final ModelPart bone3;
    private final ModelPart rearwing;
    private final ModelPart airintake;
    private final ModelPart wheels;
    private final ModelPart wheel;
    private final ModelPart suspention;
    private final ModelPart rearwheel;
    private final ModelPart suspention2;
    private final ModelPart front;
    private final ModelPart lwing;
    private final ModelPart wing2;
    private final ModelPart wing3;
    private final ModelPart rwing;
    private final ModelPart wing6;
    private final ModelPart wing7;

    public RecipeCarEntityModel(ModelPart root) {
        super(root);
        this.asdf = root.getChild("asdf");
        this.car = root.getChild("car");
        this.chasis = this.car.getChild("chasis");
        this.body = this.chasis.getChild("body");
        this.chonk = this.body.getChild("chonk");
        this.shield = this.body.getChild("shield");
        this.bone2 = this.shield.getChild("bone2");
        this.bone3 = this.shield.getChild("bone3");
        this.rearwing = this.body.getChild("rearwing");
        this.airintake = this.body.getChild("airintake");
        this.wheels = this.car.getChild("wheels");
        this.wheel = this.wheels.getChild("wheel");
        this.suspention = this.wheel.getChild("suspention");
        this.rearwheel = this.wheels.getChild("rearwheel");
        this.suspention2 = this.rearwheel.getChild("suspention2");
        this.front = this.car.getChild("front");
        this.lwing = this.front.getChild("lwing");
        this.wing2 = this.lwing.getChild("wing2");
        this.wing3 = this.wing2.getChild("wing3");
        this.rwing = this.front.getChild("rwing");
        this.wing6 = this.rwing.getChild("wing6");
        this.wing7 = this.wing6.getChild("wing7");
    }

    @Override
    public void setAngles(LivingEntityRenderState state) {
        // animations go here, e.g.:
        // base.yaw = state.age * 0.05F;
    }

    // Paste your getTexturedModelData() method here
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData asdf = modelPartData.addChild("asdf", ModelPartBuilder.create().uv(-101, -78).cuboid(-16.0F, -16.0F, -40.0F, 32.0F, 16.0F, 80.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 24.0F, 0.0F));

        ModelPartData car = modelPartData.addChild("car", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 24.0F, 0.0F));

        ModelPartData chasis = car.addChild("chasis", ModelPartBuilder.create().uv(0, 0).cuboid(-14.0F, -3.0F, -8.0F, 28.0F, 1.0F, 29.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = chasis.addChild("cube_r1", ModelPartBuilder.create().uv(114, 22).cuboid(-15.0F, -1.0F, 0.0F, 15.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-0.1418F, -2.0F, -13.7402F, 0.0F, 0.3927F, 0.0F));

        ModelPartData cube_r2 = chasis.addChild("cube_r2", ModelPartBuilder.create().uv(112, 50).cuboid(0.0F, -1.0F, 0.0F, 15.0F, 1.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.1418F, -2.0F, -13.7402F, 0.0F, -0.3927F, 0.0F));

        ModelPartData body = chasis.addChild("body", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, -4.0F, -9.0F));

        ModelPartData chonk = body.addChild("chonk", ModelPartBuilder.create().uv(68, 30).cuboid(-12.0F, -6.0F, 4.0F, 24.0F, 7.0F, 13.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r3 = chonk.addChild("cube_r3", ModelPartBuilder.create().uv(64, 58).cuboid(4.0F, -3.5F, -6.5F, 8.0F, 7.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(-0.9464F, -2.5F, 21.3175F, 0.0F, -0.1745F, 0.0F));

        ModelPartData cube_r4 = chonk.addChild("cube_r4", ModelPartBuilder.create().uv(116, 89).cuboid(-13.0F, -7.0F, 0.0F, 13.0F, 7.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0104F, 1.0F, -0.9749F, 0.0F, 0.3927F, 0.0F));

        ModelPartData cube_r5 = chonk.addChild("cube_r5", ModelPartBuilder.create().uv(0, 115).cuboid(0.0F, -7.0F, 0.0F, 13.0F, 7.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-0.0104F, 1.0F, -0.9749F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube_r6 = chonk.addChild("cube_r6", ModelPartBuilder.create().uv(0, 58).cuboid(-12.0F, -3.5F, -6.5F, 8.0F, 7.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.9464F, -2.5F, 21.3175F, 0.0F, 0.1745F, 0.0F));

        ModelPartData shield = body.addChild("shield", ModelPartBuilder.create().uv(28, 138).cuboid(-1.0F, -3.9663F, 2.0647F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, -7.0F, 0.0F));

        ModelPartData cube_r7 = shield.addChild("cube_r7", ModelPartBuilder.create().uv(146, 149).cuboid(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, 1.0F, 0.6109F, 0.0F, 0.0F));

        ModelPartData bone2 = shield.addChild("bone2", ModelPartBuilder.create().uv(28, 143).cuboid(-0.3432F, -0.0946F, 0.6288F, 2.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -3.0F, 7.0F, -0.1745F, 0.0F, 0.0F));

        ModelPartData cube_r8 = bone2.addChild("cube_r8", ModelPartBuilder.create().uv(150, 131).cuboid(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-0.4645F, 0.9054F, -0.0783F, 0.0F, 0.7854F, 0.0F));

        ModelPartData bone3 = shield.addChild("bone3", ModelPartBuilder.create().uv(56, 143).cuboid(-1.6569F, -0.0946F, 0.6288F, 2.0F, 2.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -3.0F, 7.0F, -0.1745F, 0.0F, 0.0F));

        ModelPartData cube_r9 = bone3.addChild("cube_r9", ModelPartBuilder.create().uv(152, 0).cuboid(-1.0F, -1.0F, -6.0F, 2.0F, 2.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.4645F, 0.9054F, -0.0783F, 0.0F, -0.7854F, 0.0F));

        ModelPartData rearwing = body.addChild("rearwing", ModelPartBuilder.create().uv(60, 113).cuboid(-9.0F, 3.0F, -12.0F, 18.0F, 0.0F, 9.0F, new Dilation(0.0F))
                .uv(68, 50).cuboid(-8.0F, 11.0F, -12.0F, 16.0F, 2.0F, 6.0F, new Dilation(0.0F))
                .uv(84, 149).cuboid(-3.0F, 9.0F, -12.0F, 6.0F, 6.0F, 7.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, -14.0F, 51.0F));

        ModelPartData cube_r10 = rearwing.addChild("cube_r10", ModelPartBuilder.create().uv(148, 112).cuboid(0.0F, 0.0F, -4.5F, 1.0F, 10.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-10.0F, 2.0F, -7.5F, 0.0F, 0.0F, -0.2182F));

        ModelPartData cube_r11 = rearwing.addChild("cube_r11", ModelPartBuilder.create().uv(28, 133).cuboid(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(28, 128).cuboid(18.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-9.5F, 1.5F, -3.0F, 0.3491F, 0.0F, 0.0F));

        ModelPartData cube_r12 = rearwing.addChild("cube_r12", ModelPartBuilder.create().uv(0, 146).cuboid(-1.0F, 0.0F, -4.5F, 1.0F, 10.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(10.0F, 2.0F, -7.5F, 0.0F, 0.0F, 0.2182F));

        ModelPartData cube_r13 = rearwing.addChild("cube_r13", ModelPartBuilder.create().uv(116, 102).cuboid(-9.0F, 0.0F, -4.0F, 18.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -3.0F, 0.3491F, 0.0F, 0.0F));

        ModelPartData airintake = body.addChild("airintake", ModelPartBuilder.create().uv(142, 29).cuboid(-2.0F, 0.0F, -12.0F, 4.0F, 4.0F, 10.0F, new Dilation(0.0F))
                .uv(154, 89).cuboid(-3.0F, -1.0F, -12.0F, 6.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, -13.0F, 28.0F));

        ModelPartData cube_r14 = airintake.addChild("cube_r14", ModelPartBuilder.create().uv(0, 30).cuboid(-5.0F, -1.5F, -12.0F, 10.0F, 4.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.5F, 0.0F, -0.0873F, 0.0F, 0.0F));

        ModelPartData cube_r15 = airintake.addChild("cube_r15", ModelPartBuilder.create().uv(114, 113).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 7.0F, 13.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -0.3927F, 0.0F, 0.0F));

        ModelPartData wheels = car.addChild("wheels", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData wheel = wheels.addChild("wheel", ModelPartBuilder.create().uv(98, 133).cuboid(12.0F, -8.0F, -23.0F, 5.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(124, 133).cuboid(-17.0F, -8.0F, -23.0F, 5.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r16 = wheel.addChild("cube_r16", ModelPartBuilder.create().uv(38, 119).cuboid(0.0F, -0.75F, -0.5F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -3.75F, -19.0F, 0.0F, 0.0F, -0.3142F));

        ModelPartData cube_r17 = wheel.addChild("cube_r17", ModelPartBuilder.create().uv(38, 115).cuboid(-9.0F, -0.75F, -0.5F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -3.75F, -19.0F, 0.0F, 0.0F, 0.3142F));

        ModelPartData suspention = wheel.addChild("suspention", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 2.0F, 0.0F));

        ModelPartData cube_r18 = suspention.addChild("cube_r18", ModelPartBuilder.create().uv(152, 18).cuboid(0.0F, -0.75F, -1.5F, 9.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -7.75F, -19.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r19 = suspention.addChild("cube_r19", ModelPartBuilder.create().uv(152, 14).cuboid(0.0F, -0.75F, -1.5F, 9.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -3.75F, -19.0F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r20 = suspention.addChild("cube_r20", ModelPartBuilder.create().uv(150, 141).cuboid(-9.0F, -0.75F, -1.5F, 9.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -7.75F, -19.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r21 = suspention.addChild("cube_r21", ModelPartBuilder.create().uv(142, 43).cuboid(-9.0F, -0.75F, -1.5F, 9.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -3.75F, -19.0F, 0.0F, 0.0F, 0.3927F));

        ModelPartData rearwheel = wheels.addChild("rearwheel", ModelPartBuilder.create().uv(0, 128).cuboid(12.0F, -9.0F, -24.0F, 5.0F, 9.0F, 9.0F, new Dilation(0.0F))
                .uv(128, 57).cuboid(-17.0F, -9.0F, -24.0F, 5.0F, 9.0F, 9.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 47.0F));

        ModelPartData cube_r22 = rearwheel.addChild("cube_r22", ModelPartBuilder.create().uv(154, 55).cuboid(0.0F, -0.75F, -0.5F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -4.25F, -19.5F, 0.0F, 0.0F, -0.3142F));

        ModelPartData cube_r23 = rearwheel.addChild("cube_r23", ModelPartBuilder.create().uv(38, 117).cuboid(-9.0F, -0.75F, -0.5F, 9.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -4.25F, -19.5F, 0.0F, 0.0F, 0.3142F));

        ModelPartData suspention2 = rearwheel.addChild("suspention2", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, 1.0F, 0.0F));

        ModelPartData cube_r24 = suspention2.addChild("cube_r24", ModelPartBuilder.create().uv(154, 51).cuboid(0.0F, -0.75F, -1.5F, 9.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -7.25F, -19.5F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r25 = suspention2.addChild("cube_r25", ModelPartBuilder.create().uv(154, 47).cuboid(0.0F, -0.75F, -1.5F, 9.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -3.25F, -19.5F, 0.0F, 0.0F, -0.3927F));

        ModelPartData cube_r26 = suspention2.addChild("cube_r26", ModelPartBuilder.create().uv(152, 10).cuboid(-9.0F, -0.75F, -1.5F, 9.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -7.25F, -19.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData cube_r27 = suspention2.addChild("cube_r27", ModelPartBuilder.create().uv(150, 145).cuboid(-9.0F, -0.75F, -1.5F, 9.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(12.0F, -3.25F, -19.5F, 0.0F, 0.0F, 0.3927F));

        ModelPartData front = car.addChild("front", ModelPartBuilder.create().uv(60, 89).cuboid(-3.0F, -4.0F, -38.0F, 6.0F, 2.0F, 22.0F, new Dilation(0.0F))
                .uv(114, 0).cuboid(-3.0F, -11.0F, -16.0F, 6.0F, 9.0F, 13.0F, new Dilation(0.0F)), ModelTransform.rotation(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r28 = front.addChild("cube_r28", ModelPartBuilder.create().uv(68, 122).cuboid(1.0F, -9.0F, 3.0F, 3.0F, 9.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.0F, -18.0F, 0.0F, 0.1745F, 0.0F));

        ModelPartData cube_r29 = front.addChild("cube_r29", ModelPartBuilder.create().uv(38, 122).cuboid(-4.0F, -9.0F, 3.0F, 3.0F, 9.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -2.0F, -18.0F, 0.0F, -0.1745F, 0.0F));

        ModelPartData cube_r30 = front.addChild("cube_r30", ModelPartBuilder.create().uv(0, 89).cuboid(-4.0F, -2.0F, 2.0F, 6.0F, 2.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -1.0505F, -39.6642F, 0.3142F, 0.0F, 0.0F));

        ModelPartData lwing = front.addChild("lwing", ModelPartBuilder.create().uv(110, 149).cuboid(11.0F, -4.0F, 0.0F, 1.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.rotation(3.706F, -2.0F, -33.4944F));

        ModelPartData cube_r31 = lwing.addChild("cube_r31", ModelPartBuilder.create().uv(98, 122).cuboid(0.0F, -2.0F, 0.0F, 4.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(128, 81).cuboid(4.0F, -1.0F, 0.0F, 13.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.706F, 0.0F, -6.5056F, 0.0F, -0.3927F, 0.0F));

        ModelPartData wing2 = lwing.addChild("wing2", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, -1.0F, 2.0F));

        ModelPartData cube_r32 = wing2.addChild("cube_r32", ModelPartBuilder.create().uv(116, 106).cuboid(0.0F, -1.0F, 0.0F, 17.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.706F, 0.5F, -6.5056F, 0.0F, -0.3927F, 0.0F));

        ModelPartData wing3 = wing2.addChild("wing3", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, -1.0F, 2.0F));

        ModelPartData cube_r33 = wing3.addChild("cube_r33", ModelPartBuilder.create().uv(116, 109).cuboid(0.0F, -1.0F, 0.0F, 17.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-3.706F, 1.0F, -6.5056F, 0.0F, -0.3927F, 0.0F));

        ModelPartData rwing = front.addChild("rwing", ModelPartBuilder.create().uv(128, 149).cuboid(-12.0F, -4.0F, 0.0F, 1.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.rotation(-3.706F, -2.0F, -33.4944F));

        ModelPartData cube_r34 = rwing.addChild("cube_r34", ModelPartBuilder.create().uv(98, 127).cuboid(-4.0F, -2.0F, 0.0F, 4.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(128, 85).cuboid(-17.0F, -1.0F, 0.0F, 13.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.706F, 0.0F, -6.5056F, 0.0F, 0.3927F, 0.0F));

        ModelPartData wing6 = rwing.addChild("wing6", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, -1.0F, 2.0F));

        ModelPartData cube_r35 = wing6.addChild("cube_r35", ModelPartBuilder.create().uv(128, 75).cuboid(-17.0F, -1.0F, 0.0F, 17.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.706F, 0.5F, -6.5056F, 0.0F, 0.3927F, 0.0F));

        ModelPartData wing7 = wing6.addChild("wing7", ModelPartBuilder.create(), ModelTransform.rotation(0.0F, -1.0F, 2.0F));

        ModelPartData cube_r36 = wing7.addChild("cube_r36", ModelPartBuilder.create().uv(128, 78).cuboid(-17.0F, -1.0F, 0.0F, 17.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(3.706F, 1.0F, -6.5056F, 0.0F, 0.3927F, 0.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }
}
