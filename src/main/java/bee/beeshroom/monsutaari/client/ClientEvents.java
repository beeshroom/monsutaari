package bee.beeshroom.monsutaari.client;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.common.items.MonsutaariSpawnEggItem;
import net.minecraft.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Monsutaari.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

 //some clinent events are in my main calss Monsutaari.java too
	//such as, Block and Entity rendering

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        MonsutaariSpawnEggItem.initSpawnEggs();
    }
}