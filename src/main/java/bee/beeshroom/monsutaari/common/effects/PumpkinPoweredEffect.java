package bee.beeshroom.monsutaari.common.effects;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.core.init.EffectsInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Monsutaari.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PumpkinPoweredEffect extends Effect
{

	public PumpkinPoweredEffect() 
	{
		super(EffectType.BENEFICIAL, 0xD66C2A);
	}

	

@SubscribeEvent
public static void onLivingUpdate(LivingUpdateEvent event) 
	{
	LivingEntity entity = event.getEntityLiving();
	
	 if (entity.hasEffect(EffectsInit.PUMPKIN_POWERED.get()))
     {
	
		 
}


}
}