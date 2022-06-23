package bee.beeshroom.monsutaari.common.blocks;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

public class GolemFlowerBlock extends ModFlowerBlock {
	protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);

	public GolemFlowerBlock (Supplier<Effect> effect, int duration, Properties properties) {
		super(effect, duration, properties);
	}
		
	  @Override
	protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
	      return p_200014_1_.is(Blocks.SNOW_BLOCK);
	   }

	  @Override
	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
	      Vector3d vector3d = p_220053_1_.getOffset(p_220053_2_, p_220053_3_);
	      return SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	   }
	  
}
