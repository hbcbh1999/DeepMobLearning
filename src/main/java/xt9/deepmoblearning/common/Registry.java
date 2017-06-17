package xt9.deepmoblearning.common;

import net.minecraft.item.ItemBlock;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xt9.deepmoblearning.DeepMobLearning;
import xt9.deepmoblearning.common.blocks.BlockBase;
import xt9.deepmoblearning.common.blocks.BlockSimulationChamber;
import xt9.deepmoblearning.common.blocks.ItemBlockBase;
import xt9.deepmoblearning.common.items.ItemDeepLearner;
import xt9.deepmoblearning.common.items.ItemBase;
import xt9.deepmoblearning.common.items.ItemMobChip;

/**
 * Created by xt9 on 2017-06-08.
 */
public class Registry {
    public static NonNullList<ItemBase> items = NonNullList.create();
    public static NonNullList<BlockBase> blocks = NonNullList.create();
    public static ItemDeepLearner deepLearner;
    public static ItemMobChip mobChip;

    public static BlockSimulationChamber simulationChamber;

    public static void preInit() {
        // Create our Item instances
        deepLearner = new ItemDeepLearner();
        items.add(deepLearner);

        mobChip = new ItemMobChip();
        items.add(mobChip);

        // Blocks
        simulationChamber = new BlockSimulationChamber();
        blocks.add(simulationChamber);

        // TileEntities
        GameRegistry.registerTileEntity(simulationChamber.getTileEntityClass(), DeepMobLearning.MODID + ":simulation_chamber");

        registerBlocks();
        registerItems();
    }

    private static void registerItems() {
        for (ItemBase item : items) {
            GameRegistry.register(item);
            item.registerItemModel();
        }
    }

    private static void registerBlocks() {
        for(BlockBase block : blocks) {
            ItemBlock itemBlock = new ItemBlockBase("simulation_chamber", 64, block);
            GameRegistry.register(block);
            GameRegistry.register(itemBlock);
            block.registerItemModel(itemBlock);

        }
    }
}
