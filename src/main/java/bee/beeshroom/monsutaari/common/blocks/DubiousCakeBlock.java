package bee.beeshroom.monsutaari.common.blocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class DubiousCakeBlock extends Block {
	   public static final IntegerProperty BITES = BlockStateProperties.AGE_3;
	   protected static final VoxelShape[] SHAPE_BY_BITE = new VoxelShape[]{
				Block.box(3, 0, 3, 13, 8, 13), 
				VoxelShapes.join(Block.box(3, 0, 3, 8, 8, 8), Block.box(8, 0, 3, 13, 8, 13), IBooleanFunction.OR),
				Block.box(3, 0, 3, 13, 8, 8),
				Block.box(3, 0, 3, 8, 8, 8)
				};

	   public DubiousCakeBlock(AbstractBlock.Properties p_i48434_1_) {
	      super(p_i48434_1_);
	      this.registerDefaultState(this.stateDefinition.any().setValue(BITES, Integer.valueOf(0)));
	   }

	   public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
	      return SHAPE_BY_BITE[p_220053_1_.getValue(BITES)];
	   }

	   public ActionResultType use(BlockState p_225533_1_, World p_225533_2_, BlockPos p_225533_3_, PlayerEntity p_225533_4_, Hand p_225533_5_, BlockRayTraceResult p_225533_6_) {
	      if (p_225533_2_.isClientSide) {
	         ItemStack itemstack = p_225533_4_.getItemInHand(p_225533_5_);
	         if (this.eat(p_225533_2_, p_225533_3_, p_225533_1_, p_225533_4_).consumesAction()) {
	            return ActionResultType.SUCCESS;
	         }

	         if (itemstack.isEmpty()) {
	            return ActionResultType.CONSUME;
	         }
	      }

	      return this.eat(p_225533_2_, p_225533_3_, p_225533_1_, p_225533_4_);
	   }

	   private ActionResultType eat(IWorld world, BlockPos blockpos, BlockState blockstate, PlayerEntity player) {
		   if (//player.getFoodData().getFoodLevel() < .5f 
				   //will this make it crash if you don't have Ghastly effect?
				   //yes. yes it will.
				   //|| (player.getEffect(EffectsInit.GHASTLY.get()).getDuration() > 3599)
				   
			//4/4/2022: Works just like normal cake now. if you are hungry, you can eat
				   (!player.canEat(false))
				   ) {
			  return ActionResultType.PASS;
		      }
		   
		   else {
			   
			   
			   //feed the player
			   //this is more than a normal cake slice feeds, but, this is a cake with only FOUR slices
		 	  	player.getFoodData().eat(3, 0.1F);
		 	  	
	    //	  player.getFoodData().eat(1, 0F);
		    	//  player.addEffect(new EffectInstance (EffectsInit.GHASTLY.get(), 1800, 0));

		 	  	
	/*		
	 //not sure where to put this so it'd work. basically, this was a function to let you stack up the Ghastly effect, but not higher than the length of its Potion is (3600)
	
	//update 4/4/2022: this isn't compatible with the cake feeding you. this would make it so you could fill hunger, but not eat cake, 
	 //since the process is being PASSed after you max out the potion effect 
	 	  if (player.hasEffect(EffectsInit.GHASTLY.get()))
				  {
					  if (player.getEffect(EffectsInit.GHASTLY.get()).getDuration() > 2699)
					  {
						  return ActionResultType.PASS;  
					  }
				  } */
	 	  	
	// 4/5/2022: giving you Ghastly doesn't make sense in Lore anymore	 	  	
		   /* 	  if (!player.hasEffect(EffectsInit.GHASTLY.get()))
		    	  {
		    	  player.addEffect(new EffectInstance (EffectsInit.GHASTLY.get(), 900, 0));
		    	  } 
		    	  else {
		    		//if you eat so much cake that you're Ghastly effect would become GREATER than 3600, you max out at 3600 and get sick
		    		  if (player.getEffect(EffectsInit.GHASTLY.get()).getDuration() > 2700)
	    		  		{  
		    			 // player.addEffect(new EffectInstance (EffectsInit.GHASTLY.get(), (player.getEffect(EffectsInit.GHASTLY.get()).getDuration() + 300), 0));
//lets see if this overwrites ppl with Creative mode long, long Ghastly effects applied or if it's fine
		    			  player.addEffect(new EffectInstance (EffectsInit.GHASTLY.get(), 3600, 0));	
		    			  player.addEffect(new EffectInstance (Effects.CONFUSION, 200, 0));	    	  
	    		  		}
		    		  //Ghastly stacks up to the length of a potion if you eat an entire cake (and some more slices of another)
		    		  if (player.getEffect(EffectsInit.GHASTLY.get()).getDuration() < 2701)
		    		  		{  
		    			  		player.addEffect(new EffectInstance (EffectsInit.GHASTLY.get(), (player.getEffect(EffectsInit.GHASTLY.get()).getDuration() + 900), 0));
		    		  		}
					  } */
		    	  
		    	  Random random = player.getRandom(); 
		    	  if (random.nextInt(5) == 0)
		    	  {
		    		//  player.addEffect(new EffectInstance (Effects.HUNGER, 600, 0));
		    		  
		    	//	  if (!player.hasEffect(Effects.HUNGER))
			   // 	  {
			    	  player.addEffect(new EffectInstance (Effects.HUNGER, 600, 0));
			 //   	  } 
			    /*	  else {
			    	  player.addEffect(new EffectInstance (Effects.HUNGER, (player.getEffect(Effects.HUNGER).getDuration() + 100), 0));
			    	  } */
		    		  
		    	  }
		    	  
		    	  if (random.nextInt(8) == 0)		    	  
		    	  {
			    	  player.addEffect(new EffectInstance (Effects.POISON, 100, 0)); 
		    	  }
		    	  
	        //	 player.addEffect(new EffectInstance (Effects.HUNGER, 160, 0));

	        //	 player.addEffect(new EffectInstance (Effects.POISON, 80, 0));
	        	 
	        //	 player.causeFoodExhaustion(6);
	        	 
	        	 player.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
	    	   	  
	         int i = blockstate.getValue(BITES);
	         if (i < 3) {
	        	 world.setBlock(blockpos, blockstate.setValue(BITES, Integer.valueOf(i + 1)), 3);
	        	 
	         } else {
	        	 world.removeBlock(blockpos, false);
	         }

	         return ActionResultType.SUCCESS;
	      }
	   }

	   public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
	      return p_196271_2_ == Direction.DOWN && !p_196271_1_.canSurvive(p_196271_4_, p_196271_5_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
	   }

	   public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
	      return p_196260_2_.getBlockState(p_196260_3_.below()).getMaterial().isSolid();
	   }

	   protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> p_206840_1_) {
	      p_206840_1_.add(BITES);
	   }

	   public int getAnalogOutputSignal(BlockState p_180641_1_, World p_180641_2_, BlockPos p_180641_3_) {
	      return (4 - p_180641_1_.getValue(BITES)) * 2;
	   }

	   public boolean hasAnalogOutputSignal(BlockState p_149740_1_) {
	      return true;
	   }

	   public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
	      return false;
	   }
	}