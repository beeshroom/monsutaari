package bee.beeshroom.monsutaari.client.entity;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.client.model.HeadlessSkeletonModel;
import bee.beeshroom.monsutaari.common.entities.HeadlessSkeletonEntity;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//public class HeadlessSkeletonRenderer extends BipedRenderer<AbstractSkeletonEntity, HeadlessSkeletonModel<AbstractSkeletonEntity>> {
@OnlyIn(Dist.CLIENT)
public class HeadlessSkeletonRenderer extends BipedRenderer<HeadlessSkeletonEntity, HeadlessSkeletonModel<HeadlessSkeletonEntity>> {
		

	public static final ResourceLocation TEXTURE = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/skeleton/headless_skeleton.png");
	
	public HeadlessSkeletonRenderer(EntityRendererManager manager) {
	     // super(manager, new HeadlessSkeletonModel<>(), 0.5F);
	      super(manager, new HeadlessSkeletonModel<>(), 0.5f);
	      this.addLayer(new BipedArmorLayer<>(this, new HeadlessSkeletonModel(0.5F, true), new HeadlessSkeletonModel(1.0F, true)));
	   }

	//@Override
	public ResourceLocation getTextureLocation(HeadlessSkeletonEntity entity) {
		return TEXTURE;
	}

	
}