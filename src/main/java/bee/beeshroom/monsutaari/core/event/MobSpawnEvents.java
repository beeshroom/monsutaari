package bee.beeshroom.monsutaari.core.event;

import java.util.List;

import bee.beeshroom.monsutaari.Config;
import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.core.init.EntityInit;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

//Thanks Cy4shot for your Entity Spawning Tutorial!!

//note to self: weight, smallest group, largest group

@EventBusSubscriber(modid = Monsutaari.MOD_ID)

public class MobSpawnEvents {
	@SubscribeEvent
	public static void onBiomeLoad(final BiomeLoadingEvent event) {
	//	IWorld world;
		if (event.getName() == null)
			return;
		MobSpawnInfoBuilder spawns = event.getSpawns();

		
		if (Config.SKELETON_HEADS_NATURALLY_SPAWN.get())
			{
		if (event.getCategory() == Biome.Category.DESERT ) {
		//	System.out.println(spawns.getSpawner(EntityClassification.MONSTER).size());
			spawns.addSpawn(EntityClassification.MONSTER,
					new MobSpawnInfo.Spawners(EntityInit.SKELETON_HEAD.get(), 4, 1, 1));															
			}
			}
		
		if (Config.SLIME_VARIANTS.get())
		{
	if (event.getCategory() == Biome.Category.ICY ) {
		//	event.getClimate().equals(RainType.SNOW)) 
		spawns.addSpawn(EntityClassification.MONSTER,
				new MobSpawnInfo.Spawners(EntityInit.SNOW_CUBE.get(), 22, 1, 3));	
													}
	if (event.getCategory() == (Biome.Category.RIVER)) { //&& event.getCategory() != (Biome.Category.ICY)) {
		spawns.addSpawn(EntityClassification.MONSTER,
				new MobSpawnInfo.Spawners(EntityInit.CLAY_CUBE.get(), 13, 2, 3));
													}
		}
		
		
		if (Config.QUARTZ_SKELETONS.get())
		{
		if (event.getCategory() == Biome.Category.NETHER && event.getName().getPath().contains("nether_wastes")) {
			//	System.out.println(spawns.getSpawner(EntityClassification.MONSTER).size());
				spawns.addSpawn(EntityClassification.MONSTER,
						new MobSpawnInfo.Spawners(EntityInit.QUARTZ_SKELETON.get(), 4, 1, 2));															
				}
		}
		
		if (Config.WISPS.get())
		{
		if (event.getCategory() == (Biome.Category.FOREST)) {
			//	System.out.println(spawns.getSpawner(EntityClassification.MONSTER).size());
				spawns.addSpawn(EntityClassification.AMBIENT,
						new MobSpawnInfo.Spawners(EntityInit.WISP.get(), 2, 1, 1));															
				}
		if (event.getCategory() == (Biome.Category.SWAMP)) {
			//	System.out.println(spawns.getSpawner(EntityClassification.MONSTER).size());
				spawns.addSpawn(EntityClassification.AMBIENT,
						new MobSpawnInfo.Spawners(EntityInit.WISP.get(), 2, 1, 1));															
				}
		}
		
	}
	
	
	//remove spiders from Snowy Biomes 

	@SubscribeEvent
	public static void onSpawn(BiomeLoadingEvent event)
	{
		 List<MobSpawnInfo.Spawners> removethis = event.getSpawns().getSpawner(EntityClassification.MONSTER);
		 
			if (event.getCategory() == Biome.Category.ICY) {
		 removethis.removeIf(entity -> entity.type == EntityType.SPIDER);
															  }
	}
 
	
/*	@SubscribeEvent
	public static void IcySpiderRemoval(LivingSpawnEvent.CheckSpawn event)
	{
		Entity entity = event.getEntity();
		IWorld world = event.getWorld();
	//	if(world.isClientSide())	{
		if (event.getResult() != Event.Result.DENY && world instanceof IServerWorld)
		{
		if (entity instanceof SpiderEntity) 	
		{
			if ((event.getSpawnReason() == SpawnReason.CHUNK_GENERATION || event.getSpawnReason() == SpawnReason.NATURAL)
				&& entity.getY() > 55 && world.getBiome(entity.blockPosition()).getBiomeCategory() == Biome.Category.ICY)
			{
			//	if(entity.isAlive()) {
				entity.remove();
				//}
			}
		}
		}
	//	}
	}
	*/
	
	
} 
