/*package bee.beeshroom.monsutaari.common.items;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WispBottle extends Item {

	public WispBottle(Properties properties) {
		super(properties);
	}

	  public ActionResultType useOn(ItemUseContext p_195939_1_) {
	      PlayerEntity playerentity = p_195939_1_.getPlayer();
	      World world = p_195939_1_.getLevel();
	      BlockPos blockpos = p_195939_1_.getClickedPos();
	     {
	         BlockPos blockpos1 = blockpos.relative(p_195939_1_.getClickedFace());
	         if (AbstractFireBlock.canBePlacedAt(world, blockpos1, p_195939_1_.getHorizontalDirection())) {
	            world.playSound(playerentity, blockpos1, SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
	            ItemStack itemstack = p_195939_1_.getItemInHand();
	            Item item = itemstack.getItem();
	            if (playerentity instanceof ServerPlayerEntity) {
	            	if (item instanceof WispBottle)
	            	{
	            		 playerentity.spawnAtLocation(Items.GLASS_BOTTLE);
	            	}
	            }
	            
	            

	            return ActionResultType.sidedSuccess(world.isClientSide());
	         } else {
	            return ActionResultType.FAIL;
	         }
	      }
	   }
	   



}
	 */
