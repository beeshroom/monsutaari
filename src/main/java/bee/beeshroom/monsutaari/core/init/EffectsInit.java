package bee.beeshroom.monsutaari.core.init;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.common.effects.PumpkinPoweredEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectsInit 
{
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Monsutaari.MOD_ID);

	//public static final RegistryObject<Effect> TEMPO = EFFECTS.register("tempo", TempoEffect::new);
	
	//public static final RegistryObject<Effect> WARPED = EFFECTS.register("warped", WarpedEffect::new);
	
	
	public static final RegistryObject<Effect> PUMPKIN_POWERED = EFFECTS.register("pumpkin_powered", PumpkinPoweredEffect::new);
	
	
//	public static final RegistryObject<Effect> SHOCK = EFFECTS.register("shock", ShockEffect::new);
}