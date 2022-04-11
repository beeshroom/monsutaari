package bee.beeshroom.monsutaari.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.client.model.ClayCubeModel;
import bee.beeshroom.monsutaari.common.entities.ClayCubeEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClayCubeRenderer extends MobRenderer<ClayCubeEntity, ClayCubeModel<ClayCubeEntity>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/slime_variants/clay_cube.png");
   private static final ResourceLocation CUTE_LOCATION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/slime_variants/clay_cube_cute.png");
   private static final ResourceLocation SLIME_LOCATION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/slime_variants/clay_cube_slime.png");
   private static final ResourceLocation CREEPER_LOCATION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/slime_variants/clay_cube_creeper.png");
   private static final ResourceLocation STEVE_LOCATION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/slime_variants/clay_cube_steve.png");
   
   public ClayCubeRenderer(EntityRendererManager p_i47193_1_) {
      super(p_i47193_1_, new ClayCubeModel<>(16), 0.25F);
      this.addLayer(new ClayCubeLayer<>(this));
   }

   public void render(ClayCubeEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
      this.shadowRadius = 0.25F * (float)p_225623_1_.getSize();
      super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
   }

   protected void scale(ClayCubeEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
      float f = 0.999F;
      p_225620_2_.scale(0.999F, 0.999F, 0.999F);
      p_225620_2_.translate(0.0D, (double)0.001F, 0.0D);
      float f1 = (float)p_225620_1_.getSize();
      float f2 = MathHelper.lerp(p_225620_3_, p_225620_1_.oSquish, p_225620_1_.squish) / (f1 * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      p_225620_2_.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
   }
   
   public ResourceLocation getTextureLocation(ClayCubeEntity p_110775_1_) {
	     {
	         switch(p_110775_1_.getSlimeType()) {
	         case 0:
	         default:
	            return TEXTURE_LOCATION;
	         case 1:
	            return CUTE_LOCATION;
	         case 2:
	            return SLIME_LOCATION;
	         case 3:
	        	 return CREEPER_LOCATION;
		     case 4:
		    	 return STEVE_LOCATION;
	         }
	   }
   }
}