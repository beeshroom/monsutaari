/* package bee.beeshroom.monsutaari.core.event;

import bee.beeshroom.monsutaari.Monsutaari;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Monsutaari.MOD_ID)

public class EffectEvents {

	@SubscribeEvent
	public static void ElectricSword (LivingUpdateEvent event)
	{
	//	Entity entity = event.getEntity();
		LivingEntity entity = event.getEntityLiving();
		
	if (entity instanceof LivingEntity)
	{		
		ItemStack stack = entity.getItemBySlot(EquipmentSlotType.FEET);

		//Item item = stack.getItem();
		int enchantlevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.DEPTH_STRIDER, stack);
		
		if (enchantlevel > 0 && (!stack.isEmpty()) )
				{
				//event.setAmount(amount * 2);
				//((LivingEntity) entity).addEffect(new EffectInstance (EffectsInit.SHOCK.get(), 800, 0));
				
		//	entity.isSteppingCarefully();
		//	entity.isShiftKeyDown(); 
			
				//for testing purposes
			if (entity.isCrouching())
			{
			entity.playSound(SoundEvents.NOTE_BLOCK_COW_BELL, 1.5f, 1.6f);
			}
				}
	}
		}
				
	

} */
	