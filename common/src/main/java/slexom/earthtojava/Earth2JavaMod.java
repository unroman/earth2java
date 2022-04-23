package slexom.earthtojava;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slexom.earthtojava.config.ModConfig;
import slexom.earthtojava.events.ModEvents;
import slexom.earthtojava.init.*;
import slexom.earthtojava.init.features.ConfiguredFeatureInit;
import slexom.earthtojava.init.features.PlacedFeatureInit;

public class Earth2JavaMod  {

    public static final String MOD_ID = "earthtojavamobs";

    public static final Identifier ITEM_GROUP_IDENTIFIER = new Identifier(MOD_ID, "group");
    public static final ItemGroup ITEM_GROUP = new ItemGroup(ItemGroup.GROUPS.length - 1, String.format("%s.%s", ITEM_GROUP_IDENTIFIER.getNamespace(), ITEM_GROUP_IDENTIFIER.getPath())) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.HORN.get());
        }
    };

    private static final Logger LOGGER = LogManager.getLogger("Earth2Java");

     public static void initialize() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        ModEvents.init();
        SoundEventsInit.init();
        FluidInit.init();
        BlockInit.init();
        BlockEntityTypeInit.init();
        ConfiguredFeatureInit.init();
        PlacedFeatureInit.init();
        BiomeInit.init();
        EntityTypesInit.init();
        EntityAttributeInit.init();
        EntitySpawnInit.init();
        ItemInit.init();
        RecipesInit.init();
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BlockInit.BUTTERCUP.get().asItem(), 0.65F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BlockInit.PINK_DAISY.get().asItem(), 0.65F);
        LOGGER.info("[Earth2Java] Mod loaded! Enjoy :D");
    }

}
