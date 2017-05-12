package eyeq.dummyblock;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import eyeq.dummyblock.block.BlockDummyNoCollision;
import eyeq.util.block.UBlockDummy;
import eyeq.util.client.model.UModelCreator;
import eyeq.util.client.model.UModelLoader;
import eyeq.util.client.model.gson.ItemmodelJsonFactory;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.renderer.block.statemap.StateMapper;
import eyeq.util.client.renderer.block.statemap.StateMapperNormal;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.creativetab.UCreativeTab;
import eyeq.util.item.UItemBlockHasSubtypes;
import eyeq.util.oredict.UOreDictionary;
import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.*;
import net.minecraft.util.NonNullList;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.io.File;
import java.util.Map;

import static eyeq.dummyblock.DummyBlock.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class DummyBlock {
    public static final String MOD_ID = "eyeq_dummyblock";

    @Mod.Instance(MOD_ID)
    public static DummyBlock instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static final CreativeTabs TAB_DUMMY = new UCreativeTab("DummyBlock", () -> new ItemStack(Blocks.DIRT));

    public static UBlockDummy dummyDirt;
    public static UBlockDummy dummyGrass;
    public static UBlockDummy dummyCobbleStone;
    public static UBlockDummy dummyStone;
    public static UBlockDummy dummyGoldOre;
    public static UBlockDummy dummyIronOre;
    public static UBlockDummy dummyCoalOre;
    public static UBlockDummy dummyLapisOre;
    public static UBlockDummy dummyDiamondOre;
    public static UBlockDummy dummyRedstoneOre;
    public static UBlockDummy dummyEmeraldOre;
    public static UBlockDummy dummySand;
    public static UBlockDummy dummySandstone;
    public static UBlockDummy dummyGravel;
    public static UBlockDummy dummyStonebrick;
    public static UBlockDummy dummyBrick;
    public static UBlockDummy dummyPlanks;
    public static UBlockDummy dummyLog;
    public static UBlockDummy dummyLog2;
    public static UBlockDummy dummySnow;
    public static UBlockDummy dummyObsidian;
    public static UBlockDummy dummyNetherrack;
    public static UBlockDummy dummyQuartsOre;
    public static UBlockDummy dummyWool;
    public static UBlockDummy dummyBookshelf;
    public static UBlockDummy dummyFire;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        addRecipes();
        if(event.getSide().isServer()) {
            return;
        }
        renderBlockModels();
        renderItemModels();
        createFiles();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if(event.getSide().isServer()) {
            return;
        }
        registerBlockColors();
        registerItemColors();
    }

    @SubscribeEvent
    protected static void registerBlocks(RegistryEvent.Register<Block> event) {
        dummyDirt = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.DIRT;
            }

            @Override
            // なんでthisやねん
            public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
                for(BlockDirt.DirtType type : BlockDirt.DirtType.values()) {
                    list.add(new ItemStack(item, 1, type.getMetadata()));
                }
            }
        };
        dummyGrass = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.GRASS;
            }
        };
        dummyCobbleStone = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.COBBLESTONE;
            }
        };
        dummyStone = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.STONE;
            }
        };
        dummyGoldOre = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.GOLD_ORE;
            }
        };
        dummyIronOre = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.IRON_ORE;
            }
        };
        dummyCoalOre = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.COAL_ORE;
            }
        };
        dummyLapisOre = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.LAPIS_ORE;
            }
        };
        dummyDiamondOre = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.DIAMOND_ORE;
            }
        };
        dummyRedstoneOre = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.REDSTONE_ORE;
            }
        };
        dummyEmeraldOre = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.EMERALD_ORE;
            }
        };
        dummySand = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.SAND;
            }
        };
        dummySandstone = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.SANDSTONE;
            }
        };
        dummyGravel = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.GRAVEL;
            }
        };
        dummyStonebrick = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.STONEBRICK;
            }
        };
        dummyBrick = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.BRICK_BLOCK;
            }
        };
        dummyPlanks = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.PLANKS;
            }
        };
        dummyLog = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.LOG;
            }
        };
        dummyLog2 = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.LOG2;
            }
        };
        dummySnow = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.SNOW;
            }
        };
        dummyObsidian = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.OBSIDIAN;
            }
        };
        dummyNetherrack = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.NETHERRACK;
            }
        };
        dummyQuartsOre = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.QUARTZ_ORE;
            }
        };
        dummyWool = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.WOOL;
            }
        };
        dummyBookshelf = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.BOOKSHELF;
            }
        };
        dummyFire = new BlockDummyNoCollision() {
            @Override
            public Block getOriginalBlock() {
                return Blocks.FIRE;
            }
        };

        GameRegistry.register(dummyDirt, resource.createResourceLocation("dummy_dirt"));
        GameRegistry.register(dummyGrass, resource.createResourceLocation("dummy_grass"));
        GameRegistry.register(dummyCobbleStone, resource.createResourceLocation("dummy_cobble_stone"));
        GameRegistry.register(dummyStone, resource.createResourceLocation("dummy_stone"));
        GameRegistry.register(dummyGoldOre, resource.createResourceLocation("dummy_gold_ore"));
        GameRegistry.register(dummyIronOre, resource.createResourceLocation("dummy_iron_ore"));
        GameRegistry.register(dummyCoalOre, resource.createResourceLocation("dummy_coal_ore"));
        GameRegistry.register(dummyLapisOre, resource.createResourceLocation("dummy_lapis_ore"));
        GameRegistry.register(dummyDiamondOre, resource.createResourceLocation("dummy_diamond_ore"));
        GameRegistry.register(dummyRedstoneOre, resource.createResourceLocation("dummy_redstone_ore"));
        GameRegistry.register(dummyEmeraldOre, resource.createResourceLocation("dummy_emerald_ore"));
        GameRegistry.register(dummySand, resource.createResourceLocation("dummy_sand"));
        GameRegistry.register(dummySandstone, resource.createResourceLocation("dummy_sandstone"));
        GameRegistry.register(dummyGravel, resource.createResourceLocation("dummy_gravel"));
        GameRegistry.register(dummyStonebrick, resource.createResourceLocation("dummy_stonebrick"));
        GameRegistry.register(dummyBrick, resource.createResourceLocation("dummy_brick"));
        GameRegistry.register(dummyPlanks, resource.createResourceLocation("dummy_planks"));
        GameRegistry.register(dummyLog, resource.createResourceLocation("dummy_log"));
        GameRegistry.register(dummyLog2, resource.createResourceLocation("dummy_log2"));
        GameRegistry.register(dummySnow, resource.createResourceLocation("dummy_snow"));
        GameRegistry.register(dummyObsidian, resource.createResourceLocation("dummy_obsidian"));
        GameRegistry.register(dummyNetherrack, resource.createResourceLocation("dummy_netherrack"));
        GameRegistry.register(dummyQuartsOre, resource.createResourceLocation("dummy_quarts_ore"));
        GameRegistry.register(dummyWool, resource.createResourceLocation("dummy_wool"));
        GameRegistry.register(dummyBookshelf, resource.createResourceLocation("dummy_bookshelf"));
        GameRegistry.register(dummyFire, resource.createResourceLocation("dummy_fire"));
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        GameRegistry.register(new UItemBlockHasSubtypes(dummyDirt), dummyDirt.getRegistryName());
        GameRegistry.register(new ItemColored(dummyGrass, false), dummyGrass.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyCobbleStone), dummyCobbleStone.getRegistryName());
        GameRegistry.register(new UItemBlockHasSubtypes(dummyStone), dummyStone.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyGoldOre), dummyGoldOre.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyIronOre), dummyIronOre.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyCoalOre), dummyCoalOre.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyLapisOre), dummyLapisOre.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyDiamondOre), dummyDiamondOre.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyRedstoneOre), dummyRedstoneOre.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyEmeraldOre), dummyEmeraldOre.getRegistryName());
        GameRegistry.register(new UItemBlockHasSubtypes(dummySand), dummySand.getRegistryName());
        GameRegistry.register(new UItemBlockHasSubtypes(dummySandstone), dummySandstone.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyGravel), dummyGravel.getRegistryName());
        GameRegistry.register(new UItemBlockHasSubtypes(dummyStonebrick), dummyStonebrick.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyBrick), dummyBrick.getRegistryName());
        GameRegistry.register(new UItemBlockHasSubtypes(dummyPlanks), dummyPlanks.getRegistryName());
        GameRegistry.register(new UItemBlockHasSubtypes(dummyLog), dummyLog.getRegistryName());
        GameRegistry.register(new UItemBlockHasSubtypes(dummyLog2), dummyLog2.getRegistryName());
        GameRegistry.register(new ItemBlock(dummySnow), dummySnow.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyObsidian), dummyObsidian.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyNetherrack), dummyNetherrack.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyQuartsOre), dummyQuartsOre.getRegistryName());
        GameRegistry.register(new UItemBlockHasSubtypes(dummyWool), dummyWool.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyBookshelf), dummyBookshelf.getRegistryName());
        GameRegistry.register(new ItemBlock(dummyFire), dummyFire.getRegistryName());
    }

    private static void addRecipeDummy(ItemStack output, Object input) {
        GameRegistry.addRecipe(new ShapedOreRecipe(output, "X X", " C ", "X X",
                'X', UOreDictionary.OREDICT_GLASS, 'C', input));
    }

    private static void addRecipeDummy(UBlockDummy block) {
        addRecipeDummy(new ItemStack(block), block.getOriginalBlock());
    }
    
    private static void addRecipeDummy(UBlockDummy block, int meta) {
        addRecipeDummy(new ItemStack(block, 1, meta), new ItemStack(block.getOriginalBlock(), 1, meta));
    }
    
    public static void addRecipes() {
        for(BlockDirt.DirtType type : BlockDirt.DirtType.values()) {
            addRecipeDummy(dummyDirt, type.getMetadata());
        }
        addRecipeDummy(dummyGrass);
        addRecipeDummy(dummyCobbleStone);
        for(BlockStone.EnumType type : BlockStone.EnumType.values()) {
            addRecipeDummy(dummyStone, type.getMetadata());
        }
        addRecipeDummy(dummyGoldOre);
        addRecipeDummy(dummyIronOre);
        addRecipeDummy(dummyCoalOre);
        addRecipeDummy(dummyLapisOre);
        addRecipeDummy(dummyDiamondOre);
        addRecipeDummy(dummyRedstoneOre);
        addRecipeDummy(dummyEmeraldOre);
        for(BlockSand.EnumType type : BlockSand.EnumType.values()) {
            addRecipeDummy(dummySand, type.getMetadata());
        }
        for(BlockSandStone.EnumType type : BlockSandStone.EnumType.values()) {
            addRecipeDummy(dummySandstone, type.getMetadata());
        }
        addRecipeDummy(dummyGravel);
        for(BlockStoneBrick.EnumType type : BlockStoneBrick.EnumType.values()) {
            addRecipeDummy(dummyStonebrick, type.getMetadata());
        }
        addRecipeDummy(dummyBrick);
        for(BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            addRecipeDummy(dummyPlanks, type.getMetadata());
        }
        for(BlockPlanks.EnumType type : BlockPlanks.EnumType.values()) {
            if(type == BlockPlanks.EnumType.ACACIA || type == BlockPlanks.EnumType.DARK_OAK) {
                addRecipeDummy(dummyLog2, type.getMetadata() - 4);
            } else {
                addRecipeDummy(dummyLog, type.getMetadata());
            }
        }
        addRecipeDummy(dummySnow);
        addRecipeDummy(dummyObsidian);
        addRecipeDummy(dummyNetherrack);
        addRecipeDummy(dummyQuartsOre);
        for(EnumDyeColor type : EnumDyeColor.values()) {
            addRecipeDummy(dummyWool, type.getMetadata());
        }
        addRecipeDummy(dummyBookshelf);
        addRecipeDummy(new ItemStack(dummyFire), UOreDictionary.OREDICT_TORCH);
    }

    @SideOnly(Side.CLIENT)
    public static void renderBlockModels() {
        ResourceLocationFactory mc = ResourceLocationFactory.mc;
        ModelLoader.setCustomStateMapper(dummyDirt, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                Map<IProperty<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
                String s = BlockDirt.VARIANT.getName((BlockDirt.DirtType) map.remove(BlockDirt.VARIANT));
                if(BlockDirt.DirtType.PODZOL != state.getValue(BlockDirt.VARIANT)) {
                    map.remove(BlockDirt.SNOWY);
                }
                return new ModelResourceLocation(s, this.getPropertyString(map));
            }
        });
        ModelLoader.setCustomStateMapper(dummyGrass, new StateMapperNormal(dummyGrass.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyCobbleStone, new StateMapperNormal(dummyCobbleStone.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyStone, new StateMapper(mc, BlockStone.VARIANT, ""));
        ModelLoader.setCustomStateMapper(dummyGoldOre, new StateMapperNormal(dummyGoldOre.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyIronOre, new StateMapperNormal(dummyIronOre.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyCoalOre, new StateMapperNormal(dummyCoalOre.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyLapisOre, new StateMapperNormal(dummyLapisOre.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyDiamondOre, new StateMapperNormal(dummyDiamondOre.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyRedstoneOre, new StateMapperNormal(dummyRedstoneOre.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyEmeraldOre, new StateMapperNormal(dummyEmeraldOre.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummySand, new StateMapper(mc, BlockSand.VARIANT, ""));
        ModelLoader.setCustomStateMapper(dummySandstone, new StateMapper(mc, BlockSandStone.TYPE, ""));
        ModelLoader.setCustomStateMapper(dummyGravel, new StateMapperNormal(dummyGravel.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyStonebrick, new StateMapper(mc, BlockStoneBrick.VARIANT, ""));
        ModelLoader.setCustomStateMapper(dummyBrick, new StateMapperNormal(dummyBrick.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyPlanks, new StateMapper(mc, BlockPlanks.VARIANT, "_planks"));
        ModelLoader.setCustomStateMapper(dummyLog, new StateMapper(mc, BlockOldLog.VARIANT, "_log"));
        ModelLoader.setCustomStateMapper(dummyLog2, new StateMapper(mc, BlockNewLog.VARIANT, "_log"));
        ModelLoader.setCustomStateMapper(dummySnow, new StateMapperNormal(dummySnow.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyObsidian, new StateMapperNormal(dummyObsidian.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyNetherrack, new StateMapperNormal(dummyNetherrack.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyQuartsOre, new StateMapperNormal(dummyQuartsOre.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyWool, new StateMapper(mc, BlockColored.COLOR, "_wool"));
        ModelLoader.setCustomStateMapper(dummyBookshelf, new StateMapperNormal(dummyBookshelf.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(dummyFire, new StateMapper(mc, null, "fire", Lists.newArrayList(BlockFire.AGE)));
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        ResourceLocationFactory mc = ResourceLocationFactory.mc;
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyDirt), BlockDirt.DirtType.COARSE_DIRT.getMetadata(), mc.createModelResourceLocation("coarse_dirt"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyDirt), BlockDirt.DirtType.DIRT.getMetadata(), mc.createModelResourceLocation("dirt"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyDirt), BlockDirt.DirtType.PODZOL.getMetadata(), mc.createModelResourceLocation("podzol"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyGrass), 0, ResourceLocationFactory.createModelResourceLocation(dummyGrass.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyCobbleStone), 0, ResourceLocationFactory.createModelResourceLocation(dummyCobbleStone.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStone), BlockStone.EnumType.ANDESITE.getMetadata(), mc.createModelResourceLocation("andesite"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStone), BlockStone.EnumType.ANDESITE_SMOOTH.getMetadata(), mc.createModelResourceLocation("andesite_smooth"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStone), BlockStone.EnumType.DIORITE.getMetadata(), mc.createModelResourceLocation("diorite"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStone), BlockStone.EnumType.DIORITE_SMOOTH.getMetadata(), mc.createModelResourceLocation("diorite_smooth"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStone), BlockStone.EnumType.GRANITE.getMetadata(), mc.createModelResourceLocation("granite"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStone), BlockStone.EnumType.GRANITE_SMOOTH.getMetadata(), mc.createModelResourceLocation("granite_smooth"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStone), BlockStone.EnumType.STONE.getMetadata(), mc.createModelResourceLocation("stone"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyGoldOre), 0, ResourceLocationFactory.createModelResourceLocation(dummyGoldOre.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyIronOre), 0, ResourceLocationFactory.createModelResourceLocation(dummyIronOre.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyCoalOre), 0, ResourceLocationFactory.createModelResourceLocation(dummyCoalOre.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyLapisOre), 0, ResourceLocationFactory.createModelResourceLocation(dummyLapisOre.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyDiamondOre), 0, ResourceLocationFactory.createModelResourceLocation(dummyDiamondOre.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyRedstoneOre), 0, ResourceLocationFactory.createModelResourceLocation(dummyRedstoneOre.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyEmeraldOre), 0, ResourceLocationFactory.createModelResourceLocation(dummyEmeraldOre.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummySand), BlockSand.EnumType.RED_SAND.getMetadata(), mc.createModelResourceLocation("red_sand"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummySand), BlockSand.EnumType.SAND.getMetadata(), mc.createModelResourceLocation("sand"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummySandstone), BlockSandStone.EnumType.CHISELED.getMetadata(), mc.createModelResourceLocation("chiseled_sandstone"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummySandstone), BlockSandStone.EnumType.DEFAULT.getMetadata(), mc.createModelResourceLocation("sandstone"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummySandstone), BlockSandStone.EnumType.SMOOTH.getMetadata(), mc.createModelResourceLocation("smooth_sandstone"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyGravel), 0, ResourceLocationFactory.createModelResourceLocation(dummyGravel.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStonebrick), BlockStoneBrick.EnumType.CRACKED.getMetadata(), mc.createModelResourceLocation("cracked_stonebrick"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStonebrick), BlockStoneBrick.EnumType.DEFAULT.getMetadata(), mc.createModelResourceLocation("stonebrick"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStonebrick), BlockStoneBrick.EnumType.CHISELED.getMetadata(), mc.createModelResourceLocation("chiseled_stonebrick"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyStonebrick), BlockStoneBrick.EnumType.MOSSY.getMetadata(), mc.createModelResourceLocation("mossy_stonebrick"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyBrick), 0, ResourceLocationFactory.createModelResourceLocation(dummyBrick.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyPlanks), BlockPlanks.EnumType.ACACIA.getMetadata(), mc.createModelResourceLocation("acacia_planks"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyPlanks), BlockPlanks.EnumType.BIRCH.getMetadata(), mc.createModelResourceLocation("birch_planks"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyPlanks), BlockPlanks.EnumType.DARK_OAK.getMetadata(), mc.createModelResourceLocation("dark_oak_planks"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyPlanks), BlockPlanks.EnumType.JUNGLE.getMetadata(), mc.createModelResourceLocation("jungle_planks"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyPlanks), BlockPlanks.EnumType.OAK.getMetadata(), mc.createModelResourceLocation("oak_planks"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyPlanks), BlockPlanks.EnumType.SPRUCE.getMetadata(), mc.createModelResourceLocation("spruce_planks"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyLog), BlockPlanks.EnumType.BIRCH.getMetadata(), mc.createModelResourceLocation("birch_log"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyLog), BlockPlanks.EnumType.JUNGLE.getMetadata(), mc.createModelResourceLocation("jungle_log"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyLog), BlockPlanks.EnumType.OAK.getMetadata(), mc.createModelResourceLocation("oak_log"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyLog), BlockPlanks.EnumType.SPRUCE.getMetadata(), mc.createModelResourceLocation("spruce_log"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyLog2), BlockPlanks.EnumType.ACACIA.getMetadata() - 4, mc.createModelResourceLocation("acacia_log"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyLog2), BlockPlanks.EnumType.DARK_OAK.getMetadata() - 4, mc.createModelResourceLocation("dark_oak_log"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummySnow), 0, ResourceLocationFactory.createModelResourceLocation(dummySnow.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyObsidian), 0, ResourceLocationFactory.createModelResourceLocation(dummyObsidian.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyNetherrack), 0, ResourceLocationFactory.createModelResourceLocation(dummyNetherrack.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyQuartsOre), 0, ResourceLocationFactory.createModelResourceLocation(dummyQuartsOre.getOriginalBlock()));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.BLACK.getMetadata(), mc.createModelResourceLocation("black_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.BLUE.getMetadata(), mc.createModelResourceLocation("blue_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.BROWN.getMetadata(), mc.createModelResourceLocation("brown_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.CYAN.getMetadata(), mc.createModelResourceLocation("cyan_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.GRAY.getMetadata(), mc.createModelResourceLocation("gray_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.GREEN.getMetadata(), mc.createModelResourceLocation("green_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.LIGHT_BLUE.getMetadata(), mc.createModelResourceLocation("light_blue_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.LIME.getMetadata(), mc.createModelResourceLocation("lime_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.MAGENTA.getMetadata(), mc.createModelResourceLocation("magenta_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.ORANGE.getMetadata(), mc.createModelResourceLocation("orange_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.PINK.getMetadata(), mc.createModelResourceLocation("pink_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.PURPLE.getMetadata(), mc.createModelResourceLocation("purple_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.RED.getMetadata(), mc.createModelResourceLocation("red_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.SILVER.getMetadata(), mc.createModelResourceLocation("silver_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.WHITE.getMetadata(), mc.createModelResourceLocation("white_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyWool), EnumDyeColor.YELLOW.getMetadata(), mc.createModelResourceLocation("yellow_wool"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(dummyBookshelf), 0, ResourceLocationFactory.createModelResourceLocation(dummyBookshelf.getOriginalBlock()));
        UModelLoader.setCustomModelResourceLocation(dummyFire);
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockColors() {
        BlockColors blockColors = FMLClientHandler.instance().getClient().getBlockColors();

        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : ColorizerGrass.getGrassColor(0.5, 1.0), dummyGrass);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemColors() {
        final BlockColors blockColors = FMLClientHandler.instance().getClient().getBlockColors();
        ItemColors itemColors = FMLClientHandler.instance().getClient().getItemColors();

        itemColors.registerItemColorHandler((itemStack, tintIndex) -> {
            IBlockState state = ((ItemBlock)itemStack.getItem()).getBlock().getStateFromMeta(itemStack.getMetadata());
            return blockColors.colorMultiplier(state, null, null, tintIndex);
        }, dummyGrass);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-DummyBlock");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, TAB_DUMMY, "DummyBlock");
        language.register(LanguageResourceManager.JA_JP, TAB_DUMMY, "ダミーブロック");

        language.register(LanguageResourceManager.EN_US, dummyDirt, "Dummy Block");
        language.register(LanguageResourceManager.JA_JP, dummyDirt, "ダミーブロック");

        ULanguageCreator.createLanguage(project, MOD_ID, language);

        UModelCreator.createItemmodelJson(project, dummyFire.getRegistryName(), ItemmodelJsonFactory.createItemmodelJson("block/fire_side0"));
    }
}
