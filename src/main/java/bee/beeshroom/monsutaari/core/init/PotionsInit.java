package bee.beeshroom.monsutaari.core.init;

import java.util.function.Supplier;

import bee.beeshroom.monsutaari.Monsutaari;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionsInit {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Monsutaari.MOD_ID);

    
    public static final RegistryObject<Potion> PUMPKIN_POWERED = register("pumpkin_powered", EffectsInit.PUMPKIN_POWERED, 3600);
   
    public static final RegistryObject<Potion> LONG_PUMPKIN_POWERED = register("long_pumpkin_powered", EffectsInit.PUMPKIN_POWERED, 9600);
    
    
   /* public static final RegistryObject<Potion> GHASTLY = register("ghastly", EffectsInit.GHASTLY, 3600);

    public static final RegistryObject<Potion> LONG_GHASTLY = register("long_ghastly", EffectsInit.GHASTLY, 9600); */
    
    /*
    public static final RegistryObject<Potion> WARPED = register("warped", EffectsInit.WARPED, 1800);
    
    public static final RegistryObject<Potion> LONG_WARPED = register("long_warped", EffectsInit.WARPED, 4800);
    */
    

    private static RegistryObject<Potion> register(String id, Supplier<Effect> effect, int duration) {
        return POTIONS.register(id, () -> new Potion(new EffectInstance(effect.get(), duration)));
    }
}
