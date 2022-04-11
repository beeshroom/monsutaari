package bee.beeshroom.monsutaari.world;

import bee.beeshroom.monsutaari.core.init.BlockInit;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;

//Referenced from Farmer's Delight by Vectorwing

//@Mod.EventBusSubscriber(modid = Monsutaari.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PlantGeneration
{
	//public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Monsutaari.MOD_ID);
	
	public static final BlockClusterFeatureConfig MARIGOLD_CONFIG = (new BlockClusterFeatureConfig.Builder(
			new SimpleBlockStateProvider(BlockInit.MARIGOLD.get().defaultBlockState()), new SimpleBlockPlacer())).tries(64)
			//.xspread(2).zspread(2)
			.build();

	public static final ConfiguredFeature<?, ?> MARIGOLD = Feature.FLOWER.configured(MARIGOLD_CONFIG)
			.decorated(Features.Placements.ADD_32)
			.decorated(Features.Placements.HEIGHTMAP_SQUARE).count(1);

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
	}

	public static void registerConfiguredFeatures() {
		register("marigold", MARIGOLD);
	}
}