package bee.beeshroom.monsutaari.common.blocks;

import java.util.function.Supplier;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.potion.Effect;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;

//Thanks for the help @SmellyModder who works with the MinecraftAbnormals team. See: "AbnormalsFlowerBlock"
//Also thanks to FlowerBlock

public class ModFlowerBlock extends FlowerBlock {
	private final Supplier<Effect> stewEffect;
	private final int effectDuration;
	protected static final VoxelShape SHAPE = Block.box(1D, 0D, 1D, 15D, 15D, 15D);

	
	public ModFlowerBlock(Supplier<Effect> effect, int duration, Properties properties) {
		super(Effects.MOVEMENT_SPEED, duration, properties);
		this.stewEffect = effect;
		this.effectDuration = duration;
	}
	
	@Override
	public Effect getSuspiciousStewEffect() {
		return this.stewEffect.get();
	}
	
	@Override
	public int getEffectDuration() {
		if (this.getSuspiciousStewEffect().isInstantenous()) {
			return effectDuration;  
	      } else {
	         return effectDuration * 20;
	      }
	}
	
	public VoxelShape getShape(BlockState p_220053_1_, IBlockReader p_220053_2_, BlockPos p_220053_3_, ISelectionContext p_220053_4_) {
	      Vector3d vector3d = p_220053_1_.getOffset(p_220053_2_, p_220053_3_);
	      return SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
	   }
	
}
