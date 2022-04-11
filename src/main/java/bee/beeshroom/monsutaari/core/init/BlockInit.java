package bee.beeshroom.monsutaari.core.init;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.common.blocks.BonesBlock;
import bee.beeshroom.monsutaari.common.blocks.DubiousCakeBlock;
import bee.beeshroom.monsutaari.common.blocks.GolemFlowerBlock;
import bee.beeshroom.monsutaari.common.blocks.HangingWebBlock;
import bee.beeshroom.monsutaari.common.blocks.Trapweb;
import bee.beeshroom.monsutaari.common.blocks.WitherRoseBush;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
			Monsutaari.MOD_ID);
	
	//thank-you Gigaherz in the Minecraft Mod Development discord!!!
	
	public static final RegistryObject<Block> TRAPWEB = BLOCKS.register("trapweb",
			() -> new Trapweb(AbstractBlock.Properties.of(Material.WEB)
					.requiresCorrectToolForDrops()
					.noCollission().randomTicks()
							.strength(2.0F, 0.5F)
							));
	
	
	public static final RegistryObject<Block> GOLEM_FLOWER = BLOCKS.register("golem_flower",
			() -> new GolemFlowerBlock(EffectsInit.PUMPKIN_POWERED, 8, AbstractBlock.Properties.of(Material.PLANT)
					.noCollission()
					.instabreak()
					.sound(SoundType.GRASS)
							));
	
	//if i want custom potion effects for a flower:
/*	public static final RegistryObject<Block> MARIGOLD = BLOCKS.register("marigold",
			() -> new ModFlowerBlock(EffectsInit.GHASTLY, 9, AbstractBlock.Properties.of(Material.PLANT)
					.noCollission()
					.instabreak()
					.sound(SoundType.GRASS)
							)); */
	
	public static final RegistryObject<Block> MARIGOLD = BLOCKS.register("marigold",
			() -> new FlowerBlock(Effects.REGENERATION, 8, AbstractBlock.Properties.of(Material.PLANT)
					.noCollission()
					.instabreak()
					.sound(SoundType.GRASS)
							)); 
	
	public static final RegistryObject<Block> WITHER_ROSE_BUSH = BLOCKS.register("wither_rose_bush",
			() -> new WitherRoseBush(AbstractBlock.Properties.of(Material.PLANT)
					.noCollission()
					.instabreak()
					//wither please stop breaking them everytime, thankyou <3
					.strength(0.0F, 3.5F)
					.sound(SoundType.GRASS)
							));
	
	public static final RegistryObject<Block> DUBIOUS_CAKE = BLOCKS.register("dubious_cake",
			() -> new DubiousCakeBlock(AbstractBlock.Properties.of(Material.CAKE)
							.strength(0.5F)
							.sound(SoundType.WOOL)
							));
	
	public static final RegistryObject<Block> BONES_BLOCK = BLOCKS.register("bones_block",
			() -> new BonesBlock(AbstractBlock.Properties.of(Material.STONE)
					.strength(1.7F, 1.5F)
					//.sound(SoundType.BONE_BLOCK)
					.sound(SoundType.GRAVEL)
							));
	
	public static final RegistryObject<Block> HANGING_WEB = BLOCKS.register("hanging_web",
			() -> new HangingWebBlock(AbstractBlock.Properties.of(Material.WEB)
				//	.requiresCorrectToolForDrops()
					.noCollission()
							.strength(0.8F, 0.5F)
							));
	
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POTTED_GOLEM_FLOWER = BLOCKS.register("potted_golem_flower", 
			() -> new FlowerPotBlock(GOLEM_FLOWER.get(), AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));

	
	@SuppressWarnings("deprecation")
	public static final RegistryObject<Block> POTTED_MARIGOLD = BLOCKS.register("potted_marigold", 
			() -> new FlowerPotBlock(MARIGOLD.get(), AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));

}
