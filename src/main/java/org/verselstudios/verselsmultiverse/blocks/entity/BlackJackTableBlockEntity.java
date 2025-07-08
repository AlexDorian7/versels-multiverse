package org.verselstudios.verselsmultiverse.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.verselstudios.verselsmultiverse.menu.BlackJackTableMenu;
import org.verselstudios.verselsmultiverse.registers.ModBlockEntityTypes;

public class BlackJackTableBlockEntity extends BaseContainerBlockEntity {

    public static final int INVENTORY_SIZE = 30;

    private ItemStackHandler inventory;
    private DataSlot gameStateSlot;

    public BlackJackTableBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.BLACK_JACK_TABLE.get(), pos, blockState);
        inventory = new ItemStackHandler(INVENTORY_SIZE);
        gameStateSlot = DataSlot.standalone();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("blockEntity.verselsmultiverse.black_jack_table.default_title");
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        NonNullList<ItemStack> items = NonNullList.withSize(30, ItemStack.EMPTY);
        for (int i=0; i<INVENTORY_SIZE; i++) {
            items.set(i, inventory.getStackInSlot(i));
        }
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> inventoryList) {
        for (int i=0; i<INVENTORY_SIZE; i++) {
            inventory.setStackInSlot(i, inventoryList.get(i));
        }
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new BlackJackTableMenu(containerId, playerInventory, this, ContainerLevelAccess.create(getLevel(), getBlockPos()), inventory, gameStateSlot);
    }

    @Override
    public int getContainerSize() {
        return INVENTORY_SIZE;
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        CompoundTag inventoryTag = inventory.serializeNBT(registries);
        tag.put("Inventory", inventoryTag);
        tag.putInt("GameState", gameStateSlot.get());
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        Tag inventoryTag = tag.get("Inventory");
        if (inventoryTag instanceof CompoundTag) {
            inventory.deserializeNBT(registries, (CompoundTag) inventoryTag);
        }
        int gameState = tag.getInt("GameState");
        gameStateSlot.set(gameState);
    }
}
