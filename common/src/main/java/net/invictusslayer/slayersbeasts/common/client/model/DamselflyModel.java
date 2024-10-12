package net.invictusslayer.slayersbeasts.common.client.model;

import net.invictusslayer.slayersbeasts.common.SlayersBeasts;
import net.invictusslayer.slayersbeasts.common.client.animation.DamselflyAnimation;
import net.invictusslayer.slayersbeasts.common.entity.Damselfly;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class DamselflyModel<T extends Damselfly> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SlayersBeasts.MOD_ID, "damselfly_model"), "main");
	private final ModelPart root;

	public DamselflyModel(ModelPart root) {
		this.root = root;
	}

	public ModelPart root() {
		return root;
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		root.getAllParts().forEach(ModelPart::resetPose);
		animate(Damselfly.flyAnimationState, DamselflyAnimation.FLY, ageInTicks, 10);
		animate(Damselfly.perchAnimationState, DamselflyAnimation.PERCH, ageInTicks, 1.5F);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, 0.0F, -6.0F, 3.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(18, 19).addBox(-1.5F, -1.0F, -6.0F, 2.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 8).addBox(-1.5F, 0.0F, -8.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 4).addBox(-2.75F, -0.5F, -8.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.25F, -0.5F, -8.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -0.5F, 0.0F, 2.0F, 2.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 22.0F, -2.0F));

		PartDefinition wing_left_front = body.addOrReplaceChild("wing_left_front", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, 0.0F, -1.0F, 15.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.0F, -4.75F, 0.0F, 0.1745F, 0.0F));

		PartDefinition wing_left_hind = body.addOrReplaceChild("wing_left_hind", CubeListBuilder.create().texOffs(13, 8).addBox(0.0F, 0.0F, -1.0F, 15.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -0.5F, -2.25F, 0.0F, -0.1745F, 0.0F));

		PartDefinition wing_right_front = body.addOrReplaceChild("wing_right_front", CubeListBuilder.create().texOffs(13, 4).addBox(-15.0F, 0.0F, -1.0F, 15.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -1.0F, -4.75F, 0.0F, -0.1745F, 0.0F));

		PartDefinition wing_right_hind = body.addOrReplaceChild("wing_right_hind", CubeListBuilder.create().texOffs(13, 0).addBox(-15.0F, 0.0F, -1.0F, 15.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -0.5F, -2.25F, 0.0F, 0.1745F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
