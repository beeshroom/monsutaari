package bee.beeshroom.monsutaari.core.init;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

//@Mod.EventBusSubscriber(modid = Monsutaari.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModPotionBrewing {

	  public static void registerBrewingRecipes() {
		  
		  addMix(Potions.AWKWARD, Items.CARVED_PUMPKIN.asItem(), PotionsInit.PUMPKIN_POWERED.get());
		  
		  addMix(PotionsInit.PUMPKIN_POWERED.get(), Items.REDSTONE, PotionsInit.LONG_PUMPKIN_POWERED.get());
		  
/*
		  addMix(Potions.AWKWARD, Items.APPLE, PotionsInit.GHASTLY.get());

		  addMix(PotionsInit.GHASTLY.get(), Items.REDSTONE, PotionsInit.LONG_GHASTLY.get());
*/		  
		  
	/*	  addMix(Potions.AWKWARD, Items.CHORUS_FRUIT, PotionsInit.WARPED.get());

		  addMix(PotionsInit.WARPED.get(), Items.REDSTONE, PotionsInit.LONG_WARPED.get());
		  */
		  

		/*  addMix(PotionsInit.PUMPKIN_POWERED.get(), Items.DRAGON_BREATH, PotionsInit.PUMPKIN_POWERED.get());

		  addMix(PotionsInit.GHASTLY.get(), Items.DRAGON_BREATH, PotionsInit.GHASTLY.get()); */

	  }
	  
	  //Accomplished thanks to Mr. Pineapple's 1.15 potion tutorial. Thank-you!
	  
	  private static Method brewing_mixes;
	  
	  private static void addMix(Potion start, Item ingredient, Potion result)
	  {
      if(brewing_mixes == null) {
          brewing_mixes = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
          brewing_mixes.setAccessible(true);
      }

      try {
          brewing_mixes.invoke(null, start, ingredient, result);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
  }
	
	/*  public static void addPotionRecipes(Potion p_193357_0_, Item p_193357_1_, Potion p_193357_2_) {
	     // POTION_MIXES.add(new PotionBrewing.MixPredicate<>(p_193357_0_, Ingredient.of(p_193357_1_), p_193357_2_));
		  addMix(p_193357_0_, p_193357_1_, p_193357_2_);
	   } */
}
