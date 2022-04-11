package bee.beeshroom.monsutaari.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import bee.beeshroom.monsutaari.common.entities.BookWyrmEntity;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


public class BookWyrmModel extends SegmentedModel<BookWyrmEntity> {
	private final ModelRenderer leftWing;
	private final ModelRenderer pages_r1;
	private final ModelRenderer rightWing;
	private final ModelRenderer pages_r2;
	private final ModelRenderer tail;
	private final ModelRenderer tail_r1;
	private final ModelRenderer body;

	public BookWyrmModel() {
		texWidth = 64;
		texHeight = 64;

		leftWing = new ModelRenderer(this);
		//leftWing.setRotationPoint(-0.1305F, 22.0086F, -5.0F);
		setRotationAngle(leftWing, 0.0F, 0.0F, -0.1309F);
		

		pages_r1 = new ModelRenderer(this);
		//pages_r1.setRotationPoint(0.0F, 1.0F, 5.0F);
		leftWing.addChild(pages_r1);
		setRotationAngle(pages_r1, -1.219F, -0.123F, 0.0859F);
		pages_r1.setTexSize(26, 0).addBox(-6.5068F, -4.7463F, -12.0529F, 7.0F, 1.0F, 8.0F, 0.0F, false);
		pages_r1.setTexSize(0, 10).addBox(-7.3829F, -4.7799F, -13.0529F, 8.0F, 0.0F, 10.0F, 0.0F, false);

		rightWing = new ModelRenderer(this);
	//	rightWing.setRotationPoint(8.0F, 23.0F, -5.0F);
		setRotationAngle(rightWing, 0.0F, 0.0F, 0.1309F);
		

		pages_r2 = new ModelRenderer(this);
	//	pages_r2.setRotationPoint(-8.0F, 1.0F, 5.0F);
		rightWing.addChild(pages_r2);
		setRotationAngle(pages_r2, -1.219F, 0.123F, -0.0859F);
		pages_r2.setTexSize(18, 18).addBox(-0.4932F, -4.7463F, -12.0529F, 7.0F, 1.0F, 8.0F, 0.0F, false);
		pages_r2.setTexSize(0, 0).addBox(-0.6171F, -4.7799F, -13.0529F, 8.0F, 0.0F, 10.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		//tail.setRotationPoint(0.0F, 22.0F, 0.0F);
		

		tail_r1 = new ModelRenderer(this);
		//tail_r1.setRotationPoint(0.0F, -8.0F, -2.0F);
		tail.addChild(tail_r1);
		setRotationAngle(tail_r1, -1.2217F, 0.0F, 0.0F);
		tail_r1.setTexSize(0, 14).addBox(0.0F, -3.0F, 5.0F, 0.0F, 2.0F, 6.0F, 0.0F, false);

		body = new ModelRenderer(this);
	//	body.setRotationPoint(0.0F, 25.0F, 3.0F);
		body.setTexSize(0, 0).addBox(-1.0F, -14.0F, -4.0F, 3.0F, 7.0F, 2.0F, 0.0F, false);
	}

	//@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
	}

	//@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		leftWing.render(matrixStack, buffer, packedLight, packedOverlay);
		rightWing.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_,
			float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		leftWing.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_);
		rightWing.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_);
		tail.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_);
		body.render(p_225598_1_, p_225598_2_, p_225598_3_, p_225598_4_);	
	}

	@Override
	public Iterable<ModelRenderer> parts() {
		// TODO Auto-generated method stub
		return null;
	}

	 public void setupAnim(BookWyrmEntity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
	 /*     if (p_225597_1_.isResting()) {
	         this.head.xRot = p_225597_6_ * ((float)Math.PI / 180F);
	         this.head.yRot = (float)Math.PI - p_225597_5_ * ((float)Math.PI / 180F);
	         this.head.zRot = (float)Math.PI;
	         this.head.setPos(0.0F, -2.0F, 0.0F);
	         this.rightWing.setPos(-3.0F, 0.0F, 3.0F);
	         this.leftWing.setPos(3.0F, 0.0F, 3.0F);
	         this.body.xRot = (float)Math.PI;
	         this.rightWing.xRot = -0.15707964F;
	         this.rightWing.yRot = -1.2566371F;
	         this.rightWingTip.yRot = -1.7278761F;
	         this.leftWing.xRot = this.rightWing.xRot;
	         this.leftWing.yRot = -this.rightWing.yRot;
	         this.leftWingTip.yRot = -this.rightWingTip.yRot;
	      } else { */
	     /*    this.head.xRot = p_225597_6_ * ((float)Math.PI / 180F);
	         this.head.yRot = p_225597_5_ * ((float)Math.PI / 180F);
	         this.head.zRot = 0.0F;
	         this.head.setPos(0.0F, 0.0F, 0.0F); */
	         this.rightWing.setPos(0.0F, 0.0F, 0.0F);
	         this.leftWing.setPos(0.0F, 0.0F, 0.0F);
	         this.body.xRot = ((float)Math.PI / 4F) + MathHelper.cos(p_225597_4_ * 0.1F) * 0.15F;
	         this.body.yRot = 0.0F;
	         this.rightWing.yRot = MathHelper.cos(p_225597_4_ * 1.3F) * (float)Math.PI * 0.25F;
	         this.leftWing.yRot = -this.rightWing.yRot;
	    /*     this.rightWingTip.yRot = this.rightWing.yRot * 0.5F;
	         this.leftWingTip.yRot = -this.rightWing.yRot * 0.5F; */
	      }

	   }
