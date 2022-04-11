package bee.beeshroom.monsutaari.client.entity;

import bee.beeshroom.monsutaari.client.model.SkeletonHeadModel;
import bee.beeshroom.monsutaari.common.entities.SkeletonHeadEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletonHeadRenderer extends MobRenderer<SkeletonHeadEntity, SkeletonHeadModel<SkeletonHeadEntity>> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/skeleton/skeleton.png");
	
	public SkeletonHeadRenderer(EntityRendererManager manager) {
		super(manager, new SkeletonHeadModel<>(), 0.4f); //0.7f
	}

	//@Override
	public ResourceLocation getTextureLocation(SkeletonHeadEntity entity) {
		return TEXTURE;
	}

	
}