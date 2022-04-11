package bee.beeshroom.monsutaari.core.init;

import bee.beeshroom.monsutaari.Monsutaari;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Monsutaari.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Enchantments {
	
	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Monsutaari.MOD_ID);
	
	/*public static final RegistryObject<Enchantment> NO_STICK = ENCHANTMENTS.register("no_stick", 
			() -> new NoStickEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.FEET)); */
	
	//public static final RegistryObject<Enchantment> SHOCKING = ENCHANTMENTS.register("shocking", 
	//		() -> new ShockingEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND)); 
	
//	public static final RegistryObject<Enchantment> ELECTRIC = ENCHANTMENTS.register("electric", 
	//		() -> new ElectricEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND)); 
}
