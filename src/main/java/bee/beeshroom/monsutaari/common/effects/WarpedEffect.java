/* package bee.beeshroom.monsutaari.common.effects;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.core.init.EffectsInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Monsutaari.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WarpedEffect extends Effect
{

	public WarpedEffect() 
	{
		super(EffectType.HARMFUL, 0x9F55D1);
				//0x9B14BC); this is more purple. too similar to Ghastly though.
	}

	

@SubscribeEvent
public static void onLivingUpdate(LivingUpdateEvent event) 
	{
	LivingEntity entity = event.getEntityLiving();
	
	 if (entity.hasEffect(EffectsInit.WARPED.get()))
     {
	
		 
}


}
}
*/