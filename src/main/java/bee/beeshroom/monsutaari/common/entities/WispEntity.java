package bee.beeshroom.monsutaari.common.entities;

import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tags.ITag;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//Made out of bits of Slime and Rabbit code with a bunch of tweaking

public class WispEntity extends AnimalEntity implements IFlyingAnimal 
{
	   private static final DataParameter<Integer> DATA_TYPE_ID = EntityDataManager.defineId(WispEntity.class, DataSerializers.INT);
	   @Nullable
	   private BlockPos boundOrigin;

	   public WispEntity(EntityType<? extends WispEntity> p_i48552_1_, World p_i48552_2_) {
	      super(p_i48552_1_, p_i48552_2_);
	      this.moveControl = new FlyingMovementController(this, 20, true);
	      this.lookControl = new WispEntity.WispLookController(this);
	      this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
	      this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
	      this.setPathfindingMalus(PathNodeType.WATER_BORDER, 16.0F);
	      this.setPathfindingMalus(PathNodeType.COCOA, -1.0F);
	      this.setPathfindingMalus(PathNodeType.FENCE, -1.0F);
	      this.xpReward = 0;
	   }
	   
	/*   public void move(MoverType p_213315_1_, Vector3d p_213315_2_) {
		      super.move(p_213315_1_, p_213315_2_);
		      this.checkInsideBlocks();
		   }

		   public void tick() {
		   //   this.noPhysics = true;
		      super.tick();
		   //   this.noPhysics = false;
		      this.setNoGravity(true);
		   } */

	   
	   @Override
	protected void registerGoals() {
		   this.goalSelector.addGoal(0, new SwimGoal(this));
		    this.goalSelector.addGoal(1, new LookAtGoal(this, MobEntity.class, 8.0F));
		    this.goalSelector.addGoal(3, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
		    this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
		    this.goalSelector.addGoal(8, new WispEntity.WanderGoal());
		     this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(Items.GLASS_BOTTLE), false));
	   }

	   @Override
	protected void defineSynchedData() {
	      super.defineSynchedData();
	      this.entityData.define(DATA_TYPE_ID, 0);
	   }

	   public static AttributeModifierMap.MutableAttribute registerAttributes() {
		      return MobEntity.createMobAttributes()
		    		  .add(Attributes.MAX_HEALTH, 1.0D)
		    		 // .add(Attributes.ATTACK_DAMAGE, 0.0D)
		    		  .add(Attributes.MOVEMENT_SPEED, 0.3F)
		      		  .add(Attributes.FLYING_SPEED, 0.4F)
		    		  .add(Attributes.FOLLOW_RANGE, 48.0D);
		   }
	   
	   @Override
	protected PathNavigator createNavigation(World p_175447_1_) {
		      FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, p_175447_1_) {
		         @Override
				public boolean isStableDestination(BlockPos p_188555_1_) {
		            return !this.level.getBlockState(p_188555_1_.below()).isAir();
		         }

		         @Override
				public void tick() {
		               super.tick(); 
		         }
		      };
		      flyingpathnavigator.setCanOpenDoors(false);
		      flyingpathnavigator.setCanFloat(false);
		      flyingpathnavigator.setCanPassDoors(true);
		      return flyingpathnavigator;
		   }
	   
	   @Override
	public float getBrightness() {
		      return 1.0F;
		   }
	   
	   @Override
	public boolean causeFallDamage(float p_225503_1_, float p_225503_2_) {
		      return false;
		   }
	   
	   @Override
	protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState p_184231_4_, BlockPos p_184231_5_) {
	   }
	   
	/*   public CreatureAttribute getMobType() {
		      return CreatureAttribute.UNDEAD;
		   } */
	   
	   @Override
	public boolean removeWhenFarAway(double p_213397_1_) {
		      return !this.hasCustomName();
		   }
	   
	   @Override
	public void aiStep() {
		      if (this.isInWater())
		      {
		    	  this.hurt(DamageSource.GENERIC, 1.0F);
		      }
		      if (this.isSunBurnTick())
		      {
		    	  this.hurt(DamageSource.GENERIC, 1.0F);
		      }
		  
		 /*     if (this.level.isClientSide) {
		     //     if (this.random.nextInt(24) == 0 && !this.isSilent()) {
		        //     this.level.playLocalSound(this.getX() + 0.5D, this.getY() + 0.5D, this.getZ() + 0.5D, SoundEvents.BLAZE_BURN, this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
		        //  } 

		          for(int i = 0; i < 2; ++i) {
		             this.level.addParticle(ParticleTypes.END_ROD, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
		          }
		       } */
		      super.aiStep();
		   }

	   @Override
	@OnlyIn(Dist.CLIENT)
	   public Vector3d getLeashOffset() {
	      return new Vector3d(0.0D, 0.5F * this.getEyeHeight(), this.getBbWidth() * 0.2F);
	   }
	   
	   
	   
	   @Override
	public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
	      super.addAdditionalSaveData(p_213281_1_);
	      p_213281_1_.putInt("WispType", this.getWispType());
	
	   }

	   @Override
	public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	      this.setWispType(p_70037_1_.getInt("WispType"));
	      super.readAdditionalSaveData(p_70037_1_);
	      
	   }
	 

	 

	   @Override
	public EntityType<? extends WispEntity> getType() {
	      return (EntityType<? extends WispEntity>)super.getType();
	   }

	   @Override
	   public void remove(boolean keepData) {
	      super.remove(keepData);
	   }

	   @Override
	public void push(Entity entity) {
		   
		   if (!(entity instanceof WispEntity) && !this.isLeashed() && this.isEffectiveAi())
		   {
		   
			   this.applyEffects((LivingEntity)entity);
			   
		   }
	   }
	   
	   @Override
	public void playerTouch(PlayerEntity player) {
		      if (this.isEffectiveAi()) {
		         this.applyEffects(player);
		      }

		   }
	   
	   protected void applyEffects(LivingEntity entity) {
		      if (this.isAlive()) {
		         
		    	  this.playSound(SoundEvents.BREWING_STAND_BREW, 1.0F, 1.5F);
				   
				   if (this.getWispType() == 0) 
			         {
					   entity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 3600));
			         }
			         if (this.getWispType() == 1) 
			         {
			      	   entity.addEffect(new EffectInstance(Effects.NIGHT_VISION, 3600));
			         }
			         if (this.getWispType() == 2) 
			         {
			      	   entity.addEffect(new EffectInstance(Effects.JUMP, 3600));
			         }
			         if (this.getWispType() == 3) 
			         {
			      	   entity.addEffect(new EffectInstance(Effects.REGENERATION, 900));
			         }
			         if (this.getWispType() == 4) 
			         {
			      	   entity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 1800));
			         }
			        
			         for(int i = 0; i < 20; ++i) {
			             double d0 = this.random.nextGaussian() * 0.02D;
			             double d1 = this.random.nextGaussian() * 0.02D;
			             double d2 = this.random.nextGaussian() * 0.02D;
			             this.level.addParticle(ParticleTypes.BUBBLE_POP, this.getRandomX(1.0D), this.getRandomY(), this.getRandomZ(1.0D), d0, d1, d2);
			         }
			         
			         this.remove();
			         
		      }

		   }

	 /*  public void playerTouch(PlayerEntity p_70100_1_) {
		   if (this.getWispType() == 0) 
	         {
	         
	         }
	         if (this.getWispType() == 1) 
	         {
	         
	         }
	         if (this.getWispType() == 2) 
	         {
	         
	         }
	         if (this.getWispType() == 3) 
	         {
	         
	         }
	         this.remove();
	   } */

	   

	   @Override
	protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
	      return 0.625F * p_213348_2_.height;
	   }

	 
	   
	   public static boolean checkWispSpawnRules(EntityType<WispEntity> entity, IWorld world, SpawnReason spawnreason, BlockPos blockpos, Random random) {       
			   if (world.getMoonBrightness() != 0f) 
				   {
				   return isDarkEnoughToSpawn(entity, world, spawnreason, blockpos, random);
				   } 
		   return false;
	   }
	   
	   public static boolean isDarkEnoughToSpawn(EntityType<WispEntity> entity, IWorld world, SpawnReason spawnreason, BlockPos blockpos, Random random) {
		      if (world.getBrightness(LightType.SKY, blockpos) > random.nextInt(32)) {
		         return false;
		      } else {
		         int i = ((IServerWorld) world).getLevel().isThundering() ? world.getMaxLocalRawBrightness(blockpos, 10) : world.getMaxLocalRawBrightness(blockpos);
		         return i <= random.nextInt(8);
		      }
		   }

	   
	

	   @Override
	public int getMaxHeadXRot() {
	      return 0;
	   }


	   @Override
	@Nullable
	   public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
	      int r = this.getRandomWispType(p_213386_1_);
	      
	      if (p_213386_4_ == null) {
	          p_213386_4_ = new AgeableEntity.AgeableData(false);
	       }
	      
	      if (p_213386_4_ instanceof WispEntity.WispData) {
		         r = ((WispEntity.WispData)p_213386_4_).WispType;
		      } else {
		         p_213386_4_ = new WispEntity.WispData(r);
		      }
	      
	      this.setWispType(r);
	      
	      if (this.getWispType() == 0) 
	         {
			    this.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, Integer.MAX_VALUE));
	         }
	         if (this.getWispType() == 1) 
	         {
	        	 this.addEffect(new EffectInstance(Effects.NIGHT_VISION, Integer.MAX_VALUE));
	         }
	         if (this.getWispType() == 2) 
	         {
	        	 this.addEffect(new EffectInstance(Effects.JUMP, Integer.MAX_VALUE));
	         }
	         if (this.getWispType() == 3) 
	         {
	        	 this.addEffect(new EffectInstance(Effects.REGENERATION, Integer.MAX_VALUE));
	         }
	         if (this.getWispType() == 4) 
	         {
	        	 this.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, Integer.MAX_VALUE));
	         }	
	      
	    
	      return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
	   }

	   @Override
	public boolean isBaby() {
		      return false;
		   }

	   @Override
	public boolean canMate(AnimalEntity p_70878_1_) {
		      return false;
		   }
	   
	   @Override
	@Nullable
	   public AgeableEntity getBreedOffspring(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
	      return null;
	   }
	   
	   public int getWispType() {
		      return this.entityData.get(DATA_TYPE_ID);
		   }

		   public void setWispType(int p_175529_1_) {
		      this.entityData.set(DATA_TYPE_ID, p_175529_1_);
		   }
		   

		   private int getRandomWispType(IWorld p_213610_1_) {
		      int i = this.random.nextInt(5);
		         return i;
		   }
		   
		   public static class WispData extends AgeableEntity.AgeableData {
			      public final int WispType;

			      public WispData(int p_i45864_1_) {
			         super(1.0f);
			         this.WispType = p_i45864_1_;
			      }
			   }
		   
	/*	   public ActionResultType mobInteract(PlayerEntity entity, Hand hand) {
			      ItemStack itemstack = entity.getItemInHand(hand);
			      if (itemstack.getItem() instanceof GlassBottleItem) {
			         
			    	  entity.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 0.5F);
			    	  itemstack.shrink(1);
			         if (itemstack.isEmpty()) {
			  
			         if (this.getWispType() == 0) 
			         {
			        
			         }
			         if (this.getWispType() == 1) 
			         {
			   
			         }
			         if (this.getWispType() == 2) 
			         {
			        
			         }
			         if (this.getWispType() == 3) 
			         {
			        	
			         }
			         if (this.getWispType() == 4) 
			         {
			        	
			         }
			         
			         }
			         else
			         { 
			        	   if (this.getWispType() == 0) 
					         {
					        
					         }
					         if (this.getWispType() == 1) 
					         {
					   
					         }
					         if (this.getWispType() == 2) 
					         {
					        
					         }
					         if (this.getWispType() == 3) 
					         {
					        	
					         }
					         if (this.getWispType() == 4) 
					         {
					        	
					         }  
			         }
			         
			    	  this.remove();
			         return ActionResultType.sidedSuccess(this.level.isClientSide);
			      } else {
			         return super.mobInteract(entity, hand);
			      }
			   } */

		   //from Beehives
		   @Override
		public ActionResultType mobInteract(PlayerEntity entity, Hand hand) {
			      ItemStack itemstack = entity.getItemInHand(hand);
			      if (itemstack.getItem() instanceof GlassBottleItem) {
			         
			    	  entity.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 0.5F);
			    	  itemstack.shrink(1);
			      //   if (itemstack.isEmpty()) {
			        //	 entity.setItemInHand(hand, new ItemStack(Items.POTION));
			          //  } else if (!entity.inventory.add(new ItemStack(Items.HONEY_BOTTLE))) {
			           // 	entity.drop(new ItemStack(Items.HONEY_BOTTLE), false);
			          //  } 
			         if (itemstack.isEmpty()) {
			  
			         if (this.getWispType() == 0) 
			         {
			        	 entity.setItemInHand(hand, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS));
			         }
			         if (this.getWispType() == 1) 
			         {
			        	 entity.setItemInHand(hand, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.NIGHT_VISION));
			         }
			         if (this.getWispType() == 2) 
			         {
			        	 entity.setItemInHand(hand, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LEAPING));
			         }
			         if (this.getWispType() == 3) 
			         {
			        	 entity.setItemInHand(hand, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.REGENERATION));
			         }
			         if (this.getWispType() == 4) 
			         {
			        	 entity.setItemInHand(hand, PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS));
			         }
			         
			         }
			         else
			         { 
			        	 if (this.getWispType() == 0) 
				         {
				        	 entity.drop(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SWIFTNESS), false);
				         }
				         if (this.getWispType() == 1) 
				         {
				        	 entity.drop(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.NIGHT_VISION), false);
				         }
				         if (this.getWispType() == 2) 
				         {
				        	 entity.drop(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.LEAPING), false);
				         }
				         if (this.getWispType() == 3) 
				         {
				        	 entity.drop(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.REGENERATION), false);
				         }
				         if (this.getWispType() == 4) 
				         {
				        	 entity.drop(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.SLOWNESS), false);
				         }   
			         }
			         
			    	  this.remove();
			         return ActionResultType.sidedSuccess(this.level.isClientSide);
			      } else {
			         return super.mobInteract(entity, hand);
			      }
			   } 
		   
		   
		/*   private void pathfindRandomlyTowards(BlockPos p_226433_1_) {
			      Vector3d vector3d = Vector3d.atBottomCenterOf(p_226433_1_);
			      int i = 0;
			      BlockPos blockpos = this.blockPosition();
			      int j = (int)vector3d.y - blockpos.getY();
			      if (j > 2) {
			         i = 4;
			      } else if (j < -2) {
			         i = -4;
			      }

			      int k = 6;
			      int l = 8;
			      int i1 = blockpos.distManhattan(p_226433_1_);
			      if (i1 < 15) {
			         k = i1 / 2;
			         l = i1 / 2;
			      }

			      Vector3d vector3d1 = RandomPositionGenerator.getAirPosTowards(this, k, l, i, vector3d, (double)((float)Math.PI / 10F));
			      if (vector3d1 != null) {
			         this.navigation.setMaxVisitedNodesMultiplier(0.5F);
			         this.navigation.moveTo(vector3d1.x, vector3d1.y, vector3d1.z, 1.0D);
			      }
			   } */
		   
		   
		   @Override
		protected void jumpInLiquid(ITag<Fluid> p_180466_1_) {
			      this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.01D, 0.0D));
			   }
		   
		   
		   class WispLookController extends LookController {
			      WispLookController(MobEntity p_i225729_2_) {
			         super(p_i225729_2_);
			      }

			      @Override
				public void tick() {
			            super.tick();
			      }

			      @Override
				protected boolean resetXRotOnTick() {
			         return true;
			      }
			   }
		   
		   class WanderGoal extends Goal {
			      WanderGoal() {
			         this.setFlags(EnumSet.of(Goal.Flag.MOVE));
			      }

			      @Override
				public boolean canUse() {
			         return WispEntity.this.navigation.isDone() && WispEntity.this.random.nextInt(10) == 0;
			      }

			      @Override
				public boolean canContinueToUse() {
			         return WispEntity.this.navigation.isInProgress();
			      }

			      @Override
				public void start() {
			         Vector3d vector3d = this.findPos();
			         if (vector3d != null) {
			            WispEntity.this.navigation.moveTo(WispEntity.this.navigation.createPath(new BlockPos(vector3d), 1), 1.0D);
			         }

			      }

			      @Nullable
			      private Vector3d findPos() {
			         Vector3d vector3d;
			         {
			            vector3d = WispEntity.this.getViewVector(0.0F);
			         }

			         int i = 8;
			         Vector3d vector3d2 = RandomPositionGenerator.getAboveLandPos(WispEntity.this, 8, 7, vector3d, ((float)Math.PI / 2F), 2, 1);
			         return vector3d2 != null ? vector3d2 : RandomPositionGenerator.getAirPos(WispEntity.this, 8, 4, -2, vector3d, (float)Math.PI / 2F);
			      }
			   }

	
	}
