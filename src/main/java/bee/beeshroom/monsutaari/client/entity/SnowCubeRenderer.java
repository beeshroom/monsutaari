package bee.beeshroom.monsutaari.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.client.model.SnowCubeModel;
import bee.beeshroom.monsutaari.common.entities.SnowCubeEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnowCubeRenderer extends MobRenderer<SnowCubeEntity, SnowCubeModel<SnowCubeEntity>> {
   private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/slime_variants/snow_cube.png");

   public SnowCubeRenderer(EntityRendererManager p_i47193_1_) {
      super(p_i47193_1_, new SnowCubeModel<>(16), 0.25F);
      this.addLayer(new SnowCubeLayer<>(this));
   }

   @Override
public void render(SnowCubeEntity p_225623_1_, float p_225623_2_, float p_225623_3_, MatrixStack p_225623_4_, IRenderTypeBuffer p_225623_5_, int p_225623_6_) {
      this.shadowRadius = 0.25F * p_225623_1_.getSize();
      super.render(p_225623_1_, p_225623_2_, p_225623_3_, p_225623_4_, p_225623_5_, p_225623_6_);
   }

   @Override
protected void scale(SnowCubeEntity p_225620_1_, MatrixStack p_225620_2_, float p_225620_3_) {
      float f = 0.999F;
      p_225620_2_.scale(0.999F, 0.999F, 0.999F);
      p_225620_2_.translate(0.0D, 0.001F, 0.0D);
      float f1 = p_225620_1_.getSize();
      float f2 = MathHelper.lerp(p_225620_3_, p_225620_1_.oSquish, p_225620_1_.squish) / (f1 * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      p_225620_2_.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
   }

   @Override
public ResourceLocation getTextureLocation(SnowCubeEntity p_110775_1_) {
      return TEXTURE_LOCATION;
   }
}