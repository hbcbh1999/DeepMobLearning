package xt9.deepmoblearning.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import xt9.deepmoblearning.DeepConstants;
import xt9.deepmoblearning.DeepMobLearning;
import xt9.deepmoblearning.common.inventory.ContainerDeepLearner;
import xt9.deepmoblearning.common.inventory.ContainerSimulationChamber;
import xt9.deepmoblearning.common.items.IGuiItem;
import xt9.deepmoblearning.common.items.ItemDeepLearner;
import xt9.deepmoblearning.common.tiles.IGuiTile;
import xt9.deepmoblearning.common.tiles.TileEntitySimulationChamber;


/**
 * Created by xt9 on 2017-06-08.
 */
public class CommonProxy implements IGuiHandler {
    public static boolean openItemGui(EntityPlayer player, EntityEquipmentSlot slot) {
        ItemStack stack = player.getItemStackFromSlot(slot);
        IGuiItem item = (IGuiItem)stack.getItem();

        if(stack.isEmpty() || !(stack.getItem() instanceof IGuiItem)) {
            return false;
        }
        int slotID = slot.getName() == "mainhand" ? 0 : 1;

        player.openGui(DeepMobLearning.instance, 100 * slotID + item.getGuiID(), player.world, (int)player.posX, (int)player.posY, (int)player.posZ);
        return true;
    }

    public static void openTileEntityGui(World world, EntityPlayer player, IGuiTile te, BlockPos pos) {
        player.openGui(DeepMobLearning.instance, te.getGuiID(), player.world, pos.getX(), pos.getY(), pos.getZ());
    }

    public void preInit() {

    }

    public void init() {

    }

    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        // Find which slot triggered this
        EntityEquipmentSlot slot = EntityEquipmentSlot.values()[ID/100];
        ItemStack item = player.getItemStackFromSlot(slot);

        if(ID % 100 == DeepConstants.ITEM_DEEP_LEARNER_GUI_ID && item.getItem() instanceof ItemDeepLearner) {
            return new ContainerDeepLearner(player.inventory, world, slot, item);
        } else {

            switch (ID) {
                case DeepConstants.TILE_SIMULATION_CHAMBER_GUI_ID:
                    return new ContainerSimulationChamber((TileEntitySimulationChamber) world.getTileEntity(new BlockPos(x, y, z)), player.inventory, world);
                default:
                    return null;
            }
        }
    }

    // Client only methods
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
    public void registerItemRenderer(Item item, int meta, String id) {}
    public void registerRenderers() {}

}
