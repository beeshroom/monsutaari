package bee.beeshroom.monsutaari.core.init;

import bee.beeshroom.monsutaari.Monsutaari;
import bee.beeshroom.monsutaari.common.entities.ClayCubeEntity;
import bee.beeshroom.monsutaari.common.entities.HeadlessSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.HeadlessWitherSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.QuartzSkeletonEntity;
import bee.beeshroom.monsutaari.common.entities.SkeletonHeadEntity;
import bee.beeshroom.monsutaari.common.entities.SnowCubeEntity;
import bee.beeshroom.monsutaari.common.entities.WispEntity;
import bee.beeshroom.monsutaari.common.entities.WitherSkeletonHeadEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//thanks cy4shot

public class EntityInit {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES,
			Monsutaari.MOD_ID);

	public static final RegistryObject<EntityType<SkeletonHeadEntity>> SKELETON_HEAD = ENTITY_TYPES.register("skeleton_head",
			() -> EntityType.Builder.of(SkeletonHeadEntity::new, EntityClassification.MONSTER).sized(0.5f, 0.5f)
					.build(new ResourceLocation(Monsutaari.MOD_ID, "skeleton_head").toString()));
	
	public static final RegistryObject<EntityType<HeadlessSkeletonEntity>> HEADLESS_SKELETON = ENTITY_TYPES.register("headless_skeleton",
			() -> EntityType.Builder.of(HeadlessSkeletonEntity::new, EntityClassification.MONSTER).sized(0.65f, 1.5f)
					.build(new ResourceLocation(Monsutaari.MOD_ID, "headless_skeleton").toString()));
	
	public static final RegistryObject<EntityType<WitherSkeletonHeadEntity>> WITHER_SKELETON_HEAD = ENTITY_TYPES.register("wither_skeleton_head",
			() -> EntityType.Builder.of(WitherSkeletonHeadEntity::new, EntityClassification.MONSTER).sized(0.5f, 0.5f)
					.build(new ResourceLocation(Monsutaari.MOD_ID, "wither_skeleton_head").toString()));
	
	public static final RegistryObject<EntityType<HeadlessWitherSkeletonEntity>> HEADLESS_WITHER_SKELETON = ENTITY_TYPES.register("headless_wither_skeleton",
			() -> EntityType.Builder.of(HeadlessWitherSkeletonEntity::new, EntityClassification.MONSTER).sized(0.75f, 1.6f)
					.build(new ResourceLocation(Monsutaari.MOD_ID, "headless_wither_skeleton").toString()));
	
	public static final RegistryObject<EntityType<ClayCubeEntity>> CLAY_CUBE = ENTITY_TYPES.register("clay_cube",
			() -> EntityType.Builder.of(ClayCubeEntity::new, EntityClassification.WATER_CREATURE).sized(2.04F, 2.04F)
					.build(new ResourceLocation(Monsutaari.MOD_ID, "clay_cube").toString()));
	
	public static final RegistryObject<EntityType<SnowCubeEntity>> SNOW_CUBE = ENTITY_TYPES.register("snow_cube",
			() -> EntityType.Builder.of(SnowCubeEntity::new, EntityClassification.MONSTER).sized(2.04F, 2.04F)
					.build(new ResourceLocation(Monsutaari.MOD_ID, "snow_cube").toString()));
	
	public static final RegistryObject<EntityType<QuartzSkeletonEntity>> QUARTZ_SKELETON = ENTITY_TYPES.register("quartz_skeleton",
			() -> EntityType.Builder.of(QuartzSkeletonEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.99F)
					.build(new ResourceLocation(Monsutaari.MOD_ID, "quartz_skeleton").toString()));
	
	public static final RegistryObject<EntityType<WispEntity>> WISP = ENTITY_TYPES.register("wisp",
			() -> EntityType.Builder.of(WispEntity::new, EntityClassification.MONSTER).sized(0.6f, 0.6f)
					.build(new ResourceLocation(Monsutaari.MOD_ID, "wisp").toString()));
	
	

	
	
	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(SKELETON_HEAD.get(), SkeletonHeadEntity.registerAttributes().build());
		event.put(HEADLESS_SKELETON.get(), HeadlessSkeletonEntity.registerAttributes().build());
		event.put(WITHER_SKELETON_HEAD.get(), WitherSkeletonHeadEntity.registerAttributes().build());
		event.put(HEADLESS_WITHER_SKELETON.get(), HeadlessWitherSkeletonEntity.registerAttributes().build());
		event.put(CLAY_CUBE.get(), ClayCubeEntity.registerAttributes().build());
		event.put(SNOW_CUBE.get(), SnowCubeEntity.registerAttributes().build());
		event.put(QUARTZ_SKELETON.get(), QuartzSkeletonEntity.registerAttributes().build());
		event.put(WISP.get(), WispEntity.registerAttributes().build());

	}
}



