package bee.beeshroom.monsutaari.common.entities;

import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

//Copied from SlimeEntity

public class ClayCubeEntity extends WaterMobEntity implements IMob {
	   private static final DataParameter<Integer> ID_SIZE = EntityDataManager.defineId(ClayCubeEntity.class, DataSerializers.INT);
	   private static final DataParameter<Integer> DATA_TYPE_ID = EntityDataManager.defineId(ClayCubeEntity.class, DataSerializers.INT);
	   public float targetSquish;
	   public float squish;
	   public float oSquish;
	   private boolean wasOnGround;

	   public ClayCubeEntity(EntityType<? extends ClayCubeEntity> p_i48552_1_, World p_i48552_2_) {
	      super(p_i48552_1_, p_i48552_2_);
	      this.moveControl = new ClayCubeEntity.MoveHelperController(this);
	   }

	   protected void registerGoals() {
	      this.goalSelector.addGoal(1, new ClayCubeEntity.FloatGoal(this));
	      this.goalSelector.addGoal(2, new ClayCubeEntity.AttackGoal(this));
	      this.goalSelector.addGoal(3, new ClayCubeEntity.FaceRandomGoal(this));
	      this.goalSelector.addGoal(5, new ClayCubeEntity.HopGoal(this));
	      this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p_213811_1_) -> {
	         return Math.abs(p_213811_1_.getY() - this.getY()) <= 4.0D;
	      }));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	   }

		public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MonsterEntity.createMonsterAttributes();
	}
	   
	   protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(ID_SIZE, 1);
	      this.entityData.define(DATA_TYPE_ID, 0);
	   }

	   protected void setSize(int p_70799_1_, boolean p_70799_2_) {
	      this.entityData.set(ID_SIZE, p_70799_1_);
	      this.reapplyPosition();
	      this.refreshDimensions();
	      this.getAttribute(Attributes.MAX_HEALTH).setBaseValue((double)(p_70799_1_ * p_70799_1_));
	      this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue((double)(0.13F + 0.1F * (float)p_70799_1_));
	      this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue((double)p_70799_1_);
	      if (p_70799_2_) {
	         this.setHealth(this.getMaxHealth());
	      }

	      this.xpReward = p_70799_1_;
	   }

	   public int getSize() {
	      return this.entityData.get(ID_SIZE);
	   }

	   public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
	      super.addAdditionalSaveData(p_213281_1_);
	      p_213281_1_.putInt("Size", this.getSize() - 1);
	      p_213281_1_.putInt("SlimeType", this.getSlimeType());
	      p_213281_1_.putBoolean("wasOnGround", this.wasOnGround);
	   }

	   public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	      int i = p_70037_1_.getInt("Size");
	      this.setSlimeType(p_70037_1_.getInt("SlimeType"));
	      if (i < 0) {
	         i = 0;
	      }

	      this.setSize(i + 1, false);
	      super.readAdditionalSaveData(p_70037_1_);
	      this.wasOnGround = p_70037_1_.getBoolean("wasOnGround");
	   }

	   public boolean isTiny() {
	      return this.getSize() <= 1;
	   }

	 /*getp  protected IParticleData getParticleType() {
	      return null;
	    		  //(ParticleTypes.ITEM_SNOWBALL);
	   } */

	   protected boolean shouldDespawnInPeaceful() {
	      return this.getSize() > 0;
	   }

	   public void tick() {
	      this.squish += (this.targetSquish - this.squish) * 0.5F;
	      this.oSquish = this.squish;
	      super.tick();
	      if (this.onGround && !this.wasOnGround) {
	         int i = this.getSize();

	     /*    if (spawnCustomParticles()) i = 0; // don't spawn particles if it's handled by the implementation itself
	         for(int j = 0; j < i * 8; ++j) {
	            float f = this.random.nextFloat() * ((float)Math.PI * 2F);
	            float f1 = this.random.nextFloat() * 0.5F + 0.5F;
	            float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
	            float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
	            this.level.addParticle(this.getPremovearticleType(), this.getX() + (double)f2, this.getY(), this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
	     //       BlockState blockstate = BlockState.getBlock();
	    //        this.level.addParticle(new BlockParticleData(ParticleTypes.BLOCK, blockstate), this.getX() + (double)f2, this.getY(), this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
		        
	         } */

	         this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) / 0.8F);
	         this.targetSquish = -0.5F;
	      } else if (!this.onGround && this.wasOnGround) {
	         this.targetSquish = 1.0F;
	      }

	      this.wasOnGround = this.onGround;
	      this.decreaseSquish();
	   }

	   protected void decreaseSquish() {
	      this.targetSquish *= 0.6F;
	   }

	   protected int getJumpDelay() {
	      return this.random.nextInt(20) + 10;
	   }

	   public void refreshDimensions() {
	      double d0 = this.getX();
	      double d1 = this.getY();
	      double d2 = this.getZ();
	      super.refreshDimensions();
	      this.setPos(d0, d1, d2);
	   }

	   public void onSyncedDataUpdated(DataParameter<?> p_184206_1_) {
	      if (ID_SIZE.equals(p_184206_1_)) {
	         this.refreshDimensions();
	         this.yRot = this.yHeadRot;
	         this.yBodyRot = this.yHeadRot;
	         if (this.isInWater() && this.random.nextInt(20) == 0) {
	            this.doWaterSplashEffect();
	         }
	      }

	      super.onSyncedDataUpdated(p_184206_1_);
	   }

	   public EntityType<? extends ClayCubeEntity> getType() {
	      return (EntityType<? extends ClayCubeEntity>)super.getType();
	   }

	   @Override
	   public void remove(boolean keepData) {
	      int i = this.getSize();
	      if (!this.level.isClientSide && i > 1 && this.isDeadOrDying() && !this.removed) {
	         ITextComponent itextcomponent = this.getCustomName();
	         boolean flag = this.isNoAi();
	         float f = (float)i / 4.0F;
	         int j = i / 2;
	         int k = 2 + this.random.nextInt(3);
	         int r = this.getRandomSlimeType(level);

	         for(int l = 0; l < k; ++l) {
	            float f1 = ((float)(l % 2) - 0.5F) * f;
	            float f2 = ((float)(l / 2) - 0.5F) * f;
	            ClayCubeEntity slimeentity = this.getType().create(this.level);
	            if (this.isPersistenceRequired()) {
	               slimeentity.setPersistenceRequired();
	            }

	            slimeentity.setCustomName(itextcomponent);
	            slimeentity.setNoAi(flag);
	            slimeentity.setInvulnerable(this.isInvulnerable());
	            slimeentity.setSize(j, true);
	            slimeentity.setSlimeType(r);
	            slimeentity.moveTo(this.getX() + (double)f1, this.getY() + 0.5D, this.getZ() + (double)f2, this.random.nextFloat() * 360.0F, 0.0F);
	            this.level.addFreshEntity(slimeentity);
	         }
	      }

	      super.remove(keepData);
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
	         int i = this.getSize();
	         if (this.distanceToSqr(p_175451_1_) < 0.6D * (double)i * 0.6D * (double)i && this.canSee(p_175451_1_) && p_175451_1_.hurt(DamageSource.mobAttack(this), this.getAttackDamage())) {
	            this.playSound(SoundEvents.GRAVEL_BREAK, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
	            this.doEnchantDamageEffects(this, p_175451_1_);
	         }
	      }

	   }

	   protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
	      return 0.625F * p_213348_2_.height;
	   }

	   protected boolean isDealsDamage() {
	      return !this.isTiny() && this.isEffectiveAi();
	   }

	   protected float getAttackDamage() {
	      return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
	   }

	   protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
	      return this.isTiny() ? SoundEvents.GRAVEL_HIT : SoundEvents.GRAVEL_HIT;
	   }

	   protected SoundEvent getDeathSound() {
	      return this.isTiny() ? SoundEvents.GRAVEL_BREAK : SoundEvents.GRAVEL_BREAK;
	   }

	   protected SoundEvent getSquishSound() {
	      return this.isTiny() ? SoundEvents.GRAVEL_STEP : SoundEvents.GRAVEL_STEP;
	   }

	   protected ResourceLocation getDefaultLootTable() {
	      return this.getSize() == 1 ? this.getType().getDefaultLootTable() : LootTables.EMPTY;
	   }
	   
	   
	   public static boolean checkClayCubeSpawnRules(EntityType<ClayCubeEntity> entity, IWorld world, SpawnReason spawnreason, BlockPos blockpos, Random random) {
		 //  BlockState blockstate = world.getBlockState(blockpos.below());
		   if (world.getDifficulty() != Difficulty.PEACEFUL) {       
			   if (//Objects.equals(world.getBiomeName(blockpos), Optional.of(Biomes.RIVER)) 
				 //  && 
				   world.getMoonBrightness() > .5f 
			/*	   && (world.getBlockState(blockpos).is(Blocks.WATER) 
						   || world.getBlockState(blockpos.above()).is(Blocks.WATER) 
						   || world.getBlockState(blockpos.below()).is(Blocks.WATER)
						 //  || !world.getBlockState(blockpos).is(Blocks.WATER)
						   || spawnreason == SpawnReason.SPAWNER 
						   || world.getFluidState(blockpos).is(FluidTags.WATER)
						   )	*/		 
					   ) 
				   {
				   return isDarkEnoughToSpawn(entity, world, spawnreason, blockpos, random);
				   } 
			   
			/*    if (blockstate.is(Blocks.CLAY) 
					//   && blockpos.getY() < 70 
					   && world.getMoonBrightness() > .5f )
					   {
					  return checkMobSpawnRules(entity, world, spawnreason, blockpos, random);
					   } */
			   
			/*   if (!(world instanceof ISeedReader)) {
		            return false;
		         } */
			   
		   }
		   return false;
	   }
	   
	   public static boolean isDarkEnoughToSpawn(EntityType<ClayCubeEntity> entity, IWorld world, SpawnReason spawnreason, BlockPos blockpos, Random random) {
		      if (world.getBrightness(LightType.SKY, blockpos) > random.nextInt(32)) {
		         return false;
		      } else {
		         int i = ((IServerWorld) world).getLevel().isThundering() ? world.getMaxLocalRawBrightness(blockpos, 10) : world.getMaxLocalRawBrightness(blockpos);
		         return i <= random.nextInt(8);
		      }
		   }

	   
	/*   public static boolean checkSlimeSpawnRulez(EntityType<ClayCubeEntity> entity, IWorld world, SpawnReason spawnreason, BlockPos blockpos, Random random) {
		     BlockState blockstate = world.getBlockState(blockpos.below());
		   if (world.getDifficulty() != Difficulty.PEACEFUL) {
	         if (Objects.equals(world.getBiomeName(blockpos), Optional.of(Biomes.RIVER)) && world.getFluidState(blockpos).is(FluidTags.WATER) && blockpos.getY() > 50 && blockpos.getY() < 70 && random.nextFloat() < 0.5F && random.nextFloat() < world.getMoonBrightness() && world.getMaxLocalRawBrightness(blockpos) <= random.nextInt(8)
	        		// && blockstate.is(Blocks.CLAY) || blockstate.is(Blocks.SAND) || blockstate.is(Blocks.GRAVEL) || blockstate.is(Blocks.DIRT) || blockstate.is(Blocks.GRASS)
	        		 ) {
	            return checkMobSpawnRules(entity, world, spawnreason, blockpos, random)
	            	//	&& blockstate.is(Blocks.CLAY) || blockstate.is(Blocks.SAND) || blockstate.is(Blocks.GRAVEL) || blockstate.is(Blocks.DIRT) || blockstate.is(Blocks.GRASS)
		        		 ;
	         }
	         
	         if (blockpos.getY() > 5 && blockpos.getY() < 70 && random.nextFloat() < 0.5F && random.nextFloat() < world.getMoonBrightness() && world.getMaxLocalRawBrightness(blockpos) <= random.nextInt(8) 
	        		 && blockstate.is(Blocks.CLAY)
	        		 ) {
		            return checkMobSpawnRules(entity, world, spawnreason, blockpos, random) 
		            		&& blockstate.is(Blocks.CLAY)
		            		;
		         } 
	      } 

	      return false;
	   } */

	   protected float getSoundVolume() {
	      return 0.4F * (float)this.getSize();
	   }

	   public int getMaxHeadXRot() {
	      return 0;
	   }

	   protected boolean doPlayJumpSound() {
	      return this.getSize() > 0;
	   }

	   protected void jumpFromGround() {
	      Vector3d vector3d = this.getDeltaMovement();
	      this.setDeltaMovement(vector3d.x, (double)this.getJumpPower(), vector3d.z);
	      this.hasImpulse = true;
	   }

	   @Nullable
	   public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
		   int i = this.random.nextInt(3);
	      int r = this.getRandomSlimeType(p_213386_1_);
	      if (i < 2 && this.random.nextFloat() < 0.5F * p_213386_2_.getSpecialMultiplier()) {
	         ++i;
	      }
	      if (p_213386_4_ instanceof ClayCubeEntity.ClayCubeData) {
		         r = ((ClayCubeEntity.ClayCubeData)p_213386_4_).ClayCubeType;
		      } else {
		         p_213386_4_ = new ClayCubeEntity.ClayCubeData(r);
		      }

	      int j = 1 << i;
	      this.setSize(j, true);
	      this.setSlimeType(r);
	      return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
	   }

	   private float getSoundPitch() {
	      float f = this.isTiny() ? 1.4F : 0.8F;
	      return ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F) * f;
	   }

	   protected SoundEvent getJumpSound() {
	      return this.isTiny() ? SoundEvents.GRAVEL_STEP : SoundEvents.GRAVEL_STEP;
	   }

	   public EntitySize getDimensions(Pose p_213305_1_) {
	      return super.getDimensions(p_213305_1_).scale(0.255F * (float)this.getSize());
	   }

	   /**
	    * Called when the slime spawns particles on landing, see onUpdate.
	    * Return true to prevent the spawning of the default particles.
	    */
	   protected boolean spawnCustomParticles() { return false; }

	   static class AttackGoal extends Goal {
	      private final ClayCubeEntity slime;
	      private int growTiredTimer;

	      public AttackGoal(ClayCubeEntity p_i45824_1_) {
	         this.slime = p_i45824_1_;
	         this.setFlags(EnumSet.of(Goal.Flag.LOOK));
	      }

	      public boolean canUse() {
	         LivingEntity livingentity = this.slime.getTarget();
	         if (livingentity == null) {
	            return false;
	         } else if (!livingentity.isAlive()) {
	            return false;
	         } else {
	            return livingentity instanceof PlayerEntity && ((PlayerEntity)livingentity).abilities.invulnerable ? false : this.slime.getMoveControl() instanceof ClayCubeEntity.MoveHelperController;
	         }
	      }

	      public void start() {
	         this.growTiredTimer = 300;
	         super.start();
	      }

	      public boolean canContinueToUse() {
	         LivingEntity livingentity = this.slime.getTarget();
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
	         this.slime.lookAt(this.slime.getTarget(), 10.0F, 10.0F);
	         ((ClayCubeEntity.MoveHelperController)this.slime.getMoveControl()).setDirection(this.slime.yRot, this.slime.isDealsDamage());
	      }
	   }

	   static class FaceRandomGoal extends Goal {
	      private final ClayCubeEntity slime;
	      private float chosenDegrees;
	      private int nextRandomizeTime;

	      public FaceRandomGoal(ClayCubeEntity p_i45820_1_) {
	         this.slime = p_i45820_1_;
	         this.setFlags(EnumSet.of(Goal.Flag.LOOK));
	      }

	      public boolean canUse() {
	         return this.slime.getTarget() == null && (this.slime.onGround || this.slime.isInWater() || this.slime.isInLava() || this.slime.hasEffect(Effects.LEVITATION)) && this.slime.getMoveControl() instanceof ClayCubeEntity.MoveHelperController;
	      }

	      public void tick() {
	         if (--this.nextRandomizeTime <= 0) {
	            this.nextRandomizeTime = 40 + this.slime.getRandom().nextInt(60);
	            this.chosenDegrees = (float)this.slime.getRandom().nextInt(360);
	         }

	         ((ClayCubeEntity.MoveHelperController)this.slime.getMoveControl()).setDirection(this.chosenDegrees, false);
	      }
	   }

	   static class FloatGoal extends Goal {
	      private final ClayCubeEntity slime;

	      public FloatGoal(ClayCubeEntity p_i45823_1_) {
	         this.slime = p_i45823_1_;
	         this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	         p_i45823_1_.getNavigation().setCanFloat(true);
	      }

	      public boolean canUse() {
	         return (this.slime.isInWater() || this.slime.isInLava()) && this.slime.getMoveControl() instanceof ClayCubeEntity.MoveHelperController;
	      }

	      public void tick() {
	         if (this.slime.getRandom().nextFloat() < 0.8F) {
	            this.slime.getJumpControl().jump();
	         }

	         ((ClayCubeEntity.MoveHelperController)this.slime.getMoveControl()).setWantedMovement(1.2D);
	      }
	   }

	   static class HopGoal extends Goal {
	      private final ClayCubeEntity slime;

	      public HopGoal(ClayCubeEntity p_i45822_1_) {
	         this.slime = p_i45822_1_;
	         this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	      }

	      public boolean canUse() {
	         return !this.slime.isPassenger();
	      }

	      public void tick() {
	         ((ClayCubeEntity.MoveHelperController)this.slime.getMoveControl()).setWantedMovement(1.0D);
	      }
	   }

	   static class MoveHelperController extends MovementController {
	      private float yRot;
	      private int jumpDelay;
	      private final ClayCubeEntity slime;
	      private boolean isAggressive;

	      public MoveHelperController(ClayCubeEntity p_i45821_1_) {
	         super(p_i45821_1_);
	         this.slime = p_i45821_1_;
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
	                  this.jumpDelay = this.slime.getJumpDelay();
	                  if (this.isAggressive) {
	                     this.jumpDelay /= 3;
	                  }

	                  this.slime.getJumpControl().jump();
	                  if (this.slime.doPlayJumpSound()) {
	                     this.slime.playSound(this.slime.getJumpSound(), this.slime.getSoundVolume(), this.slime.getSoundPitch());
	                  }
	               } else {
	                  this.slime.xxa = 0.0F;
	                  this.slime.zza = 0.0F;
	                  this.mob.setSpeed(0.0F);
	               }
	            } else {
	               this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));
	            }

	         }
	      }
	   }
	   
	   public int getSlimeType() {
		      return this.entityData.get(DATA_TYPE_ID);
		   }

		   public void setSlimeType(int p_175529_1_) {
		      this.entityData.set(DATA_TYPE_ID, p_175529_1_);
		   }
		   

		   private int getRandomSlimeType(IWorld p_213610_1_) {
		      int i = this.random.nextInt(4);
		         return i;
		   }	   
		   public static class ClayCubeData implements ILivingEntityData {
			      public final int ClayCubeType;

			      public ClayCubeData(int p_i45864_1_) {
			         super();
			         this.ClayCubeType = p_i45864_1_;
			      }
			   }

		   //Change its face!! 
		   public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
			      ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
			      if (itemstack.getItem() instanceof ShovelItem) {
			         p_230254_1_.playSound(SoundEvents.GRAVEL_HIT, 1.0F, 0.5F);
			   
			         if (this.getSlimeType() < 5) {
			         this.setSlimeType(this.getSlimeType() + 1);
			         }
			         if (this.getSlimeType() >= 5) {
				         this.setSlimeType(0);
				         }
			         if (!this.level.isClientSide) {
			             itemstack.hurtAndBreak(1, p_230254_1_, (p_213442_1_) -> {
			                p_213442_1_.broadcastBreakEvent(p_230254_2_);
			             });
			             
			             ((ServerWorld)this.level).sendParticles(ParticleTypes.ITEM_SNOWBALL, this.getX(), this.getY(0.4D), this.getZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
				          
			           //  this.level.addParticle(ParticleTypes.ITEM_SNOWBALL, this.getX(), this.getY() + this.getSize(), this.getZ(), 0.0D, 0.0D, 0.0D);
				       	   
			          }
			         
			         return ActionResultType.sidedSuccess(this.level.isClientSide);
			      } else {
			         return super.mobInteract(p_230254_1_, p_230254_2_);
			      }
			   }
		   
	}
