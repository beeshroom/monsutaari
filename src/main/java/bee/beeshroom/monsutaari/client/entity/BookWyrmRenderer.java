package bee.beeshroom.monsutaari.client.entity;

import com.mojang.blaze3d.matrix.MatrixStack;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.client.model.BookWyrmModel;
import bee.beeshroom.monsutaari.common.entities.BookWyrmEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class BookWyrmRenderer extends MobRenderer<BookWyrmEntity, BookWyrmModel> {
   private static final ResourceLocation BOOK_WYRM_LOCATION = new ResourceLocation(Monsutaari.MOD_ID, "textures/entity/book_wyrm/book_wyrm.png");

   public BookWyrmRenderer(EntityRendererManager p_i48829_1_) {
      super(p_i48829_1_, new BookWyrmModel(), 0.75F);
    //  this.addLayer(new PhantomEyesLayer<>(this));
   }

   public ResourceLocation getTextureLocation(BookWyrmEntity p_110775_1_) {
      return BOOK_WYRM_LOCATION;
   }

   protected void setupRotations(BookWyrmEntity p_225621_1_, MatrixStack p_225621_2_, float p_225621_3_, float p_225621_4_, float p_225621_5_) {
	   //   if (p_225621_1_.isResting()) {
	//         p_225621_2_.translate(0.0D, (double)-0.1F, 0.0D);
	//      } else {
	         p_225621_2_.translate(0.0D, (double)(MathHelper.cos(p_225621_3_ * 0.3F) * 0.1F), 0.0D);
	//      }

	      super.setupRotations(p_225621_1_, p_225621_2_, p_225621_3_, p_225621_4_, p_225621_5_);
	   }
}