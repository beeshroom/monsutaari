package bee.beeshroom.monsutaari.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

@OnlyIn(Dist.CLIENT)

public class WispModel<T extends Entity> extends SegmentedModel<T> {
//public class WispModel<T extends MobEntity> extends EntityModel<T> {
	private final ModelRenderer body;

	public WispModel(int p_i1157_1_) {
	//	public WispModel() {
		texWidth = 32;
		texHeight = 32;
		
		body = new ModelRenderer(this, 0, p_i1157_1_);
	//	body = new ModelRenderer(this);
		//body.setPos(0.0F, 24.0F, 0.0F);
	//	body.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		
		  if (p_i1157_1_ > 0) {
			//	body.setPos(0.0F, 24.0F, 0.0F);
			//	body.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		      } else {
		    		body.setPos(0.0F, 24.0F, 0.0F);
		    		body.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		      } 
	}

	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_,
			float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		body.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_);
		
	}


	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_,
			float p_225597_6_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		 return ImmutableList.of(this.body);
	} 
}