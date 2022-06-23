
package bee.beeshroom.monsutaari.core.init;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.common.items.MonsutaariSpawnEggItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			Monsutaari.MOD_ID);

	public static final RegistryObject<MonsutaariSpawnEggItem> SKELETON_SPAWN_EGG = ITEMS.register("skeleton_head_spawn_egg",
			() -> new MonsutaariSpawnEggItem(EntityInit.SKELETON_HEAD, 0xC6C6C6, 0x777777,
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	
	public static final RegistryObject<MonsutaariSpawnEggItem> WITHER_SKELETON_SPAWN_EGG = ITEMS.register("wither_skeleton_head_spawn_egg",
			() -> new MonsutaariSpawnEggItem(EntityInit.WITHER_SKELETON_HEAD, 0x1c1c1c, 0x333333,
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	/* public static final RegistryObject<SpawnEggItem> WITHER_SKELETON_SPAWN_EGG = ITEMS.register("wither_skeleton_head_spawn_egg",
			() -> new SpawnEggItem(EntityInit.WITHER_SKELETON_HEAD, 0x333333, 0x1c1c1c,
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP))); */
	
	/* public static final RegistryObject<SpawnEggItem> BOOK_WYRM_SPAWN_EGG = ITEMS.register("book_wyrm_spawn_egg",
			() -> new SpawnEggItem(EntityInit.BOOK_WYRM, 0x444520, 0x202115,
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP))); */
	
	public static final RegistryObject<MonsutaariSpawnEggItem> CLAY_CUBE_SPAWN_EGG = ITEMS.register("clay_cube_spawn_egg",
			() -> new MonsutaariSpawnEggItem(EntityInit.CLAY_CUBE, 0x8e939c, 0x5B6882,
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	
	public static final RegistryObject<MonsutaariSpawnEggItem> SNOW_CUBE_SPAWN_EGG = ITEMS.register("snow_cube_spawn_egg",
			() -> new MonsutaariSpawnEggItem(EntityInit.SNOW_CUBE, 0xB2E6FF, 0xFFFFFF,
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	
	public static final RegistryObject<MonsutaariSpawnEggItem> QUARTZ_SKELETON_SPAWN_EGG = ITEMS.register("quartz_skeleton_spawn_egg",
			() -> new MonsutaariSpawnEggItem(EntityInit.QUARTZ_SKELETON, 0xFFFFFF, 0xD6BE8B,
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	
	public static final RegistryObject<MonsutaariSpawnEggItem> WISP_SPAWN_EGG = ITEMS.register("wisp_spawn_egg",
			() -> new MonsutaariSpawnEggItem(EntityInit.WISP, 0x48BCE2, 0x37FF00,
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	
/*	public static final RegistryObject<Item> WISP_BOTTLE = ITEMS.register("wisp_bottle",
			() -> new WispBottle(new Item.Properties().stacksTo(1).tab(Monsutaari.MONSUTAARI_GROUP)));
	*/
	
	
	//Block Items
	
	public static final RegistryObject<BlockItem> MARIGOLD = ITEMS.register("marigold",
			() -> new BlockItem(BlockInit.MARIGOLD.get(),
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	
	public static final RegistryObject<BlockItem> GOLEM_FLOWER = ITEMS.register("golem_flower",
			() -> new BlockItem(BlockInit.GOLEM_FLOWER.get(),
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	
	public static final RegistryObject<BlockItem> WITHER_ROSE_BUSH = ITEMS.register("wither_rose_bush",
			() -> new BlockItem(BlockInit.WITHER_ROSE_BUSH.get(),
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
			
	public static final RegistryObject<BlockItem> DUBIOUS_CAKE = ITEMS.register("dubious_cake",
			() -> new BlockItem(BlockInit.DUBIOUS_CAKE.get(),
					new Item.Properties().stacksTo(1).tab(Monsutaari.MONSUTAARI_GROUP)));
			
	public static final RegistryObject<BlockItem> BONES_BLOCK = ITEMS.register("bones_block",
			() -> new BlockItem(BlockInit.BONES_BLOCK.get(),
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
		
	public static final RegistryObject<BlockItem> HANGING_WEB = ITEMS.register("hanging_web",
			() -> new BlockItem(BlockInit.HANGING_WEB.get(),
					new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
		
/*
	public static final RegistryObject<Item> MUTATIUS = ITEMS.register("mutatius",
			() -> new Mutatius(new Item.Properties().tab(Monsutaari.MONSUTAARI_GROUP)));
	*/
	}
	
