
package bee.beeshroom.monsutaari;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

//thank-you mcjty !!!

@Mod.EventBusSubscriber
public class Config {

	public static ForgeConfigSpec COMMON_CONFIG;
	public static ForgeConfigSpec CLIENT_CONFIG;
	
	//
	public static final String CATEGORY_SETTINGS = "settings";
	
	public static ForgeConfigSpec.BooleanValue SPIDERWEB;
	public static ForgeConfigSpec.BooleanValue ENDERMITE_SPAWN;
	//public static ForgeConfigSpec.BooleanValue WARPED_EFFECT;
	public static ForgeConfigSpec.BooleanValue SKELETON_HEADS;
	public static ForgeConfigSpec.BooleanValue SKELETON_HEADS_NATURALLY_SPAWN;
	public static ForgeConfigSpec.BooleanValue SLIME_VARIANTS;
	public static ForgeConfigSpec.BooleanValue QUARTZ_SKELETONS;
	public static ForgeConfigSpec.BooleanValue NERF_BABY_MONSTERS;
	public static ForgeConfigSpec.BooleanValue SPIDERS_HUNT;
	public static ForgeConfigSpec.BooleanValue WISPS;
	public static ForgeConfigSpec.BooleanValue MARIGOLD;
	public static ForgeConfigSpec.BooleanValue GOLEM_FLOWERS;
	public static ForgeConfigSpec.BooleanValue WITHER_ROSE_BUSH;

	//
	public static final String CATEGORY_CLIENT = "client";

	static {
		ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
		COMMON_BUILDER
				.comment("Settings").push(CATEGORY_SETTINGS);
		
		//
		
		SPIDERWEB = COMMON_BUILDER
				.comment("Should Spiders trap you in webs? (Default: true)")
				.define("enableSpiderWebs", true);
		
		ENDERMITE_SPAWN = COMMON_BUILDER
				.comment("Should Endermite have a chance to spawn when Enderman, Shulkers, and Players teleport? (Default: true)")
				.define("enableEndermiteSpawn", true);
		
	/*	WARPED_EFFECT = COMMON_BUILDER
				.comment("Should Endermite be able to afflict you with an effect called Warped (random chance of teleporting when you take damage)? (Default: true)")
				.define("enableWarped", false);	*/
		
		SKELETON_HEADS = COMMON_BUILDER
				.comment("Should Skeleton's heads fall off (creating a Skeleton Head mob) occassionally when you hit them? (Default: true)")
				.define("enableSkeletonHeads", true);
		
		SKELETON_HEADS_NATURALLY_SPAWN = COMMON_BUILDER
				.comment("Should Skeleton Heads spawn naturally? (Default: false)")
				.define("enableSkeletonHeadsNaturallySpawn", false);
		
		GOLEM_FLOWERS = COMMON_BUILDER
				.comment("Should Golems plant a Golem Flower in their memory when they die? (Default: true)")
				.define("enableGolemFlowers", true);
		
		SLIME_VARIANTS = COMMON_BUILDER
				.comment("Should Snow Cubes and Clay Cubes be able to spawn in the world on full moons? (Default: true)")
				.define("enableSlimeVariants", true);
		
		QUARTZ_SKELETONS = COMMON_BUILDER
				.comment("Should Skeletons made of Quartz spawn in the Nether Wastes biome? (Default: true)")
				.define("enableQuartzSkeletons", true);
		
		NERF_BABY_MONSTERS = COMMON_BUILDER
				.comment("Should Baby Hostile Mobs have half as much health as usual? (Default: false)")
				.define("enableNerfBabyMonster", false);
		
		SPIDERS_HUNT = COMMON_BUILDER
				.comment("Should Spiders attack Silverfish and Endermites? (Default: true)")
				.define("enableSpidersHunt", true);
		
		WISPS = COMMON_BUILDER
				.comment("Should Wisps spawn in Forests and Swamps at night? (Default: false)")
				.define("enableWisps", false);
		
		WITHER_ROSE_BUSH = COMMON_BUILDER
				.comment("Should large mobs and mobs with more than 30 health plant/drop Wither Rose Bushes instead of Wither Roses when they are killed by the Wither? (Default: true)")
				.define("enableWitherRoseBush", true);
		
		MARIGOLD = COMMON_BUILDER
				.comment("Should Marigold flowers generate in the world? (Default: true)")
				.define("enableMarigold", true);
		
		
		
	/*	SILVERFISH = COMMON_BUILDER
				.comment("Should Silverfish infested blocks be able to generate in any biome, not just Extreme Hills? (Default: false)")
				.define("enableSilverfish", false); */
		
		//
		
		COMMON_BUILDER.pop();
		
		COMMON_CONFIG = COMMON_BUILDER.build();

		ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

		CLIENT_BUILDER.comment("Client settings").push(CATEGORY_CLIENT);

		CLIENT_CONFIG = CLIENT_BUILDER.build();
	}
	
	
	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading configEvent) {
	}

	@SubscribeEvent
	public static void onReload(final ModConfig.Reloading configEvent) {
	}
	
}
