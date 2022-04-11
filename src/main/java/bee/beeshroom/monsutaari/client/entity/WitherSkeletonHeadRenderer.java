package bee.beeshroom.monsutaari.client.entity;
import com.mojang.blaze3d.matrix.MatrixStack;

import bee.beeshroom.monsutaari.client.model.WitherSkeletonHeadModel;
import bee.beeshroom.monsutaari.common.entities.WitherSkeletonHeadEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WitherSkeletonHeadRenderer extends MobRenderer<WitherSkeletonHeadEntity, WitherSkeletonHeadModel<WitherSkeletonHeadEntity>> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
	
	public WitherSkeletonHeadRenderer(EntityRendererManager manager) {
		super(manager, new WitherSkeletonHeadModel<>(), 0.55f); //0.7f
	}

	//@Override
	public ResourceLocation getTextureLocation(WitherSkeletonHeadEntity entity) {
		return TEXTURE;
	}

	protected void scale(WitherSkeletonHeadEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
	      p_225620_2_.scale(1.2F, 1.2F, 1.2F);
	   }
	
}