package bee.beeshroom.monsutaari.common.blocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WitherRoseBush extends TallFlowerBlock {

	public WitherRoseBush(AbstractBlock.Properties p_i48412_1_) {
	      super(p_i48412_1_);
	      this.registerDefaultState(this.stateDefinition.any().setValue(HALF, DoubleBlockHalf.LOWER));
	   }
		
/*	public AbstractBlock.OffsetType getOffsetType() {
	      return AbstractBlock.OffsetType.NONE;
	   } */
	
	   @Override
	public boolean isValidBonemealTarget(IBlockReader p_176473_1_, BlockPos p_176473_2_, BlockState p_176473_3_, boolean p_176473_4_) {
		      return false;
		   }

		   @Override
		public boolean isBonemealSuccess(World p_180670_1_, Random p_180670_2_, BlockPos p_180670_3_, BlockState p_180670_4_) {
		      return false;
		   }

		   
		   @Override
		protected boolean mayPlaceOn(BlockState p_200014_1_, IBlockReader p_200014_2_, BlockPos p_200014_3_) {
			      return super.mayPlaceOn(p_200014_1_, p_200014_2_, p_200014_3_) || p_200014_1_.is(Blocks.NETHERRACK) || p_200014_1_.is(Blocks.SOUL_SAND) || p_200014_1_.is(Blocks.SOUL_SOIL);
			   }

			   @Override
			@OnlyIn(Dist.CLIENT)
			   public void animateTick(BlockState p_180655_1_, World p_180655_2_, BlockPos p_180655_3_, Random p_180655_4_) {
				   /////
				   //stole this from AbstractFireBlock
				   /////
				   for(int j1 = 0; j1 < 2; ++j1) {
		               double d7 = p_180655_3_.getX() + p_180655_4_.nextDouble();
		               double d12 = p_180655_3_.getY() + 1 - p_180655_4_.nextDouble() * 0.1F;
		               double d17 = p_180655_3_.getZ() + p_180655_4_.nextDouble();
		               p_180655_2_.addParticle(ParticleTypes.SMOKE, d7, d12, d17, 0.0D, 0.0D, 0.0D);
				   }
			   } 

			   @Override
			public void entityInside(BlockState p_196262_1_, World p_196262_2_, BlockPos p_196262_3_, Entity p_196262_4_) {
			      if (!p_196262_2_.isClientSide && p_196262_2_.getDifficulty() != Difficulty.PEACEFUL) {
			         if (p_196262_4_ instanceof LivingEntity) {
			            LivingEntity livingentity = (LivingEntity)p_196262_4_;
			            if (!livingentity.isInvulnerableTo(DamageSource.WITHER)) {
			               p_196262_4_.makeStuckInBlock(p_196262_1_, new Vector3d((double)0.8F, 0.75D, (double)0.8F));     
			               livingentity.addEffect(new EffectInstance(Effects.WITHER, 40));
			            }
			         }

			      }
			   }
			   
		/*	   public void attack(BlockState p_196270_1_, World p_196270_2_, BlockPos blockpos, PlayerEntity player) {
				    //  interact(p_196270_1_, p_196270_2_, p_196270_3_);
					ItemStack stack = player.getMainHandItem();
				      Item item = stack.getItem();
				      if (item instanceof ShearsItem) {
				 
				      playerDestroy(p_196270_2_, player, blockpos, Blocks.AIR.defaultBlockState(), null, stack);
				      }
				      super.attack(p_196270_1_, p_196270_2_, blockpos, player);
				   } */
		   
}
