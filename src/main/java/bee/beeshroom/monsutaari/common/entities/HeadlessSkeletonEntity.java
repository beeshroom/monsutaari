
package bee.beeshroom.monsutaari.common.entities;

import javax.annotation.Nullable;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class HeadlessSkeletonEntity extends MonsterEntity implements IRangedAttackMob 
{

	public HeadlessSkeletonEntity(EntityType<? extends HeadlessSkeletonEntity> type, World worldIn) {
		super(type, worldIn);
	//	this.reassessWeaponGoal();
	//	 this.xpReward = 5;
	}
	
	/* final RangedBowAttackGoal<AbstractSkeletonEntity> bowGoal = new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F);	   
	 private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false) {
	      public void stop() {
	         super.stop();
	         HeadlessSkeletonEntity.this.setAggressive(false);
	      }

	      public void start() {
	         super.start();
	         HeadlessSkeletonEntity.this.setAggressive(true);
	      }
	   }; */

	   @Override
	   protected void registerGoals() {
		   super.registerGoals();
		 //  this.goalSelector.addGoal(1, new SwimGoal(this));
		   this.goalSelector.addGoal(2, new PanicGoal(this, 1.25D));
	      //this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		 //    this.goalSelector.addGoal(3, new RangedBowAttackGoal<>(this, 1.0D, 20, 15.0F));
		//     this.goalSelector.addGoal(11, new RangedBowAttackGoal<>(this, 0.0D, 90, 0.0F));
	      this.goalSelector.addGoal(3, new FleeSunGoal(this, 0.5D));
	   //   this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, WolfEntity.class, 6.0F, 1.0D, 1.2D));
	//      this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, LivingEntity.class, 6.0F, 1.0D, 1.2D));
	    //  this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	    //  this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	      this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
	      this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 0.6D));
	      
	//      this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
	   //   this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	   //   this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	   //   this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
	   //   this.goalSelector.addGoal(11, new LookAtGoal(this, PlayerEntity.class, 10.0F));
	   }

	   public static AttributeModifierMap.MutableAttribute registerAttributes() {
	      return MonsterEntity.createMonsterAttributes()
	    		  .add(Attributes.MOVEMENT_SPEED, 0.25D);
	   }
	   
	   
	   protected SoundEvent getAmbientSound() {
		      return SoundEvents.SKELETON_AMBIENT;
		   }

		   protected SoundEvent getHurtSound(DamageSource p_184601_1_) {
		      return SoundEvents.SKELETON_HURT;
		   }

		   protected SoundEvent getDeathSound() {
		      return SoundEvents.SKELETON_DEATH;
		   }

		   protected SoundEvent getStepSound() {
		      return SoundEvents.SKELETON_STEP;
		   }

	   public CreatureAttribute getMobType() {
	      return CreatureAttribute.UNDEAD;
	   }

	   public void aiStep() {
	      boolean flag = this.isSunBurnTick();
	      if (flag) {
	         if (flag) {
	            this.setSecondsOnFire(8);
	         }
	      }
	      
	      this.handDropChances[EquipmentSlotType.OFFHAND.getIndex()] = 2.0F;
	        this.handDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
	        this.armorDropChances[EquipmentSlotType.HEAD.getIndex()] = 2.0F;
	        this.armorDropChances[EquipmentSlotType.CHEST.getIndex()] = 2.0F;
	        this.armorDropChances[EquipmentSlotType.LEGS.getIndex()] = 2.0F;
	        this.armorDropChances[EquipmentSlotType.FEET.getIndex()] = 2.0F;
	      
	      
	      super.aiStep();
	   }

	  

	   protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
	      super.populateDefaultEquipmentSlots(p_180481_1_);
	      this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
	   }

	   @Nullable
	   public ILivingEntityData finalizeSpawn(IServerWorld p_213386_1_, DifficultyInstance p_213386_2_, SpawnReason p_213386_3_, @Nullable ILivingEntityData p_213386_4_, @Nullable CompoundNBT p_213386_5_) {
	      p_213386_4_ = super.finalizeSpawn(p_213386_1_, p_213386_2_, p_213386_3_, p_213386_4_, p_213386_5_);
	      this.populateDefaultEquipmentSlots(p_213386_2_);
	      this.populateDefaultEquipmentEnchantments(p_213386_2_);
	      this.handDropChances[EquipmentSlotType.OFFHAND.getIndex()] = 2.0F;
	        this.handDropChances[EquipmentSlotType.MAINHAND.getIndex()] = 2.0F;
	        this.armorDropChances[EquipmentSlotType.HEAD.getIndex()] = 2.0F;
	        this.armorDropChances[EquipmentSlotType.CHEST.getIndex()] = 2.0F;
	        this.armorDropChances[EquipmentSlotType.LEGS.getIndex()] = 2.0F;
	        this.armorDropChances[EquipmentSlotType.FEET.getIndex()] = 2.0F;
	   //.reassessWeaponGoal();
	    
	      //cannot pick up loot bc if it could, it'd wear helmets and make no sense
	      //  this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * p_213386_2_.getSpecialMultiplier());

	      return p_213386_4_;
	   }

	/*   public void reassessWeaponGoal() {
	      if (this.level != null && !this.level.isClientSide) {
	         this.goalSelector.removeGoal(this.meleeGoal);
	         this.goalSelector.removeGoal(this.bowGoal);
	         ItemStack itemstack = this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW));
	         if (itemstack.getItem() == Items.BOW) {
	          
	            this.goalSelector.addGoal(4, this.bowGoal);
	         } else {
	            this.goalSelector.addGoal(4, this.meleeGoal);
	         }

	      }
	   } */

	   public void performRangedAttack(LivingEntity p_82196_1_, float p_82196_2_) {
	      ItemStack itemstack = this.getProjectile(this.getItemInHand(ProjectileHelper.getWeaponHoldingHand(this, Items.BOW)));
	      AbstractArrowEntity abstractarrowentity = this.getArrow(itemstack, p_82196_2_);
	      if (this.getMainHandItem().getItem() instanceof net.minecraft.item.BowItem)
	         abstractarrowentity = ((net.minecraft.item.BowItem)this.getMainHandItem().getItem()).customArrow(abstractarrowentity);
	      double d0 = p_82196_1_.getX() - this.getX();
	      double d1 = p_82196_1_.getY(0.3333333333333333D) - abstractarrowentity.getY();
	      double d2 = p_82196_1_.getZ() - this.getZ();
	      double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
	      abstractarrowentity.shoot(d0, d1 + d3 * (double)0.2F, d2, 1.6F, (float)(14 - this.level.getDifficulty().getId() * 4));
	      this.playSound(SoundEvents.SKELETON_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	      this.level.addFreshEntity(abstractarrowentity);
	   }

	   protected AbstractArrowEntity getArrow(ItemStack p_213624_1_, float p_213624_2_) {
	      return ProjectileHelper.getMobArrow(this, p_213624_1_, p_213624_2_);
	   }

	   public boolean canFireProjectileWeapon(ShootableItem p_230280_1_) {
	      return p_230280_1_ == Items.BOW;
	   }

	   public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	      super.readAdditionalSaveData(p_70037_1_);
	  //.reassessWeaponGoal();
	   }

	   public void setItemSlot(EquipmentSlotType p_184201_1_, ItemStack p_184201_2_) {
	      super.setItemSlot(p_184201_1_, p_184201_2_);
	//      if (!this.level.isClientSide) {
	//         this.reassessWeaponGoal();
	//      }

	   }

	   protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
	      return 1.2F;
	   }

	   public double getMyRidingOffset() {
	      return -0.6D;
	   }
	   
	   
	   
	   
	/*   static class RangedBowAttackGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.RangedBowAttackGoal<T> {
		      public RangedBowAttackGoal(T HeadlessSkeletonEntity, double p_i47515_2_, int p_i47515_4_, float p_i47515_5_) {
			super(HeadlessSkeletonEntity, p_i47515_2_, p_i47515_4_, p_i47515_5_);
			// TODO Auto-generated constructor stub
		}

			private final HeadlessSkeletonEntity headlessskeleton;
	   
	   } 
	   */

	}
