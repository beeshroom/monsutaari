package bee.beeshroom.monsutaari.common.entities;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.JumpController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.piglin.AbstractPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//MASSIVELY referenced from RabbitEntity
//also thank you for your Entity tutorial, Cy4shot
public class WitherSkeletonHeadEntityBackup extends MonsterEntity {
	  private int jumpTicks;
	   private int jumpDuration;
	   private boolean wasOnGround;
	   private int jumpDelayTicks;
	
	public WitherSkeletonHeadEntityBackup(EntityType<? extends WitherSkeletonHeadEntityBackup> type, World worldIn) {
		super(type, worldIn);
		// this.moveControl = new SkeletonHeadEntity.MoveHelperController(this);
		// this.xpReward = 5;
		 this.jumpControl = new WitherSkeletonHeadEntityBackup.JumpHelperController(this);
	      this.moveControl = new WitherSkeletonHeadEntityBackup.MoveHelperController(this);
	      this.setSpeedModifier(0.0D);
	      this.setPathfindingMalus(PathNodeType.LAVA, 8.0F);
	}
	
	
	@Override
	   protected void registerGoals() {
		   super.registerGoals();
		   
		//   this.goalSelector.addGoal(1, new SwimGoal(this));
		 //  this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		//      this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		    //  this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
		    //  this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		     // this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		      //this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		      this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		     // this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		   //   this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
		      
		      this.goalSelector.addGoal(4, new WitherSkeletonHeadEntityBackup.AvoidEntityGoal<>(this, WolfEntity.class, 10.0F, 2.2D, 2.2D));
		      this.goalSelector.addGoal(4, new WitherSkeletonHeadEntityBackup.AvoidEntityGoal<>(this, IronGolemEntity.class, 10.0F, 2.2D, 2.2D));
		      
		     // this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
		      this.goalSelector.addGoal(11, new LookAtGoal(this, HeadlessWitherSkeletonEntity.class, 10.0F));
		      this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
		      this.goalSelector.addGoal(11, new LookRandomlyGoal(this));
		      
		      
		      this.goalSelector.addGoal(4, new WitherSkeletonHeadEntityBackup.EvilAttackGoal(this));
		       //  this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		         this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		         this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractPiglinEntity.class, true));
		   }
	

		public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 7.0f)
				.add(Attributes.ATTACK_DAMAGE, 1.0f)
			//	.add(Attributes.ATTACK_SPEED, 2.0f)
			//	.add(Attributes.FOLLOW_RANGE, 7.0D)
			//	.add(Attributes.MOVEMENT_SPEED, 0.25f)
				.add(Attributes.MOVEMENT_SPEED, 0.28F);
	}

	
		 @Override
		public boolean doHurtTarget(Entity p_70652_1_) {
		      if (!super.doHurtTarget(p_70652_1_)) {
		         return false;
		      } else {
		         if (p_70652_1_ instanceof LivingEntity) {
		            ((LivingEntity)p_70652_1_).addEffect(new EffectInstance(Effects.WITHER, 200));
		         }

		         return true;
		      }
		   }
		 
		 @Override
		public boolean canBeAffected(EffectInstance p_70687_1_) {
		      return p_70687_1_.getEffect() == Effects.WITHER ? false : super.canBeAffected(p_70687_1_);
		   }
		
		
	 
		@Override
		public CreatureAttribute getMobType() {
		      return CreatureAttribute.UNDEAD;
	   } 

		
	
	/*   protected SoundEvent getAmbientSound() {
		      return SoundEvents.SKELETON_AMBIENT;
		   } */

		   @Override
		protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		      return SoundEvents.WITHER_SKELETON_HURT;
		   }

		   @Override
		protected SoundEvent getDeathSound() {
		      return SoundEvents.WITHER_SKELETON_DEATH;
		   }

		/*   protected SoundEvent getStepSound() {
		      return SoundEvents.SKELETON_STEP;
		   } */
		   
		   @Override
		protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
			      super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
			      Entity entity = p_213333_1_.getEntity();
			      if (entity instanceof CreeperEntity) {
			         CreeperEntity creeperentity = (CreeperEntity)entity;
			         if (creeperentity.canDropMobsSkull()) {
			            creeperentity.increaseDroppedSkulls();
			            this.spawnAtLocation(Items.WITHER_SKELETON_SKULL);
			         }
			      }

			   }
		   
		   
		   
		   protected boolean isSunSensitive() {
			      return true;
			   }
		    
		/*   public void aiStep() {
			      if (this.isAlive()) {
			         boolean flag = this.isSunSensitive() && this.isSunBurnTick();
			         if (flag) {
			           

			            if (flag) {
			               this.setSecondsOnFire(8);
			            }
			         }
			      }

			      super.aiStep();
			    }
		   */
		   
		   @Override
		protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
			      return 0.3F;
			   }
		   
		   @Override
		public int getMaxHeadXRot() {
			      return 0;
			    }
		   
		   
		   @Override
		protected float getJumpPower() {
			      if (!this.horizontalCollision && (!this.moveControl.hasWanted() || !(this.moveControl.getWantedY() > this.getY() + 0.1D))) {
			         Path path = this.navigation.getPath();
			         if (path != null && !path.isDone()) {
			            Vector3d vector3d = path.getNextEntityPos(this);
			            if (vector3d.y > this.getY() + 0.1D) {
			               return 015F;
			            }
			         }

			         return this.moveControl.getSpeedModifier() <= 0.6D ? 0.2F : 0.3F;
			      } else {
			         return 0.1F;
			      }
			   }

			   @Override
			protected void jumpFromGround() {
			      super.jumpFromGround();
			      double d0 = this.moveControl.getSpeedModifier();
			      if (d0 > 0.0D) {
			         double d1 = getHorizontalDistanceSqr(this.getDeltaMovement());
			         if (d1 < 0.01D) {
			            this.moveRelative(0.1F, new Vector3d(0.0D, 0.0D, 1.0D));
			         }
			      }

			      if (!this.level.isClientSide) {
			         this.level.broadcastEntityEvent(this, (byte)1);
			      }

			   }

			   @OnlyIn(Dist.CLIENT)
			   public float getJumpCompletion(float p_175521_1_) {
			      return this.jumpDuration == 0 ? 0.0F : (this.jumpTicks + p_175521_1_) / this.jumpDuration;
			   }

			   public void setSpeedModifier(double p_175515_1_) {
			      this.getNavigation().setSpeedModifier(p_175515_1_);
			      this.moveControl.setWantedPosition(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ(), p_175515_1_);
			   }

			   @Override
			public void setJumping(boolean p_70637_1_) {
			      super.setJumping(p_70637_1_);
			      if (p_70637_1_) {
			         //this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * 0.8F);
			    	  this.playSound(SoundEvents.WITHER_SKELETON_STEP, 1.0f, 2.0f);
			      }

			   }

			   public void startJumping() {
			      this.setJumping(true);
			      if (!this.isUnderWater()) {
			      this.jumpDuration = 4;
			      }
			      if (this.isUnderWater()) {
				      this.jumpDuration = 1;
				      }
			      this.jumpTicks = 0;
			   }

			   @Override
			public void customServerAiStep() {
			      if (this.jumpDelayTicks > 0) {
			         --this.jumpDelayTicks;
			      }

			      if (this.onGround) {
			         if (!this.wasOnGround) {
			            this.setJumping(false);
			            this.checkLandingDelay();
			         }

			         if (this.jumpDelayTicks == 0) {
			            LivingEntity livingentity = this.getTarget();
			            if (livingentity != null && this.distanceToSqr(livingentity) < 16.0D) {
			               this.facePoint(livingentity.getX(), livingentity.getZ());
			               this.moveControl.setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), this.moveControl.getSpeedModifier());
			               this.startJumping();
			               this.wasOnGround = true;
			            }
			         }

			         WitherSkeletonHeadEntityBackup.JumpHelperController skeletonheadentity$jumphelpercontroller = (WitherSkeletonHeadEntityBackup.JumpHelperController)this.jumpControl;
			         if (!skeletonheadentity$jumphelpercontroller.wantJump()) {
			            if (this.moveControl.hasWanted() && this.jumpDelayTicks == 0) {
			               Path path = this.navigation.getPath();
			               Vector3d vector3d = new Vector3d(this.moveControl.getWantedX(), this.moveControl.getWantedY(), this.moveControl.getWantedZ());
			               if (path != null && !path.isDone()) {
			                  vector3d = path.getNextEntityPos(this);
			               }

			               this.facePoint(vector3d.x, vector3d.z);
			               this.startJumping();
			            }
			         } else if (!skeletonheadentity$jumphelpercontroller.canJump()) {
			            this.enableJumpControl();
			         }
			      }

			      this.wasOnGround = this.onGround;
			   }

			   @Override
			public boolean canSpawnSprintParticle() {
			      return false;
			   }

			   private void facePoint(double p_175533_1_, double p_175533_3_) {
			      this.yRot = (float)(MathHelper.atan2(p_175533_3_ - this.getZ(), p_175533_1_ - this.getX()) * (180F / (float)Math.PI)) - 90.0F;
			   }

			   private void enableJumpControl() {
			      ((WitherSkeletonHeadEntityBackup.JumpHelperController)this.jumpControl).setCanJump(true);
			   }

			   private void disableJumpControl() {
			      ((WitherSkeletonHeadEntityBackup.JumpHelperController)this.jumpControl).setCanJump(false);
			   }

			   private void setLandingDelay() {
			      if (this.moveControl.getSpeedModifier() < 2.2D) {
			         this.jumpDelayTicks = 10;
			      } else {
			         this.jumpDelayTicks = 1;
			      }

			   }

			   private void checkLandingDelay() {
			      this.setLandingDelay();
			      this.disableJumpControl();
			   }

			   @Override
			public void aiStep() {
			      super.aiStep();
			      if (this.jumpTicks != this.jumpDuration) {
			         ++this.jumpTicks;
			      } else if (this.jumpDuration != 0) {
			         this.jumpTicks = 0;
			         this.jumpDuration = 0;
			         this.setJumping(false);
			      }
			      
			    // Wither skeletons are Sun-proof, right?
			/*      if (this.isAlive()) {
				         boolean flag = this.isSunSensitive() && this.isSunBurnTick();
				         if (flag) {
				           

				            if (flag) {
				               this.setSecondsOnFire(8);
				            }
				         }
				      } */
			      
			 /*     if (this.isUnderWater())
			      {
			      this.setJumping(false);
			      } */
			      
			/*      if (this.isUnderWater())
			      {
			    	  ((SkeletonHeadEntity.JumpHelperController)this.jumpControl).setCanJump(false);
			      } */

				     // super.aiStep();
				      ///////////

			   }

			/*   protected SoundEvent getJumpSound() {
			      return SoundEvents.SKELETON_STEP;
			   } */

			/*   public boolean doHurtTarget(Entity p_70652_1_) {
			         return p_70652_1_.hurt(DamageSource.mobAttack(this), 1.0F);
			      } */

			   @Override
			public boolean hurt(DamageSource p_70097_1_, float p_70097_2_) {
			      return this.isInvulnerableTo(p_70097_1_) ? false : super.hurt(p_70097_1_, p_70097_2_);
			   }

			   
			   @Override
			@OnlyIn(Dist.CLIENT)
			   public void handleEntityEvent(byte p_70103_1_) {
			      if (p_70103_1_ == 1) {
			        // this.spawnSprintParticle();
			         this.jumpDuration = 5;
			         this.jumpTicks = 0;
			      } else {
			         super.handleEntityEvent(p_70103_1_);
			      }

			   }

			   static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
			      private final WitherSkeletonHeadEntityBackup skeleton_head;

			      public AvoidEntityGoal(WitherSkeletonHeadEntityBackup p_i46403_1_, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
			         super(p_i46403_1_, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
			         this.skeleton_head = p_i46403_1_;
			      }

			 /*     public boolean canUse() {
			         return this.skeleton_head.getRabbitType() != 99 && super.canUse();
			      } */
			   }

			   static class EvilAttackGoal extends MeleeAttackGoal {
			      public EvilAttackGoal(WitherSkeletonHeadEntityBackup p_i45867_1_) {
			         super(p_i45867_1_, 1.4D, true);
			      }

			      @Override
				protected double getAttackReachSqr(LivingEntity p_179512_1_) {
			         return 1.0F + p_179512_1_.getBbWidth();
			      }
			   }

			   public class JumpHelperController extends JumpController {
			      private final WitherSkeletonHeadEntityBackup skeleton_head;
			      private boolean canJump;

			      public JumpHelperController(WitherSkeletonHeadEntityBackup p_i45863_2_) {
			         super(p_i45863_2_);
			         this.skeleton_head = p_i45863_2_;
			      }

			      public boolean wantJump() {
			         return this.jump;
			      }

			      public boolean canJump() {
			         return this.canJump;
			      }

			      public void setCanJump(boolean p_180066_1_) {
			         this.canJump = p_180066_1_;
			      }

			      @Override
				public void tick() {
			         if (this.jump) {
			            this.skeleton_head.startJumping();
			            this.jump = false;
			         }

			      }
			   }

			   static class MoveHelperController extends MovementController {
			      private final WitherSkeletonHeadEntityBackup skeleton_head;
			      private double nextJumpSpeed;

			      public MoveHelperController(WitherSkeletonHeadEntityBackup p_i45862_1_) {
			         super(p_i45862_1_);
			         this.skeleton_head = p_i45862_1_;
			      }

			      @Override
				public void tick() {
			         if (this.skeleton_head.onGround && !this.skeleton_head.jumping && !((WitherSkeletonHeadEntityBackup.JumpHelperController)this.skeleton_head.jumpControl).wantJump()) {
			            this.skeleton_head.setSpeedModifier(0.0D);
			         } else if (this.hasWanted()) {
			            this.skeleton_head.setSpeedModifier(this.nextJumpSpeed);
			         }

			         super.tick();
			      }

			      @Override
				public void setWantedPosition(double p_75642_1_, double p_75642_3_, double p_75642_5_, double p_75642_7_) {
			      if (this.skeleton_head.isInWater()) {
			            p_75642_7_ = 0.45D;
			         } 

			         super.setWantedPosition(p_75642_1_, p_75642_3_, p_75642_5_, p_75642_7_);
			         if (p_75642_7_ > 0.0D) {
			            this.nextJumpSpeed = p_75642_7_;
			         }

			      }
			   }

			/*   static class PanicGoal extends net.minecraft.entity.ai.goal.PanicGoal {
			      private final WitherSkeletonHeadEntity skeleton_head;

			      public PanicGoal(WitherSkeletonHeadEntity p_i45861_1_, double p_i45861_2_) {
			         super(p_i45861_1_, p_i45861_2_);
			         this.skeleton_head = p_i45861_1_;
			      }

			      public void tick() {
			         super.tick();
			         this.skeleton_head.setSpeedModifier(this.speedModifier);
			      }
			   } */

		
			}