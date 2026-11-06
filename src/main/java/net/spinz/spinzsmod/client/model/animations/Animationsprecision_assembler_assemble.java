package net.spinz.spinzsmod.client.model.animations;

import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.AnimationChannel;

public class Animationsprecision_assembler_assemble {
	public static final AnimationDefinition assemble = AnimationDefinition.Builder.withLength(4.0667F).looping()
			.addAnimation("claw",
					new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
							new Keyframe(0.6F, KeyframeAnimations.degreeVec(0.0F, -720.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.addAnimation("rightpincer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(1.1333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3667F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.2667F, KeyframeAnimations.degreeVec(-17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("leftpincer", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(1.2F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3667F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.2667F, KeyframeAnimations.degreeVec(17.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(2.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)))
			.addAnimation("arm", new AnimationChannel(AnimationChannel.Targets.ROTATION, new Keyframe(0.9333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.1F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM), new Keyframe(1.1333F, KeyframeAnimations.degreeVec(0.0F, -12.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.3667F, KeyframeAnimations.degreeVec(1.1069F, -12.4517F, -5.1207F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.5F, KeyframeAnimations.degreeVec(1.11F, -12.45F, -5.12F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.7333F, KeyframeAnimations.degreeVec(0.0032F, -12.4986F, 0.0007F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.2667F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.5F, KeyframeAnimations.degreeVec(-0.9096F, 19.9802F, -2.6602F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.6F, KeyframeAnimations.degreeVec(-0.91F, 19.98F, -2.66F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.8F, KeyframeAnimations.degreeVec(-0.0004F, 19.9999F, 0.0002F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(3.1333F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR), new Keyframe(3.8667F, KeyframeAnimations.degreeVec(-360.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)))
			.build();
}