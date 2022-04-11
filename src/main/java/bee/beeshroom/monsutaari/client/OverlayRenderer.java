//copied from GamerRenderer, but this is too advanced for my tiny brain

/*package bee.beeshroom.monsutaari.client;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderTypeBuffers;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.potion.Effects;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OverlayRenderer extends GameRenderer {

	   private static final ResourceLocation WARPED_LOCATION = new ResourceLocation("textures/misc/nausea.png");
	   private final Minecraft minecraft;

	public OverlayRenderer(Minecraft p_i225966_1_, IResourceManager p_i225966_2_, RenderTypeBuffers p_i225966_3_) {
		super(p_i225966_1_, p_i225966_2_, p_i225966_3_);
		this.minecraft = p_i225966_1_;
	}

	private void renderWarpedOverlay(float p_243497_1_) {
	      int i = this.minecraft.getWindow().getGuiScaledWidth();
	      int j = this.minecraft.getWindow().getGuiScaledHeight();
	      double d0 = MathHelper.lerp((double)p_243497_1_, 2.0D, 1.0D);
	      float f = 0.2F * p_243497_1_;
	      float f1 = 0.4F * p_243497_1_;
	      float f2 = 0.2F * p_243497_1_;
	      double d1 = (double)i * d0;
	      double d2 = (double)j * d0;
	      double d3 = ((double)i - d1) / 2.0D;
	      double d4 = ((double)j - d2) / 2.0D;
	      RenderSystem.disableDepthTest();
	      RenderSystem.depthMask(false);
	      RenderSystem.enableBlend();
	      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
	      RenderSystem.color4f(f, f1, f2, 1.0F);
	      this.minecraft.getTextureManager().bind(WARPED_LOCATION);
	      Tessellator tessellator = Tessellator.getInstance();
	      BufferBuilder bufferbuilder = tessellator.getBuilder();
	      bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
	      bufferbuilder.vertex(d3, d4 + d2, -90.0D).uv(0.0F, 1.0F).endVertex();
	      bufferbuilder.vertex(d3 + d1, d4 + d2, -90.0D).uv(1.0F, 1.0F).endVertex();
	      bufferbuilder.vertex(d3 + d1, d4, -90.0D).uv(1.0F, 0.0F).endVertex();
	      bufferbuilder.vertex(d3, d4, -90.0D).uv(0.0F, 0.0F).endVertex();
	      tessellator.end();
	      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
	      RenderSystem.defaultBlendFunc();
	      RenderSystem.disableBlend();
	      RenderSystem.depthMask(true);
	      RenderSystem.enableDepthTest();
	   }
	
	 public void render(float p_195458_1_, long p_195458_2_, boolean p_195458_4_) {
		 if (p_195458_4_ && this.minecraft.level != null) {
	            this.minecraft.getProfiler().popPush("gui");
	            if (this.minecraft.player != null) {
	               float f = MathHelper.lerp(p_195458_1_, this.minecraft.player.oPortalTime, this.minecraft.player.portalTime);
	               if (f > 0.0F && this.minecraft.player.hasEffect(Effects.CONFUSION) && this.minecraft.options.screenEffectScale < 1.0F) {
	                  this.renderWarpedOverlay(f * (1.0F - this.minecraft.options.screenEffectScale));
	               }
	            }

	            this.minecraft.getProfiler().pop();
	         }
		 
		 
	 }
} */
