package bee.beeshroom.monsutaari.common.entities;

import java.util.EnumSet;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

//Credit entirely to SlimeEntity.class
//also thank you for your Entity tutorial, Cy4shot

	public class SkeletonHeadEntity extends MonsterEntity //implements IMob 
	{
		   private boolean wasOnGround;

		   public SkeletonHeadEntity(EntityType<? extends SkeletonHeadEntity> p_i48552_1_, World p_i48552_2_) {
		      super(p_i48552_1_, p_i48552_2_);
		      this.moveControl = new SkeletonHeadEntity.MoveHelperController(this);
		   }

		   protected void registerGoals() {
		  //    this.goalSelector.addGoal(1, new SkeletonHeadEntity.FloatGoal(this));
		      this.goalSelector.addGoal(2, new SkeletonHeadEntity.AttackGoal(this));
		      this.goalSelector.addGoal(3, new SkeletonHeadEntity.FaceRandomGoal(this));
		      this.goalSelector.addGoal(5, new SkeletonHeadEntity.HopGoal(this));
		      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213811_1_) -> {
		         return Math.abs(p_213811_1_.getY() - this.getY()) <= 4.0D;
		      }));
		      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		      this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		     
		      this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		      this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
		      this.goalSelector.addGoal(4, new SkeletonHeadEntity.AvoidEntityGoal<>(this, WolfEntity.class, 10.0F, 2.2D, 2.2D));
		      this.goalSelector.addGoal(11, new LookAtGoal(this, HeadlessSkeletonEntity.class, 10.0F));
		      this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 0.6D));

		       //  this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
		     //    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		         this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
				   
		   }
		   
			public static AttributeModifierMap.MutableAttribute registerAttributes() {
				return MonsterEntity.createMonsterAttributes()
						.add(Attributes.MAX_HEALTH, 7.0f)
						.add(Attributes.ATTACK_DAMAGE, 0.5f)
					//	.add(Attributes.ATTACK_SPEED, 2.0f)
					//	.add(Attributes.FOLLOW_RANGE, 7.0D)
					//	.add(Attributes.MOVEMENT_SPEED, 0.25f)
						.add(Attributes.MOVEMENT_SPEED, 0.25F);
			}
			
			public CreatureAttribute getMobType() {
			      return CreatureAttribute.UNDEAD;
		   } 

			   protected boolean isSunSensitive() {
				      return true;
				   }
			
				protected void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
					      super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
					      Entity entity = p_213333_1_.getEntity();
					      if (entity instanceof CreeperEntity) {
					         CreeperEntity creeperentity = (CreeperEntity)entity;
					         if (creeperentity.canDropMobsSkull()) {
					            creeperentity.increaseDroppedSkulls();
					            this.spawnAtLocation(Items.SKELETON_SKULL);
					         }
					      }

					   }
				
				public void aiStep() {
				      super.aiStep();
				      if (this.isAlive()) {
					         boolean flag = this.isSunSensitive() && this.isSunBurnTick();
					            if (flag) {
					               this.setSecondsOnFire(8);
					            }
					      }
				   }
			
		   public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
		      super.addAdditionalSaveData(p_213281_1_);
		      p_213281_1_.putBoolean("wasOnGround", this.wasOnGround);
		   }

		   public void readAdditionalSaveData(CompoundNBT p_70037_1_) {		      
		      super.readAdditionalSaveData(p_70037_1_);
		      this.wasOnGround = p_70037_1_.getBoolean("wasOnGround");
		   }

		   protected boolean shouldDespawnInPeaceful() {
		      return true;
		   }

		   public void tick() {
		      super.tick();
		      if (this.onGround && !this.wasOnGround) {
		         this.playSound(this.getHopSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
		      } else if (!this.onGround && this.wasOnGround) {
		    	  
		      }

		      this.wasOnGround = this.onGround;
		   }

		   protected int getJumpDelay() {
		      return this.random.nextInt(30) + 10;
		   }

		   public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
		         this.yRot = this.yHeadRot;
		         this.yBodyRot = this.yHeadRot;
		         if (this.isInWater() && this.random.nextInt(20) == 0) {
		            this.doWaterSplashEffect();
		         }
		      
		      super.onSyncedDataUpdated(p_184206_1_);
		   }

		   public void push(Entity p_70108_1_) {
		      super.push(p_70108_1_);
		      if (p_70108_1_ instanceof IronGolemEntity && this.isDealsDamage()) {
		         this.dealDamage((LivingEntity)p_70108_1_);
		      }

		   }

		   public void playerTouch(PlayerEntity p_70100_1_) {
		      if (this.isDealsDamage()) {
		         this.dealDamage(p_70100_1_);
		      }

		   }

		   protected void dealDamage(LivingEntity p_175451_1_) {
		      if (this.isAlive()) {
		         if (this.distanceToSqr(p_175451_1_) < 0.5D && this.canSee(p_175451_1_) && p_175451_1_.hurt(DamageSource.mobAttack(this), this.getAttackDamage())) {
		           // this.playSound(SoundEvents., 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
		            this.doEnchantDamageEffects(this, p_175451_1_);
		         }
		      }

		   }
		   
			protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
			      return 0.25F;
			   }

		   protected boolean isDealsDamage() {
		      return this.isEffectiveAi();
		   }

		   protected float getAttackDamage() {
		      return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
		   }

			protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
			      return SoundEvents.SKELETON_HURT;
			   }

			protected SoundEvent getDeathSound() {
			      return SoundEvents.SKELETON_DEATH;
			   }

		   protected SoundEvent getHopSound() {
		      return SoundEvents.SKELETON_STEP;	   
		      }

		   protected float getSoundVolume() {
		      return 0.5F;
		   }

		   public int getMaxHeadXRot() {
		      return 0;
		   }

		   protected boolean doPlayJumpSound() {
		      return true;
		   }

		   protected void jumpFromGround() {
		      Vector3d vector3d = this.getDeltaMovement();
		      this.setDeltaMovement(vector3d.x, (double)this.getJumpPower(), vector3d.z);
		      this.hasImpulse = true;
		   }

		   private float getSoundPitch() {
		      float f = 2.0F;
		      return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
		   }

		   protected SoundEvent getJumpSound() {
		      return SoundEvents.SKELETON_STEP;
		   }

		   /**
		    * Called when the slime spawns particles on landing, see onUpdate.
		    * Return true to prevent the spawning of the default particles.
		    */
		   protected boolean spawnCustomParticles() { return false; }

/*		   static class AttackGoal extends MeleeAttackGoal {
			      public AttackGoal(SkeletonHeadEntity p_i45867_1_) {
			         super(p_i45867_1_, 1.4D, true);
			      }

				protected double getAttackReachSqr(LivingEntity p_179512_1_) {
			         return 1.0F + p_179512_1_.getBbWidth();
			      }
			   }  */
		   
		   static class AttackGoal extends Goal {
		      private final SkeletonHeadEntity skeletonhead;
		      private int growTiredTimer;

		      public AttackGoal(SkeletonHeadEntity p_i45824_1_) {
		         this.skeletonhead = p_i45824_1_;
		         this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		      }

		      public boolean canUse() {
		         LivingEntity livingentity = this.skeletonhead.getTarget();
		         if (livingentity == null) {
		            return false;
		         } else if (!livingentity.isAlive()) {
		            return false;
		         } else {
		            return livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable ? false : this.skeletonhead.getMoveControl() instanceof SkeletonHeadEntity.MoveHelperController;
		         }
		      }

		      public void start() {
		         this.growTiredTimer = 300;
		         super.start();
		      }

		      public boolean canContinueToUse() {
		         LivingEntity livingentity = this.skeletonhead.getTarget();
		         if (livingentity == null) {
		            return false;
		         } else if (!livingentity.isAlive()) {
		            return false;
		         } else if (livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable) {
		            return false;
		         } else {
		            return --this.growTiredTimer > 0;
		         }
		      }

		      public void tick() {
		         this.skeletonhead.lookAt(this.skeletonhead.getTarget(), 10.0F, 10.0F);
		         ((SkeletonHeadEntity.MoveHelperController)this.skeletonhead.getMoveControl()).setDirection(this.skeletonhead.yRot, this.skeletonhead.isDealsDamage());
		      }
		   } 

		   static class FaceRandomGoal extends Goal {
		      private final SkeletonHeadEntity skeletonhead;
		      private float chosenDegrees;
		      private int nextRandomizeTime;

		      public FaceRandomGoal(SkeletonHeadEntity p_i45820_1_) {
		         this.skeletonhead = p_i45820_1_;
		         this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		      }

		      public boolean canUse() {
		         return this.skeletonhead.getTarget() == null && (this.skeletonhead.onGround || this.skeletonhead.isInWater() || this.skeletonhead.isInLava() || this.skeletonhead.hasEffect(Effects.LEVITATION)) && this.skeletonhead.getMoveControl() instanceof SkeletonHeadEntity.MoveHelperController;
		      }

		      public void tick() {
		         if (--this.nextRandomizeTime <= 0) {
		            this.nextRandomizeTime = 40 + this.skeletonhead.getRandom().nextInt(60);
		            this.chosenDegrees = (float)this.skeletonhead.getRandom().nextInt(360);
		         }

		         ((SkeletonHeadEntity.MoveHelperController)this.skeletonhead.getMoveControl()).setDirection(this.chosenDegrees, false);
		      }
		   }

		   static class HopGoal extends Goal {
		      private final SkeletonHeadEntity skeletonhead;

		      public HopGoal(SkeletonHeadEntity p_i45822_1_) {
		         this.skeletonhead = p_i45822_1_;
		         this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		      }

		      public boolean canUse() {
		         return !this.skeletonhead.isPassenger();
		      }

		      public void tick() {
		         ((SkeletonHeadEntity.MoveHelperController)this.skeletonhead.getMoveControl()).setWantedMovement(1.0D);
		//          ((SkeletonHeadEntity.MoveHelperController)this.skeletonhead.getMoveControl()).setWantedMovement(0.2D);
		      }
		   }

		   static class MoveHelperController extends MovementController {
		      private float yRot;
		      private int jumpDelay;
		      private final SkeletonHeadEntity skeletonhead;
		      private boolean isAggressive;

		      public MoveHelperController(SkeletonHeadEntity p_i45821_1_) {
		         super(p_i45821_1_);
		         this.skeletonhead = p_i45821_1_;
		         this.yRot = 180.0F * p_i45821_1_.yRot / (float)Math.PI;
		      }

		      public void setDirection(float p_179920_1_, boolean p_179920_2_) {
		         this.yRot = p_179920_1_;
		         this.isAggressive = p_179920_2_;
		      }

		      public void setWantedMovement(double p_179921_1_) {
		         this.speedModifier = p_179921_1_;
		         this.operation = MovementController.Action.MOVE_TO;
		      }

		      public void tick() {
		         this.mob.yRot = this.rotlerp(this.mob.yRot, this.yRot, 90.0F);
		         this.mob.yHeadRot = this.mob.yRot;
		         this.mob.yBodyRot = this.mob.yRot;
		         if (this.operation != MovementController.Action.MOVE_TO) {
		            this.mob.setZza(0.0F);
		         } else {
		            this.operation = MovementController.Action.WAIT;
		            if (this.mob.isOnGround()) {
		               this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
		               if (this.jumpDelay-- <= 0) {
		                  this.jumpDelay = this.skeletonhead.getJumpDelay();
		                  if (this.isAggressive) {
		                     this.jumpDelay /= 3;
		                  }

		                  this.skeletonhead.getJumpControl().jump();
		                  if (this.skeletonhead.doPlayJumpSound()) {
		                     this.skeletonhead.playSound(this.skeletonhead.getJumpSound(), this.skeletonhead.getSoundVolume(), this.skeletonhead.getSoundPitch());
		                  }
		               } else {
		                  this.skeletonhead.xxa = 0.0F;
		                  this.skeletonhead.zza = 0.0F;
		                  this.mob.setSpeed(0.0F);
		               }
		            } else {
		               this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
		            }

		         }
		      }
		   }
		   
		   static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
			      private final SkeletonHeadEntity skeleton_head;

			      public AvoidEntityGoal(SkeletonHeadEntity p_i46403_1_, Class<T> p_i46403_2_, float p_i46403_3_, double p_i46403_4_, double p_i46403_6_) {
			         super(p_i46403_1_, p_i46403_2_, p_i46403_3_, p_i46403_4_, p_i46403_6_);
			         this.skeleton_head = p_i46403_1_;
			      }
			   }
		}