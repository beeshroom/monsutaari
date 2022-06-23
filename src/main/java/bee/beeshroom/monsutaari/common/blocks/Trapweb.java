package bee.beeshroom.monsutaari.common.blocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.BreakableBlock;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class Trapweb extends BreakableBlock implements net.minecraftforge.common.IForgeShearable
//implements net.minecraftforge.common.IForgeShearable 
//public class Trapweb extends WebBlock implements net.minecraftforge.common.IForgeShearable 
{
	
	
	//add do hit box by blockstate or whatever
	
	
	
	  public static final IntegerProperty AGE = BlockStateProperties.AGE_5;
	  protected static final VoxelShape[] SHAPE_WEB = new VoxelShape[]{
			  Block.box(0, 0, 0, 16, 16, 16), 
			   Block.box(0, 0, 0, 16, 16, 16),
			   Block.box(0, 0, 0, 16, 15, 16),
			   Block.box(1, 0, 1, 15, 13, 15),
			   
			   Block.box(1, 0, 1, 15, 9, 15),
			   Block.box(1, 0, 1, 15, 7, 15) 
		
		/*  Block.box(0, 0, 0, 16, 13, 16), 
			   Block.box(0, 0, 0, 16, 11, 16),
			   Block.box(0, 0, 0, 6, 8, 16),
			   Block.box(1, 0, 1, 15, 5, 15) 
			   
			   Block.box(0, 0, 0, 16, 2, 16), 
			   Block.box(0, 0, 0, 16, 2, 16),
			   Block.box(0, 0, 0, 16, 2, 16),
			   Block.box(1, 0, 1, 15, 2, 15) */
			   };  
	   protected static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 2, 16);
	 //  protected static final AxisAlignedBB TOUCH_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.25D, 0.875D);

	   @Override
	public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
		      return SHAPE;
		   } 
	   
	  public Trapweb(AbstractBlock.Properties p_i48394_1_) {
	      super(p_i48394_1_);
	      this.registerDefaultState(this.stateDefinition.any().setValue(AGE, Integer.valueOf(0)));
	      
	   }
	  
	  
	   protected static float getGrowthSpeed(Block p_180672_0_, IBlockReader p_180672_1_, BlockPos p_180672_2_) {
		   return 5.0f;
	   }
	  
	  
	  
	  @Override
	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
	      return SHAPE_WEB[p_220053_1_.getValue(AGE)];
	   }
	  
	  
	/*  public void stepOn(World world, BlockPos pos, Entity entity) {
	   //   double d0 = Math.abs(entity.getDeltaMovement().y);
	    //  if (d0 < 0.1D && !entity.isSteppingCarefully()) 
	      {
	        // double d1 = 0.4D + d0 * 0.2D;
	         entity.setDeltaMovement(entity.getDeltaMovement().multiply(0.15D, (double)0.08F, 0.15D));
	      } 

	      super.stepOn(world, pos, entity);
	   } */
	
		   @Override
		public void entityInside(BlockState state, World world, BlockPos pos, Entity entity) {
			   if (!(entity instanceof SpiderEntity))
			   {
				   entity.makeStuckInBlock(state, new Vector3d(0.15D, 0.08F, 0.15D));
				//   this.slightlyMelt(state, world, pos);
			   }
			   }
		   
		   
	/*	   public void stepOn(World world, BlockPos pos, Entity entity) {
			   AbstractBlockState state = world.getBlockState(pos); 
			   if (entity instanceof LivingEntity && !(entity instanceof SpiderEntity)) //&& !EnchantmentHelper.hasFrostWalker((LivingEntity)p_176199_3_)) 
			    	  {
			    	  		
			    	  	  int i = state.getValue(AGE);
					      if (i < 2) {
					         world.setBlock(pos, state.setValue(AGE, Integer.valueOf(i + 1)), 2);
			      }
					      if (i == 3) {
					    	  world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
				      }

			      super.stepOn(world, pos, entity);
			   }
		   }  */
		   
		   

		   
		   // frosted ice code + edits

		   @Override
		public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
			   if (!world.isAreaLoaded(pos, 1)) return; 
			   //  this.tick(state, world, pos, rand);
		//	   if ((rand.nextInt(3) == 0))
		//			   {
			   this.slightlyMelt(state, world, pos);
		//			   }
			   }
		   
		   
	/*	   public void tick(BlockState p_225534_1_, ServerWorld p_225534_2_, BlockPos p_225534_3_, Random p_225534_4_) {
			      if ((p_225534_4_.nextInt(1) == 0) //&& p_225534_2_.getMaxLocalRawBrightness(p_225534_3_) > 11 - p_225534_1_.getValue(AGE) - p_225534_1_.getLightBlock(p_225534_2_, p_225534_3_) && this.slightlyMelt(p_225534_1_, p_225534_2_, p_225534_3_)
			    		  ) {
			         BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

			         for(Direction direction : Direction.values()) {
			            blockpos$mutable.setWithOffset(p_225534_3_, direction);
			            BlockState blockstate = p_225534_2_.getBlockState(blockpos$mutable);
			            if (blockstate.is(this) && !this.slightlyMelt(blockstate, p_225534_2_, blockpos$mutable)) {
			               p_225534_2_.getBlockTicks().scheduleTick(blockpos$mutable, this, MathHelper.nextInt(p_225534_4_, 20, 40));
			            }
			         }

			      } else {
			         p_225534_2_.getBlockTicks().scheduleTick(p_225534_3_, this, MathHelper.nextInt(p_225534_4_, 20, 40));
			      }
			   }
		   */
		   
		   
		   
		   
		   	/*	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) 
			   {
		   		
			     if (//(rand.nextInt(3) == 0) && 
			    		 this.slightlyMelt(state, world, pos))
			   		  {
			    //	  BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
			    	  world.getBlockTicks().scheduleTick(pos, this, 15);
			    	//  BlockState blockstate = world.getBlockState(blockpos$mutable);
			    	//  world.getBlockTicks().scheduleTick(blockpos$mutable, this, MathHelper.nextInt(rand, 20, 40));
			    	  //  	  if (blockstate.is(this) && !this.slightlyMelt(blockstate, world, blockpos$mutable)) {
			             //  world.getBlockTicks().scheduleTick(blockpos$mutable, this, MathHelper.nextInt(rand, 20, 40));
			/*            }
			         }

			      	else {
			         world.getBlockTicks().scheduleTick(pos, this, MathHelper.nextInt(rand, 20, 40));
			     	*/	
			  //  	  } 
			//   		} 
		   
			    		 // || this.fewerNeigboursThan(world, pos, 4)
			/*    		  ) && world.getMaxLocalRawBrightness(pos) > 11 - state.getValue(AGE) - state.getLightBlock(world, pos) && this.slightlyMelt(state, world, pos)) {
			         BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

			         for(Direction direction : Direction.values()) {
			            blockpos$mutable.setWithOffset(pos, direction);
			            BlockState blockstate = world.getBlockState(blockpos$mutable);
			            if (blockstate.is(this) && !this.slightlyMelt(blockstate, world, blockpos$mutable)) {
			            	world.getBlockTicks().scheduleTick(blockpos$mutable, this, MathHelper.nextInt(rand, 20, 40));
			            }
			         } } 
	   else {
			    	  world.getBlockTicks().scheduleTick(pos, this, MathHelper.nextInt(rand, 20, 40));
			      }
			   } 
*/
			   private boolean slightlyMelt(BlockState state, World world, BlockPos pos) 
			   {
			      int i = state.getValue(AGE);
			      if (i != 5) 
			      {
			         world.setBlock(pos, state.setValue(AGE, Integer.valueOf(i + 1)), 2);
			         return false;
			      } else {
			         this.melt(state, world, pos);
			         return true;
			      }
			   }

	//added this suppress warning
			   @Override
			@SuppressWarnings("deprecation")
			public void neighborChanged(BlockState state, World world, BlockPos pos1, Block block, BlockPos pos2, boolean bool) {
			      if (block == this) //&& this.fewerNeigboursThan(world, pos1, 2)) 
			    		  {
			       //  this.melt(state, world, pos1);
					 //  world.setBlockAndUpdate(pos1, Blocks.AIR.defaultBlockState());
			    	  this.slightlyMelt(state, world, pos1);
			      }

			      super.neighborChanged(state, world, pos1, block, pos2, bool);
			   }

		/*	   private boolean fewerNeigboursThan(IBlockReader p_196456_1_, BlockPos pos, int p_196456_3_) {
			      int i = 0;
			      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

			      for(Direction direction : Direction.values()) {
			         blockpos$mutable.setWithOffset(pos, direction);
			         if (p_196456_1_.getBlockState(blockpos$mutable).is(this)) {
			            ++i;
			            if (i >= p_196456_3_) {
			               return false;
			            }
			         }
			      }

			      return true;
			   } */

			  @Override
			protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> state) {
			      state.add(AGE);
			   } 

			   @Override
			public ItemStack getCloneItemStack(IBlockReader p_185473_1_, BlockPos pos, BlockState state) {
			      return ItemStack.EMPTY;
			   }
 
			   //ice code + edit
			   
			   protected void melt(BlockState state, World world, BlockPos pos) {		    
				   world.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
			         world.neighborChanged(pos, Blocks.AIR, pos);
			   }

			   //

		   @Override
		public PushReaction getPistonPushReaction(BlockState state) {
		      return PushReaction.DESTROY;
		   }
		   
		  
		}