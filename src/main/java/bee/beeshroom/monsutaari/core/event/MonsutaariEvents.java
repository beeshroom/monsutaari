package bee.beeshroom.monsutaari.core.event;

import java.util.Random;

import bee.beeshroom.monsutaari.Config;
import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.common.blocks.WitherRoseBush;
import bee.beeshroom.monsutaari.common.entities.ClayCubeEntity;
import bee.beeshroom.monsutaari.common.entities.HeadlessSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.HeadlessWitherSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.QuartzSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.SkeletonHeadEntity;
import bee.beeshroom.monsutaari.common.entities.SnowCubeEntity;
import bee.beeshroom.monsutaari.common.entities.WitherSkeletonHeadEntity;
import bee.beeshroom.monsutaari.core.init.BlockInit;
import bee.beeshroom.monsutaari.core.init.EffectsInit;
import bee.beeshroom.monsutaari.core.init.EntityInit;
import bee.beeshroom.monsutaari.core.init.ItemInit;
import bee.beeshroom.monsutaari.world.PlantGeneration;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.SnowGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.HarvestCheck;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickItem;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Monsutaari.MOD_ID)
public class MonsutaariEvents {
	
	//endermites will spawn sometimes when enderman or shulkers teleport and when enderpearls are used.
/*	@SubscribeEvent
	public static void TeleportingSpawnsEndermiteMoreOften (net.minecraftforge.event.entity.living.EnderTeleportEvent event) 
	{
		if (Config.ENDERMITE_SPAWN.get())
		{
		LivingEntity entity = event.getEntityLiving();
		
	        	 Random random = ((LivingEntity)entity).getRandom(); 
	        
		            if ((random.nextInt(6) == 0) && !entity.hasEffect(EffectsInit.WARPED.get())) {
			        	
		            	EndermiteEntity endermiteentity = EntityType.ENDERMITE.create(entity.level);
		            	endermiteentity.moveTo(entity.getX(), entity.getY(), entity.getZ(),entity.yRot, entity.xRot);
		                endermiteentity.setPlayerSpawned(true);
		            	entity.level.addFreshEntity(endermiteentity);
		            }
	} } */
	
//Endermite will sometimes afflict you with "WARPED" when attacking
//ones with Name Tags won't give you the Warped effect because that would ruin people keeping them as pets or using them in farms
/*	@SubscribeEvent
	public static void Endermited (net.minecraftforge.event.entity.living.LivingAttackEvent event) 
	{
		if (Config.WARPED_EFFECT.get())
		{
		LivingEntity entity = event.getEntityLiving();
		DamageSource source = event.getSource();
		Entity attacker = source.getEntity();		
		
		 //endermite giving you the Warped effect
		if (!entity.level.isClientSide) {
	         boolean flag = false; 
	         
	         if (attacker instanceof EndermiteEntity && !(attacker.hasCustomName()) && !(entity.isBlocking()))
		{
	        	 if (!entity.hasEffect(EffectsInit.WARPED.get()))
	        	 {
	        			 {
	        	 Random random = ((LivingEntity)attacker).getRandom(); 
	        
		            if (random.nextInt(3) == 0) {
			        	
		            	//make this into a custom sound effect in the future maybe
		            	attacker.playSound(SoundEvents.ENDERMITE_AMBIENT, 1.5f, 1.6f);
		            	attacker.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.5f, 1.6f);
		            	attacker.remove();
		            	entity.addEffect(new EffectInstance (EffectsInit.WARPED.get(), 3600, 0));
		            	
		            	//event.setCanceled(true);
		            	
		                  flag = true;
		            }
		            }
		}
            if (!flag) {
             } 
          }
	} }
	} 
	*/
	
	//When a player is afflicted with WARPED, they will sometimes teleport upon taking damage
/*	@SubscribeEvent
	public static void WarpedEffect (net.minecraftforge.event.entity.living.LivingDamageEvent event) 
	{
	//	if (Config.WARPED_EFFECT.get())
	//	{
		LivingEntity entity = event.getEntityLiving();
		//DamageSource source = event.getSource();
		//Entity attacker = source.getEntity();		
		
		//if (!entity.level.isClientSide) {
	        // boolean flag = false; 
	         
	         if (entity.hasEffect(EffectsInit.WARPED.get()) 
	        		// && !(attacker instanceof EndermiteEntity)
	        		 )
		{
	        	// Random random = ((LivingEntity)entity).getRandom(); 
	        
	        	 //play endermite hurt sound bc the player is afflicted with one at the moment!!
	        	// entity.playSound(SoundEvents.ENDERMITE_HURT, 1.5f, 1.6f);  
	       // 	 SoundEvent endermitesound = SoundEvents.ENDERMITE_HURT;
          //       entity.level.playSound((PlayerEntity)null, entity.getX(), entity.getY(), entity.getZ(), endermitesound, SoundCategory.PLAYERS, 1.0F, 1.0F);
          //       entity.playSound(endermitesound, 1.0F, 1.0F);
	        	
//		            if (
		     /////////////// REMOVED RANDOM NEXT INT BC ITS JUST A FUN POTION NOW /////////////////
		            	//	(random.nextInt(2) == 0) //&& !(attacker instanceof EndermiteEntity)
		            		//|| attacker instanceof EndermanEntity
		            	//	&&
	//	            		!(source == DamageSource.SWEET_BERRY_BUSH)
	//	            		) 
		                	
		            	//entity.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.5f, 1.6f);		            
		            	
		        
		            	
	        	  if (!entity.level.isClientSide) {
	        	         double d0 = entity.getX();
	        	         double d1 = entity.getY();
	        	         double d2 = entity.getZ();
	        	         
	        	         entity.setOnGround(false);
				         	entity.push(0, 0.65D, 0);

	        	         for(int i = 0; i < 16; ++i) {
	        	            double d3 = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
	        	            double d4 = MathHelper.clamp(entity.getY() + (double)(entity.getRandom().nextInt(16) - 8), 0.0D, (double)(entity.level.getHeight() - 1));
	        	            double d5 = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 16.0D;
	        	            if (entity.isPassenger()) {
	        	               entity.stopRiding();
	        	            }

	        	            if (entity.randomTeleport(d3, d4, d5, true)) {
	        	               SoundEvent soundevent = entity instanceof FoxEntity ? SoundEvents.FOX_TELEPORT : SoundEvents.CHORUS_FRUIT_TELEPORT;
	        	               entity.level.playSound((PlayerEntity)null, d0, d1, d2, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
	        	               entity.playSound(soundevent, 1.0F, 1.0F);
	        	               break;
	        	            }
	        	         }
	        	  }
	        	         
	       // 	      }
		            
		}//}//}
	} */
		                       	
	
	//Event for when the Warped effect is REMOVED (such as when the player drinks milk, i hope??)
/*	@SubscribeEvent
	public static void WarpedRemoved (net.minecraftforge.event.entity.living.PotionEvent.PotionRemoveEvent event) 
	{
		if (Config.WARPED_EFFECT.get())
		{
		LivingEntity entity = event.getEntityLiving();
		//DamageSource source = event.getSource();
		//Entity attacker = source.getEntity();		
		
		if (!entity.level.isClientSide) {
	         boolean flag = false; 
	         
	         if (entity.hasEffect(EffectsInit.WARPED.get()))
		{
		            	EndermiteEntity endermiteentity = EntityType.ENDERMITE.create(entity.level);
		            	endermiteentity.setHealth(2);
		            	endermiteentity.moveTo(entity.getX(), entity.getY(), entity.getZ(),entity.yRot, entity.xRot);
		            
		            	endermiteentity.addEffect(new EffectInstance (Effects.MOVEMENT_SLOWDOWN, 30, 0));
		            	
		                endermiteentity.setPlayerSpawned(true);
		            	entity.level.addFreshEntity(endermiteentity);
		            	endermiteentity.playSound(SoundEvents.ENDERMITE_HURT, 1.5f, 1.6f);  
		            	endermiteentity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.5f, 1.6f); 
		            	
		          //  	endermiteentity.setSpeed(endermiteentity.getSpeed() * .01f);
		            	
		            	endermiteentity.addEffect(new EffectInstance (Effects.MOVEMENT_SLOWDOWN, 30, 0));
		            	//
		            	
		                  flag = true;
		            }
	         
	         if (!flag) 
	            {
	            	
	             } 
		            }
		
          }
	}
	*/
	
	//when the Warped Effect expires, an endermite will spawn
/*	@SubscribeEvent
	public static void WarpedExpire (net.minecraftforge.event.entity.living.PotionEvent.PotionExpiryEvent event) 
	{
		if (Config.WARPED_EFFECT.get())
		{
		LivingEntity entity = event.getEntityLiving();
		//DamageSource source = event.getSource();
		//Entity attacker = source.getEntity();		
		
		if (!entity.level.isClientSide) {
	         boolean flag = false; 
	         
	         if (entity.hasEffect(EffectsInit.WARPED.get()))
		{
	        	    	
		            	EndermiteEntity endermiteentity = EntityType.ENDERMITE.create(entity.level);
		            	 endermiteentity.setHealth(8);
		            	 endermiteentity.moveTo(entity.getX(), entity.getY(), entity.getZ(),entity.yRot, entity.xRot);
		            	 endermiteentity.setSpeed(endermiteentity.getSpeed() * .03f);
		            	 
		            		endermiteentity.addEffect(new EffectInstance (Effects.MOVEMENT_SLOWDOWN, 30, 0));
		            	 
		                 endermiteentity.setPlayerSpawned(true);
		            	 entity.level.addFreshEntity(endermiteentity);
		            	 endermiteentity.playSound(SoundEvents.ENDERMITE_HURT, 1.5f, 1.6f);  
			            	endermiteentity.playSound(SoundEvents.CHORUS_FRUIT_TELEPORT, 1.5f, 1.6f);   	
			            
			            	//idk if this goes before or after. both shouldnt hurt.
			            	endermiteentity.setSpeed(endermiteentity.getSpeed() * .03f);
			            	endermiteentity.addEffect(new EffectInstance (Effects.MOVEMENT_SLOWDOWN, 30, 0));
		            	
		                  flag = true;
		            }
	         
	         if (!flag) 
	            {
	            	
	             } 
		            }
		
          }
	} */
	
	//when an entity with WARPED dies, an endermite spawns
/*	@SubscribeEvent
	public static void WarpedExpire (net.minecraftforge.event.entity.living.LivingDeathEvent event) 
	{
		if (Config.WARPED_EFFECT.get())
		{
		LivingEntity entity = event.getEntityLiving();
		//DamageSource source = event.getSource();
		//Entity attacker = source.getEntity();		
		
		if (!entity.level.isClientSide) {
	         boolean flag = false; 
	         
	         if (entity.hasEffect(EffectsInit.WARPED.get()))
		{
	        	    	
		            	EndermiteEntity endermiteentity = EntityType.ENDERMITE.create(entity.level);
		            	 endermiteentity.setHealth(2);
		            	 endermiteentity.moveTo(entity.getX(), entity.getY(), entity.getZ(),entity.yRot, entity.xRot);
		                 endermiteentity.setPlayerSpawned(true);
		            	 entity.level.addFreshEntity(endermiteentity);
		            	 endermiteentity.playSound(SoundEvents.ENDERMITE_HURT, 1.5f, 1.6f);  
			            	endermiteentity.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.5f, 1.6f);   	
			            	
		            	
		                  flag = true;
		            }
	         
	         if (!flag) 
	            {
	            	
	             } 
		            }
          }
	}
	*/
	
	
	//spider placing a degrade-able web when attacking you event
		@SubscribeEvent
		public static void SpiderWebsYou (net.minecraftforge.event.entity.living.LivingAttackEvent event) 
		{	
			if (Config.SPIDERWEB.get())
			{
			LivingEntity entity = event.getEntityLiving();
			DamageSource source = event.getSource();
			Entity attacker = source.getEntity();		
			
			 //spider web attack
			//makes heavy use of Entity code for placing Wither Roses upon death
			if (!entity.level.isClientSide) {
		         boolean flag = false; 
		         
			//if (source.getEntity() instanceof SpiderEntity )
		         if (attacker instanceof SpiderEntity && !(entity.isBlocking()) && entity.isOnGround() && attacker.isOnGround() 
		        		 //&& entity.getHealth() > 4.0F 
		        		 )
			{
		        	 Random random = ((LivingEntity)attacker).getRandom(); 
		        	 
		       
		        /*	 SpiderEntity spider = (SpiderEntity) event.getEntity();
		        	 spider.goalSelector.addGoal(1, rot); */
		        
			            if ((random.nextInt(4) == 0) && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(entity.level, entity)) {
				        	 event.setCanceled(true);
			            	BlockPos blockpos = entity.blockPosition();
			               BlockState blockstate = BlockInit.TRAPWEB.get().defaultBlockState();
			              
			               if (entity.level.isEmptyBlock(blockpos) && blockstate.canSurvive(entity.level, blockpos) 
			            		   && entity.isOnGround()) 
			               {
			            	 //  attacker.rotate(Rotation.CLOCKWISE_180);
			            	   
			            	//   attacker.turn(180, 180);
			            	 
			            	   attacker.playSound(SoundEvents.SLIME_BLOCK_PLACE, 0.8f, 1.5f);
			            	   attacker.playSound(SoundEvents.STONE_PLACE, 1.5f, 1.6f);
			            	   
			            	   entity.level.setBlock(blockpos, blockstate, 3);
			            
			                  flag = true;
			            }
			            }

	            if (!flag) {
	          /*      ItemEntity itementity = new ItemEntity(entity.level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(Items.COBWEB));
	                entity.level.addFreshEntity(itementity);  */
	             } 
	          }
		
		
		} } }
		
		
		//thanks Gigaherz from the Minecraft Mod Development Discord!!!!
		
		@SubscribeEvent
		public static void HarvestCheck (HarvestCheck event)
		{
			PlayerEntity player = event.getPlayer();
			ItemStack stack = player.getMainHandItem();
			Item item = stack.getItem();
			BlockState state = event.getTargetBlock();
			if (item instanceof SwordItem && state.is(BlockInit.TRAPWEB.get()))
			{
				event.setCanHarvest(true);
			}
			if (item instanceof SwordItem && state.is(BlockInit.HANGING_WEB.get()))
			{
				event.setCanHarvest(true);
			}
			
			if (item instanceof ShearsItem && state.is(BlockInit.TRAPWEB.get()))
			{
				event.setCanHarvest(true);
			}
			if (item instanceof ShearsItem && state.is(BlockInit.HANGING_WEB.get()))
			{
				event.setCanHarvest(true);
			}
				}	
			
			@SubscribeEvent
			public static void BreakSpeed (net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed event)
			{
				PlayerEntity player = event.getPlayer();
				ItemStack stack = player.getMainHandItem();
				Item item = stack.getItem();
				BlockState state = event.getState();
				if (item instanceof SwordItem && state.is(BlockInit.TRAPWEB.get()))
				{
					event.setNewSpeed(item.getDestroySpeed(stack, state) * 2);
				}
				if (item instanceof SwordItem && state.is(BlockInit.HANGING_WEB.get()))
				{
					event.setNewSpeed(item.getDestroySpeed(stack, state) * 2);
				}
				
				if (item instanceof ShearsItem && state.is(BlockInit.TRAPWEB.get()))
				{
					event.setNewSpeed(item.getDestroySpeed(stack, state) * 2);
				}
				if (item instanceof ShearsItem && state.is(BlockInit.HANGING_WEB.get()))
				{
					event.setNewSpeed(item.getDestroySpeed(stack, state) * 2);
				}
				if (item instanceof PickaxeItem && state.is(BlockInit.BONES_BLOCK.get()))
				{
					event.setNewSpeed(item.getDestroySpeed(stack, state) * 2);
				}
				if (item instanceof ShovelItem && state.is(BlockInit.BONES_BLOCK.get()))
				{
					event.setNewSpeed(item.getDestroySpeed(stack, state) * 2);
				}
		}
			
			
			
			@SubscribeEvent
			public static void onEntityJoinWorld(EntityJoinWorldEvent event)
			{
				
				World world = event.getWorld();
				Entity entity = event.getEntity();
						
				if (!world.isClientSide)
				{
					if (Config.SKELETON_HEADS.get() || (Config.SKELETON_HEADS_NATURALLY_SPAWN.get()))
					{
					if (entity instanceof WolfEntity)
					{
						((CreatureEntity) entity).targetSelector.addGoal(4, new NearestAttackableTargetGoal<>((CreatureEntity) entity, SkeletonHeadEntity.class, false));
						((CreatureEntity) entity).targetSelector.addGoal(4, new NearestAttackableTargetGoal<>((CreatureEntity) entity, WitherSkeletonHeadEntity.class, false));
						((CreatureEntity) entity).targetSelector.addGoal(4, new NearestAttackableTargetGoal<>((CreatureEntity) entity,HeadlessSkeletonEntity.class, false));
						((CreatureEntity) entity).targetSelector.addGoal(4, new NearestAttackableTargetGoal<>((CreatureEntity) entity, HeadlessWitherSkeletonEntity.class, false));
						((CreatureEntity) entity).targetSelector.addGoal(4, new NearestAttackableTargetGoal<>((CreatureEntity) entity, QuartzSkeletonEntity.class, false));
					}
					}
					if (Config.SPIDERS_HUNT.get())
					{
					if (entity instanceof SpiderEntity)
					{
						((CreatureEntity) entity).targetSelector.addGoal(4, new NearestAttackableTargetGoal<>((CreatureEntity) entity, EndermiteEntity.class, false));
						((CreatureEntity) entity).targetSelector.addGoal(4, new NearestAttackableTargetGoal<>((CreatureEntity) entity, SilverfishEntity.class, false));
					}
					}
				} 
			}
			
			@SubscribeEvent
			public static void SkeletonBecomesSkeletonHead (LivingDamageEvent event)
			{
				if (Config.SKELETON_HEADS.get())
				{
				LivingEntity entity = event.getEntityLiving();
				DamageSource source = event.getSource();
				Entity attacker = source.getEntity();
				Random random = entity.getRandom(); 
				
				if ((entity instanceof SkeletonEntity || entity instanceof WitherSkeletonEntity)
						&& 
						(attacker instanceof PlayerEntity || attacker instanceof TameableEntity) &&
					/*	!(source == DamageSource.OUT_OF_WORLD) &&
						!(source == DamageSource.ON_FIRE) &&
						!(source == DamageSource.explosion(entity)) && */
						(entity.getItemBySlot(EquipmentSlotType.HEAD).isEmpty()) &&
					//	!(entity.getItemBySlot(EquipmentSlotType.CHEST).isEnchanted()) &&
					//	!(entity.getItemBySlot(EquipmentSlotType.LEGS).isEnchanted()) &&
					//	!(entity.getItemBySlot(EquipmentSlotType.FEET).isEnchanted()) &&
//removed this to test				//		!(entity.getItemBySlot(EquipmentSlotType.MAINHAND).isEnchanted()) &&
					//vvv this stops this from happening to mobs in minecarts, boats, and on spiders?? i hope??
						!(entity.getVehicle() instanceof Entity) 
						//!(entity.canHoldItem())
						)
				{
				
//Skeleton					//////////////////////////////////////////////////////////////////////////					
					if (entity instanceof SkeletonEntity && (random.nextInt(75) == 0)
							&& (entity.getItemBySlot(EquipmentSlotType.MAINHAND)).getItem() instanceof BowItem)
					{
					//Body
					HeadlessSkeletonEntity headlessskeletonentity = EntityInit.HEADLESS_SKELETON.get().create(entity.level);
					
					/*	for(EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) 
							skeleton.setItemSlot(equipmentslottype, skeleton.getItemBySlot(equipmentslottype));*/
					 //  for(EquipmentSlotType equipmentslottype : EquipmentSlotType.values()) {
			           //    ItemStack itemstack = entity.getItemBySlot(equipmentslottype);
			              // if (!itemstack.isEmpty()) {
			            	 //  entity.setItemSlot(equipmentslottype, itemstack.copy());
			              //   entity.setDropChance(equipmentslottype, .getEquipmentDropChance(equipmentslottype));
			            	 // don't need this  headlessskeletonentity.setItemSlot(EquipmentSlotType.HEAD, entity.getItemBySlot(EquipmentSlotType.HEAD));
			              //    itemstack.setCount(0);
			              // }}
					
					   headlessskeletonentity.moveTo(entity.getX(), entity.getY() + 0.6f, entity.getZ(),entity.yRot, entity.xRot);
						headlessskeletonentity.setHealth(entity.getHealth() + 0.5f);   
			               entity.level.addFreshEntity(headlessskeletonentity);
			               
					//Head
					SkeletonHeadEntity skeletonheadentity = EntityInit.SKELETON_HEAD.get().create(entity.level);
					skeletonheadentity.moveTo(entity.getX(), entity.getY() + 2.2f, entity.getZ(),entity.yRot, entity.xRot);
					skeletonheadentity.setHealth(2 + 0.5f);
					if (entity.hasCustomName())
					{
						skeletonheadentity.setCustomName(entity.getCustomName());
						skeletonheadentity.setCustomNameVisible(entity.isCustomNameVisible());
					}
					entity.level.addFreshEntity(skeletonheadentity);	
					
					//Copy the Armor and Held Item of the mob (unenchanted only, because I'm bad at coding)
					if (entity.getArmorCoverPercentage() > 0)
					{		
					  headlessskeletonentity.setItemSlot(EquipmentSlotType.CHEST, entity.getItemBySlot(EquipmentSlotType.CHEST));			  
			          headlessskeletonentity.setItemSlot(EquipmentSlotType.LEGS, entity.getItemBySlot(EquipmentSlotType.LEGS));
			          headlessskeletonentity.setItemSlot(EquipmentSlotType.FEET, entity.getItemBySlot(EquipmentSlotType.FEET));       	 	 
			        }
					if (entity.hasItemInSlot(EquipmentSlotType.MAINHAND))
					{
		     	     headlessskeletonentity.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(entity.getItemBySlot(EquipmentSlotType.MAINHAND).getItem()));
					}
					skeletonheadentity.hurt(DamageSource.GENERIC, 0.5f);
					headlessskeletonentity.hurt(DamageSource.GENERIC, 0.5f);
					entity.remove();
					}
					
//Wither Skeleton					//////////////////////////////////////////////////////////////////////////
					if (entity instanceof WitherSkeletonEntity && (random.nextInt(300) == 0)
							&& (entity.getItemBySlot(EquipmentSlotType.MAINHAND)).getItem() instanceof SwordItem) 
					{
					//Body
					HeadlessWitherSkeletonEntity headlessskeletonentity = EntityInit.HEADLESS_WITHER_SKELETON.get().create(entity.level);
					
					   headlessskeletonentity.moveTo(entity.getX(), entity.getY() + 0.6f, entity.getZ(),entity.yRot, entity.xRot);
						headlessskeletonentity.setHealth(entity.getHealth() + 0.5f);  
			               entity.level.addFreshEntity(headlessskeletonentity);
			               
					//Head
					WitherSkeletonHeadEntity skeletonheadentity = EntityInit.WITHER_SKELETON_HEAD.get().create(entity.level);
					skeletonheadentity.moveTo(entity.getX(), entity.getY() + 2.5f, entity.getZ(),entity.yRot, entity.xRot);
					skeletonheadentity.setHealth(2 + 0.5f);
					if (entity.hasCustomName())
					{
						skeletonheadentity.setCustomName(entity.getCustomName());
						skeletonheadentity.setCustomNameVisible(entity.isCustomNameVisible());
					}
					entity.level.addFreshEntity(skeletonheadentity);	
					
					//Copy the Armor and Held Item of the mob (unenchanted only, because I'm bad at coding)
					if (entity.getArmorCoverPercentage() > 0)
					{		
					  headlessskeletonentity.setItemSlot(EquipmentSlotType.CHEST, entity.getItemBySlot(EquipmentSlotType.CHEST));			  
			          headlessskeletonentity.setItemSlot(EquipmentSlotType.LEGS, entity.getItemBySlot(EquipmentSlotType.LEGS));
			          headlessskeletonentity.setItemSlot(EquipmentSlotType.FEET, entity.getItemBySlot(EquipmentSlotType.FEET));  
			        }
					if (entity.hasItemInSlot(EquipmentSlotType.MAINHAND))
					{
		     	     headlessskeletonentity.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(entity.getItemBySlot(EquipmentSlotType.MAINHAND).getItem()));
					}
					skeletonheadentity.hurt(DamageSource.GENERIC, 0.5f);
					headlessskeletonentity.hurt(DamageSource.GENERIC, 0.5f);
					headlessskeletonentity.setPersistenceRequired();
					entity.remove();
					}
				} } 
			}
			
			/* save for wither skeleton head
			 	  for(int i = 0; i < 20; ++i) {
				            double d0 = entity.getRandom().nextGaussian() * 0.02D;
				            double d1 = entity.getRandom().nextGaussian() * 0.02D;
				            double d2 = entity.getRandom().nextGaussian() * 0.02D;
				            entity.level.addParticle(ParticleTypes.POOF, entity.getRandomX(1.0D), entity.getRandomY(), entity.getRandomZ(1.0D), d0, d1, d2);
				         }
			 */			
			
			@SubscribeEvent
			public static void GolemFlowerEvent(LivingDeathEvent event)
			{ 
				if (Config.GOLEM_FLOWERS.get())
				{
				LivingEntity entity = event.getEntityLiving();
				LivingEntity killer = entity.getKillCredit();

				 if (!entity.level.isClientSide) {
			         boolean flag = false;
			         if (entity instanceof GolemEntity) {
	// 4/3/2022: Added a check for if the Wither was the killer; 
	//prior to this, if a Wither killed a golem, it would drop both a Golem Flower AND a Wither Rose / Wither Rose Bush
			        	 if (!(killer instanceof WitherEntity))
			        	 {
			            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(entity.level, entity)) {
			               BlockPos blockpos = entity.blockPosition();
			               BlockState blockstate = BlockInit.GOLEM_FLOWER.get().defaultBlockState();
			               
			               if ((entity.level.isEmptyBlock(blockpos) || entity.level.getBlockState(blockpos).getBlock() == Blocks.SNOW.defaultBlockState().getBlock()) 
			            	&& blockstate.canSurvive(entity.level, blockpos)) {
			                  entity.level.setBlock(blockpos, blockstate, 3);
			                  flag = true;
			               }}}
			         }
				 								} }
			}
			
		
		// Extra damage is dealt to Clay Cubes and Snow Cubes with a Shovel
			@SubscribeEvent
			public static void ToolDoesExtraDamage(LivingDamageEvent event)
			{ 
				
				if (Config.SLIME_VARIANTS.get())
				{
				Entity entity = event.getEntity();
				float amount = event.getAmount();
				DamageSource source = event.getSource();
				
				if (source.getEntity() instanceof LivingEntity)
				{
				
				LivingEntity attacker = (LivingEntity) source.getEntity();
				
				if (!(attacker == null) && attacker instanceof LivingEntity)
				{
				ItemStack stack = attacker.getMainHandItem();
				Item item = stack.getItem();
				if (entity instanceof ClayCubeEntity || entity instanceof SnowCubeEntity)
				{
				if (item instanceof ShovelItem)
					{
					event.setAmount(amount * 1.5f);
					} 
				} }
				} 
				}
				
				
				if (Config.QUARTZ_SKELETONS.get())
				{
				Entity entity = event.getEntity();
				float amount = event.getAmount();
				DamageSource source = event.getSource();
				
				// prevent a Crash caused by Dispenser Arrows not being a damage source from a LivingEntity
				if (source.getEntity() instanceof LivingEntity)
				{
				
				LivingEntity attacker = (LivingEntity) source.getEntity();
			//	AbstractArrowEntity arrow = (AbstractArrowEntity) source.getEntity();
				
				if (!(attacker == null) && attacker instanceof LivingEntity)
				{
				ItemStack stack = attacker.getMainHandItem();
				Item item = stack.getItem();
				if (entity instanceof QuartzSkeletonEntity)
				{
				QuartzSkeletonEntity quartzskeleton = (QuartzSkeletonEntity)entity;
				Random random = ((LivingEntity)entity).getRandom();
		        
				if (item instanceof PickaxeItem)
					{
					if (quartzskeleton.getQuartzGrown() == 1)
					{
					event.setAmount(amount * 1.4f);
					}
					
				if (quartzskeleton.getQuartzGrown() == 0)
					{
					entity.playSound(SoundEvents.STONE_BREAK, 1.5f, 1.6f);
					quartzskeleton.setQuartzGrown(1);
					entity.spawnAtLocation(Items.QUARTZ);
					entity.spawnAtLocation(Items.QUARTZ);
					
					if (random.nextInt(5) == 0)
					{
						entity.spawnAtLocation(Items.QUARTZ);
					}
					
					event.isCanceled();
					} 
				} 
				}
				}
				}
				
				//added ability to shoot an arrow to knock quartz off
				if (source.getDirectEntity() instanceof AbstractArrowEntity
					|| source.getDirectEntity() instanceof TridentEntity)
				{
					if (entity instanceof QuartzSkeletonEntity)
					{
					QuartzSkeletonEntity quartzskeleton = (QuartzSkeletonEntity)entity;
					Random random = ((LivingEntity)entity).getRandom();
			        
					if (quartzskeleton.getQuartzGrown() == 0)
						{
						entity.playSound(SoundEvents.STONE_BREAK, 1.5f, 1.6f);
						quartzskeleton.setQuartzGrown(1);
						entity.spawnAtLocation(Items.QUARTZ);
						entity.spawnAtLocation(Items.QUARTZ);
						
						if (random.nextInt(5) == 0)
						{
							entity.spawnAtLocation(Items.QUARTZ);
						}
						
						event.isCanceled();
						} 
					
					}
				}
				
				
				
				}
				
			}
			
			
			
			//Decrease the Health level of Baby Hostile mobs by half
			
			@SubscribeEvent
			public static void NerfBabyMonsters(LivingSpawnEvent event)
			{
				if (Config.NERF_BABY_MONSTERS.get())
			{
				Entity entity = event.getEntity();
				if (entity instanceof MonsterEntity)
				{
					MonsterEntity babymonster = (MonsterEntity) entity;
				
					if (babymonster.isBaby())
					{
						babymonster.setHealth(babymonster.getHealth()/1.5f);
					}
				} 
			}
			}
			
			
			
			@SubscribeEvent
			public static void WitherRoseBush(LivingDeathEvent event)
			{ 
				if (Config.WITHER_ROSE_BUSH.get())
				{
				LivingEntity entity = event.getEntityLiving();
				LivingEntity killer = entity.getKillCredit();
			//	DamageSource damage = event.getSource();

				 if (!entity.level.isClientSide) {
			         boolean flag = false;
			         if (killer instanceof WitherEntity) {
			        
			        		if (entity.getBbHeight() > 2.0f || entity.getMaxHealth() >= 30)
				        	{
			        			
			        		//////this should stop wither rose from spawning, i hope!!/////
					        	 entity.setLastHurtByMob(null);
					        	 ////////////////////////////
			        	 
			            if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(entity.level, entity)) {
			               BlockPos blockpos = entity.blockPosition();
			               BlockState blockstate = BlockInit.WITHER_ROSE_BUSH.get().defaultBlockState();
			             
			               if (entity.level.isEmptyBlock(blockpos) 
			            		   && entity.level.isEmptyBlock(blockpos.above())
			            		   && blockstate.canSurvive(entity.level, blockpos)
			            		   ) {
			            	   	WitherRoseBush block = (WitherRoseBush) blockstate.getBlock();
								block.placeAt(entity.level, blockpos, 2);

			            	//   entity.level.setBlock(blockpos, blockstate, 2);
			            	//   entity.level.setBlock(blockpos2, BlockInit.WITHER_ROSE_BUSH.get().defaultBlockState().setValue(DoublePlantBlock.HALF, DoubleBlockHalf.UPPER), 1);
			            	   
			            //	   entity.level.setBlock(blockpos, blockstate.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER), 3);
			            	 //  entity.level.setBlock(blockpos.above(), blockstate.setValue(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER), 3);
			            	      
			                  flag = true;
			               }
			            }

			            if (!flag) {
			               ItemEntity itementity = new ItemEntity(entity.level, entity.getX(), entity.getY(), entity.getZ(), new ItemStack(ItemInit.WITHER_ROSE_BUSH.get()));
			               entity.level.addFreshEntity(itementity);
			            }
			            
			            event.setCanceled(true);
			            entity.kill();
			         } }
				 }
				}	} 
			
	/*
	 //this didnt work or i don't care
	 		@SubscribeEvent
			public static void GhastlyMakesYouWeak(LivingDamageEvent event) {
				//LivingEntity entity = event.getEntityLiving();
				DamageSource source = event.getSource();
				Entity sourceentity = source.getEntity();		
				
			         if (sourceentity instanceof LivingEntity)
			         {
			        	 LivingEntity attacker = (LivingEntity) sourceentity;

			        	if (attacker.hasEffect(EffectsInit.GHASTLY.get()))
			        			{
			        		
			        		event.setAmount(event.getAmount() - 2);
			        		
			        		attacker.playSound(SoundEvents.CAT_PURREOW, 3.0f, 3.0f);
			        			} 														
			        }
			} */
				
	/*		@SubscribeEvent
			public static void GhastlyMakesUndeadIgnoreYou(LivingUpdateEvent event) {
				LivingEntity entity = event.getEntityLiving();
/*			if (entity.getMobType() == CreatureAttribute.UNDEAD)
{
				//Phantoms dont care if you look dead or not! They just want to attack you for not sleeping!!
				//This caused a crash, so i have to be careful
				//I hope it doesnt crash with BuzzierBees's Flare...
				//This also does not work at all with Mobs ursing ranged weapon attacks, like Skeletons and Drowneds :///
				if (entity instanceof MonsterEntity && !(entity instanceof PhantomEntity) )
				{
	if (((MonsterEntity) entity).getTarget() instanceof LivingEntity)
	{
		if (((MonsterEntity) entity).getTarget().hasEffect(EffectsInit.GHASTLY.get()))
		{
			((MonsterEntity) entity).setTarget(null);
			
		}
	} }
			 } */
			
			   //Was trying to make mobs scared of you after you get ghastly, but i give up + changed my mind
// 4/5/2022: decided to remove this again
		/*	  	if (entity instanceof MonsterEntity)
				{
					if (((MonsterEntity) entity).getTarget() instanceof LivingEntity)
					{
						if (((MonsterEntity) entity).getTarget().hasEffect(EffectsInit.GHASTLY.get()))
						{
							MonsterEntity monster = (MonsterEntity) entity;
					//		((MonsterEntity) entity).goalSelector.addGoal(4, new AvoidEntityGoal<>((CreatureEntity) entity, QuartzSkeletonEntity.class, false));
							((MonsterEntity) entity).goalSelector.addGoal(0, new AvoidEntityGoal<>((monster), LivingEntity.class, 6.0F, 1.0D, 1.2D));							//((MonsterEntity) entity).setTarget(null);
						}
					}
							} */ 
				
				
	//this made ghasts ignore you			
/*			if (entity instanceof GhastEntity)
			{
				if (((GhastEntity) entity).getTarget() instanceof LivingEntity)
				{
					if (((GhastEntity) entity).getTarget().hasEffect(EffectsInit.GHASTLY.get()))
					{
						((GhastEntity) entity).setTarget(null);
					}
				}
						}
				
			}
			
			//this made Skeletons, Zoglins, etc etc unable to damage you while you had Ghastly
			//and for some reason, Zoglins and Skeletons still chase you around, but they cant 
			//so now im making it only have zombies not hurt you? ? ?  
			//hopefully that doesnt mean drowned tridents will bounce off you..... 
	/*		@SubscribeEvent
			public static void UndeadMonsterHitYouButYouHaveGhastlyEffect(LivingAttackEvent event) {
				LivingEntity entity = event.getEntityLiving();
				DamageSource source = event.getSource();
				Entity attacker = source.getEntity();	
				if (attacker instanceof ZombieEntity)
				{
					if (entity.hasEffect(EffectsInit.GHASTLY.get()))
					{
					event.setCanceled(true);
					}
				}
			}  */
			
	
	/*
		//Ghastly effect makes your hunger deplete faster, apparently ?? ?? I don't remember this
			@SubscribeEvent
			public static void GhastlyDebuffs(LivingUpdateEvent event) {
				LivingEntity entity = event.getEntityLiving();
				if (entity.hasEffect(EffectsInit.GHASTLY.get()))
				{
					if (entity instanceof PlayerEntity)
					{
						PlayerEntity player = (PlayerEntity) entity;
						
						if (player.getFoodData().getFoodLevel() > 1) {
					     player.causeFoodExhaustion(0.005F * 7);
						}
					//	if (player.getFoodData().getSaturationLevel() > 0.5f)
						//{
					//	player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() - 2);
				//	}  
					}
				}
			} */
			
			
			@SubscribeEvent
			public static void PumpkinPoweredGolemLaunch (LivingHurtEvent event) 
			{	
				LivingEntity entity = event.getEntityLiving();
				DamageSource source = event.getSource();
				Entity sourceentity = source.getEntity();		
				
			//	if (!entity.level.isClientSide) {
			  //       boolean flag = false; 
			         if (sourceentity instanceof LivingEntity)
			         {
			        	 LivingEntity attacker = (LivingEntity) sourceentity;
			        	 //Random random = ((LivingEntity)entity).getRandom(); 
			        	 
			        	if (attacker.hasEffect(EffectsInit.PUMPKIN_POWERED.get())
			        		&& attacker.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Blocks.CARVED_PUMPKIN.asItem())
			        	 {
			        // event.isCanceled();
			         
			         //from IronGolemEntity
			         
			     //    attacker.level.broadcastEntityEvent(attacker, (byte)4);
			    //     float f = 10;
			  //       float f1 = (int)f > 0 ? f / 2.0F + random.nextInt((int)f) : f;
			  //       boolean flag = entity.hurt(DamageSource.mobAttack(attacker), f1);
			  //       if (flag) {
			         	entity.hurt(DamageSource.GENERIC, 1);
			           // entity.setDeltaMovement(entity.getDeltaMovement().add(0.0D, (double)0.4F, 0.0D));
			          entity.setOnGround(false);
			         	entity.push(0, 0.85D, 0);
			      //      attacker.doEnchantDamageEffects(attacker, entity);
			            
			        	 
	////////FOR TESTING PURPOSES /////////
			       // 	 entity.playSound(SoundEvents.CAT_PURREOW, 3.0f, 3.0f);
			
		          } 
				}
			
			}
	
			@SubscribeEvent
			public static void PumpkinPoweredSnowball(RightClickBlock event) {
				PlayerEntity player = event.getPlayer();
				ItemStack stack = event.getItemStack();
				Item item = stack.getItem();
				Hand hand = event.getHand();
				Random random = ((LivingEntity)player).getRandom(); 
				
			//	if (!player.level.isClientSide) {
			      //   boolean flag = false; 
				if (player.hasEffect(EffectsInit.PUMPKIN_POWERED.get())
						&&
						player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Blocks.CARVED_PUMPKIN.asItem())
						{
					if (item instanceof SnowballItem)
					{
						if (stack.getCount() < 16)
						{
						if (random.nextInt(3) == 0)
						{
					 if (stack.isEmpty()) {
			               player.setItemInHand(hand, new ItemStack(Items.SNOWBALL));
			            } else if (!player.inventory.add(new ItemStack(Items.SNOWBALL))) {
			        //       player.drop(new ItemStack(Items.SNOWBALL), false);
			            }
						} } } }
			}
			
			@SubscribeEvent
			public static void PumpkinPoweredSnowball(RightClickItem event) {
				PlayerEntity player = event.getPlayer();
				ItemStack stack = event.getItemStack();
				Item item = stack.getItem();
				Hand hand = event.getHand();
				Random random = ((LivingEntity)player).getRandom(); 
				
				if (player.hasEffect(EffectsInit.PUMPKIN_POWERED.get())
						&&
						player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == Blocks.CARVED_PUMPKIN.asItem())
						{
					if (item instanceof SnowballItem)
					{
						if (stack.getCount() < 16)
						{
						if (random.nextInt(3) == 0)
						{
					 if (stack.isEmpty()) {
			               player.setItemInHand(hand, new ItemStack(Items.SNOWBALL));
			            } else if (!player.inventory.add(new ItemStack(Items.SNOWBALL))) {
			          //     player.drop(new ItemStack(Items.SNOWBALL), false);
			            }
						} } } }
			}
			
			
			@SubscribeEvent
			public static void GolemHealingAndRewarding(PlayerInteractEvent.EntityInteractSpecific event) {
				PlayerEntity player = event.getPlayer();
				Hand hand = event.getHand();
				ItemStack stack = player.getItemInHand(hand);				
				Item item = stack.getItem();
				Entity entity = event.getTarget();
				
				if (entity instanceof LivingEntity)
				{
					LivingEntity living = (LivingEntity) entity;
				
				if (living instanceof SnowGolemEntity)
				{
					if (item == Blocks.SNOW_BLOCK.asItem() || item == Items.SNOWBALL)
					{
						if (living.getHealth() != living.getMaxHealth()) {
						living.heal(10);
						player.playSound(SoundEvents.SNOW_PLACE, 3.0f, 1.0f);
						living.addEffect(new EffectInstance(EffectsInit.PUMPKIN_POWERED.get(), 15, 0));
						player.addEffect(new EffectInstance(EffectsInit.PUMPKIN_POWERED.get(), 1800, 0));
					    if (!player.abilities.instabuild) {
				               stack.shrink(1);
				            }
					   // player.playSound(SoundEvents.CAT_PURREOW, 3.0f, 3.0f);
					} 
						}
				}
				if (living instanceof IronGolemEntity)
				{
					if (item == Items.IRON_INGOT)
					{
						if (living.getHealth() != living.getMaxHealth()) {
						//(Iron Ingots already heal Iron Golems)
						//
						living.addEffect(new EffectInstance(EffectsInit.PUMPKIN_POWERED.get(), 15, 0));
						player.addEffect(new EffectInstance(EffectsInit.PUMPKIN_POWERED.get(), 1800, 0));
						// player.playSound(SoundEvents.CAT_PURREOW, 3.0f, 3.0f);
					}
				} 
					}
			}}
			
/*
			//Expirimental potion concept that makes Experience Orbs heal you. Kinda like Mending, but for a player!
			@SubscribeEvent
			public static void LifeExperiencePotion(PlayerXpEvent.PickupXp event) {
				PlayerEntity player = event.getPlayer();
				
				if (player.hasEffect(EffectsInit.PUMPKIN_POWERED.get())) 
				{
					//Heal Player
					if (player.getHealth() < player.getMaxHealth()) 
					{
					event.setCanceled(true);
					event.getOrb().remove();
					player.heal(1);
					player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0f, 0.7f);
					}
					//Feed Player
					if (player.getFoodData().getFoodLevel() < 20) 
					{
					event.setCanceled(true);
					event.getOrb().remove();
					player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() + 1);
					player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0f, 0.6f);
					}
					//Saturate Player, but only if if it won't exceed their Hunger Level
					//Actually, scratch that- Saturate player, but only up to 5
					//and also it doesnt cost anything, bc you cant see saturation in Vanilla anyway
					if (player.getFoodData().getSaturationLevel() < player.getFoodData().getFoodLevel() 
					&& player.getFoodData().getSaturationLevel() < 5) 
					{
					//event.setCanceled(true);
					//event.getOrb().remove();
					player.getFoodData().setSaturation(player.getFoodData().getSaturationLevel() + 1);
					//player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP, 1.0f, 0.7f);
					}
				}
			}
			*/
			
			///referenced Farmer's Delight ///
			
			@SubscribeEvent
			public static void onBiomeLoad(BiomeLoadingEvent event) {
				if (Config.MARIGOLD.get()) {
				BiomeGenerationSettingsBuilder builder = event.getGeneration();
				Biome.Climate climate = event.getClimate();

				if (
						(climate.temperature >= 0.3F && event.getCategory() == Biome.Category.PLAINS) 
						//some Autumnity compat
						|| event.getName().getPath().contains("maple_forest")
						|| event.getName().getPath().contains("maple_forest_hills")
						|| event.getName().getPath().contains("pumpkin_fields")
						|| event.getName().getPath().contains("yellow_spotted_forest")
						|| event.getName().getPath().contains("orange_spotted_dark_forest")
						//some BoP compat
						|| event.getName().getPath().contains("seasonal_forest")
						|| event.getName().getPath().contains("seasonal_orchard")
						|| event.getName().getPath().contains("seasonal_pumpkin_patch")
					)
						{
						builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, PlantGeneration.MARIGOLD);
						}
				}
				
			}
			
}