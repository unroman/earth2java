/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and the proxy files
 *    and they won't get overwritten. If you change your mod package or modid, you
 *    need to apply these changes to this file MANUALLY.
 *
 *    Settings in @Mod annotation WON'T be changed in case of the base mod element
 *    files lock too, so you need to set them manually here in such case.
 *
 *    Keep the EarthtojavamobsModElements object in this class and all calls to this object
 *    INTACT in order to preserve functionality of mod elements generated by MCreator.
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package net.slexom.earthtojavamobs;

import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.LakesFeature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.slexom.earthtojavamobs.entity.GlowSquidEntity;
import net.slexom.earthtojavamobs.init.*;
import net.slexom.earthtojavamobs.utils.BiomeSpawnHelper;

import java.util.Random;

@Mod(EarthtojavamobsMod.MOD_ID)
public class EarthtojavamobsMod {

    public static final String MOD_ID = "earthtojavamobs";

    public EarthtojavamobsMod() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemInit.ITEMS.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        EntityTypesInit.ENTITY_TYPES.register(modEventBus);
        RecipesInit.RECIPES.register(modEventBus);
        modEventBus.register(this);
        modEventBus.addListener(this::setup);

//        modLoadingContext.registerConfig(ModConfig.Type.CLIENT, ConfigHolder.CLIENT_SPEC);
//        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigHolder.SERVER_SPEC);

    }

    private void setup(final FMLCommonSetupEvent event) {
        setMudLakeSpawn();
        registerEntitiesSpawn();
    }

    private static void setMudLakeSpawn() {
        DeferredWorkQueue.runLater(new Runnable() {
            @Override
            public void run() {
                for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
                    biome.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, new LakesFeature(BlockStateFeatureConfig::deserialize) {
                        @Override
                        public boolean place(IWorld world, ChunkGenerator generator, Random rand, BlockPos pos, BlockStateFeatureConfig config) {
                            DimensionType dimensionType = world.getDimension().getType();
                            boolean dimensionCriteria = false;
                            if (dimensionType == DimensionType.OVERWORLD)
                                dimensionCriteria = true;
                            if (!dimensionCriteria)
                                return false;
                            return super.place(world, generator, rand, pos, config);
                        }
                    }.withConfiguration(new BlockStateFeatureConfig(FluidInit.MUD_BLOCK.get().getDefaultState())).withPlacement(Placement.WATER_LAKE.configure(new ChanceConfig(40))));
                }
            }
        });
    }

    private static void registerEntitiesSpawn() {
        registerAnimalEntitySpawn(EntityTypesInit.AmberChicken.registryObject.get(), EntityTypesInit.AmberChicken.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.AshenCow.registryObject.get(), EntityTypesInit.AshenCow.spawnBiomes, 8, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.Cluckshroom.registryObject.get(), EntityTypesInit.Cluckshroom.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.FleckedSheep.registryObject.get(), EntityTypesInit.FleckedSheep.spawnBiomes, 12, 2, 4);
        registerGlowingSquidSpawn();
        registerAnimalEntitySpawn(EntityTypesInit.HornedSheep.registryObject.get(), EntityTypesInit.HornedSheep.spawnBiomes, 12, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.InkySheep.registryObject.get(), EntityTypesInit.InkySheep.spawnBiomes, 12, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.MidnightChicken.registryObject.get(), EntityTypesInit.MidnightChicken.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.Moobloom.registryObject.get(), EntityTypesInit.Moobloom.spawnBiomes, 8, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.MuddyPig.registryObject.get(), EntityTypesInit.MuddyPig.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.PalePig.registryObject.get(), EntityTypesInit.PalePig.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.PiebaldPig.registryObject.get(), EntityTypesInit.PiebaldPig.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.RockySheep.registryObject.get(), EntityTypesInit.RockySheep.spawnBiomes, 12, 2, 4);
        registerMonsterEntitySpawn(EntityTypesInit.SkeletonWolf.registryObject.get(), EntityTypesInit.SkeletonWolf.spawnBiomes, 7, 2, 6);
        registerAnimalEntitySpawn(EntityTypesInit.SpottedPig.registryObject.get(), EntityTypesInit.SpottedPig.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.StormyChicken.registryObject.get(), EntityTypesInit.StormyChicken.spawnBiomes, 10, 2, 4);
        registerAnimalEntitySpawn(EntityTypesInit.SunsetCow.registryObject.get(), EntityTypesInit.SunsetCow.spawnBiomes, 8, 2, 4);
        registerMonsterEntitySpawn(EntityTypesInit.TropicalSlime.registryObject.get(), EntityTypesInit.TropicalSlime.spawnBiomes, 7, 1, 3);
        registerAnimalEntitySpawn(EntityTypesInit.WoolyCow.registryObject.get(), EntityTypesInit.WoolyCow.spawnBiomes, 8, 2, 4);
    }

    private static void registerAnimalEntitySpawn(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        DeferredWorkQueue.runLater(() -> {
            BiomeSpawnHelper.setCreatureSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
            EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AnimalEntity::canAnimalSpawn);
        });
    }

    private static void registerMonsterEntitySpawn(EntityType entity, String[] spawnBiomes, int weight, int minGroupCountIn, int maxGroupCountIn) {
        DeferredWorkQueue.runLater(() -> {
            BiomeSpawnHelper.setMonsterSpawnBiomes(entity, spawnBiomes, weight, minGroupCountIn, maxGroupCountIn);
            EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
        });
    }

    private static void registerGlowingSquidSpawn() {
        DeferredWorkQueue.runLater(() -> {
            BiomeSpawnHelper.setWaterCreatureSpawnBiomes(EntityTypesInit.GlowSquid.registryObject.get(), EntityTypesInit.GlowSquid.spawnBiomes, 6, 1, 4);
            EntitySpawnPlacementRegistry.register(EntityTypesInit.GlowSquid.registryObject.get(), EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GlowSquidEntity::canGlowingSquidSpawn);
        });
    }

}
