package bee.beeshroom.monsutaari.common.entities;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class QuartzSkeletonEntity extends MonsterEntity {
	private static final DataParameter<Integer> DATA_TYPE_ID = EntityDataManager.defineId(QuartzSkeletonEntity.class, DataSerializers.INT);
	public int quartzTime = this.random.nextInt(6000) + 6000;   
	
	public QuartzSkeletonEntity(EntityType<? extends QuartzSkeletonEntity> p_i50194_1_, World p_i50194_2_) {
		      super(p_i50194_1_, p_i50194_2_);
		   }
	   
	   
	   @Override
	protected void registerGoals() {
		      this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		      this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
		      this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		      this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		      this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		      this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
		      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		      this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
		   }
	   

		   @Override
		protected SoundEvent getAmbientSound() {
		      return SoundEvents.SKELETON_AMBIENT;
		   }

		   @Override
		protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		      return SoundEvents.SKELETON_HURT;
		   }

		   @Override
		protected SoundEvent getDeathSound() {
		      return SoundEvents.SKELETON_DEATH;
		   }

		   protected SoundEvent getStepSound() {
		      return SoundEvents.SKELETON_STEP;
		   }
		   
		   
			public static AttributeModifierMap.MutableAttribute registerAttributes() {
				return MonsterEntity.createMonsterAttributes()
					//	.add(Attributes.MAX_HEALTH, 7.0f)
						.add(Attributes.ATTACK_DAMAGE, 3.0f)
					//	.add(Attributes.ATTACK_SPEED, 2.0f)
					//	.add(Attributes.FOLLOW_RANGE, 7.0D)
				//		.add(Attributes.MOVEMENT_SPEED, 0.1f);
						
						//they were moving way too slow
						//arguably, quartz should be FASTER shouldn't it?? 
						//I mean, they're FOSSILS too though, so idk
				.add(Attributes.MOVEMENT_SPEED, 0.2f);
			}
			
			protected float getAttackDamage() {
				if (this.getQuartzGrown()== 0)
				{
			      return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
				}
				else 
					return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE) - 2.0f;
			   }
 
				@Override
				public CreatureAttribute getMobType() {
				      return CreatureAttribute.UNDEAD;
			   } 
		   
		   
		   @Override
		protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
			      super.populateDefaultEquipmentSlots(p_180481_1_);
			     // this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
			   }
		   

		   @Override
		@Nullable
		   public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
			  // this.setQuartzGrown(0);
			      int r = 0;
			      if (p_213386_4_ instanceof QuartzSkeletonEntity.QuartzSkeletonData) {
				         r = ((QuartzSkeletonEntity.QuartzSkeletonData)p_213386_4_).QuartzSkeletonType;
				      } else {
				         p_213386_4_ = new QuartzSkeletonEntity.QuartzSkeletonData(r);
				      }
			      this.setQuartzGrown(r);
			      return super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
			  			      
		   }
		   
		   public static boolean checkSpawnRules(EntityType<QuartzSkeletonEntity> p_223321_0_, IWorld p_223321_1_, SpawnReason p_223321_2_, BlockPos p_223321_3_, Random p_223321_4_) {
			      BlockState blockstate = p_223321_1_.getBlockState(p_223321_3_.below());
			      return (blockstate.is(Blocks.NETHERRACK) || blockstate.is(Blocks.NETHER_QUARTZ_ORE));
			   }
		   
		   
		 //  @Override
/*			public boolean attackEntityFrom(DamageSource source, float amount) {
				Entity entity = source.getEntity();
				if(entity instanceof LivingEntity) {
					LivingEntity living = (LivingEntity) entity;
					ItemStack stack = living.getMainHandItem();
					Item item = stack.getItem();
					if (item instanceof PickaxeItem)
					{
				        this.quartzTime = this.random.nextInt(6000) + 6000;
					} 
				}
			//	return super.attackEntityFrom(source, amount);
				return false;
		   } */
		   
	/*	   public ActionResultType mobInteract(PlayerEntity p_230254_1_, Hand p_230254_2_) {
			      ItemStack itemstack = p_230254_1_.getItemInHand(p_230254_2_);
			      if (itemstack.getItem() instanceof PickaxeItem) 
			      {
			    	  if (!this.level.isClientSide && this.getQuartzGrown() == 0) {
			              itemstack.hurtAndBreak(1, p_230254_1_, (p_213613_1_) -> {
			                 p_213613_1_.broadcastBreakEvent(p_230254_2_);
			              });
			              return ActionResultType.SUCCESS;
			           } else {
			              return ActionResultType.CONSUME;
			           }
			      } else {
			          return super.mobInteract(p_230254_1_, p_230254_2_);
			       }
		   } */
		   
		   
	/*	   public void shear(SoundCategory p_230263_1_) {
			      this.level.playSound((PlayerEntity)null, this, SoundEvents.SHEEP_SHEAR, p_230263_1_, 1.0F, 1.0F);
			      this.setSheared(true);
			      int i = 1 + this.random.nextInt(3);

			      for(int j = 0; j < i; ++j) {
			         ItemEntity itementity = this.spawnAtLocation(ITEM_BY_DYE.get(this.getColor()), 1);
			         if (itementity != null) {
			            itementity.setDeltaMovement(itementity.getDeltaMovement().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
			         }
			      }

			   } */
		   
		   @Override
		public void aiStep() {
			   super.aiStep();
			   if (!this.level.isClientSide && this.isAlive() && --this.quartzTime <= 0) {
			         this.playSound(SoundEvents.ITEM_PICKUP, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
			       this.setQuartzGrown(0);
			         // this.spawnAtLocation(Items.EGG);
			         this.quartzTime = this.random.nextInt(6000) + 6000;
			      }
		   }
		   
		   
		   
	/*	   public boolean readyForPicking() {
			      return this.isAlive() //&& !this.isSheared() && !this.isBaby()
			    		  ;
			   } */
		   
		   public int getQuartzGrown() {
			      return this.entityData.get(DATA_TYPE_ID);
			   }

			   public void setQuartzGrown(int p_175529_1_) {
			      this.entityData.set(DATA_TYPE_ID, p_175529_1_);
			   }
			   
			   public static class QuartzSkeletonData implements ILivingEntityData {
				      public final int QuartzSkeletonType;

				      public QuartzSkeletonData(int p_i45864_1_) {
				         super();
				         this.QuartzSkeletonType = p_i45864_1_;
				      }
				   }
		   
			   
			   @Override
			protected void defineSynchedData() {
				      super.defineSynchedData();
				      this.entityData.define(DATA_TYPE_ID, 0);
				   }
			   
			   
		   @Override
		public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
			      super.readAdditionalSaveData(p_70037_1_);
			      this.setQuartzGrown(p_70037_1_.getInt("QuartzGrown"));
			     
			      if (p_70037_1_.contains("QuartzGrowTime")) {
			         this.quartzTime = p_70037_1_.getInt("QuartzGrowTime");
			      }
			   }

			   @Override
			public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
			      super.addAdditionalSaveData(p_213281_1_);
			      p_213281_1_.putInt("QuartzGrown", this.getQuartzGrown());
			      p_213281_1_.putInt("QuartzGrowTime", this.quartzTime);
			   }
		}