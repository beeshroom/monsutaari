package bee.beeshroom.monsutaari.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BonesBlock extends FallingBlock{
	 protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 15.5D, 16.0D);

	public BonesBlock(Properties p_i48440_1_) {
		super(p_i48440_1_);		
	}
	
	//removed this to hopefully stop bones blocks from breaking when landing on themselves
/*	 public VoxelShape getCollisionShape(BlockState p_220071_1_, IBlockReader p_220071_2_, BlockPos p_220071_3_, ISelectionContext p_220071_4_) {
	      return SHAPE;
	   }

	   public VoxelShape getBlockSupportShape(BlockState p_230335_1_, IBlockReader p_230335_2_, BlockPos p_230335_3_) {
	      return VoxelShapes.block();
	   }

	   public VoxelShape getVisualShape(BlockState p_230322_1_, IBlockReader p_230322_2_, BlockPos p_230322_3_, ISelectionContext p_230322_4_) {
	      return VoxelShapes.block();
	   } */

	/*  public void attack(BlockState p_196270_1_, World p_196270_2_, BlockPos p_196270_3_, PlayerEntity p_196270_4_) {
	      interact(p_196270_1_, p_196270_2_, p_196270_3_);
	      super.attack(p_196270_1_, p_196270_2_, p_196270_3_, p_196270_4_);
	   }

	//   public void stepOn(World p_176199_1_, BlockPos p_176199_2_, Entity p_176199_3_) {
	   //   interact(p_176199_1_.getBlockState(p_176199_2_), p_176199_1_, p_176199_2_);
	   //   super.stepOn(p_176199_1_, p_176199_2_, p_176199_3_);
	 //  } 
	
	   private static void interact(BlockState state, World world, BlockPos pos) {
		      
		   //.playSound(SoundEvents.GENERIC_EAT, 1.0F, 1.0F);
		   world.playSound((PlayerEntity)null, pos, SoundEvents.SKELETON_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F);
		    
		   }
	   
	   
	   public void entityInside(BlockState p_196262_1_, World world, BlockPos pos, Entity entity) {
		      if (entity instanceof LivingEntity) {
		    	 // entity.makeStuckInBlock(p_196262_1_, new Vector3d((double)0.8F, 0.75D, (double)0.8F));
		         if (!world.isClientSide && (entity.xOld != entity.getX() || entity.zOld != entity.getZ())) {
		            double d0 = Math.abs(entity.getX() - entity.xOld);
		            double d1 = Math.abs(entity.getZ() - entity.zOld);
		            if (d0 >= (double)0.003F || d1 >= (double)0.003F) {
		            	world.playSound((PlayerEntity)null, pos, SoundEvents.SKELETON_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F);
		            }
		         }

		      }
		   }
	    */
	   
	   
	   @Override
	@OnlyIn(Dist.CLIENT)
	   public int getDustColor(BlockState p_189876_1_, IBlockReader p_189876_2_, BlockPos p_189876_3_) {
		   return 14406560;
	   }
	
}
