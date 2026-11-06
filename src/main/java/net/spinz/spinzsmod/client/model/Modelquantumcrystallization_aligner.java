package net.spinz.spinzsmod.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 5.2.0
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modelquantumcrystallization_aligner<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("spinzs_mod", "modelquantumcrystallization_aligner"), "main");
	public final ModelPart laser;
	public final ModelPart shell;

	public Modelquantumcrystallization_aligner(ModelPart root) {
		this.laser = root.getChild("laser");
		this.shell = root.getChild("shell");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition laser = partdefinition.addOrReplaceChild("laser", CubeListBuilder.create(), PartPose.offset(0.0F, 16.5F, 0.0F));
		PartDefinition cube_r1 = laser.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(56, 23).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -2.3562F, 0.0F));
		PartDefinition cube_r2 = laser.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(56, 23).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 11.0F, 0.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, -0.7854F, 0.0F));
		PartDefinition shell = partdefinition.addOrReplaceChild("shell",
				CubeListBuilder.create().texOffs(0, 35).addBox(-7.0F, -1.0F, -7.0F, 14.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)).texOffs(64, 24).addBox(-6.0F, 1.0F, 2.5F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(48, 10)
						.addBox(2.0F, 1.0F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(64, 24).addBox(-6.0F, 2.5F, 2.5F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(48, 10)
						.addBox(2.0F, 2.5F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(48, 10).addBox(2.0F, 7.0F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(64, 24)
						.addBox(-6.0F, 7.0F, 2.5F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(64, 24).addBox(-6.0F, 8.5F, 2.5F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(48, 10)
						.addBox(2.0F, 8.5F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(48, 10).addBox(2.0F, 10.0F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(64, 24)
						.addBox(-6.0F, 10.0F, 2.5F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(56, 35).addBox(-6.0F, -3.5F, -6.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)).texOffs(66, 19)
						.addBox(-6.5F, -4.0F, 3.0F, 13.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(66, 2).addBox(-4.0F, -4.0F, -6.5F, 1.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(66, 2)
						.addBox(3.0F, -4.0F, -6.5F, 1.0F, 4.0F, 13.0F, new CubeDeformation(0.0F)).texOffs(66, 19).addBox(-6.5F, -4.0F, -4.0F, 13.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(48, 21)
						.addBox(-7.0F, 0.5F, -7.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 21).addBox(5.0F, 0.5F, -7.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 21)
						.addBox(-7.0F, 0.5F, 5.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(48, 21).addBox(5.0F, 0.5F, 5.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 18)
						.addBox(-8.0F, -0.5F, -8.0F, 16.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-8.0F, 11.5F, -8.0F, 16.0F, 2.0F, 16.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 10.5F, 0.0F));
		PartDefinition cube_r3 = shell.addOrReplaceChild("cube_r3",
				CubeListBuilder.create().texOffs(64, 24).addBox(-6.0F, -0.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(64, 24).addBox(-6.0F, -2.0F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(64, 24)
						.addBox(-6.0F, -3.5F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(64, 24).addBox(-6.0F, -8.0F, -2.0F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 10.5F, -4.5F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r4 = shell.addOrReplaceChild("cube_r4",
				CubeListBuilder.create().texOffs(48, 10).addBox(-6.0F, -1.0F, -3.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(48, 10).addBox(-6.0F, -2.5F, -3.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(48, 10)
						.addBox(-6.0F, -4.0F, -3.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(48, 10).addBox(-6.0F, -8.5F, -3.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(48, 10)
						.addBox(-6.0F, -10.0F, -3.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-8.0F, 11.0F, -0.5F, 0.0F, 3.1416F, 0.0F));
		PartDefinition cube_r5 = shell.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(64, 24).addBox(-6.0F, -0.5F, -2.5F, 12.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 1.5F, -5.0F, 0.0F, 3.1416F, 0.0F));
		return LayerDefinition.create(meshdefinition, 128, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int rgb) {
		laser.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
		shell.render(poseStack, vertexConsumer, packedLight, packedOverlay, rgb);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}