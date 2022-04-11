package bee.beeshroom.monsutaari.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.client.model.HeadlessWitherSkeletonModel;
import bee.beeshroom.monsutaari.common.entities.HeadlessWitherSkeletonEntity;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//public class HeadlessSkeletonRenderer extends BipedRenderer<AbstractSkeletonEntity, HeadlessSkeletonModel<AbstractSkeletonEntity>> {
@OnlyIn(Dist.CLIENT)
public class HeadlessWitherSkeletonRenderer extends BipedRenderer<HeadlessWitherSkeletonEntity, HeadlessWitherSkeletonModel<HeadlessWitherSkeletonEntity>> {
		

	public static final ResourceLocation TEXTURE = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/skeleton/headless_wither_skeleton.png");
	
	public HeadlessWitherSkeletonRenderer(EntityRendererManager manager) {
	      super(manager, new HeadlessWitherSkeletonModel<>(), 0.5f);
	      this.addLayer(new BipedArmorLayer<>(this, new HeadlessWitherSkeletonModel(0.5F, true), new HeadlessWitherSkeletonModel(1.0F, true)));
	   }

	//@Override
	public ResourceLocation getTextureLocation(HeadlessWitherSkeletonEntity entity) {
		return TEXTURE;
	}

	protected void scale(HeadlessWitherSkeletonEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
	      p_225620_2_.scale(1.2F, 1.2F, 1.2F);
	   }
	
}