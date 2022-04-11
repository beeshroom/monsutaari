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
public class GhastlyEffect extends Effect
{

	public GhastlyEffect() 
	{
		super(EffectType.NEUTRAL, 0x8E3D49);
	}

	

@SubscribeEvent
public static void onLivingUpdate(LivingUpdateEvent event) 
	{
	LivingEntity entity = event.getEntityLiving();
	
	 if (entity.hasEffect(EffectsInit.GHASTLY.get()))
     {
	
		 
}


}
} */