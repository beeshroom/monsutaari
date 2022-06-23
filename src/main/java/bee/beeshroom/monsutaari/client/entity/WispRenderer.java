package bee.beeshroom.monsutaari.client.entity;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.client.model.WispModel;
import bee.beeshroom.monsutaari.common.entities.WispEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WispRenderer extends MobRenderer<WispEntity, WispModel<WispEntity>> {
   private static final ResourceLocation SPEED = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/wisp/speed_wisp.png");
   private static final ResourceLocation NIGHT_VISION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/wisp/night_vision_wisp.png");
   private static final ResourceLocation JUMP = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/wisp/jump_wisp.png");
   private static final ResourceLocation REGENERATION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/wisp/regeneration_wisp.png");
   private static final ResourceLocation SLOWNESS = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/wisp/slowness_wisp.png");


   public WispRenderer(EntityRendererManager p_i46143_1_) {
	   //super(p_i46143_1_, new WispModel<>(), 0.5F); 
	     super(p_i46143_1_, new WispModel<>(16), 0.5F);
	      this.addLayer(new WispLayer<>(this));
   }  
   
   @Override
protected int getBlockLightLevel(WispEntity p_225624_1_, BlockPos p_225624_2_) {
	      return 15;
	   }
   
   @Override
public ResourceLocation getTextureLocation(WispEntity p_110775_1_) {
	     {
	         switch(p_110775_1_.getWispType()) {
	         case 0:
	         default:
	            return SPEED;
	         case 1:
	            return NIGHT_VISION;
	         case 2:
	            return JUMP;
	         case 3:
	        	 return REGENERATION;
		     case 4:
		    	 return SLOWNESS;
	         }
	   }
   }
}