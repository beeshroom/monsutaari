package bee.beeshroom.monsutaari.core;

import bee.beeshroom.monsutaari.Monsutaari;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeTagHandler;
import net.minecraftforge.registries.ForgeRegistries;

public class Tags {

/*		public static final ITag.INamedTag<Block> STICKY_BLOCKS = createTag("sticky_blocks");

		private static ITag.INamedTag<Block> createTag(String name) {
			return ForgeTagHandler.makeWrapperTag(ForgeRegistries.BLOCKS, new ResourceLocation(Monsutaari.MOD_ID, name));
		}
	} */
	
	public static final ITag.INamedTag<Block> STICKY_BLOCKS = modBlockTag("sticky_blocks");

	public static final ITag.INamedTag<Item> SHOCKABLE = modItemTag("shockable");

/*	private static ITag.INamedTag<Item> modItemTag(String path) {
		return ItemTags.bind(Monsutaari.MOD_ID + ":" + path);
	} */
	
/*	private static ITag.INamedTag<Block> modBlockTag(String path) {
		return ForgeTagHandler.makeWrapperTag(ForgeRegistries.BLOCKS, new ResourceLocation(Monsutaari.MOD_ID + ":" + path));
	} */
	
	private static ITag.INamedTag<Block> modBlockTag(String path) {
		return ForgeTagHandler.makeWrapperTag(ForgeRegistries.BLOCKS, new ResourceLocation(Monsutaari.MOD_ID + ":" + path));
	}
	
	private static ITag.INamedTag<Item> modItemTag(String path) {
		return ForgeTagHandler.makeWrapperTag(ForgeRegistries.ITEMS, new ResourceLocation(Monsutaari.MOD_ID + ":" + path));
	}
	
	
//	public static final INamedTag<Block> BLAST_PROOF_ITEMS = BlockTags.makeWrapperTag(Monsutaari.MOD_ID + ":blast_proof_items");
	
/*	private static ITag.INamedTag<Block> createTag(String name) {
		return ForgeTagHandler.makeWrapperTag(ForgeRegistries.BLOCKS, new ResourceLocation(Monsutaari.MOD_ID, name));
}   */
}
