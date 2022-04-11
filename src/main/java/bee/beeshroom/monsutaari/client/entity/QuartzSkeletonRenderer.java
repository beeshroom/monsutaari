package bee.beeshroom.monsutaari.client.entity;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.client.model.QuartzSkeletonModel;
import bee.beeshroom.monsutaari.common.entities.QuartzSkeletonEntity;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/* @OnlyIn(Dist.CLIENT)
public class QuartzSkeletonRenderer extends MobRenderer<QuartzSkeletonEntity, QuartzSkeletonModel<QuartzSkeletonEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/skeleton/quartz_skeleton.png");

   public QuartzSkeletonRenderer(EntityRendererManager manager) {
      super(manager, new QuartzSkeletonModel<>(), 0.5F);
    //  this.addLayer(new BipedArmorLayer<>(this, new QuartzSkeletonModel(0.5F), new QuartzSkeletonModel(1.0F)));
   }

   public ResourceLocation getTextureLocation(QuartzSkeletonEntity p_110775_1_) {
      return TEXTURE;
   }
} */

@OnlyIn(Dist.CLIENT)
public class QuartzSkeletonRenderer extends BipedRenderer<QuartzSkeletonEntity, QuartzSkeletonModel<QuartzSkeletonEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/skeleton/quartz_skeleton.png");
   private static final ResourceLocation TEXTURE_NO_QUARTZ = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/skeleton/quartzless_quartz_skeleton.png");
  
   public QuartzSkeletonRenderer(EntityRendererManager p_i46143_1_) {
      super(p_i46143_1_, new QuartzSkeletonModel<>(), 0.5F);
     // this.addLayer(new BipedArmorLayer<>(this, new SkeletonModel(0.5F, true), new SkeletonModel(1.0F, true)));
   }

   public ResourceLocation getTextureLocation(QuartzSkeletonEntity p_110775_1_) {
	   switch(p_110775_1_.getQuartzGrown()) {
       case 0:
       default:
          return TEXTURE;
       case 1:
          return TEXTURE_NO_QUARTZ;
   }
   }
}