package bee.beeshroom.monsutaari;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import bee.beeshroom.monsutaari.client.entity.ClayCubeRenderer;
import bee.beeshroom.monsutaari.client.entity.HeadlessSkeletonRenderer;
import bee.beeshroom.monsutaari.client.entity.HeadlessWitherSkeletonRenderer;
import bee.beeshroom.monsutaari.client.entity.QuartzSkeletonRenderer;
import bee.beeshroom.monsutaari.client.entity.SkeletonHeadRenderer;
import bee.beeshroom.monsutaari.client.entity.SnowCubeRenderer;
import bee.beeshroom.monsutaari.client.entity.WispRenderer;
import bee.beeshroom.monsutaari.client.entity.WitherSkeletonHeadRenderer;
import bee.beeshroom.monsutaari.common.entities.ClayCubeEntity;
import bee.beeshroom.monsutaari.common.entities.HeadlessSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.HeadlessWitherSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.QuartzSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.SkeletonHeadEntity;
import bee.beeshroom.monsutaari.common.entities.SnowCubeEntity;
import bee.beeshroom.monsutaari.common.entities.WispEntity;
import bee.beeshroom.monsutaari.common.entities.WitherSkeletonHeadEntity;
import bee.beeshroom.monsutaari.core.init.BlockInit;
import bee.beeshroom.monsutaari.core.init.EffectsInit;
import bee.beeshroom.monsutaari.core.init.EntityInit;
import bee.beeshroom.monsutaari.core.init.ItemInit;
import bee.beeshroom.monsutaari.core.init.ModPotionBrewing;
import bee.beeshroom.monsutaari.core.init.PotionsInit;
import bee.beeshroom.monsutaari.world.PlantGeneration;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(Monsutaari.MOD_ID)
public class Monsutaari {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MOD_ID = "monsutaari";
	public static final ItemGroup MONSUTAARI_GROUP = new MonsutaariGroup("monsutaarigroup");

	public Monsutaari() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		bus.addListener(this::setup);
		bus.addListener(this::clientSetup);
		//MinecraftForge.EVENT_BUS.register(this);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		
		EntityInit.ENTITY_TYPES.register(bus);
	//	Enchantments.ENCHANTMENTS.register(bus);
		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		
	//	PlantGeneration.FEATURES.register(bus);
		
		EffectsInit.EFFECTS.register(bus);
		PotionsInit.POTIONS.register(bus);
		//MinecraftForge.EVENT_BUS.register(this);
	//	MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGen::generateOres);

		//MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGen::generateOres);

		//BiomeFeatures.FEATURES.register(bus);
		
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event) {
		
		   event.enqueueWork(() -> {
		   registerSpawns();
		   registerCompostables();
		   ModPotionBrewing.registerBrewingRecipes();
		   PlantGeneration.registerConfiguredFeatures();
		   });
		   registerEntityAttributes();
			
		   
	}
	
	 private void clientSetup(final FMLClientSetupEvent event)
	    {
	        RenderTypeLookup.setRenderLayer(BlockInit.TRAPWEB.get(), RenderType.cutout());
	        RenderTypeLookup.setRenderLayer(BlockInit.GOLEM_FLOWER.get(), RenderType.cutout());
	        RenderTypeLookup.setRenderLayer(BlockInit.DUBIOUS_CAKE.get(), RenderType.cutout());
	        RenderTypeLookup.setRenderLayer(BlockInit.WITHER_ROSE_BUSH.get(), RenderType.cutout());
	        RenderTypeLookup.setRenderLayer(BlockInit.MARIGOLD.get(), RenderType.cutout());
	        RenderTypeLookup.setRenderLayer(BlockInit.POTTED_GOLEM_FLOWER.get(), RenderType.cutout());
	        RenderTypeLookup.setRenderLayer(BlockInit.POTTED_MARIGOLD.get(), RenderType.cutout());
	        RenderTypeLookup.setRenderLayer(BlockInit.HANGING_WEB.get(), RenderType.cutout());
	        
	        RenderingRegistry.registerEntityRenderingHandler(EntityInit.SKELETON_HEAD.get(), SkeletonHeadRenderer::new);
	        RenderingRegistry.registerEntityRenderingHandler(EntityInit.HEADLESS_SKELETON.get(), HeadlessSkeletonRenderer::new);
	        
	        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WITHER_SKELETON_HEAD.get(), WitherSkeletonHeadRenderer::new);
	        RenderingRegistry.registerEntityRenderingHandler(EntityInit.HEADLESS_WITHER_SKELETON.get(), HeadlessWitherSkeletonRenderer::new);
	   
	        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CLAY_CUBE.get(), ClayCubeRenderer::new);
	        RenderingRegistry.registerEntityRenderingHandler(EntityInit.SNOW_CUBE.get(), SnowCubeRenderer::new);
	        
	        RenderingRegistry.registerEntityRenderingHandler(EntityInit.QUARTZ_SKELETON.get(), QuartzSkeletonRenderer::new);
	        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WISP.get(), WispRenderer::new);
	 	   
	    } 

	    @SuppressWarnings("deprecation")
	    private void registerEntityAttributes() {
	        GlobalEntityTypeAttributes.put(EntityInit.SKELETON_HEAD.get(), SkeletonHeadEntity.registerAttributes().build());
	        GlobalEntityTypeAttributes.put(EntityInit.HEADLESS_SKELETON.get(), HeadlessSkeletonEntity.registerAttributes().build());
	        
	        GlobalEntityTypeAttributes.put(EntityInit.WITHER_SKELETON_HEAD.get(), WitherSkeletonHeadEntity.registerAttributes().build());
	        GlobalEntityTypeAttributes.put(EntityInit.HEADLESS_WITHER_SKELETON.get(), HeadlessWitherSkeletonEntity.registerAttributes().build());
	        
	        GlobalEntityTypeAttributes.put(EntityInit.CLAY_CUBE.get(), ClayCubeEntity.registerAttributes().build());
	        GlobalEntityTypeAttributes.put(EntityInit.SNOW_CUBE.get(), SnowCubeEntity.registerAttributes().build());
	        
	        GlobalEntityTypeAttributes.put(EntityInit.QUARTZ_SKELETON.get(), QuartzSkeletonEntity.registerAttributes().build());
	        GlobalEntityTypeAttributes.put(EntityInit.WISP.get(), WispEntity.registerAttributes().build());
		 	   
	    }
	    
	    public static void registerSpawns() {
			EntitySpawnPlacementRegistry.register(EntityInit.CLAY_CUBE.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ClayCubeEntity::checkClayCubeSpawnRules);
			//EntitySpawnPlacementRegistry.register(EntityInit.CLAY_CUBE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, ClayCubeEntity::checkCaveClayCubeSpawnRules);
			EntitySpawnPlacementRegistry.register(EntityInit.SNOW_CUBE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SnowCubeEntity::checkSnowCubeSpawnRules);	
			
			EntitySpawnPlacementRegistry.register(EntityInit.QUARTZ_SKELETON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, QuartzSkeletonEntity::checkSpawnRules);	
			EntitySpawnPlacementRegistry.register(EntityInit.WISP.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WispEntity::checkWispSpawnRules);	
			
	   }
	    
	  //referenced Farmer's Delight ///
		
		public static void registerCompostables() {
			ComposterBlock.COMPOSTABLES.put(ItemInit.DUBIOUS_CAKE.get(), 1.0F);
		}

	 public static class MonsutaariGroup extends ItemGroup {

		public MonsutaariGroup(String label) {
			super(label);
		}

		@Override
		public ItemStack makeIcon() {
			//return ItemInit.RUBY_ORE.get().getDefaultInstance(); 
			return Items.ROTTEN_FLESH.getDefaultInstance(); 
		} 
	} 
}