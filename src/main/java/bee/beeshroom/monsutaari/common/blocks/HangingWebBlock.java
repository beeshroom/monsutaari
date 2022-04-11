package bee.beeshroom.monsutaari.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class HangingWebBlock extends Block implements net.minecraftforge.common.IForgeShearable {
	protected static final VoxelShape SHAPE = Block.box(0, 5, 0, 16, 16, 16);

	public HangingWebBlock(Properties p_i48440_1_) {
		super(p_i48440_1_);
		//this.registerDefaultState();
	}


	   public BlockState getStateForPlacement(BlockItemUseContext p_196258_1_) {		   
		   return this.defaultBlockState();
	   }

	   public PushReaction getPistonPushReaction(BlockState p_149656_1_) {
	      return PushReaction.DESTROY;
	   }
	   
	   public boolean isPathfindable(BlockState p_196266_1_, IBlockReader p_196266_2_, BlockPos p_196266_3_, PathType p_196266_4_) {
	      return false;
	   }

	   
	   public boolean canSurvive(BlockState p_196260_1_, IWorldReader p_196260_2_, BlockPos p_196260_3_) {
		      BlockPos blockpos = p_196260_3_.relative(Direction.UP);
		      BlockState blockstate = p_196260_2_.getBlockState(blockpos);
		     // Block block = blockstate.getBlock();
		      return blockstate.isFaceSturdy(p_196260_2_, blockpos, Direction.UP);
		   }
	   
	   public BlockState updateShape(BlockState p_196271_1_, Direction p_196271_2_, BlockState p_196271_3_, IWorld p_196271_4_, BlockPos p_196271_5_, BlockPos p_196271_6_) {
		      return p_196271_2_ == Direction.UP && !this.canSurvive(p_196271_1_, p_196271_4_, p_196271_5_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_196271_1_, p_196271_2_, p_196271_3_, p_196271_4_, p_196271_5_, p_196271_6_);
		   }
	   
		public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
		      return SHAPE;
		   }
	   
	   public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
		      p_196262_4_.makeStuckInBlock(p_196262_1_, new Vector3d(0.8D, (double)0.5F, 0.8D));
		   }
	   
}