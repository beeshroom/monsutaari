package bee.beeshroom.monsutaari.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

@OnlyIn(Dist.CLIENT)
public class QuartzSkeletonModel<T extends MobEntity> extends BipedModel<T> {
   public QuartzSkeletonModel() {
      this(0.0F, false);
   }

/*	//private final ModelRenderer waist;
	private final ModelRenderer body;
	private final ModelRenderer head;
//	private final ModelRenderer hat;
	private final ModelRenderer rightArm;
	//private final ModelRenderer rightItem;
	private final ModelRenderer leftArm;
	//private final ModelRenderer leftItem;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg; */
	
	  public QuartzSkeletonModel(float p_i46303_1_, boolean p_i46303_2_) {
	      super(p_i46303_1_);
	      
	    //  texWidth = 64;
		//	texHeight = 32;
	      
	      if (!p_i46303_2_) {
	    	  body = new ModelRenderer(this);
	    	  this.body.texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
	  		 this.body.texOffs(8, 16).addBox(0.0F, 0.0F, 2.0F, 0.0F, 12.0F, 4.0F, 0.0F, true);
	  		 this.body.texOffs(9, 13).addBox(0.0F, 2.0F, -5.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
	  		 
	  		this.head = new ModelRenderer(this);
		//	this.head.setPos(0.0F, 0.0F, 0.0F);
			this.head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
			this.head.addBox(-6.0F, -10.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);
	       
	  		 this.rightArm = new ModelRenderer(this, 40, 16);
	         this.rightArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, p_i46303_1_);
	         
	 		rightArm.texOffs(48, 20).addBox(0.0F, 7.0F, -2.0F, 0.0F, 6.0F, 4.0F, 0.0F, false);
	         
	         this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
	         this.leftArm = new ModelRenderer(this, 40, 16);
	         this.leftArm.mirror = true;
	         this.leftArm.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, p_i46303_1_);
	         
	     	leftArm.texOffs(48, 20).addBox(0.0F, 7.0F, -2.0F, 0.0F, 6.0F, 4.0F, 0.0F, false);
	         
	         this.leftArm.setPos(5.0F, 2.0F, 0.0F);
	         this.rightLeg = new ModelRenderer(this, 0, 16);
	         this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, p_i46303_1_);
	         this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
	         this.leftLeg = new ModelRenderer(this, 0, 16);
	         this.leftLeg.mirror = true;
	         this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, p_i46303_1_);
	         this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
	      }

	   }
/*	public skeleton() {
		textureWidth = 64;
		textureHeight = 32;

		waist = new ModelRenderer(this);
		waist.setRotationPoint(0.0F, 12.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -12.0F, 0.0F);
		waist.addChild(body);
		body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		body.setTextureOffset(8, 16).addBox(0.0F, 0.0F, 2.0F, 0.0F, 12.0F, 4.0F, 0.0F, true);
		body.setTextureOffset(9, 13).addBox(0.0F, 2.0F, -5.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(head);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-6.0F, -10.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, false);

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(hat);
		hat.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		body.addChild(rightArm);
		rightArm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);
		rightArm.setTextureOffset(48, 20).addBox(0.0F, 7.0F, -2.0F, 0.0F, 6.0F, 4.0F, 0.0F, false);

		rightItem = new ModelRenderer(this);
		rightItem.setRotationPoint(-1.0F, 7.0F, 1.0F);
		rightArm.addChild(rightItem);
		

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		body.addChild(leftArm);
		leftArm.setTextureOffset(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);
		leftArm.setTextureOffset(48, 20).addBox(0.0F, 7.0F, -2.0F, 0.0F, 6.0F, 4.0F, 0.0F, false);

		leftItem = new ModelRenderer(this);
		leftItem.setRotationPoint(1.0F, 7.0F, 1.0F);
		leftArm.addChild(leftItem);
		

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		body.addChild(rightLeg);
		rightLeg.setTextureOffset(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
		body.addChild(leftLeg);
		leftLeg.setTextureOffset(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 0.0F, true);
	} */

	   public void prepareMobModel(T p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
		      this.rightArmPose = BipedModel.ArmPose.EMPTY;
		      this.leftArmPose = BipedModel.ArmPose.EMPTY;
		      ItemStack itemstack = p_212843_1_.getItemInHand(Hand.MAIN_HAND);
		      if (itemstack.getItem() == Items.BOW && p_212843_1_.isAggressive()) {
		         if (p_212843_1_.getMainArm() == HandSide.RIGHT) {
		            this.rightArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
		         } else {
		            this.leftArmPose = BipedModel.ArmPose.BOW_AND_ARROW;
		         }
		      }

		      super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
		   }

		   public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_, float p_225597_6_) {
		      super.setupAnim(p_225597_1_, p_225597_2_, p_225597_3_, p_225597_4_, p_225597_5_, p_225597_6_);
		      ItemStack itemstack = p_225597_1_.getMainHandItem();
		      if (p_225597_1_.isAggressive() && (itemstack.isEmpty() || itemstack.getItem() != Items.BOW)) {
		         float f = MathHelper.sin(this.attackTime * (float)Math.PI);
		         float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
		         this.rightArm.zRot = 0.0F;
		         this.leftArm.zRot = 0.0F;
		         this.rightArm.yRot = -(0.1F - f * 0.6F);
		         this.leftArm.yRot = 0.1F - f * 0.6F;
		         this.rightArm.xRot = (-(float)Math.PI / 2F);
		         this.leftArm.xRot = (-(float)Math.PI / 2F);
		         this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
		         this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
		         ModelHelper.bobArms(this.rightArm, this.leftArm, p_225597_4_);
		      }

		   }

		   public void translateToHand(HandSide p_225599_1_, MatrixStack p_225599_2_) {
		      float f = p_225599_1_ == HandSide.RIGHT ? 1.0F : -1.0F;
		      ModelRenderer modelrenderer = this.getArm(p_225599_1_);
		      modelrenderer.x += f;
		      modelrenderer.translateAndRotate(p_225599_2_);
		      modelrenderer.x -= f;
		   }
		}